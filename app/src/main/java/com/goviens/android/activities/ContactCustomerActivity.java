package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityContactCustomerBinding;
import com.goviens.android.models.ModelCustomerSupport;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;


import butterknife.ButterKnife;

public class ContactCustomerActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityContactCustomerBinding mBinding;

    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    ModelCustomerSupport customerSupport;
    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityContactCustomerBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.relativeWp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(customerSupport.getData().get(0).getWhatsapp_no()) + "@s.whatsapp.net");
                try {
                    startActivity(sendIntent);
                }catch (Exception e){
                    Toast.makeText(ContactCustomerActivity.this, getResources().getString(R.string.whatsapp_not_installed), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        mBinding.relativeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!AppUtils.hasPermissions(ContactCustomerActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(ContactCustomerActivity.this, PERMISSIONS, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + customerSupport.getData().get(0).getCustomer_support_no()));
                    startActivity(intent);
                }
            }
        });
        mBinding.relativeMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + customerSupport.getData().get(0).getEmail()));
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(ContactCustomerActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            progressDialog.show();
            HashMap<String,String> map = new HashMap<>();
            map.put("country_id",""+sessionManager.getcountryid());
            manager._post(API_S.Tags.CUSTOMER_SUPPORT, API_S.Endpoints.CUSTOMER_SUPPORT, map, sessionManager);
        }catch (Exception e){
            e.printStackTrace();
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        customerSupport = SingletonGson.getInstance().fromJson(""+script, ModelCustomerSupport.class);
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }
}
