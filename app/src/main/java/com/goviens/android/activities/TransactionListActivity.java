package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.models.ModelPayoutHistory;
import com.goviens.android.models.ModelWallet;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.HashMap;


import butterknife.ButterKnife;

public class TransactionListActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    PlaceHolderView placeHolderTransaction;
    ProgressDialog progressDialog;
    ApiManager apiManager;
    SessionManager manager;
    ModelWallet modelWallet;
    ModelPayoutHistory payoutHistory;
    ImageView imgBack;
    String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this,this);
        manager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        if(getIntent() != null){
            if(getIntent().getStringExtra("notification") != null){
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
            }else {
                from = getIntent().getStringExtra("from");
                if(from.equals("wallet")) {
                    modelWallet = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelWallet.class);
                    for (int i = 0; i < modelWallet.getData().getWallet_transaction().getRecords().size(); i++) {
                        placeHolderTransaction.addView(new HolderWalletTransaction(modelWallet.getData().getWallet_transaction().getRecords().get(i)));
                    }
                }else if(from.equals("payout")){
                    payoutHistory = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelPayoutHistory.class);
                    for (int i = 0; i < payoutHistory.getData().size(); i++) {
                        placeHolderTransaction.addView(new HolderPayoutTransaction(payoutHistory.getData().get(i)));
                    }
                } else {
                    modelWallet = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelWallet.class);
                    for (int i = 0; i < modelWallet.getData().getHold_amount_details().size(); i++) {
                        placeHolderTransaction.addView(new HolderHoldTransaction(modelWallet.getData().getHold_amount_details().get(i)));
                    }
                }
            }
        }
        imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.WALLET)){
            try {
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                modelWallet = SingletonGson.getInstance().fromJson("" + script, ModelWallet.class);
                for (int i = 0; i < modelWallet.getData().getWallet_transaction().getRecords().size(); i++) {
                    placeHolderTransaction.addView(new HolderWalletTransaction(modelWallet.getData().getWallet_transaction().getRecords().get(i)));
                }
            }catch (Exception e){
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }

    @Layout(R.layout.raw_transaction)
    class HolderWalletTransaction{
        @View(R.id.tv_dt_tm)
        TextView tvDtTm;
        @View(R.id.tv_ride_id)
        TextView tvRideId;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.tv_status)
        TextView tvStatus;
        @View(R.id.img)
        ImageView img;

        ModelWallet.DataBean.WalletTransactionBean.RecordsBean recordsBean;

        public HolderWalletTransaction(ModelWallet.DataBean.WalletTransactionBean.RecordsBean recordsBean) {
            this.recordsBean = recordsBean;
        }

        @Resolve
        void setData(){
            try{
                Glide.with(TransactionListActivity.this).load(recordsBean.getIcon()).into(img);
                tvDtTm.setText(AppUtils.getDateTimeInYearFormat(""+recordsBean.getDate()));
                tvRideId.setText(recordsBean.getTransaction_name());
                tvAmt.setText(recordsBean.getAmount());
                if(recordsBean.getDisplay_payment() == null){
                    tvStatus.setVisibility(android.view.View.GONE);
                }else {
                    tvStatus.setText(recordsBean.getDisplay_payment());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Layout(R.layout.raw_transaction)
    class HolderHoldTransaction{
        @View(R.id.tv_dt_tm)
        TextView tvDtTm;
        @View(R.id.tv_ride_id)
        TextView tvRideId;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.tv_status)
        TextView tvStatus;
        @View(R.id.img)
        ImageView img;

        ModelWallet.DataBean.HoldAmountDetailsBean recordsBean;
        public HolderHoldTransaction(ModelWallet.DataBean.HoldAmountDetailsBean recordsBean) {
            this.recordsBean = recordsBean;
        }

        @Resolve
        void setData(){
            try{
                img.setVisibility(android.view.View.GONE);
                tvDtTm.setText(AppUtils.getDateTimeInYearFormat(""+recordsBean.getHold_time()));
                tvRideId.setVisibility(android.view.View.VISIBLE);
                tvRideId.setText(getResources().getString(R.string.ride_id_)+recordsBean.getCarpooling_ride_id());
                tvAmt.setText(recordsBean.getAmount());
                if(recordsBean.getIs_user_offer_ride() == 1) {
                    tvStatus.setText(getResources().getString(R.string.offer_rides));
                }else {
                    tvStatus.setText(getResources().getString(R.string.taken_rides));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Layout(R.layout.raw_transaction)
    class HolderPayoutTransaction{
        @View(R.id.tv_dt_tm)
        TextView tvDtTm;
        @View(R.id.tv_ride_id)
        TextView tvRideId;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.tv_status)
        TextView tvStatus;
        @View(R.id.img)
        ImageView img;

        ModelPayoutHistory.DataBean dataBean;
        public HolderPayoutTransaction(ModelPayoutHistory.DataBean dataBean) {
            this.dataBean = dataBean;
        }


        @Resolve
        void setData(){
            try{
                img.setVisibility(android.view.View.GONE);
                if(dataBean.getUpdated_at() == 0) {
                    tvDtTm.setText(AppUtils.getDateTimeInYearFormat("" + dataBean.getCreated_at()));
                }else {
                    tvDtTm.setText(AppUtils.getDateTimeInYearFormat("" + dataBean.getUpdated_at()));
                }
                tvRideId.setVisibility(android.view.View.VISIBLE);
                if(dataBean.getTransaction_id() == null){
                    tvRideId.setText("ID:"+dataBean.getId());
                }else {
                    tvRideId.setText("ID:"+dataBean.getTransaction_id());
                }
                tvAmt.setText(dataBean.getAmount());
                tvStatus.setText(dataBean.getCashout_status());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}