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
import com.goviens.android.databinding.ActivityReferBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelReferral;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;



public class ReferActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    ModelReferral modelReferral;
    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    ActivityReferBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_refer);
        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, modelReferral.getData().getSharing_text());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
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
        binding.etReferralCode.setText(modelReferral.getData().getRefer_code());
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }
}
