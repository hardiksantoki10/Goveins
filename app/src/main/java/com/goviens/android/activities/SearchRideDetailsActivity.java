package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivitySearchRideDetailsBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelCheckout;
import com.goviens.android.models.ModelConfirm;
import com.goviens.android.models.ModelProfileDetails;
import com.goviens.android.models.ModelSearchDetails;
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

public class SearchRideDetailsActivity extends AppCompatActivity implements ApiManager.APIFETCHER{


    ApiManager manager;
    SessionManager sessionManager;
    ModelSearchDetails searchDetails;
    PlaceHolderView placeHolderRiders;

    String cashAmt;
    ProgressDialog progressDialog;
    String payment_action = "0", payment_status = "0";

    ActivitySearchRideDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchRideDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        cashAmt = getIntent().getStringExtra("payment_type_id");

        binding.tvLabelTotalPrice.setText(getResources().getString(R.string.total_price_for)+" "+getIntent().getStringExtra("no_of_seats")+" "+getResources().getString(R.string.seats));
        binding.btnConfirmBooking.setText(getResources().getString(R.string.confirm_booking_for)+" "+getIntent().getStringExtra("no_of_seats")+" "+getResources().getString(R.string.seats));
        searchDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelSearchDetails.class);
        setData();
        binding.linearDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("user_id",""+searchDetails.getData().getOffer_user().getId());
                    map.put("type","driver");
                    map.put("ride id",""+searchDetails.getData().getCarpooling_ride_id());
                    //map.put("carpooling_ride_user_detail_id",""+searchDetails.getData().getAccept_users().get(0).getCarpooling_ride_user_detail_id());
                    manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(SearchRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.tvPaymentMethodSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(SearchRideDetailsActivity.this,PaymentMethodActivity.class)
                        .putExtra("cashAmt", cashAmt)
                        .putExtra("balance",searchDetails.getData().getWallet_balance()), 100);
            }
        });
        binding.btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payment_action.equals("0")){
                    Toast.makeText(SearchRideDetailsActivity.this, getResources().getString(R.string.select_payment_method), Toast.LENGTH_SHORT).show();
                }else {
                    createBooking();
                }
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    void createBooking(){
        try{
            progressDialog.show();
            HashMap<String, String> map = new HashMap<>();
            map.put("carpooling_ride_id",""+searchDetails.getData().getCarpooling_ride_id());
            map.put("pickup_id",getIntent().getStringExtra("pickup_id"));
            map.put("drop_id",getIntent().getStringExtra("drop_id"));
            map.put("segment_id","15");
            map.put("pickup_latitude",getIntent().getStringExtra("pickup_latitude"));
            map.put("pickup_longitude",getIntent().getStringExtra("pickup_longitude"));
            map.put("pickup_location",getIntent().getStringExtra("pickup_location"));
            map.put("drop_latitude",getIntent().getStringExtra("drop_latitude"));
            map.put("drop_longitude",getIntent().getStringExtra("drop_longitude"));
            map.put("drop_location",getIntent().getStringExtra("drop_location"));
            map.put("no_of_seats",getIntent().getStringExtra("no_of_seats"));
            map.put("ride_timestamp",getIntent().getStringExtra("ride_timestamp"));
            map.put("ac_ride",getIntent().getStringExtra("ac_ride"));
            map.put("female_ride",getIntent().getStringExtra("female_ride"));
            map.put("payment_action",payment_action);
            manager._post(API_S.Tags.CHECKOUT, API_S.Endpoints.CHECKOUT,map,sessionManager);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            progressDialog.hide();
        }
    }

    void setData(){
        try {
            if (searchDetails.getData().isAc_ride()) {
                binding.linearAc.setVisibility(View.VISIBLE);
            } else {
                binding.linearAc.setVisibility(View.GONE);
            }
            if (searchDetails.getData().isOnly_females()) {
                binding.linearFemale.setVisibility(View.VISIBLE);
            } else {
                binding.linearFemale.setVisibility(View.GONE);
            }
            if (searchDetails.getData().getAccept_users().size() != 0) {
                binding.linearRiders.setVisibility(android.view.View.VISIBLE);
                for (int i = 0; i < searchDetails.getData().getAccept_users().size(); i++) {
                    placeHolderRiders.addView(new HolderRiders(searchDetails.getData().getAccept_users().get(i)));
                }
            }
            binding.tvTotalSeats.setText(""+ searchDetails.getData().getTotal_seats());
            binding.tvAvailableSeats.setText(""+searchDetails.getData().getAvailable_seats());
            binding.tvDt.setText(AppUtils.getDateTimeInFormat(searchDetails.getData().getRide_timestamp()));
            binding.tvAmt.setText(getIntent().getStringExtra("amt"));
            binding.tvStartTime.setText(AppUtils.getTimeViaTimestamp(searchDetails.getData().getRide_details_list().get(0).getRide_timestamp()));
            binding.tvEndTime.setText(AppUtils.getTimeViaTimestamp(searchDetails.getData().getRide_details_list().get(1).getRide_timestamp()));
            binding.tvFrom.setText(searchDetails.getData().getRide_details_list().get(0).getLocation());
            binding.tvTo.setText(searchDetails.getData().getRide_details_list().get(1).getLocation());
            Glide.with(this).load(searchDetails.getData().getOffer_user().getImage()).into(binding.imgProfile);

            // Need to check if data not Display Hardik
            ///binding.tvDriverName.setText(searchDetails.getData().getOffer_user().getName());
            ///binding.tvDriverRating.setText(searchDetails.getData().getOffer_user().getRating());
            binding.tvPaymentMethod.setText(searchDetails.getData().getPayment_type());
            Glide.with(this).load(searchDetails.getData().getOffer_user_vehicle().getVehicle_image()).into(binding.imgVehicle);
            binding.tvVehicleName.setText(searchDetails.getData().getOffer_user_vehicle().getVehicle_name());
            binding.tvVehicleNumber.setText(searchDetails.getData().getOffer_user_vehicle().getVehicle_number());
            binding.tvVehicleColor.setText(searchDetails.getData().getOffer_user_vehicle().getVehicle_color());
            binding.tvInstruction.setText(searchDetails.getData().getInstructions());
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            payment_action = data.getStringExtra("payment");
            if(payment_action.equals("1")){
                binding.tvPaymentMethodSelection.setText(getResources().getString(R.string.wallet));
                payment_status = "1";
            }else if(payment_action.equals("2")){
                binding.tvPaymentMethodSelection.setText(getResources().getString(R.string.cash));
                payment_status = "0";
            }else if(payment_action.equals("3")){
                binding.tvPaymentMethodSelection.setText(getResources().getString(R.string.wallet_at_pickup));
                payment_status = "0";
            }
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        progressDialog.hide();
        if(APINAME.equals(API_S.Tags.PROFILE_DETAILS)){
            ModelProfileDetails profileDetails = SingletonGson.getInstance().fromJson(""+script, ModelProfileDetails.class);
            if(profileDetails.getResult().equals("1")) {
                startActivity(new Intent(SearchRideDetailsActivity.this, ProfileActivity.class)
                        .putExtra("script", "" + script)
                        .putExtra("search","1"));
            }
        }else if(APINAME.equals(API_S.Tags.CHECKOUT)){
            try {
                progressDialog.hide();
                ModelCheckout checkout = SingletonGson.getInstance().fromJson("" + script, ModelCheckout.class);
                if (checkout.getResult().equals("1")) {
                    progressDialog.show();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("carpooling_ride_checkout_id",""+checkout.getData().getCarpooling_ride_checkout_id());
                    map.put("payment_action",payment_action);
                    map.put("payment_status",payment_status);
                    manager._post(API_S.Tags.CONFIRM, API_S.Endpoints.CONFIRM,map,sessionManager);
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }else if(APINAME.equals(API_S.Tags.CONFIRM)){
            try {
                progressDialog.hide();
                ModelConfirm confirm = SingletonGson.getInstance().fromJson("" + script, ModelConfirm.class);
                if (confirm.getResult().equals("1")) {
                    Toast.makeText(this, "" + confirm.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SearchRideDetailsActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
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

        ModelSearchDetails.DataBean.AcceptUsersBean acceptUsersBean;

        public HolderRiders(ModelSearchDetails.DataBean.AcceptUsersBean acceptUsersBean) {
            this.acceptUsersBean = acceptUsersBean;
        }

        @Resolve
        void setData() {
            if(searchDetails.getData().getAccept_users().size() == 1){
                view.setVisibility(View.GONE);
            }
            imgIndicator.setVisibility(View.GONE);
            try {
                Glide.with(SearchRideDetailsActivity.this).load(acceptUsersBean.getAccept_user_image()).into(imgProfile);
                tvName.setText(acceptUsersBean.getAccept_user_name());
                tvRating.setText(""+acceptUsersBean.getAccept_user_rating());
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    // try {
//                        progressDialog.show();
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("user_id", "" + rideDetails.getData().get(0).getAccept_users().get(mPostion).getAccept_user_id());
//                        map.put("type", "user");
//                        manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        progressDialog.hide();
//                    }
                }
            });
        }
    }
}
