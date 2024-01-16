package com.goviens.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityMainBinding;
import com.goviens.android.fragments.MainFragment;
import com.goviens.android.fragments.OfferFragment;
import com.goviens.android.fragments.ProfileFragment;
import com.goviens.android.fragments.RidesFragment;
import com.goviens.android.models.ModelMainScreen;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener
        , OfferFragment.OnFragmentInteractionListener, RidesFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, ApiManager.APIFETCHER {

    private ActivityMainBinding mBinding;
    BottomNavigationView navigation;
    DrawerLayout drawerLayout;
    ImageView menu;

    TextView tvProfile;
    LinearLayout linearWallet;
    LinearLayout linearAddress;
    LinearLayout linearDocument;
    LinearLayout linearPastRides;
    LinearLayout linearPaymentMethod;
    LinearLayout linearRefer;
    LinearLayout linearTerms;
    LinearLayout linearCustomer;
    LinearLayout linearEmergency;
    LinearLayout linearBankDetails;
    LinearLayout linearLogout;

    CircleImageView imageUser;
    TextView tvName;

    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar toolbar;


    ApiManager apiManager;
    SessionManager manager;
    ModelMainScreen mainScreen;

    ProgressDialog progressDialog;
    boolean offer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);
        apiManager = new ApiManager(this,this);
        manager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        loadFragment(new MainFragment());
        setupToolbar();
        //getting bottom navigation view and attaching the listener
        navigation.setOnNavigationItemSelectedListener(MainActivity.this);
        navigation.getMenu().findItem(R.id.search).setChecked(true);

