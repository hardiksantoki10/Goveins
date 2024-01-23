package com.goviens.android.activities;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityRideDetailsBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelProfileDetails;
import com.goviens.android.models.ModelRideDetails;
import com.goviens.android.models.ModelStartRide;
import com.goviens.android.models.ModelTracking;
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

public class RideDetailsActivity extends AppCompatActivity implements ApiManager.APIFETCHER, TextWatcher {


    public static String RIDE_ID;
    ApiManager manager;
    SessionManager sessionManager;
    PlaceHolderView placeHolderRequest;
    PlaceHolderView placeHolderRide;
    android.view.View viewRequest;


    PlaceHolderView placeHolderRiders;


    String cancelReasonId = "null";

    Integer position = 0;

    ModelRideDetails rideDetails;
    ProgressDialog progressDialog;
    PlaceHolderView placeholder_cancel_reason;

    String other_reason = "";
    String from;//0 for upcoming 1 for ongoing

    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;
    String rider = "0";

    ActivityRideDetailsBinding binding;

    RadioButton radioOther;
    EditText etOtherReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRideDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        RIDE_ID = getIntent().getStringExtra("ride_id");
        from = getIntent().getStringExtra("from"); //0 for upcoming 1 for ongoing
        if (from.equals("0")) {
            binding.btnStartRide.setText(getResources().getString(R.string.start_ride));
        } else {
            binding.btnStartRide.setText(getResources().getString(R.string.continue_ride));
            binding.btnCancel.setVisibility(android.view.View.GONE);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        binding.btnStartRide.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if (from.equals("0")) {
                    if(rideDetails.getData().getRequest_users().size() > 0){
                        Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.pending_ride_request), Toast.LENGTH_SHORT).show();
                    }else {
                        if (checkPermission())
                            onLocationPermissionGranted();
                    }
                } else {
                    if (rideDetails.getData().isCan_ride_start()) {
                        try {
                            progressDialog.show();
                            HashMap<String, String> map = new HashMap<>();
                            //map.put("user_id",""+rideDetails.getData().getOffer_user().getId());
                            map.put("ride_id", RIDE_ID);
                            manager._post(API_S.Tags.RIDERS_PICK_DROP, API_S.Endpoints.RIDERS_PICK_DROP, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            if (progressDialog.isShowing()) {
                                progressDialog.hide();
                            }
                        }
                    } else {
                        Toast.makeText(RideDetailsActivity.this, rideDetails.getData().getCan_ride_start_text(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        binding.btnCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
//                AlertDialog.Builder builderSingle = new AlertDialog.Builder(RideDetailsActivity.this);
//                builderSingle.setTitle("Select Reason");
//                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RideDetailsActivity.this, android.R.layout.select_dialog_singlechoice);
//                for (int i = 0; i < rideDetails.getData().getCancel_reason().size(); i++) {
//                    arrayAdapter.add(rideDetails.getData().getCancel_reason().get(i).getReason());
//                }
//                builderSingle.setNegativeButton(RideDetailsActivity.this.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
////                builderSingle.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialogInterface, int i) {
////                        if (cancelReasonId.equals("null")) {
////                            Toast.makeText(RideDetailsActivity.this, "Please select reason", Toast.LENGTH_SHORT).show();
////                        } else {
////                            try {
////                                HashMap<String, String> map = new HashMap<>();
////                                map.put("ride_id", ""+rideDetails.getData().getCarpooling_ride_id());
////                                map.put("cancel_reason_id", cancelReasonId);
////                                manager._post(API_S.Tags.CANCEL_OFFER_RIDE, API_S.Endpoints.CANCEL_OFFER_RIDE,map,sessionManager);
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                                Toast.makeText(RideDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
////                            }
////                            dialogInterface.dismiss();
////                        }
////                    }
////                });
//                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        cancelReasonId = "" + rideDetails.getData().getCancel_reason().get(which).getId();
//                        try {
//                            HashMap<String, String> map = new HashMap<>();
//                            map.put("ride_id", "" + rideDetails.getData().getCarpooling_ride_id());
//                            map.put("cancel_reason_id", cancelReasonId);
//                            manager._post(API_S.Tags.CANCEL_OFFER_RIDE, API_S.Endpoints.CANCEL_OFFER_RIDE, map, sessionManager);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(RideDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                        }
//                        dialog.dismiss();
//                    }
//                });
//                builderSingle.show();
                openDialogForCancel(view);
            }
        });
    }

    void openDialogForCancel(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RideDetailsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_cancel_reasons, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        placeholder_cancel_reason = dialogView.findViewById(R.id.placeholder_cancel_reason);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        radioOther = dialogView.findViewById(R.id.radio_other);
        etOtherReason = dialogView.findViewById(R.id.et_other_reason);
        etOtherReason.setEnabled(false);
        TextView tvCancelAmt = dialogView.findViewById(R.id.tv_cancellation_amt);
        if(rideDetails.getData().getCancel_amount().equals("0")
                || rideDetails.getData().getCancel_amount() == null){
            tvCancelAmt.setVisibility(android.view.View.GONE);
        }else {
            tvCancelAmt.setVisibility(android.view.View.VISIBLE);
            tvCancelAmt.setText(rideDetails.getData().getCancel_amount()+ " "+getResources().getString(R.string.cancellation_fee));
        }
        for (int i = 0; i < rideDetails.getData().getCancel_reason().size(); i++) {
            placeholder_cancel_reason.addView(new HolderCancelReasons(rideDetails.getData().getCancel_reason().get(i)));
        }
        radioOther.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                etOtherReason.setEnabled(true);
                placeholder_cancel_reason.refresh();
                position = null;
            }
        });
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if(radioOther.isChecked()) {
                    if(etOtherReason.getText().toString().equals("")){
                        Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.please_specify_reason), Toast.LENGTH_SHORT).show();
                    }else {
                        try {
                            progressDialog.show();
                            HashMap<String, String> map = new HashMap<>();
                            map.put("ride_id", "" + rideDetails.getData().getCarpooling_ride_id());
                            map.put("other_reason", etOtherReason.getText().toString());
                            manager._post(API_S.Tags.CANCEL_OFFER_RIDE, API_S.Endpoints.CANCEL_OFFER_RIDE, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                } else {
                    if (cancelReasonId.equals("null")) {
                        Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.please_select_reason), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            progressDialog.show();
                            HashMap<String, String> map = new HashMap<>();
                            map.put("ride_id", "" + rideDetails.getData().getCarpooling_ride_id());
                            map.put("cancel_reason_id", cancelReasonId);
                            manager._post(API_S.Tags.CANCEL_OFFER_RIDE, API_S.Endpoints.CANCEL_OFFER_RIDE, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog.show();
        try {
            placeHolderRide.removeAllViews();
            placeHolderRequest.removeAllViews();
            placeHolderRiders.removeAllViews();
            HashMap<String, String> map = new HashMap<>();
            map.put("ride_id", RIDE_ID);
            manager._post(API_S.Tags.RIDE_DETAILS, API_S.Endpoints.RIDE_DETAILS, map, sessionManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void onLocationPermissionGranted() {
        if (!checkPermission()) return;
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            try {
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("latitude", "" + location.getLatitude());
                                map.put("longitude", "" + location.getLongitude());
                                map.put("ride_id", RIDE_ID);
                                manager._post(API_S.Tags.START_RIDE, API_S.Endpoints.START_RIDE, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                                if (progressDialog.isShowing()) {
                                    progressDialog.hide();
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            if (APINAME.equals(API_S.Tags.CANCEL_OFFER_RIDE)) {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                finish();
            } else if (APINAME.equals(API_S.Tags.START_RIDE)) {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                ModelStartRide startRide = SingletonGson.getInstance().fromJson("" + script, ModelStartRide.class);
                if (startRide.getResult().equals("1")) {
                    try {
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        //map.put("user_id",""+rideDetails.getData().getOffer_user().getId());
                        map.put("ride_id", RIDE_ID);
                        manager._post(API_S.Tags.RIDERS_PICK_DROP, API_S.Endpoints.RIDERS_PICK_DROP, map, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(RideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.hide();
                        }
                    }
                }
            } else if (APINAME.equals(API_S.Tags.PROFILE_DETAILS)) {
                ModelProfileDetails profileDetails = SingletonGson.getInstance().fromJson("" + script, ModelProfileDetails.class);
                if (profileDetails.getResult().equals("1")) {
                    startActivity(new Intent(RideDetailsActivity.this, ProfileActivity.class)
                            .putExtra("script", "" + script)
                            .putExtra("for", "0")
                            .putExtra("rider", rider)
                            .putExtra("ride_id", RIDE_ID)); // 0 for user request details
                }
            } else if (APINAME.equals(API_S.Tags.RIDERS_PICK_DROP)) {
                ModelTracking tracking = SingletonGson.getInstance().fromJson("" + script, ModelTracking.class);
                if (tracking.getResult().equals("1")) {
                    startActivity(new Intent(RideDetailsActivity.this, TrackingActivity.class)
                            .putExtra("user_id", "" + rideDetails.getData().getOffer_user().getId())
                            .putExtra("ride_id", RIDE_ID)
                            .putExtra("script", "" + script));
                    finish();
                }
            } else {
                try {
                    progressDialog.hide();
                    rideDetails = SingletonGson.getInstance().fromJson("" + script, ModelRideDetails.class);
                    if (rideDetails.getResult().equals("1")) {
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
                        binding.tvAvailableSeats.setText(""+rideDetails.getData().getAvailable_seats());
                        binding.tvTotalSeats.setText(""+rideDetails.getData().getTotal_seats());
                        binding.tvChargePerSeat.setText(""+rideDetails.getData().getPer_seat_charge());
                        if (rideDetails.getData().getRequest_users().size() != 0) {
                            placeHolderRequest.setVisibility(android.view.View.VISIBLE);
                            binding.tvRequest.setVisibility(android.view.View.VISIBLE);
                            viewRequest.setVisibility(android.view.View.VISIBLE);
                            for (int i = 0; i < rideDetails.getData().getRequest_users().size(); i++) {
                                placeHolderRequest.addView(new HolderRequest(rideDetails.getData().getRequest_users().get(i)));
                            }
                        } else {
                            binding.tvRequest.setVisibility(android.view.View.GONE);
                            viewRequest.setVisibility(android.view.View.GONE);
                            placeHolderRequest.setVisibility(android.view.View.GONE);
                        }
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (progressDialog.isShowing()) {
                progressDialog.hide();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        other_reason = String.valueOf(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

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
    class HolderRequest{
        @View(R.id.image_profile)
        CircleImageView imgProfile;
        @View(R.id.tv_name)
        TextView tvName;
        @View(R.id.tv_rating)
        TextView tvRating;
        @View(R.id.root)
        RelativeLayout root;
        @View(R.id.view)
        android.view.View view;

        @Position
        int mPostion;

        ModelRideDetails.DataBean.RequestUsersBean rideDetailsListBean;

        public HolderRequest(ModelRideDetails.DataBean.RequestUsersBean rideDetailsListBean) {
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData(){
            if(rideDetails.getData().getRequest_users().size() == 1){
                view.setVisibility(android.view.View.GONE);
            }
            tvName.setTextColor(getResources().getColor(R.color.red));
            try {
                Glide.with(RideDetailsActivity.this).load(rideDetailsListBean.getRequest_user_image()).into(imgProfile);
                tvName.setText(rideDetailsListBean.getRequest_user_name());
                tvRating.setText(rideDetailsListBean.getRequest_user_rating());
            }catch (Exception e){
                e.printStackTrace();
            }
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    try{
                        rider = "0";
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("user_id",""+rideDetails.getData().getRequest_users().get(mPostion).getRequest_user_id());
                        map.put("type","user");
                        map.put("ride_id", ""+rideDetails.getData().getCarpooling_ride_id());
                        map.put("carpooling_ride_user_detail_id",""+rideDetails.getData().getRequest_users().get(mPostion).getCarpooling_ride_user_detail_id());
                        manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
                    }catch (Exception e){
                        e.printStackTrace();
                        progressDialog.hide();
                    }
                }
            });
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
        @View(R.id.tv_id)
        TextView tvId;
        @View(R.id.tv_status)
        TextView tvStatus;
        @View(R.id.tv_reason)
        TextView tvReason;
        @View(R.id.label_cancel)
        TextView tvLabelCancel;
        @View(R.id.view)
        android.view.View view;


        @Position
        int mPostion;

        ModelRideDetails.AcceptUsersBean rideDetailsListBean;

        public HolderRiders(ModelRideDetails.AcceptUsersBean rideDetailsListBean) {
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData() {
            try {
                if(rideDetails.getData().getAccept_users().size() == 1){
                    view.setVisibility(android.view.View.GONE);
                }
                tvName.setTextColor(getResources().getColor(R.color.green));
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
                }else {
                    tvStatus.setVisibility(android.view.View.GONE);
                    tvReason.setVisibility(android.view.View.GONE);
                    tvLabelCancel.setVisibility(android.view.View.GONE);
                }
                tvId.setVisibility(android.view.View.VISIBLE);
                tvId.setText(rideDetailsListBean.getUnique_id());
                Glide.with(RideDetailsActivity.this).load(rideDetailsListBean.getAccept_user_image()).into(imgProfile);
                tvName.setText(rideDetailsListBean.getAccept_user_name());
                tvRating.setText(rideDetailsListBean.getAccept_user_rating());
            } catch (Exception e) {
                e.printStackTrace();
            }
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    try {
                        rider = "1";
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("user_id", "" + rideDetails.getData().getAccept_users().get(mPostion).getAccept_user_id());
                        map.put("type", "user");
                        map.put("ride_id", ""+rideDetails.getData().getCarpooling_ride_id());
                        map.put("carpooling_ride_user_detail_id",""+rideDetails.getData().getAccept_users().get(mPostion).getCarpooling_ride_user_detail_id());
                        manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.hide();
                    }
                }
            });
        }
    }

    @Layout(R.layout.raw_cancel_reason)
    class HolderCancelReasons {
        @View(R.id.radio_reason)
        RadioButton radioReason;


        @Position
        int mPostion;
        ModelRideDetails.DataBean.CancelReasonBean cancelReasonBean;

        public HolderCancelReasons(ModelRideDetails.DataBean.CancelReasonBean cancelReasonBean) {
            this.cancelReasonBean = cancelReasonBean;
        }


        @Resolve
        void setData() {
            try {
                if(position == null){
                    radioReason.setChecked(false);
                }else {
                    if (mPostion == position) {
                        cancelReasonId = ""+cancelReasonBean.getId();
                        radioReason.setChecked(true);
                    } else {
                        radioReason.setChecked(false);
                    }
                }
                radioReason.setVisibility(android.view.View.VISIBLE);
                radioReason.setText(cancelReasonBean.getReason());
            } catch (Exception e) {
                e.printStackTrace();
            }
            radioReason.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    etOtherReason.setEnabled(false);
                    if(radioOther.isChecked()){
                        radioOther.setChecked(false);
                    }
                    placeholder_cancel_reason.refresh();
                    position = mPostion;
                    cancelReasonId = ""+cancelReasonBean.getId();
                }
            });
        }
    }
}
