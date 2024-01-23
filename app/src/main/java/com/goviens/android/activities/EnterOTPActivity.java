package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityEnterOtpBinding;
import com.goviens.android.databinding.ActivityLoginBinding;
import com.goviens.android.models.ModelLogin;
import com.goviens.android.models.ModelUserDetails;
import com.goviens.android.models.ModelVerifyOtp;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;

public class EnterOTPActivity extends AppCompatActivity implements ApiManager.APIFETCHER, TextWatcher {

    private ActivityEnterOtpBinding mBinding;

    ApiManager manager;

    String forr = "";

    String isRegister = "";
    private SessionManager sessionManager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_enter_otp);


        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        forr = getIntent().getStringExtra("for");
        isRegister = getIntent().getStringExtra("isRegister");

        mBinding.etOTPfirst.addTextChangedListener(this);
        mBinding.etOTPsecond.addTextChangedListener(this);
        mBinding.etOTPthird.addTextChangedListener(this);
        mBinding.etOTPfourth.addTextChangedListener(this);
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(forr.equals("0")) {
            mBinding.text.setText(getResources().getString(R.string.login));
        }else {
            mBinding.text.setText("Change Mobile Number");
        }
        mBinding.tvResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> data = new HashMap<>();
                progressDialog.show();
                data.put("user_name",getIntent().getStringExtra("check_value"));
                data.put("type", "4");
                data.put("for","PHONE");
                data.put("phone_code", getIntent().getStringExtra("country_code"));
                try {
                    manager._post_with_secreteonly(API_S.Tags.LOGIN, API_S.Endpoints.LOGIN, data);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        mBinding.tvOtp.setText(getResources().getString(R.string.PIN_message)+" " +getIntent().getStringExtra("check_value"));
    }

    public void btnVerify(View view) {
        if(mBinding.etOTPfirst.getText().toString().equals("") || mBinding.etOTPsecond.getText().toString().equals("")
                || mBinding.etOTPthird.getText().toString().equals("") || mBinding.etOTPfourth.getText().toString().equals("")){
            Toast.makeText(EnterOTPActivity.this, getResources().getString(R.string.please_fill_the_entire_field), Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.show();
            if (forr.equals("0")) {
                HashMap<String, String> map = new HashMap<>();
                map.put("check_value",  getIntent().getStringExtra("check_value"));
                map.put("for", "PHONE");
                map.put("otp", mBinding.etOTPfirst.getText().toString() + mBinding.etOTPsecond.getText().toString() + mBinding.etOTPthird.getText().toString() + mBinding.etOTPfourth.getText().toString());
                //(Optional parameters, if is_register is true in user/otp api)
                if (isRegister.equals("true")) {
                    if(getIntent().getStringExtra("player_id").equals("")) {
                        Toast.makeText(this, "Please Restart the Application", Toast.LENGTH_SHORT).show();
                    }else {
                        System.out.println("@@PLAYER_ID"+getIntent().getStringExtra("player_id"));
                        map.put("unique_no", "" + Settings.Secure.getString(EnterOTPActivity.this.getContentResolver(), Settings.Secure.ANDROID_ID));
                        map.put("package_name", BuildConfig.APPLICATION_ID);
                        map.put("player_id", getIntent().getStringExtra("player_id"));
                        map.put("apk_version", BuildConfig.VERSION_NAME);
                        map.put("device", "1");
                        map.put("operating_system", "" + Build.VERSION.SDK_INT);
                    }
                }
                try {
                    manager._post_with_secreteonly(API_S.Tags.VERIFY_OTP, API_S.Endpoints.VERIFY_OTP, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                HashMap<String, String> map = new HashMap<>();
                map.put("check_value", getIntent().getStringExtra("check_value"));
                map.put("for", "PHONE");
                map.put("otp", mBinding.etOTPfirst.getText().toString() + mBinding.etOTPsecond.getText().toString() + mBinding.etOTPthird.getText().toString() + mBinding.etOTPfourth.getText().toString());
                try {
                    manager._post_with_secreteonly(API_S.Tags.VERIFY_OTP, API_S.Endpoints.VERIFY_OTP, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.VERIFY_OTP)) {
            ModelVerifyOtp verifyOtp = SingletonGson.getInstance().fromJson("" + script, ModelVerifyOtp.class);
            if(forr.equals("0")) {
                if (verifyOtp.getResult().equals("1")) {
                    if (isRegister.equals("true")) {
                        sessionManager.setAccessToken("" + verifyOtp.getData().getAccess_token());
                        try {
                            manager._post(API_S.Tags.USER_DETAILS, API_S.Endpoints.USER_DETAILS, null, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        progressDialog.hide();
                        Intent intent = new Intent(EnterOTPActivity.this, SignUpActivity.class);
                        intent.putExtra("country_id", getIntent().getStringExtra("country_id"));
                        intent.putExtra("phone", getIntent().getStringExtra("check_value"));
                        intent.putExtra("country_code", getIntent().getStringExtra("country_code"));
                        startActivity(intent);
                        finish();
                    }
                }
            } else {
                progressDialog.hide();
                if (verifyOtp.getResult().equals("1")) {
                    Toast.makeText(this, "" + verifyOtp.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "" + verifyOtp.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }else if(APINAME.equals(API_S.Tags.USER_DETAILS)){
            progressDialog.hide();
            ModelUserDetails modelUserDetails = SingletonGson.getInstance().fromJson("" + script, ModelUserDetails.class);
                    sessionManager.createLoginSession(""+modelUserDetails.getData().getDob(),"" + modelUserDetails.getData().getId(),
                            modelUserDetails.getData().getFirst_name(),
                            modelUserDetails.getData().getLast_name(),
                            modelUserDetails.getData().getEmail(),
                            modelUserDetails.getData().getPassword(),
                            modelUserDetails.getData().getUserPhone(),
                            "" + modelUserDetails.getData().getPhone_code(),
                            "" + modelUserDetails.getData().getUserProfileImage(),
                            "" + modelUserDetails.getData().getUser_gender(),
                            "" + modelUserDetails.getData().getSmoker_type(),
                            "" + modelUserDetails.getData().getAllow_other_smoker(),
                            "" + modelUserDetails.getData().getUserSignupType()
                            , ""
                            ,modelUserDetails.getData().getCurrency()
                            ,modelUserDetails.getData().getMobile_number());
                    sessionManager.makUserLoggedIn();
                    try {
                        sessionManager.setcountryid(Integer.parseInt(modelUserDetails.getData().getCountry_id()));
                    } catch (Exception e) {

                    }
            sessionManager.makUserLoggedIn();
            Intent intent = new Intent(EnterOTPActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(APINAME.equals(API_S.Tags.LOGIN)){
            progressDialog.hide();
            ModelLogin modelLogin = SingletonGson.getInstance().fromJson("" + script, ModelLogin.class);
            Toast.makeText(this, modelLogin.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
        progressDialog.hide();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 1) {
            if (mBinding.etOTPfirst.length() == 1) {
                mBinding.etOTPsecond.requestFocus();
            }

            if (mBinding.etOTPsecond.length() == 1) {
                mBinding.etOTPthird.requestFocus();
            }
            if (mBinding.etOTPthird.length() == 1) {
                mBinding.etOTPfourth.requestFocus();
            }
        } else if (s.length() == 0) {
            if (mBinding.etOTPfourth.length() == 0) {
                mBinding.etOTPthird.requestFocus();
            }
            if (mBinding.etOTPthird.length() == 0) {
                mBinding.etOTPsecond.requestFocus();
            }
            if (mBinding.etOTPsecond.length() == 0) {
                mBinding.etOTPfirst.requestFocus();
            }
        }
    }
}
