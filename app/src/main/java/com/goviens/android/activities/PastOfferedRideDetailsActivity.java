package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityPastOfferedRideDetailsBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelReceiptDriver;
import com.goviens.android.models.ModelRideDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.HashMap;


import de.hdodenhof.circleimageview.CircleImageView;

public class PastOfferedRideDetailsActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ModelRideDetails rideDetails;
    ApiManager manager;
    SessionManager sessionManager;
    PlaceHolderView placeHolderRequest;
    PlaceHolderView placeHolderRide;
    android.view.View viewRequest;

    ModelReceiptDriver receiptUser;

    PlaceHolderView placeHolderRiders;



    ProgressDialog progressDialog;
    android.view.View view;

    private static String RIDE_STATUS;

    String from;//0 for upcoming 1 for ongoing

    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;
    String rider = "0";

    ActivityPastOfferedRideDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPastOfferedRideDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_past_offered_ride_details);
        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        RIDE_STATUS = getIntent().getStringExtra("ride_status");
        if(RIDE_STATUS.equals("9")){
            binding.tvStatus.setText(getResources().getString(R.string.auto_cancel));
            binding.tvStatus.setTextSize(14);
            binding.tvStatus.setTextColor(Color.RED);
        }
        rideDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelRideDetails.class);
        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        binding.btnReceipt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                view = v;
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("ride_id", ""+rideDetails.getData().getCarpooling_ride_id());
                    manager._post(API_S.Tags.RECEIPT_DRIVER, API_S.Endpoints.RECEIPT_DRIVER, map, sessionManager);
                }catch (Exception e){
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(PastOfferedRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(rideDetails.getData().getInstructions() != null){
            if(!rideDetails.getData().getInstructions().equals("")) {
                binding.tvLabelInstruction.setVisibility(android.view.View.VISIBLE);
                binding.tvInstruction.setVisibility(android.view.View.VISIBLE);
                binding.tvInstruction.setText(rideDetails.getData().getInstructions());
            }else {
                binding.tvLabelInstruction.setVisibility(android.view.View.GONE);
                binding.tvInstruction.setVisibility(android.view.View.GONE);
            }
        }else {
            binding.tvLabelInstruction.setVisibility(android.view.View.GONE);
            binding.tvInstruction.setVisibility(android.view.View.GONE);
        }
        binding.tvTotalSeats.setText(""+rideDetails.getData().getTotal_seats());
        binding.tvRequest.setVisibility(android.view.View.GONE);
        viewRequest.setVisibility(android.view.View.GONE);
        placeHolderRequest.setVisibility(android.view.View.GONE);
        if (rideDetails.getData().getAccept_users().size() != 0) {
            binding.linearRiders.setVisibility(android.view.View.VISIBLE);
            for (int i = 0; i < rideDetails.getData().getAccept_users().size(); i++) {
                placeHolderRiders.addView(new HolderRiders(rideDetails.getData().getAccept_users().get(i)));
            }
        }
        binding.tvDt.setText(AppUtils.getDateTimeInDayFormat(rideDetails.getData().getRide_timestamp()));
        binding.tvAmt.setText(rideDetails.getData().getTotal_amount());
        if (rideDetails.getData().isAc_ride()) {
            binding.linearAc.setVisibility(android.view.View.VISIBLE);
        } else {
            binding.linearAc.setVisibility(android.view.View.GONE);
        }
        if (rideDetails.getData().isOnly_females()) {
            binding.linearFemale.setVisibility(android.view.View.VISIBLE);
        } else {
            binding.linearFemale.setVisibility(android.view.View.GONE);
        }
        for (int i = 0; i < rideDetails.getData().getRide_details_list().size(); i++) {
            if (i == rideDetails.getData().getRide_details_list().size() - 1) {
                placeHolderRide.addView(new HolderRides(rideDetails.getData().getRide_details_list().get(i), "1"));
            } else {
                placeHolderRide.addView(new HolderRides(rideDetails.getData().getRide_details_list().get(i), "0"));
            }
        }
    }

    void openDialogForRating(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PastOfferedRideDetailsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        LinearLayout btnDoneReceipt = dialogView.findViewById(R.id.lldone_receipt);
        LinearLayout linearReceipt = dialogView.findViewById(R.id.linear_receipt);
        LinearLayout linearRating = dialogView.findViewById(R.id.linear_rating);
        PlaceHolderView placeHolderView = dialogView.findViewById(R.id.placeHolder_receipt);
        linearReceipt.setVisibility(android.view.View.VISIBLE);
        linearRating.setVisibility(android.view.View.GONE);
        for(int i=0;i<receiptUser.getData().getDriver_Receipt().getBody().getRide_details().size(); i++){
            placeHolderView.addView(new HolderReceipt(receiptUser.getData().getDriver_Receipt().getBody().getRide_details().get(i)));
        }
        for(int i=0;i<receiptUser.getData().getDriver_Receipt().getBody().getBill_details().size(); i++){
            placeHolderView.addView(new HolderReceiptBill(receiptUser.getData().getDriver_Receipt().getBody().getBill_details().get(i)));
        }

        placeHolderView.addView(new HolderFooter(receiptUser.getData().getDriver_Receipt().getFooter()));


        btnDoneReceipt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
               alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.RECEIPT_DRIVER)){
            try {
                receiptUser = SingletonGson.getInstance().fromJson("" + script, ModelReceiptDriver.class);
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                startActivity(new Intent(PastOfferedRideDetailsActivity.this, ReceiptActivity.class)
                        .putExtra("script", ""+script)
                        .putExtra("user_receipt","0"));
                //openDialogForRating(view);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {

    }

    @Layout(R.layout.raw_ride_details)
    class HolderRides{
        @View(R.id.tv_address)
        TextView tvAddress;
        @View(R.id.tv_time)
        TextView tvTime;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.view)
        android.view.View view;

        ModelRideDetails.DataBean.RideDetailsListBean rideDetailsListBean;
        String lastElement;

        public HolderRides(ModelRideDetails.DataBean.RideDetailsListBean rideDetailsListBean, String lastElement) {
            this.lastElement = lastElement;
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData(){
            try {
                if (lastElement.equals("1")) {
                    tvAddress.setText(rideDetailsListBean.getLocation());
                    tvAmt.setVisibility(android.view.View.VISIBLE);
                    tvAmt.setText("" + rideDetailsListBean.getFinal_charges());
                    tvTime.setText(AppUtils.getTimeViaTimestamp(rideDetailsListBean.getRide_timestamp()));
                    view.setVisibility(android.view.View.GONE);
                } else {
                    if (rideDetailsListBean.getFinal_charges() != null) {
                        tvAmt.setVisibility(android.view.View.VISIBLE);
                        tvAmt.setText("" + rideDetailsListBean.getFinal_charges());
                    } else {
                        tvAmt.setVisibility(android.view.View.GONE);
                    }
                    tvAddress.setText(rideDetailsListBean.getLocation());
                    tvTime.setText(AppUtils.getTimeViaTimestamp(rideDetailsListBean.getRide_timestamp()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Layout(R.layout.raw_request)
    class HolderRiders {
        @View(R.id.image_profile)
        CircleImageView imgProfile;
        @View(R.id.tv_name)
        TextView tvName;
        @View(R.id.tv_rating)
        TextView tvRating;
        @View(R.id.root)
        RelativeLayout root;
        @View(R.id.tv_status)
        TextView tvStatus;
        @View(R.id.tv_id)
        TextView tvId;
        @View(R.id.tv_reason)
        TextView tvReason;
        @View(R.id.label_cancel)
        TextView tvLabelCancel;
        @View(R.id.img_indicator)
        ImageView imgIndicator;

        @Position
        int mPostion;

        ModelRideDetails.AcceptUsersBean rideDetailsListBean;

        public HolderRiders(ModelRideDetails.AcceptUsersBean rideDetailsListBean) {
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData() {
            try {
                imgIndicator.setVisibility(android.view.View.GONE);
                tvId.setVisibility(android.view.View.VISIBLE);
                tvId.setText(rideDetailsListBean.getUnique_id());
                if(rideDetailsListBean.getRide_status() == 5){
                    tvStatus.setVisibility(android.view.View.VISIBLE);
                    tvStatus.setText(getResources().getString(R.string.cancelled));
                    tvReason.setVisibility(android.view.View.VISIBLE);
                    tvLabelCancel.setVisibility(android.view.View.VISIBLE);
                    tvReason.setText(rideDetailsListBean.getCancel_reason());
                }else if(rideDetailsListBean.getRide_status() == 6){
                    tvStatus.setVisibility(android.view.View.VISIBLE);
                    tvStatus.setText(getResources().getString(R.string.cancel_by_user));
                    tvReason.setVisibility(android.view.View.VISIBLE);
                    tvLabelCancel.setVisibility(android.view.View.VISIBLE);
                    tvReason.setText(rideDetailsListBean.getCancel_reason());
                }else if(rideDetailsListBean.getRide_status() == 9){
                    tvStatus.setVisibility(android.view.View.VISIBLE);
                    tvStatus.setText(getResources().getString(R.string.auto_cancel));
                    tvReason.setVisibility(android.view.View.GONE);
                    tvLabelCancel.setVisibility(android.view.View.VISIBLE);
                    //tvReason.setText(rideDetailsListBean.getCancel_reason());
                }else {
                    tvStatus.setVisibility(android.view.View.GONE);
                    tvReason.setVisibility(android.view.View.GONE);
                    tvLabelCancel.setVisibility(android.view.View.GONE);
                }
                Glide.with(PastOfferedRideDetailsActivity.this).load(rideDetailsListBean.getAccept_user_image()).into(imgProfile);
                tvName.setText(rideDetailsListBean.getAccept_user_name());
                tvRating.setText(rideDetailsListBean.getAccept_user_rating());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Layout(R.layout.raw_receipt)
    class HolderReceipt{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean ;

        public HolderReceipt(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean) {
            this.rideDetailsBean = rideDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+rideDetailsBean.getLeft_text());
                tvRightText.setText(""+rideDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastOfferedRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderReceiptBill{
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean;

        public HolderReceiptBill(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean) {
            this.billDetailsBean = billDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+billDetailsBean.getLeft_text());
                tvRightText.setText(""+billDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastOfferedRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Layout(R.layout.raw_receipt)
    class HolderFooter{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer;

        public HolderFooter(ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer) {
            this.footer = footer;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+footer.getLeft_text());
                tvRightText.setText(""+footer.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastOfferedRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
}