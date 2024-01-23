package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityPastTakenRideDetailsBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelProfileDetails;
import com.goviens.android.models.ModelReceiptUser;
import com.goviens.android.models.ModelTakenRideDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;

import java.util.HashMap;


import de.hdodenhof.circleimageview.CircleImageView;

public class PastTakenRideDetailsActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager manager;
    SessionManager sessionManager;

    ModelReceiptUser receiptUser;

    PlaceHolderView placeHolderRiders;
    ModelTakenRideDetails rideDetails;
    ProgressDialog progressDialog;
    String cancelReasonId = "null";

    android.view.View view;


    String from;

    ActivityPastTakenRideDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPastTakenRideDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        if (getIntent().getStringExtra("ride_id") != null) {
            try {
                progressDialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("carpooling_ride_user_detail_id", "" + getIntent().getStringExtra("ride_id"));
                manager._post(API_S.Tags.PAST_TAKEN_RIDE_DETAILS, API_S.Endpoints.PAST_TAKEN_RIDE_DETAILS, map, sessionManager);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }
        } else{
            rideDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelTakenRideDetails.class);
            setData();
        }
        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        binding.btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForRating(v);
            }
        });
        binding.btnReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = v;
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("carpooling_ride_user_detail_id", ""+rideDetails.getData().get(0).getTaken_ride_details().getId());
                    manager._post(API_S.Tags.RECEIPT_USER, API_S.Endpoints.RECEIPT_USER, map, sessionManager);
                }catch (Exception e){
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.linearDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    progressDialog.show();
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("user_id",""+rideDetails.getData().get(0).getOffer_user_detail().getId());
//                    map.put("type","driver");
//                    map.put("ride id",""+rideDetails.getData().get(0).getTaken_ride_details().getRide_id());
//                    manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
//                }catch (Exception e){
//                    e.printStackTrace();
//                    Toast.makeText(PastTakenRideDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    if(progressDialog.isShowing()){
//                        progressDialog.hide();
//                    }
//                }
            }
        });
    }


    void openDialogforReceipt(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PastTakenRideDetailsActivity.this);
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
        for(int i=0;i<receiptUser.getData().getUser_Receipt().getBody().getRide_details().size(); i++){
            placeHolderView.addView(new HolderReceipt(receiptUser.getData().getUser_Receipt().getBody().getRide_details().get(i)));
        }
        for(int i=0;i<receiptUser.getData().getUser_Receipt().getBody().getBill_details().size(); i++){
            placeHolderView.addView(new HolderReceiptBill(receiptUser.getData().getUser_Receipt().getBody().getBill_details().get(i)));
        }
        placeHolderView.addView(new HolderFooter(receiptUser.getData().getUser_Receipt().getFooter()));

        btnDoneReceipt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    void openDialogForRating(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PastTakenRideDetailsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        TextView tvComments = dialogView.findViewById(R.id.comments);
        LinearLayout btnDone = dialogView.findViewById(R.id.lldone);
        LinearLayout linearReceipt = dialogView.findViewById(R.id.linear_receipt);
        linearReceipt.setVisibility(View.GONE);
        btnDone.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try{
                    progressDialog.show();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("ride_id",""+rideDetails.getData().get(0).getTaken_ride_details().getRide_id());
                    map.put("carpooling_ride_user_detail_id",""+rideDetails.getData().get(0).getTaken_ride_details().getId());
                    map.put("rating",""+ratingBar.getRating());
                    map.put("comment",tvComments.getText().toString());
                    manager._post(API_S.Tags.DRIVER_RATING,API_S.Endpoints.DRIVER_RATING,map,sessionManager);
                    alertDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    progressDialog.hide();
                }
            }
        });
        alertDialog.show();
    }

    private void setData() {
        try {
            if(rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("7")
                    || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("8")
                    || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("9")){
                binding.btnReceipt.setVisibility(View.GONE);
            }else {
                binding.btnReceipt.setVisibility(View.VISIBLE);
            }
            binding.tvTakenSeats.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getBooked_seat());
            binding.tvDt.setText(AppUtils.getDateTimeInDayFormat("" + rideDetails.getData().get(0).getTaken_ride_details().getRide_time()));
            binding.tvAmt.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getRide_amount());
            if(rideDetails.getData().get(0).getTaken_ride_details().getPayment_status() == 1){
                binding.tvAmountType.setText(getResources().getString(R.string.wallet));
            }else if(rideDetails.getData().get(0).getTaken_ride_details().getPayment_status() == 3){
                binding.tvAmountType.setText(getResources().getString(R.string.wallet_at_pickup));
            }else{
                binding.tvAmountType.setText(getResources().getString(R.string.cash));
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getCancel_reason() == null){
                binding.tvCancelReason.setVisibility(View.GONE);
                binding.tvLabelReason.setVisibility(View.GONE);
            }else {
                binding.tvCancelReason.setText(rideDetails.getData().get(0).getTaken_ride_details().getCancel_reason());
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("5")
                    || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("6")){
                binding.btnRating.setVisibility(View.GONE);
            }else {
                if (rideDetails.getData().get(0).getTaken_ride_details().isIs_user_rated()) {
                    binding.btnRating.setVisibility(View.GONE);
                } else {
                    if(rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("7")
                            || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("8")
                            || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("9")){
                        binding.btnRating.setVisibility(View.GONE);
                    }else {
                        binding.btnRating.setVisibility(View.VISIBLE);
                    }
                }
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getInstructions() != null){
                if(!rideDetails.getData().get(0).getTaken_ride_details().getInstructions().equals("")) {
                    binding.tvLabelInstruction.setVisibility(View.VISIBLE);
                    binding.tvInstruction.setVisibility(View.VISIBLE);
                    binding.tvInstruction.setText(rideDetails.getData().get(0).getTaken_ride_details().getInstructions());
                }else {
                    binding.tvLabelInstruction.setVisibility(View.GONE);
                    binding.tvInstruction.setVisibility(View.GONE);
                }
            }else {
                binding.tvLabelInstruction.setVisibility(View.GONE);
                binding.tvInstruction.setVisibility(View.GONE);
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getAc_ride().equals("1")){
                binding.linearAc.setVisibility(View.VISIBLE);
            }else {
                binding.linearAc.setVisibility(View.GONE);
            }
            binding.tvStartTime.setText(AppUtils.getTimeViaTimestamp(rideDetails.getData().get(0).getTaken_ride_details().getRide_time()));
            binding.tvEndTime.setText(AppUtils.getTimeViaTimestamp(rideDetails.getData().get(0).getTaken_ride_details().getEnd_ride_time()));
            binding.tvFrom.setText(rideDetails.getData().get(0).getTaken_ride_details().getPickup_location());
            binding.tvTo.setText(rideDetails.getData().get(0).getTaken_ride_details().getDrop_location());
            Glide.with(this).load(rideDetails.getData().get(0).getOffer_user_detail().getImage()).into(binding.imgProfile);
            binding.tvName.setText(rideDetails.getData().get(0).getOffer_user_detail().getName());
            Glide.with(this).load(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_image()).into(binding.imgVehicle);
            binding.tvVehicleName.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_type_name());
            binding.tvVehicleColor.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_color());
            binding.tvVehicleNumber.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_number());
            if (rideDetails.getData().get(0).getAccept_users().size() != 0) {
                binding.linearRiders.setVisibility(android.view.View.VISIBLE);
                for (int i = 0; i < rideDetails.getData().get(0).getAccept_users().size(); i++) {
                    placeHolderRiders.addView(new HolderRiders(rideDetails.getData().get(0).getAccept_users().get(i)));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.RECEIPT_USER)){
            try {
                receiptUser = SingletonGson.getInstance().fromJson("" + script, ModelReceiptUser.class);
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                startActivity(new Intent(PastTakenRideDetailsActivity.this, ReceiptActivity.class)
                        .putExtra("script", ""+script)
                        .putExtra("user_receipt","1"));
                //openDialogforReceipt(view);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if(APINAME.equals(API_S.Tags.PROFILE_DETAILS)){
            try {
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                ModelProfileDetails profileDetails = SingletonGson.getInstance().fromJson("" + script, ModelProfileDetails.class);
                if (profileDetails.getResult().equals("1")) {
                    startActivity(new Intent(PastTakenRideDetailsActivity.this, ProfileActivity.class)
                            .putExtra("script", "" + script));
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
            }
        }else if(APINAME.equals(API_S.Tags.CANCEL_TAKEN_RIDE)){
            finish();
        }else if(APINAME.equals(API_S.Tags.DRIVER_RATING)){
            if(progressDialog.isShowing()) {
                progressDialog.hide();
            }
            try {
                progressDialog.show();
                HashMap<String, String> map = new HashMap<>();
                if (getIntent().getStringExtra("ride_id") != null) {
                    map.put("carpooling_ride_user_detail_id", "" + getIntent().getStringExtra("ride_id"));
                }else {
                    map.put("carpooling_ride_user_detail_id", "" + getIntent().getStringExtra("id"));
                }
                manager._post(API_S.Tags.PAST_TAKEN_RIDE_DETAILS, API_S.Endpoints.PAST_TAKEN_RIDE_DETAILS, map, sessionManager);
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
            }
        }else if(APINAME.equals(API_S.Tags.PAST_TAKEN_RIDE_DETAILS)){
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
            rideDetails = SingletonGson.getInstance().fromJson(""+script, ModelTakenRideDetails.class);
            if(rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("5")
                    || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("6")
                    || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("9")){
                binding.btnRating.setVisibility(View.GONE);
            }else {
                if (rideDetails.getData().get(0).getTaken_ride_details().isIs_user_rated()) {
                    binding.btnRating.setVisibility(View.GONE);
                } else {
                    if(rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("7")
                            || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("8")
                            || rideDetails.getData().get(0).getTaken_ride_details().getRide_status().equals("9")){
                        binding.btnRating.setVisibility(View.GONE);
                    }else {
                        binding.btnRating.setVisibility(View.VISIBLE);
                    }
                }
            }
            setData();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
        if(progressDialog.isShowing()){
            progressDialog.hide();
        }
    }


    @Layout(R.layout.raw_request)
    class HolderRiders {
        @com.mindorks.placeholderview.annotations.View(R.id.image_profile)
        CircleImageView imgProfile;
        @com.mindorks.placeholderview.annotations.View(R.id.img_indicator)
        ImageView imgIndicator;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_name)
        TextView tvName;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_rating)
        TextView tvRating;
        @com.mindorks.placeholderview.annotations.View(R.id.root)
        RelativeLayout root;
        @com.mindorks.placeholderview.annotations.View(R.id.view)
        View view;

        @Position
        int mPostion;

        ModelTakenRideDetails.DataBean.AcceptUsersBean rideDetailsListBean;

        public HolderRiders(ModelTakenRideDetails.DataBean.AcceptUsersBean rideDetailsListBean) {
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData() {
            if(rideDetails.getData().get(0).getAccept_users().size() == 1){
                view.setVisibility(View.GONE);
            }
            imgIndicator.setVisibility(View.GONE);
            try {
                Glide.with(PastTakenRideDetailsActivity.this).load(rideDetailsListBean.getAccept_user_image()).into(imgProfile);
                tvName.setText(rideDetailsListBean.getAccept_user_name());
                tvRating.setText(rideDetailsListBean.getAccept_user_rating());
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {

                }
            });
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderReceipt{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.RideDetailsBean rideDetailsBean;

        public HolderReceipt(ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.RideDetailsBean rideDetailsBean) {
            this.rideDetailsBean = rideDetailsBean;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+rideDetailsBean.getLeft_text());
                tvRightText.setText(""+rideDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderReceiptBill{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.BillDetailsBean billDetailsBean;

        public HolderReceiptBill(ModelReceiptUser.DataBean.UserReceiptBean.BodyBean.BillDetailsBean billDetailsBean) {
            this.billDetailsBean = billDetailsBean;
        }

        public HolderReceiptBill(ModelReceiptUser.DataBean.UserReceiptBean.FooterBean footer) {
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+billDetailsBean.getLeft_text());
                tvRightText.setText(""+billDetailsBean.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Layout(R.layout.raw_receipt)
    class HolderFooter{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptUser.DataBean.UserReceiptBean.FooterBean footer;

        public HolderFooter(ModelReceiptUser.DataBean.UserReceiptBean.FooterBean footer) {
            this.footer = footer;
        }


        @Resolve
        void setData(){
            try{
                tvLeftText.setText(""+footer.getLeft_text());
                tvRightText.setText(""+footer.getRight_text());
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(PastTakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
}