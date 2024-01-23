package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityConfirmBinding;
import com.goviens.android.models.ModelStepFour;
import com.goviens.android.models.ModelStepThree;
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



public class ConfirmActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityConfirmBinding binding;
    ApiManager apiManager;
    SessionManager manager;

    PlaceHolderView placeHolderDeparture;
    PlaceHolderView placeHolderReturn;

    ModelStepThree stepThree;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmBinding.inflate(getLayoutInflater());
        android.view.View view = binding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);
        apiManager = new ApiManager(this,this);
        manager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        stepThree = SingletonGson.getInstance().fromJson(""+getIntent().getStringExtra("script"), ModelStepThree.class);
        binding.tvDate.setText(AppUtils.getDateTimeInDayFormat(stepThree.getData().getDeparture().getRide_date()));
        binding.tvTotalAmt.setText(""+stepThree.getData().getDeparture().getTotal_amount());
        binding.tvSeat.setText(stepThree.getData().getDeparture().getAvailable_seats()+" "+getResources().getString(R.string.seats));
        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        if(stepThree.getData().getDeparture().isAc_ride()){
            binding.linearAc.setVisibility(android.view.View.VISIBLE);
        }else {
            binding.linearAc.setVisibility(android.view.View.GONE);
        }
        for(int i=0; i<stepThree.getData().getDeparture().getRoutes().size();i++) {
            if(i == stepThree.getData().getDeparture().getRoutes().size()-1) {
                placeHolderDeparture.addView(new HolderDeparture(stepThree.getData().getDeparture().getRoutes().get(i), "1"));
            }else {
                placeHolderDeparture.addView(new HolderDeparture(stepThree.getData().getDeparture().getRoutes().get(i), "0"));
            }
        }
        if(stepThree.getData().getIs_return().equals("1")){
            placeHolderReturn.setVisibility(android.view.View.VISIBLE);
            binding.etReturnLabel.setVisibility(android.view.View.VISIBLE);
            binding.linearReturn.setVisibility(android.view.View.VISIBLE);
            if(stepThree.getData().getDeparture().isAc_ride()){
                binding.linearAcReturn.setVisibility(android.view.View.GONE);
            }
            binding.tvSeatReturn.setText(stepThree.getData().getReturnX().getAvailable_seats()+" "+getResources().getString(R.string.seats));
            binding.tvDateReturn.setText(AppUtils.getDateTimeInDayFormat(stepThree.getData().getReturnX().getRide_date()));
            binding.tvTotalAmt.setText(stepThree.getData().getReturnX().getTotal_amount());
            for (int i = 0; i < stepThree.getData().getReturnX().getRoutes().size(); i++) {
                if (i == stepThree.getData().getReturnX().getRoutes().size() - 1) {
                    placeHolderReturn.addView(new HolderReturn(stepThree.getData().getReturnX().getRoutes().get(i), "1"));
                } else {
                    placeHolderReturn.addView(new HolderReturn(stepThree.getData().getReturnX().getRoutes().get(i), "0"));
                }
            }
        }
        binding.btnContinue.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                try{
                    progressDialog.show();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("offer_ride_checkout_id", "" + getIntent().getStringExtra("checkout"));
                    apiManager._post(API_S.Tags.RIDE_STEP_FOUR, API_S.Endpoints.RIDE_STEP_FOUR, map, manager);
                }catch (Exception e){
                    progressDialog.hide();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        progressDialog.hide();
        ModelStepFour stepFour = SingletonGson.getInstance().fromJson(""+script,ModelStepFour.class);
        if(stepFour.getResult().equals("1")){
            Toast.makeText(this, ""+stepFour.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }

    @Layout(R.layout.raw_ride_details)
    class HolderDeparture{
        @View(R.id.tv_address)
        TextView tvAddress;
        @View(R.id.tv_time)
        TextView tvTime;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.view)
        android.view.View view;

        ModelStepThree.DataBean.DepartureBean.RoutesBean routesBean;
        String lastElement;

        public HolderDeparture(ModelStepThree.DataBean.DepartureBean.RoutesBean routesBean, String lastElement) {
            this.routesBean = routesBean;
            this.lastElement = lastElement;
        }

        @Resolve
        void setData(){
            if(lastElement.equals("1")){
                tvAmt.setText(""+routesBean.getFinal_charges());
                tvAddress.setText(routesBean.getLocation());
                tvTime.setText(AppUtils.getTimeViaTimestamp(routesBean.getRide_timestamp()));
                view.setVisibility(android.view.View.GONE);
                if(routesBean.getFinal_charges() == null) {
                    tvAmt.setVisibility(android.view.View.GONE);
                }else {
                    tvAmt.setVisibility(android.view.View.VISIBLE);
                    tvAmt.setText(""+routesBean.getFinal_charges());
                }
            } else {
                if(routesBean.getFinal_charges() == null) {
                    tvAmt.setVisibility(android.view.View.GONE);
                }else {
                    tvAmt.setVisibility(android.view.View.VISIBLE);
                    tvAmt.setText(""+routesBean.getFinal_charges());
                }
                tvAddress.setText(routesBean.getLocation());
                tvTime.setText(AppUtils.getTimeViaTimestamp(routesBean.getRide_timestamp()));
                view.setVisibility(android.view.View.VISIBLE);
            }
        }
    }

    @Layout(R.layout.raw_ride_details)
    class HolderReturn{
        @View(R.id.tv_address)
        TextView tvAddress;
        @View(R.id.tv_time)
        TextView tvTime;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.view)
        android.view.View view;

        ModelStepThree.DataBean.ReturnBean.RoutesBeanX routesBean;
        String lastElement;

        public HolderReturn(ModelStepThree.DataBean.ReturnBean.RoutesBeanX routesBean, String lastElement) {
            this.routesBean = routesBean;
            this.lastElement = lastElement;
        }

        @Resolve
        void setData(){
            if(lastElement.equals("1")){
                tvAmt.setText(""+routesBean.getFinal_charges());
                tvAddress.setText(routesBean.getLocation());
                tvTime.setText(AppUtils.getTimeViaTimestamp(routesBean.getRide_timestamp()));
                view.setVisibility(android.view.View.GONE);
                if(routesBean.getFinal_charges() == null) {
                    tvAmt.setVisibility(android.view.View.GONE);
                }else {
                    tvAmt.setVisibility(android.view.View.VISIBLE);
                    tvAmt.setText(""+routesBean.getFinal_charges());
                }
            } else {
                if(routesBean.getFinal_charges() == null) {
                    tvAmt.setVisibility(android.view.View.GONE);
                }else {
                    tvAmt.setVisibility(android.view.View.VISIBLE);
                    tvAmt.setText(""+routesBean.getFinal_charges());
                }
                tvAddress.setText(routesBean.getLocation());
                tvTime.setText(AppUtils.getTimeViaTimestamp(routesBean.getRide_timestamp()));
                view.setVisibility(android.view.View.VISIBLE);
            }
        }
    }
}