//        if(getIntent() != null){
//            if(getIntent().getStringExtra("notification") != null) {
//
//            }else {
//                navigation.getMenu().findItem(R.id.search).setChecked(true);
//            }
//        }else {
//            navigation.getMenu().findItem(R.id.search).setChecked(true);
//        }

        clickListeners();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        Glide.with(this).load(manager.getUserDetails().get(SessionManager.USER_IMAGE)).into(imageUser);
        tvName.setText(manager.getUserDetails().get(SessionManager.USER_FIRST_NAME));

    }

    @Override
    protected void onResume() {
        super.onResume();
        offer = false;
        try {
            progressDialog.show();
            apiManager._post(API_S.Tags.MAIN_SCREEN, API_S.Endpoints.MAIN_SCREEN, null, manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setupToolbar(){
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(getResources().getString(R.string.find_a_ride));
    }


    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout,toolbar,R.string.camera, R.string.alert);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    private void showAppmaintainanceDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(!offer) {
            if (mainScreen.getData().isIs_user_personal_document_expire()) {
                builder.setMessage(mainScreen.getData().getIs_user_personal_document_expire_text());
            }else if(mainScreen.getData().isIs_user_personal_document_expired()) {
                builder.setMessage(mainScreen.getData().getIs_user_personal_document_expired_text());
            }
        }else if(mainScreen.getData().isIs_user_personal_document_expired()) {
            builder.setMessage(mainScreen.getData().getIs_user_personal_document_expired_text());
        }else if(mainScreen.getData().isIs_user_vehicle_document_expired()){
            builder.setMessage(mainScreen.getData().getIs_user_vehicle_document_expired_text());
        }else if(!mainScreen.getData().isIs_veh_upload()){
            builder.setMessage(mainScreen.getData().getUpload_vehicle());
        }else if(!mainScreen.getData().isIs_veh_doc_upload()){
            builder.setMessage(mainScreen.getData().getUpload_document());
        }else if(!mainScreen.getData().isIs_user_minimum_balance()) {
            builder.setMessage(mainScreen.getData().getIs_user_minimum_balance_text());
        }else  {
            builder.setMessage(mainScreen.getData().getOffer_ride_text());
        }
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!offer) {
                    if (mainScreen.getData().isIs_user_personal_document_expire()) {
                        dialog.dismiss();
                    }else if(mainScreen.getData().isIs_user_personal_document_expired()){
                        startActivity(new Intent(MainActivity.this,UploadDocumentActivity.class).putExtra("for","user"));
                        dialog.dismiss();
                    }
                }else if(mainScreen.getData().isIs_user_personal_document_expired()){
                        startActivity(new Intent(MainActivity.this,UploadDocumentActivity.class).putExtra("for","user"));
                        dialog.dismiss();
                }else if(!mainScreen.getData().isIs_veh_upload()){
                    startActivity(new Intent(MainActivity.this,AddCarActivity.class));
                    dialog.dismiss();
                }else if(!mainScreen.getData().isIs_veh_doc_upload()
                        || mainScreen.getData().isIs_user_vehicle_document_expired()){
                    startActivity(new Intent(MainActivity.this,UploadDocumentActivity.class).putExtra("for","driver"));
                    dialog.dismiss();
                }else if(!mainScreen.getData().isIs_user_minimum_balance()) {
                    startActivity(new Intent(MainActivity.this,WalletActivity.class));
                    dialog.dismiss();
                }else {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    void clickListeners(){
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                navigation.getMenu().findItem(R.id.profile).setChecked(true);
                toolbar.setTitle("Profile");
                loadFragment(new ProfileFragment());
            }
        });
        linearWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,WalletActivity.class));
            }
        });
        linearAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,SaveAddressActivity.class)
                        .putExtra("for", "1"));
            }
        });
        linearCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,ContactCustomerActivity.class));
            }
        });
        linearPastRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,PastRidesActivity.class));
            }
        });
        linearDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,UploadDocumentActivity.class)
                        .putExtra("for", "driver"));
            }
        });
        linearPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,PaymentMethodActivity.class));
            }
        });
        linearRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,ReferActivity.class));
            }
        });
        linearTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,TermsAndConditionActivity.class));
            }
        });
        linearEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                Toast.makeText(MainActivity.this, "This is pending", Toast.LENGTH_SHORT).show();
            //    startActivity(new Intent(MainActivity.this,BankAccountActivity.class));
            }
        });

        linearBankDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawers();
                }
                startActivity(new Intent(MainActivity.this,BankAccountActivity.class));
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.logoutUser();
                manager.cleartAccessToken("");
                finish();
                startActivity(new Intent(MainActivity.this, SplashActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.Rides:
                offer = false;
                fragment = new RidesFragment();
                toolbar.setTitle(getResources().getString(R.string.rides));
                break;

            case R.id.search:
                offer = false;
                fragment = new MainFragment();
                toolbar.setTitle(getResources().getString(R.string.find_ride));
                break;

            case R.id.offer:
                offer = true;
                if(mainScreen.getData().isIs_user_offer_ride() && mainScreen.getData().isIs_user_minimum_balance()
                        && !mainScreen.getData().isIs_user_vehicle_document_expired()) {
                    if(mainScreen.getData().isIs_user_vehicle_document_expire()){
                        fragment = new OfferFragment();
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(mainScreen.getData().getIs_user_vehicle_document_expire_text());
                        builder.setCancelable(false);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                toolbar.setTitle(getResources().getString(R.string.offer_ride));
                            }
                        });
                        builder.show();
                    }else {
                        fragment = new OfferFragment();
                        toolbar.setTitle(getResources().getString(R.string.offer_ride));
                    }
                }else {
                    showAppmaintainanceDialog();
                }
                break;

            case R.id.profile:
                offer = false;
                fragment = new ProfileFragment();
                toolbar.setTitle(getResources().getString(R.string.profile));
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            finish();
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            progressDialog.hide();
            mainScreen = SingletonGson.getInstance().fromJson("" + script, ModelMainScreen.class);
            if(!mainScreen.getData().isIs_user_doc_upload()){
                Intent intent = new Intent(MainActivity.this,UploadDocumentActivity.class);
                intent.putExtra("for", "user");
                startActivity(intent);
                finish();
            }else if(mainScreen.getData().isIs_user_personal_document_expire()){
                showAppmaintainanceDialog();
            }else if(mainScreen.getData().isIs_user_personal_document_expired()) {
                showAppmaintainanceDialog();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
    }
}
