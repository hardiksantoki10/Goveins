package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityBankAccountBinding;
import com.goviens.android.models.ModelUpdateBankDetails;
import com.goviens.android.models.ModelUserDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import butterknife.ButterKnife;

public class BankAccountActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityBankAccountBinding mBinding;
    SessionManager manager;
    ApiManager apiManager;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityBankAccountBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);

        apiManager = new ApiManager(this,this);
        manager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < manager.getAppConfig().getData().getAccount_types().size(); i++) {
            list.add(manager.getAppConfig().getData().getAccount_types().get(i).getTitle());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.accounttypeSpinner.setAdapter(dataAdapter);
        mBinding.accounttypeSpinner.setSelection(0);



        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBinding.etBankName.getText().toString().isEmpty() || mBinding.etAccountName.getText().toString().isEmpty()
                        || mBinding.etAccountNo.getText().toString().isEmpty() || mBinding.etBankAddress.getText().toString().isEmpty()){
                    Toast.makeText(BankAccountActivity.this, getResources().getString(R.string.please_enter_correct_details), Toast.LENGTH_SHORT).show();
                }else {
                    try{
                        int pos = mBinding.accounttypeSpinner.getSelectedItemPosition();
                        HashMap<String,String> map = new HashMap<>();
                        if(!mBinding.etBankInstitutionNo.getText().toString().isEmpty()){
                            map.put("bank_institution_number",""+mBinding.etBankInstitutionNo.getText().toString());
                        }
                        if(!mBinding.etRoutingNumber.getText().toString().isEmpty()){
                            map.put("routing_number",""+mBinding.etRoutingNumber.getText().toString());
                        }
                        if(!mBinding.etIbanNumber.getText().toString().isEmpty()){
                            map.put("iban_number",""+mBinding.etIbanNumber.getText().toString());
                        }
                        if(!mBinding.etSwiftCode.getText().toString().isEmpty()){
                            map.put("swift_bic_code",mBinding.etSwiftCode.getText().toString());
                        }
                        map.put("bank_name",""+mBinding.etBankName.getText().toString());
                        map.put("account_holder_name",""+mBinding.etAccountName.getText().toString());
                        map.put("account_number",""+mBinding.etAccountNo.getText().toString());
                        map.put("account_type",""+manager.getAppConfig().getData().getAccount_types().get(pos).getId());
                        map.put("bank_address",""+mBinding.etBankAddress.getText().toString());
                        //map.put("online_code","demo data");
                        progressDialog.show();
                        apiManager._post(API_S.Tags.UPDATE_BANK_DETAILS,API_S.Endpoints.UPDATE_BANK_DETAILS,map,manager);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.show();
        try {
            apiManager._post(API_S.Tags.USER_DETAILS, API_S.Endpoints.USER_DETAILS, null, manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.UPDATE_BANK_DETAILS)){
            ModelUpdateBankDetails updateBankDetails = SingletonGson.getInstance().fromJson(""+script,ModelUpdateBankDetails.class);
            if(updateBankDetails.getResult().equals("1")){
                progressDialog.hide();
                finish();
            }
        }else if(APINAME.equals(API_S.Tags.USER_DETAILS)){
            progressDialog.hide();
            ModelUserDetails userDetails = SingletonGson.getInstance().fromJson(""+script,ModelUserDetails.class);
            if(!userDetails.getData().getBank_details().getAccount_holder_name().equals("")){
                try {
                    for(int i=0; i<manager.getAppConfig().getData().getAccount_types().size(); i++){
                        if(userDetails.getData().getBank_details().getAccount_type_id() == manager.getAppConfig().getData().getAccount_types().get(i).getId()){
                            mBinding.accounttypeSpinner.setSelection(i);
                        }
                    }
                    mBinding.etBankName.setText(userDetails.getData().getBank_details().getBank_name());
                    mBinding.etAccountName.setText(userDetails.getData().getBank_details().getAccount_holder_name());
                    mBinding.etAccountNo.setText(userDetails.getData().getBank_details().getAccount_number());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}
