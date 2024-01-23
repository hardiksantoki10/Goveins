package com.goviens.android.activities;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.databinding.ActivityTrackingBinding;
import com.goviens.android.models.ModelReceiptDriver;
import com.goviens.android.models.ModelRideOTP;
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

public class TrackingActivity extends AppCompatActivity implements ApiManager.APIFETCHER, TextWatcher {


    PlaceHolderView placeHolderRiders;
    SwipeRefreshLayout swipeRefreshLayout;

    boolean rating = false;

    ProgressDialog progressDialog;
    ModelReceiptDriver receiptUser;

    ApiManager manager;
    SessionManager sessionManager;
    ModelTracking tracking;

    android.view.View view;
    String carpooling_ride_user_detail_id;
    boolean rideEnd = true;
    boolean checked = true;
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;
    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};

    PlaceHolderView placeholder_cancel_reason;
    Integer position = 0;

    String cancelReasonId = "null";


    ActivityTrackingBinding binding;

    EditText etOTPfirst, etOTPsecond, etOTPthird, etOTPfourth;
    RadioButton radioOther;
    EditText etOtherReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTrackingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        tracking = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelTracking.class);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        setData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callRidersApi();
            }
        });
        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                finish();
            }
        });
        binding.btnReached.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                for (int i = 0; i < tracking.getData().getActive_User_Ride().getPickup_users().size(); i++) {
                    if (rideEnd) {
                        if (tracking.getData().getActive_User_Ride().getPickup_users().get(i).getRide_status().equals("1")
                                || tracking.getData().getActive_User_Ride().getPickup_users().get(i).getRide_status().equals("2")
                                || tracking.getData().getActive_User_Ride().getPickup_users().get(i).getRide_status().equals("3")) {
                            rideEnd = false;
                        } else {
                            rideEnd = true;
                        }
                    }
                }
                if (!rideEnd) {
                    Toast.makeText(TrackingActivity.this, getResources().getString(R.string.end_ride_of_all_riders), Toast.LENGTH_SHORT).show();
                } else {
                    if (checkPermission())
                        onLocationPermissionGranted();
                }
            }
        });

        binding.btnNavigation.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="
                                + tracking.getData().getActive_User_Ride().getRide_details_list().get(1).getTo_latitude()
                                + "," + tracking.getData().getActive_User_Ride().getRide_details_list().get(1).getTo_longitude()));
                startActivity(intent);
            }
        });
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
                                placeHolderRiders.removeAllViews();
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("latitude", "" + location.getLatitude());
                                map.put("longitude", "" + location.getLongitude());
                                //map.put("user_id",""+rideDetails.getData().getOffer_user().getId());
                                map.put("ride_id", getIntent().getStringExtra("ride_id"));
                                manager._post(API_S.Tags.END_RIDE, API_S.Endpoints.END_RIDE, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.hide();
                            }
                        }
                    }
                });
    }


    void setData() {
        try {
            binding.tvFrom.setText(tracking.getData().getActive_User_Ride().getRide_details_list().get(0).getFrom_location());
            binding.tvTo.setText(tracking.getData().getActive_User_Ride().getRide_details_list().get(1).getTo_location());
            for (int i = 0; i < tracking.getData().getActive_User_Ride().getPickup_users().size(); i++) {
                placeHolderRiders.addView(new HolderRiders(tracking.getData().getActive_User_Ride().getPickup_users().get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {
        try {
            if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
                swipeRefreshLayout.setRefreshing(true);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        } catch (Exception e) {

        }
    }


    void callRidersApi() {
        try {
            rideEnd = true;
            placeHolderRiders.removeAllViews();
            progressDialog.show();
            HashMap<String, String> map = new HashMap<>();
            //map.put("user_id",""+rideDetails.getData().getOffer_user().getId());
            map.put("ride_id", getIntent().getStringExtra("ride_id"));
            manager._post(API_S.Tags.RIDERS_PICK_DROP, API_S.Endpoints.RIDERS_PICK_DROP, map, sessionManager);
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.hide();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        switch (APINAME) {
            case API_S.Tags.RIDERS_PICK_DROP:
                progressDialog.hide();
                tracking = SingletonGson.getInstance().fromJson("" + script, ModelTracking.class);
                if (tracking.getResult().equals("1")) {
                    for (int i = 0; i < tracking.getData().getActive_User_Ride().getPickup_users().size(); i++) {
                        placeHolderRiders.addView(new HolderRiders(tracking.getData().getActive_User_Ride().getPickup_users().get(i)));
                    }
                }
                break;
            case API_S.Tags.RIDE_OTP:
                try {
                    progressDialog.hide();
                    ModelRideOTP rideOTP = SingletonGson.getInstance().fromJson("" + script, ModelRideOTP.class);
                    Toast.makeText(this, rideOTP.getMessage(), Toast.LENGTH_SHORT).show();
                    callRidersApi();
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                break;
            case API_S.Tags.ONGOING_RIDE_CANCEL:
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    callRidersApi();
                } catch (Exception e) {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    e.printStackTrace();
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                break;
            case API_S.Tags.RIDE_END_USER:
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    openDialogForRating(view, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                break;
            case API_S.Tags.RATING_USER:
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    callRidersApi();
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                break;
            case API_S.Tags.END_RIDE:
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("ride_id", "" + getIntent().getStringExtra("ride_id"));
                    manager._post(API_S.Tags.RECEIPT_DRIVER, API_S.Endpoints.RECEIPT_DRIVER, map, sessionManager);
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.hide();
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
                break;
            case API_S.Tags.RECEIPT_DRIVER:
                try {
                    receiptUser = SingletonGson.getInstance().fromJson("" + script, ModelReceiptDriver.class);
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                    startActivity(new Intent(TrackingActivity.this, ReceiptActivity.class)
                            .putExtra("script", "" + script)
                            .putExtra("user_receipt", "0"));
                    finish();
                    //openDialogForRating(view, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
        Toast.makeText(this, script, Toast.LENGTH_SHORT).show();
    }


    void openDialogForRating(android.view.View v, int from) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TrackingActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        TextView tvComments = dialogView.findViewById(R.id.comments);
        LinearLayout btnDone = dialogView.findViewById(R.id.lldone);
        LinearLayout btnDoneReceipt = dialogView.findViewById(R.id.lldone_receipt);
        LinearLayout linearReceipt = dialogView.findViewById(R.id.linear_receipt);
        LinearLayout linearRating = dialogView.findViewById(R.id.linear_rating);
        PlaceHolderView placeHolderView = dialogView.findViewById(R.id.placeHolder_receipt);
        if (from == 0) {
            linearReceipt.setVisibility(android.view.View.GONE);
            linearRating.setVisibility(android.view.View.VISIBLE);
        } else {
            linearReceipt.setVisibility(android.view.View.VISIBLE);
            linearRating.setVisibility(android.view.View.GONE);
            for (int i = 0; i < receiptUser.getData().getDriver_Receipt().getBody().getRide_details().size(); i++) {
                placeHolderView.addView(new HolderReceipt(receiptUser.getData().getDriver_Receipt().getBody().getRide_details().get(i)));
            }
            for (int i = 0; i < receiptUser.getData().getDriver_Receipt().getBody().getBill_details().size(); i++) {
                placeHolderView.addView(new HolderReceiptBill(receiptUser.getData().getDriver_Receipt().getBody().getBill_details().get(i)));
            }
            placeHolderView.addView(new HolderFooter(receiptUser.getData().getDriver_Receipt().getFooter()));

        }
        btnDoneReceipt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.ride_completed), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TrackingActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        btnDone.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (tvComments.getText().toString().equals("")) {
                        Toast.makeText(TrackingActivity.this, getResources().getString(R.string.add_comments), Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("ride_id", "" + getIntent().getStringExtra("ride_id"));
                        map.put("carpooling_ride_user_detail_id", carpooling_ride_user_detail_id);
                        map.put("rating", "" + ratingBar.getRating());
                        map.put("comment", tvComments.getText().toString());
                        rating = true;
                        manager._post(API_S.Tags.RATING_USER, API_S.Endpoints.RATING_USER, map, sessionManager);
                        alertDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    progressDialog.hide();
                }
            }
        });
        alertDialog.show();
    }


    void openDialogForOTP(android.view.View v, String userId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TrackingActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_otp, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        Button btnApply = dialogView.findViewById(R.id.btn_ok);
        etOTPfirst = dialogView.findViewById(R.id.etOTPfirst);
        etOTPsecond = dialogView.findViewById(R.id.etOTPsecond);
        etOTPthird = dialogView.findViewById(R.id.etOTPthird);
        etOTPfourth = dialogView.findViewById(R.id.etOTPfourth);
        etOTPfirst.addTextChangedListener(this);
        etOTPsecond.addTextChangedListener(this);
        etOTPthird.addTextChangedListener(this);
        etOTPfourth.addTextChangedListener(this);
        btnApply.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (etOTPfirst.getText().toString().equals("") || etOTPsecond.getText().toString().equals("")
                        || etOTPthird.getText().toString().equals("") || etOTPfourth.getText().toString().equals("")) {
                    Toast.makeText(TrackingActivity.this, getResources().getString(R.string.enter_correct_otp), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("user_ride_id", userId);
                        // map.put("ride_id",""+tracking.getData().getRide_details().get(0).getId());
                        map.put("otp", etOTPfirst.getText().toString() + etOTPsecond.getText().toString() + etOTPthird.getText().toString() + etOTPfourth.getText().toString());
                        manager._post(API_S.Tags.RIDE_OTP, API_S.Endpoints.RIDE_OTP, map, sessionManager);
                        alertDialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                        progressDialog.hide();
                    }
                }
            }
        });
        alertDialog.show();
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
            if (etOTPfirst.length() == 1) {
                etOTPsecond.requestFocus();
            }

            if (etOTPsecond.length() == 1) {
                etOTPthird.requestFocus();
            }
            if (etOTPthird.length() == 1) {
                etOTPfourth.requestFocus();
            }
        } else if (s.length() == 0) {
            if (etOTPfourth.length() == 0) {
                etOTPthird.requestFocus();
            }
            if (etOTPthird.length() == 0) {
                etOTPsecond.requestFocus();
            }
            if (etOTPsecond.length() == 0) {
                etOTPfirst.requestFocus();
            }
        }
    }

    @Layout(R.layout.raw_rider)
    class HolderRiders {

        @View(R.id.img_profile)
        CircleImageView imgProfile;
        @View(R.id.tv_name)
        TextView tvName;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.tv_from)
        TextView tvFrom;
        @View(R.id.tv_to)
        TextView tvTo;
        @View(R.id.btn_otp)
        Button btnOtp;
        @View(R.id.btn_status)
        Button btnStatus;
        @View(R.id.tv_from_time)
        TextView tvFromTime;
        @View(R.id.tv_to_time)
        TextView tvToTime;
        @View(R.id.btn_cancel)
        Button btnCancel;
        @View(R.id.btn_end_ride)
        Button btnEndRide;
        @View(R.id.textViewOptions)
        TextView tvOptions;
        @View(R.id.linear_location)
        LinearLayout linearLocation;
        @View(R.id.tv_dt_time)
        TextView tvDateTime;
        @View(R.id.tv_seat)
        TextView tvSeat;
        @View(R.id.tv_payment_method)
        TextView tvPaymentMethod;
        @View(R.id.tv_id)
        TextView tvId;
        ModelTracking.DataBean.ActiveUserRideBean.PickupUsersBean userDataBean;

        @Position
        int mPos;

        public HolderRiders(ModelTracking.DataBean.ActiveUserRideBean.PickupUsersBean userDataBean) {
            this.userDataBean = userDataBean;
        }

        @Resolve
        void setData() {
            try {
                linearLocation.setVisibility(android.view.View.GONE);
                tvDateTime.setText(AppUtils.getDateTimeInFormat(userDataBean.getRide_time()));
                tvSeat.setText(userDataBean.getNo_of_seats());
                tvId.setText(userDataBean.getUnique_id());
                if (userDataBean.getPayment_action() == 2) {
                    tvPaymentMethod.setText(getResources().getString(R.string.cash));
                } else if (userDataBean.getPayment_action() == 3) {
                    tvPaymentMethod.setText(getResources().getString(R.string.wallet_at_pickup));
                } else {
                    tvPaymentMethod.setText(getResources().getString(R.string.paid_by_wallet));
                }
                Glide.with(TrackingActivity.this).load(userDataBean.getPickup_user_image()).into(imgProfile);
                tvName.setText(userDataBean.getPickup_user_name());
                tvAmt.setText(userDataBean.getFinal_amount());
                tvFrom.setText(userDataBean.getStart_location());
                tvTo.setText(userDataBean.getEnd_location());
                tvFromTime.setText(AppUtils.getTimeViaTimestamp(userDataBean.getRide_time()));
                tvToTime.setText(AppUtils.getTimeViaTimestamp(userDataBean.getEnd_ride_time()));
                if (userDataBean.getRide_status().equals("3")) {
                    btnStatus.setText(getResources().getString(R.string.ongoing));
                } else if (userDataBean.getRide_status().equals("4")) {
                    btnStatus.setText(getResources().getString(R.string.completed));
                } else if (userDataBean.getRide_status().equals("5")) {
                    btnStatus.setText(getResources().getString(R.string.cancelled));
                } else if (userDataBean.getRide_status().equals("2")) {
                    btnStatus.setText(getResources().getString(R.string.booked));
                } else {
                    btnStatus.setVisibility(android.view.View.GONE);
                }
                if (userDataBean.getRide_status().equals("2")) {
                    btnCancel.setVisibility(android.view.View.GONE); //should be visible
                } else {
                    btnCancel.setVisibility(android.view.View.GONE);
                }
                if (Integer.parseInt(userDataBean.getRide_status()) == 3) {
                    btnEndRide.setVisibility(android.view.View.VISIBLE);
                } else {
                    btnEndRide.setVisibility(android.view.View.GONE);
                }
                if (userDataBean.getRide_status().equals("2")) {
                    btnOtp.setVisibility(android.view.View.VISIBLE);
                } else {
                    btnOtp.setVisibility(android.view.View.GONE);
                }
                btnOtp.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        openDialogForOTP(v, "" + userDataBean.getPickup_user_id());
                    }
                });
                btnEndRide.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        view = v;
                        carpooling_ride_user_detail_id = "" + userDataBean.getPickup_user_id();
                        try {
                            progressDialog.show();
                            HashMap<String, String> map = new HashMap<>();
                            map.put("carpooling_ride_user_detail_id", "" + userDataBean.getPickup_user_id());
                            //map.put("action", "end");
                            map.put("ride_id", getIntent().getStringExtra("ride_id"));
                            manager._post(API_S.Tags.RIDE_END_USER, API_S.Endpoints.RIDE_END_USER, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
                tvOptions.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        PopupMenu popup = new PopupMenu(TrackingActivity.this, tvOptions);
                        //inflating menu from xml resource
                        if (userDataBean.getRide_status().equals("1")
                                || userDataBean.getRide_status().equals("2")) {
                            popup.inflate(R.menu.menu_tracking_cancel);
                        } else {
                            popup.inflate(R.menu.menu_tracking);
                        }
                        //adding click listener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_call:
                                        if (!AppUtils.hasPermissions(TrackingActivity.this, PERMISSIONS)) {
                                            ActivityCompat.requestPermissions(TrackingActivity.this, PERMISSIONS, 1);
                                        } else {
                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + userDataBean.getUserPhone()));
                                            startActivity(intent);
                                        }
                                        break;
                                    case R.id.menu_message:
                                        Intent intentMessage = new Intent(Intent.ACTION_VIEW);
                                        intentMessage.setData(Uri.parse("smsto:" + userDataBean.getUserPhone())); // This ensures only SMS apps respond
                                        startActivity(intentMessage);
                                        break;
                                    case R.id.menu_cancel:
//                                        androidx.appcompat.app.AlertDialog.Builder builderSingle = new androidx.appcompat.app.AlertDialog.Builder(TrackingActivity.this);
//                                        builderSingle.setTitle("Select Reason");
//                                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TrackingActivity.this, android.R.layout.select_dialog_singlechoice);
//                                        for (int i = 0; i < tracking.getData().getActive_User_Ride().getCancel_reason().size(); i++) {
//                                            arrayAdapter.add(tracking.getData().getActive_User_Ride().getCancel_reason().get(i).getReason());
//                                        }
//                                        builderSingle.setNegativeButton(TrackingActivity.this.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                cancelReasonId = "" + tracking.getData().getActive_User_Ride().getCancel_reason().get(which).getId();
//                                                try {
//                                                    HashMap<String, String> map = new HashMap<>();
//                                                    map.put("ride_id", "" + tracking.getData().getActive_User_Ride().getCarpooling_ride_id());
//                                                    map.put("cancel_reason_id", cancelReasonId);
//                                                    manager._post(API_S.Tags.CANCEL_OFFER_RIDE, API_S.Endpoints.CANCEL_OFFER_RIDE, map, sessionManager);
//                                                } catch (Exception e) {
//                                                    e.printStackTrace();
//                                                    Toast.makeText(TrackingActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                                                }
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                        builderSingle.show();
                                        if (userDataBean.getRide_status().equals("1")
                                                || userDataBean.getRide_status().equals("2")) {
                                            openDialogForCancel(v, mPos);
                                        } else {
                                            Toast.makeText(TrackingActivity.this, "You can not cancel ride, If it is started.", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                }
                                return false;
                            }
                        });
                        //displaying the popup
                        popup.show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }

        void openDialogForCancel(android.view.View v, int pos) {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TrackingActivity.this);
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
            for (int i = 0; i < tracking.getData().getActive_User_Ride().getCancel_reason().size(); i++) {
                placeholder_cancel_reason.addView(new HolderCancelReasons(tracking.getData().getActive_User_Ride().getCancel_reason().get(i)));
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
                    if (radioOther.isChecked()) {
                        if (etOtherReason.getText().toString().equals("")) {
                            Toast.makeText(TrackingActivity.this, getResources().getString(R.string.please_specify_reason), Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("ride_id", "" + tracking.getData().getActive_User_Ride().getCarpooling_ride_id());
                                map.put("carpooling_ride_user_detail_id", "" + tracking.getData().getActive_User_Ride().getPickup_users().get(pos).getPickup_user_id());
                                map.put("other_reason", etOtherReason.getText().toString());
                                map.put("current_time", ts);
                                manager._post(API_S.Tags.ONGOING_RIDE_CANCEL, API_S.Endpoints.ONGOING_RIDE_CANCEL, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();
                        }
                    } else {
                        if (cancelReasonId.equals("null")) {
                            Toast.makeText(TrackingActivity.this, getResources().getString(R.string.please_select_reason), Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                Long tsLong = System.currentTimeMillis() / 1000;
                                String ts = tsLong.toString();
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("ride_id", "" + tracking.getData().getActive_User_Ride().getCarpooling_ride_id());
                                map.put("carpooling_ride_user_detail_id", "" + tracking.getData().getActive_User_Ride().getPickup_users().get(pos).getPickup_user_id());
                                map.put("cancel_reason_id", cancelReasonId);
                                map.put("current_time", ts);
                                manager._post(API_S.Tags.ONGOING_RIDE_CANCEL, API_S.Endpoints.ONGOING_RIDE_CANCEL, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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
    }

    @Layout(R.layout.raw_cancel_reason)
    class HolderCancelReasons {
        @View(R.id.radio_reason)
        RadioButton radioReason;


        @Position
        int mPostion;
        ModelTracking.DataBean.ActiveUserRideBean.CancelReasonBean cancelReasonBean;

        public HolderCancelReasons(ModelTracking.DataBean.ActiveUserRideBean.CancelReasonBean cancelReasonBean) {
            this.cancelReasonBean = cancelReasonBean;
        }


        @Resolve
        void setData() {
            try {
                if (position == null) {
                    radioReason.setChecked(false);
                } else {
                    if (mPostion == position) {
                        cancelReasonId = "" + cancelReasonBean.getId();
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
                    if (radioOther.isChecked()) {
                        radioOther.setChecked(false);
                    }
                    placeholder_cancel_reason.refresh();
                    position = mPostion;
                    cancelReasonId = "" + cancelReasonBean.getId();
                }
            });
        }
    }

    @Layout(R.layout.raw_receipt)
    class HolderReceipt {
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean;

        public HolderReceipt(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.RideDetailsBean rideDetailsBean) {
            this.rideDetailsBean = rideDetailsBean;
        }


        @Resolve
        void setData() {
            try {
                tvLeftText.setText("" + rideDetailsBean.getLeft_text());
                tvRightText.setText("" + rideDetailsBean.getRight_text());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Layout(R.layout.raw_receipt)
    class HolderReceiptBill {
        @View(R.id.tv_left_text)
        TextView tvLeftText;
        @View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean;

        public HolderReceiptBill(ModelReceiptDriver.DataBean.DriverReceiptBean.BodyBean.BillDetailsBean billDetailsBean) {
            this.billDetailsBean = billDetailsBean;
        }


        @Resolve
        void setData() {
            try {
                tvLeftText.setText("" + billDetailsBean.getLeft_text());
                tvRightText.setText("" + billDetailsBean.getRight_text());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Layout(R.layout.raw_receipt)
    class HolderFooter {
        @com.mindorks.placeholderview.annotations.View(R.id.tv_left_text)
        TextView tvLeftText;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_right_text)
        TextView tvRightText;

        ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer;

        public HolderFooter(ModelReceiptDriver.DataBean.DriverReceiptBean.FooterBean footer) {
            this.footer = footer;
        }


        @Resolve
        void setData() {
            try {
                tvLeftText.setText("" + footer.getLeft_text());
                tvRightText.setText("" + footer.getRight_text());
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(TrackingActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }
}