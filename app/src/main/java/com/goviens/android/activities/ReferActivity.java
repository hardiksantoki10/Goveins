package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.models.ModelReferral;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;


import butterknife.ButterKnife;

public class ReferActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    TextView etReferralCode;
    Button btnShare;
    ImageView imgBack;
    ModelReferral modelReferral;
    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        ButterKnife.bind(this);
        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, modelReferral.getData().getSharing_text());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            progressDialog.show();
            manager._post(API_S.Tags.REFERRAL_CODE, API_S.Endpoints.REFERRAL_CODE,null,sessionManager);
        }catch (Exception e){
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
            e.printStackTrace();
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
        modelReferral = SingletonGson.getInstance().fromJson(""+script, ModelReferral.class);
        etReferralCode.setText(modelReferral.getData().getRefer_code());
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }
}
