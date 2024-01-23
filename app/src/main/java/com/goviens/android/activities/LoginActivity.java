package com.goviens.android.activities;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityLoginBinding;
import com.goviens.android.models.ModelLogin;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.ApporioLog;
import com.goviens.android.utils.MyApplicationJavaClass;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityLoginBinding mBinding;


    private ApiManager api_manager;
    private SessionManager sessionManager;
    ModelLogin modelLogin;

    private int MAX_PHONE_LENGTH = 10;
    String country_id;
    private final String TAG = "LoginActivity";

    String forr = "";

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        sessionManager = new SessionManager(LoginActivity.this);
        PLAYER_ID = MyApplicationJavaClass.PLAYER_ID;
        api_manager = new ApiManager(this, this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);


        forr = getIntent().getStringExtra("for");
        if (forr.equals("0")) {
            mBinding.tvTxt.setText(getResources().getString(R.string.login));
            mBinding.countryCode.setText(getResources().getString(R.string.country));
            //countryCode.setText("" + sessionManager.getAppConfig().getData().getCountries().get(0).getPhonecode());
            country_id = "" + sessionManager.getAppConfig().getData().getCountries().get(0).getId();
        } else {
            mBinding.tvTxt.setText("Change Mobile Number");
            mBinding.countryCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
            country_id = sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE);
        }


        mBinding.llChooseLanguage.setOnClickListener(view -> {
            try {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(LoginActivity.this);
                builderSingle.setTitle(R.string.select_language);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.select_dialog_singlechoice);
                for (int i = 0; i < sessionManager.getAppConfig().getData().getLanguages().size(); i++) {
                    arrayAdapter.add("" + sessionManager.getAppConfig().getData().getLanguages().get(i).getName());
                }
                builderSingle.setNegativeButton(LoginActivity.this.getResources().getString(R.string.cancel), (DialogInterface dialogInterface, int which) -> {
                    dialogInterface.dismiss();
                });
                builderSingle.setAdapter(arrayAdapter, (DialogInterface dialog, int which) -> {
                    sessionManager.setUpdatedStringVersion("0.0");
                    sessionManager.setLanguage("" + sessionManager.getAppConfig().getData().getLanguages().get(which).getLocale());
                    finish();
                    startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                    dialog.dismiss();
                });
                builderSingle.show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        mBinding.countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forr.equals("0")) {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(LoginActivity.this);
                    builderSingle.setTitle(R.string.select_country);
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.select_dialog_singlechoice);
                    for (int i = 0; i < sessionManager.getAppConfig().getData().getCountries().size(); i++) {
                        arrayAdapter.add(sessionManager.getAppConfig().getData().getCountries().get(i).getPhonecode() + " " + sessionManager.getAppConfig().getData().getCountries().get(i).getName());
                    }
                    builderSingle.setNegativeButton(LoginActivity.this.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setCountryCodeWithValidation(which);
                            dialog.dismiss();
                        }
                    });
                    builderSingle.show();
                } else {
                    Toast.makeText(LoginActivity.this, "You can not change Country code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void setCountryCodeWithValidation(int selected_Country_position) {
        if (sessionManager.getAppConfig().getData().getCountries().size() > 0) {
            country_id = "" + sessionManager.getAppConfig().getData().getCountries().get(selected_Country_position).getId();
            mBinding.countryCode.setText("" + sessionManager.getAppConfig().getData().getCountries().get(selected_Country_position).getPhonecode());
            MAX_PHONE_LENGTH = Integer.parseInt("" + sessionManager.getAppConfig().getData().getCountries().get(selected_Country_position).getMaxNumPhone());
            mBinding.edtPhoneLogin.setText("");
            setLoginMethodViaConfig();
        }
    }

    protected void setLoginMethodViaConfig() {

        if (sessionManager.getAppConfig().getData().getLogin().isEmail()) {
            mBinding.countryCode.setVisibility(View.GONE);
            mBinding.edtPhoneLogin.setHint(getResources().getString(R.string.enter_email));
            mBinding.edtPhoneLogin.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mBinding.edtPhoneLogin.getLayoutParams();

            lp.setMargins(50, 0, 0, 0);

            // Apply the updated layout parameters to TextView
            mBinding.edtPhoneLogin.setLayoutParams(lp);

        } else {
            mBinding.countryCode.setVisibility(View.VISIBLE);
            mBinding.edtPhoneLogin.setHint(getResources().getString(R.string.enter_phone));
            mBinding.edtPhoneLogin.setInputType(InputType.TYPE_CLASS_NUMBER);
            mBinding.edtPhoneLogin.setFilters(new InputFilter[]{AppUtils.filter, new InputFilter.LengthFilter(MAX_PHONE_LENGTH)});
        }
    }

    public void btnSendOtp(View view) {
        if (mBinding.countryCode.getText().toString().equals(getResources().getString(R.string.country))) {
            Toast.makeText(LoginActivity.this, "" + LoginActivity.this.getResources().getString(R.string.require_field_missing), Toast.LENGTH_SHORT).show();
        } else if (mBinding.edtPhoneLogin.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "" + LoginActivity.this.getResources().getString(R.string.require_field_missing), Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> data = new HashMap<>();
            progressDialog.show();
//            if (sessionManager.getAppConfig().getData().getLogin().isEmail()) {
//                data.put("phone", "" + mBinding.edtPhoneLogin.getText().toString());
//            } else {
//                data.put("phone", "" + mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
//            }
//
//            data.put("unique_no", "" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
//            data.put("package_name", "" + BuildConfig.APPLICATION_ID);
//            data.put("player_id", "" + PLAYER_ID);
//            data.put("apk_version", BuildConfig.VERSION_NAME);
//            data.put("device", "1");
//            data.put("operating_system", "" + Build.VERSION.SDK_INT);
//            data.put("manufacture", "" + Build.MANUFACTURER);
//            data.put("model", "" + Build.MODEL);

            data.put("user_name", mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
            if (forr.equals("1")) {
                data.put("type", "1");
            } else {
                data.put("type", "4");
            }
            data.put("for", "PHONE");
            data.put("phone_code", mBinding.countryCode.getText().toString());
            try {
                api_manager._post_with_secreteonly(API_S.Tags.LOGIN, API_S.Endpoints.LOGIN, data);
            } catch (Exception e) {
                ApporioLog.logE(TAG, "Exception Caught while calling api " + e.getMessage());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent();
                intent.putExtra("phone", mBinding.edtPhoneLogin.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
    String PLAYER_ID ="";
    private void showPaymentDialog(String message) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {




                Intent intent = new Intent(LoginActivity.this, EnterOTPActivity.class);
                intent.putExtra("check_value", mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
                intent.putExtra("isRegister", "" + modelLogin.getData().isIs_register());
                intent.putExtra("country_code", mBinding.countryCode.getText().toString());
                intent.putExtra("player_id", "" + PLAYER_ID);
                intent.putExtra("for", forr);
                intent.putExtra("country_id", country_id);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            switch (APINAME) {
                case API_S.Tags.LOGIN:
                    progressDialog.hide();
                    modelLogin = SingletonGson.getInstance().fromJson("" + script, ModelLogin.class);
                    if (forr.equals("0")) {
                        if (modelLogin.getData().isIs_register()) {
                            Intent intent = new Intent(LoginActivity.this, EnterOTPActivity.class);
                            intent.putExtra("check_value", mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
                            intent.putExtra("isRegister", "" + modelLogin.getData().isIs_register());
                            intent.putExtra("country_code", mBinding.countryCode.getText().toString());
                            intent.putExtra("player_id", "" + PLAYER_ID);
                            intent.putExtra("for", forr);
                            intent.putExtra("country_id", country_id);
                            startActivity(intent);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            LoginActivity.this.finish();
                        } else {
                            if (!modelLogin.getData().getAutomatic_cashout().equals("")) {
                                showPaymentDialog(modelLogin.getData().getAutomatic_cashout());
                            } else {
                                Intent intent = new Intent(LoginActivity.this, EnterOTPActivity.class);
                                intent.putExtra("check_value", mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
                                intent.putExtra("isRegister", "" + modelLogin.getData().isIs_register());
                                intent.putExtra("country_code", mBinding.countryCode.getText().toString());
                                intent.putExtra("player_id", "" + PLAYER_ID);
                                intent.putExtra("for", forr);
                                intent.putExtra("country_id", country_id);
                                startActivity(intent);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                LoginActivity.this.finish();
                            }
                        }
                    } else {
                        Intent intent = new Intent(LoginActivity.this, EnterOTPActivity.class);
                        intent.putExtra("check_value", mBinding.countryCode.getText().toString() + mBinding.edtPhoneLogin.getText().toString());
                        intent.putExtra("for", forr);
                        intent.putExtra("country_code", mBinding.countryCode.getText().toString());
                        intent.putExtra("player_id", "" + PLAYER_ID);
                        intent.putExtra("country_id", country_id);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivityForResult(intent, 111);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, "" + script, Toast.LENGTH_SHORT).show();
        progressDialog.hide();
    }
}
