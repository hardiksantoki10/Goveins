package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.models.ModelAddMoney;
import com.goviens.android.models.ModelPayoutHistory;
import com.goviens.android.models.ModelWallet;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;


import butterknife.ButterKnife;

public class WalletActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    LinearLayout linearPayout;
    LinearLayout linearTripPayment;
    TextView tvBalance;
    TextView tvHoldAmt;
    TextView tvRideId;
    ImageView imgBack;

    Button btnAddMoney;

    Button btnWithdrawal;

    LinearLayout cardWallet;
    LinearLayout cardHoldAmt;

    ProgressDialog progressDialog;
    ApiManager apiManager;
    SessionManager manager;

    ModelWallet modelWallet;
    String response;

    String amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this,this);
        manager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        cardHoldAmt.setVisibility(View.GONE);
        clickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try{
            progressDialog.show();
            HashMap<String,String> map = new HashMap<>();
            map.put("filter","3");
            map.put("slug","CARPOOLING");
            apiManager._post(API_S.Tags.WALLET, API_S.Endpoints.WALLET, map,manager);
        }catch (Exception e){
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void clickListeners(){
        linearPayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    progressDialog.show();
                    apiManager._post(API_S.Tags.PAYOUT_HISTORY, API_S.Endpoints.PAYOUT_HISTORY, null, manager);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cardWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelWallet.getData().getWallet_transaction() != null) {
                    if (modelWallet.getData().getWallet_transaction().getRecords().size() > 0) {
                        startActivity(new Intent(WalletActivity.this, TransactionListActivity.class)
                                .putExtra("script", response)
                                .putExtra("from", "wallet"));
                    } else {
                        Toast.makeText(WalletActivity.this, getResources().getString(R.string.no_records), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WalletActivity.this, getResources().getString(R.string.no_records), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cardHoldAmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelWallet.getData().getHold_amount_details().size() > 0) {
                    startActivity(new Intent(WalletActivity.this, TransactionListActivity.class)
                            .putExtra("script", response)
                            .putExtra("from", "hold"));
                }else {
                    Toast.makeText(WalletActivity.this, getResources().getString(R.string.no_records), Toast.LENGTH_SHORT).show();
                }
            }
        });

        linearTripPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this,PayoutActivity.class)
                        .putExtra("script", response)
                        .putExtra("payout","0"));
            }
        });

        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this,PaymentOptionsActivity.class)
                        .putExtra("FROM", "wallet"));
                // onMakePaymentPress(v);
            }
        });

        btnWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WalletActivity.this,PaymentOptionsActivity.class)
                        .putExtra("FROM", "payout"));
            }
        });
    }



    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if (APINAME.equals("" + API_S.Tags.ADD_MONEY_IN_WALLET)) {
            ModelAddMoney modelAddMoney = SingletonGson.getInstance().fromJson("" + script, ModelAddMoney.class);
            Toast.makeText(this, "" + modelAddMoney.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else if(APINAME.equals(API_S.Tags.PAYOUT)) {
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
        }else if(APINAME.equals(API_S.Tags.PAYOUT_HISTORY)){
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
            try {
                ModelPayoutHistory payoutHistory = SingletonGson.getInstance().fromJson("" + script, ModelPayoutHistory.class);
                if (payoutHistory.getData().size() != 0) {
                    startActivity(new Intent(WalletActivity.this, TransactionListActivity.class)
                            .putExtra("script", ""+script)
                            .putExtra("from", "payout"));
                } else {
                    Toast.makeText(WalletActivity.this, getResources().getString(R.string.no_records), Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(WalletActivity.this, getResources().getString(R.string.no_records), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                response = "" + script;
                modelWallet = SingletonGson.getInstance().fromJson("" + script, ModelWallet.class);
                if (modelWallet.getResult().equals("1")) {
                    tvBalance.setText(modelWallet.getData().getWallet_balance());
                    tvHoldAmt.setText(modelWallet.getData().getHold_amount());
//                tvRideId.setText("RIDE ID:"+modelWallet.getData().getHold_amount_details().get(0).getCarpooling_ride_id());
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}
