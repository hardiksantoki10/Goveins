package com.goviens.android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.models.ModelAppConfiguration;
import com.goviens.android.models.ModelUpdateString;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.ApporioLog;
import com.goviens.android.utils.SamLocationRequestService;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;

public class SplashActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    ApiManager apiManager;
    SessionManager sessionManager;

    private boolean is_internet_dialog_is_shown = false;
    private boolean is_gps_dialog_shown = false;
    private boolean is_version_dialog_is_shown = false;


    String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int REQUEST_WRITE_PERMISSION = 786;



    private static final String TAG = "SplashActivity";
    String LATITUDE = "", LONGITUDE = "";

    SamLocationRequestService samLocationRequestService;
    ModelAppConfiguration modelAppConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiManager = new ApiManager(this, this);
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        setlanguage();
        // requestPermission();
        setContentView(R.layout.activity_splash);
        samLocationRequestService = new SamLocationRequestService(this, true);
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!AppUtils.hasPermissions(this, PERMISSIONS)) {
            Log.i(TAG, "Checking Permission On Splash");
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        } else {
            startGPSCheck();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!AppUtils.checkGPSisOnOrNot(SplashActivity.this)) {
            showGPSDialog();
        } else {
            Log.i(TAG, "Now GPS Status = " + true);
            startInternetCheckProcess();
        }
    }

    private void startGPSCheck() {
        Log.i(TAG, "Checking GPS status");
        if (!AppUtils.checkGPSisOnOrNot(SplashActivity.this)) {
            showGPSDialog();
        } else {
            Log.i(TAG, "Now GPS Status = " + true);
            startInternetCheckProcess();
        }
    }


    public void showGPSDialog() {
        if (!is_gps_dialog_shown) {

            Log.i(TAG, "Now GPS Status = " + false + ", Now Showing Dialog");
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setCancelable(false);
            builder.setTitle(R.string.enable_app_location);
            builder.setMessage(R.string.in_order_to_use_app_settings)
                    .setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            SplashActivity.this.startActivity(myIntent);
                            dialog.dismiss();
                            is_gps_dialog_shown = false;
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            builder.create().show();
            is_gps_dialog_shown = true;
        }

    }


    private void setlanguage() {
        try {
            if(Locale.getDefault().getLanguage().equals("fr")){
                sessionManager.setLanguage("fr");
            }else {
                sessionManager.setLanguage("en");
            }
           // sessionManager.setLanguage(sessionManager.getLanguage() == null || sessionManager.getLanguage().equals("") ? "en" : sessionManager.getLanguage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchRemoteConfig() throws Exception {
        Log.i("" + TAG, "Started fetching Configurations");
        HashMap<String, String> data = new HashMap<>();
//        data.put("unique_no", "" + Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
//        data.put("player_id", PLAYER_ID);
//        data.put("c_lat", "latitude");
//        data.put("c_long", "longitude");
        data.put("device", "1"); // 1 for Android and 2 for IOS
        data.put("apk_version", BuildConfig.VERSION_NAME);
//        data.put("app_package_name", "" + BuildConfig.APPLICATION_ID);
//        data.put("language_code", "");
        apiManager._post_with_secreteonly(API_S.Tags.APP_CONFIGURATIONS, "" + API_S.Endpoints.APP_CONFIGURATIONS, data);
    }

    private void startInternetCheckProcess() {
        Log.i(TAG, "Now Checking net Connectivity");
        if (AppUtils.isNetworkConnected(this)) {
            Log.i(TAG, "Internet Connectivity Status " + true);
            try {
                fetchRemoteConfig();
            } catch (Exception e) {
            }
        } else {
            // fetchRemoteConfigCache();
            Log.i(TAG, "Internet Connectivity Status " + false + ", Now Showing Internet Dialog");
            if (!is_internet_dialog_is_shown) {
                showInternetDialog();
            }
        }

    }

    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setMessage(R.string.it_seems_you_are_out_of_internet_connection)
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        is_internet_dialog_is_shown = false;
                        startInternetCheckProcess();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                SplashActivity.this.finish();
                            }
                        }
                );
        builder.create().show();
        is_internet_dialog_is_shown = true;

    }

    public void showLanguageListDialog(final ModelAppConfiguration model) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SplashActivity.this);
        builderSingle.setTitle("Select Language");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SplashActivity.this, android.R.layout.select_dialog_singlechoice);

        for (int i = 0; i < model.getData().getLanguages().size(); i++) {
            arrayAdapter.add("" + model.getData().getLanguages().get(i).getName());
        }
        builderSingle.setNegativeButton("" + "" + SplashActivity.this.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sessionManager.setLanguage("" + sessionManager.getAppConfig().getData().getLanguages().get(which).getLocale());
                sessionManager.setDefaultLanguage(model.getData().getLanguages().get(which).getLocale());
                sessionManager.setShowLanguageList(false);
                finish();
                startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        switch (APINAME) {
            case API_S.Tags.APP_CONFIGURATIONS:
                modelAppConfiguration = SingletonGson.getInstance().fromJson("" + script, ModelAppConfiguration.class);
                sessionManager.setAppConfig("" + script);

                //tvGettingYourLocation.setVisibility(View.GONE);
//
                HashMap<String, String> data = new HashMap<>();
                data.put("version", sessionManager.getUpdateStringVersion()); // 1 for Android and 2 for IOS
                data.put("loc", sessionManager.getLanguage());
                data.put("platform", "android");
                data.put("app", "DRIVER");

                try {
                    apiManager._post_with_secreteonly(API_S.Tags.SET_STRING, "" + API_S.Endpoints.SET_STRING, data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case API_S.Tags.SET_STRING:
                try {
                    ModelUpdateString modelUpdateString = SingletonGson.getInstance().fromJson("" + script, ModelUpdateString.class);
                    sessionManager.setUpdatedStringVersion(modelUpdateString.getLatest_version());

                    JSONObject obj = new JSONObject(script+"");

//                    Map<String, String> stringmap = mapper.readValue(obj.get("data") + "", new TypeReference<Map<String, String>>() {
//                    });
                    //Restring.setStrings("" + obj.get("locale"), stringmap);

                    String defaultLanguage = sessionManager.getDefaultLanguage();


                    if (!sessionManager.isShowLanguageList()) {  // checking to show language list or not
                        if (modelAppConfiguration.getData().getApp_version().isShow_dialog()) { // show dialog here
                            showUpdationDialog(modelAppConfiguration.getData().getApp_version().isMandatory(), modelAppConfiguration.getData().getApp_version().getDialog_message());
                        } else if (modelAppConfiguration.getData().getApp_maintainance().isShow_dialog()) {
                            showAppmaintainanceDialog();
                        } else {
                            startCheckingLoginProcedure();
                        }
                    } else {
                        if (modelAppConfiguration.getData().getLanguages().get(0).getLocale().equalsIgnoreCase(defaultLanguage)) {  // if first element in the list and default language is same then true
                            if (modelAppConfiguration.getData().getApp_version().isShow_dialog()) { // show dialog here
                                showUpdationDialog(modelAppConfiguration.getData().getApp_version().isMandatory(), modelAppConfiguration.getData().getApp_version().getDialog_message());
                            } else if (modelAppConfiguration.getData().getApp_maintainance().isShow_dialog()) {
                                showAppmaintainanceDialog();
                            } else {
                                startCheckingLoginProcedure();
                            }
                        }  else {
                            showLanguageListDialog(modelAppConfiguration);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(APINAME.equals(API_S.Tags.APP_CONFIGURATIONS)){
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setCancelable(false);
            builder.setTitle(R.string.alert);
            builder.setMessage(R.string.it_seems_no_proper_data_added_from_admin)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            if (!AppUtils.hasPermissions(SplashActivity.this, PERMISSIONS)) {
                                Log.i(TAG, "Checking Permission On Splash");
                                ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS, 1);
                            } else {
                                startGPSCheck();
                            }
                        }
                    });
            builder.create().show();
        }else if (APINAME.equals(API_S.Tags.SET_STRING)) {
//            sessionManager.setUpdatedStringVersion(modelUpdateString.getLatest_version());

            String defaultLanguage = sessionManager.getDefaultLanguage();


            if (!sessionManager.isShowLanguageList()) {  // checking to show language list or not
                if (modelAppConfiguration.getData().getApp_version().isShow_dialog()) { // show dialog here
                    showUpdationDialog(modelAppConfiguration.getData().getApp_version().isMandatory(), modelAppConfiguration.getData().getApp_version().getDialog_message());
                } else if (modelAppConfiguration.getData().getApp_maintainance().isShow_dialog()) {
                    showAppmaintainanceDialog();
                } else {
                    startCheckingLoginProcedure();
                }
            } else {
                if (modelAppConfiguration.getData().getLanguages().get(0).getLocale().equalsIgnoreCase(defaultLanguage)) {  // if first element in the list and default language is same then true
                    if (modelAppConfiguration.getData().getApp_version().isShow_dialog()) { // show dialog here
                        showUpdationDialog(modelAppConfiguration.getData().getApp_version().isMandatory(), modelAppConfiguration.getData().getApp_version().getDialog_message());
                    } else if (modelAppConfiguration.getData().getApp_maintainance().isShow_dialog()) {
                        showAppmaintainanceDialog();
                    } else {
                        startCheckingLoginProcedure();
                    }
                } else {
                    showLanguageListDialog(modelAppConfiguration);
                }
            }
        }

        }

    private void startCheckingLoginProcedure() {
        ApporioLog.logI(TAG, "Checking login status in session");
        if (new SessionManager(this).isLoggedIn()) {
            ApporioLog.logI(TAG, "Driver is logged in and now launching TrialActivity");
            try {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } catch (Exception e) {
                Toast.makeText(SplashActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class)
//                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .putExtra("for","0"));
        }
        finish();
    }

    private void showUpdationDialog(final boolean is_maindatory, String message) {
        if (!is_version_dialog_is_shown) {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    is_version_dialog_is_shown = false;

                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });

            if (!is_maindatory) {
                dialog.setNegativeButton(R.string.do_it_later, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        is_version_dialog_is_shown = false;
                        startCheckingLoginProcedure();
                    }
                });
            }
            dialog.show();
            is_version_dialog_is_shown = true;
        }
    }



    private void showAppmaintainanceDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(R.string.your_app_is_in_maintainance);
        dialog.setCancelable(false);
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                finish();

            }
        });
        dialog.show();
    }
}
