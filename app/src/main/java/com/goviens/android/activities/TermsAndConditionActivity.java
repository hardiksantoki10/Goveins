package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.models.ModelTermsAndCondition;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;


import butterknife.ButterKnife;

public class TermsAndConditionActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ImageView bck;
    TextView tc;
    Button accept_t_and_c;

    private SessionManager sessionManager;
    private ApiManager apiManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog.show();
        try {
            HashMap<String, String> data = new HashMap<>();
            data.put("slug", "terms_and_Conditions");
            if(null != getIntent()){
                if(null != getIntent().getStringExtra("country_id")) {
                    data.put("country_id", getIntent().getStringExtra("country_id"));
                }else {
                    data.put("country_id", "" + sessionManager.getcountryid());
                }
            }else {
                data.put("country_id", "" + sessionManager.getcountryid());
            }
            if(null != getIntent()){
                if(null != getIntent().getStringExtra("country_id")) {
                    apiManager._post_with_secreteonly(API_S.Tags.CMS_PAGES, API_S.Endpoints.CMS_PAGES, data);
                }else {
                    apiManager._post(API_S.Tags.CMS_PAGES, API_S.Endpoints.CMS_PAGES, data, sessionManager);
                }
            }else {
                apiManager._post(API_S.Tags.CMS_PAGES, API_S.Endpoints.CMS_PAGES, data, sessionManager);
            }
        } catch (Exception e) {
            Toast.makeText(TermsAndConditionActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing()){
                progressDialog.hide();
            }
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            if (progressDialog.isShowing()) {
                progressDialog.hide();
            }
            ModelTermsAndCondition termsAndCondition = SingletonGson.getInstance().fromJson("" + script, ModelTermsAndCondition.class);
            if (termsAndCondition.getResult().equals("1")) {
                tc.setText(Html.fromHtml("" + termsAndCondition.getData().getDescription()));
            }
        }catch (Exception e){
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.hide();
            }
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }
}