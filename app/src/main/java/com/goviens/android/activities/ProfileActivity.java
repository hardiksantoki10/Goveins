package com.goviens.android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.app.ActivityCompat;


import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.models.ModelProfileDetails;
import com.goviens.android.models.ModelRequest;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    CircleImageView imgProfile;
    AppCompatRatingBar rating;
    RelativeLayout relativeDoc;
    ImageView imgMessage;
    ImageView imgCall;
    TextView tvPhone;
    TextView tvTotalRides;
    TextView tvMemberSince;
    TextView tvDate;
    TextView tvSeat;
    TextView tvAmt;
    TextView tvStartTime;
    TextView tvEndTime;
    TextView tvFrom;
    TextView tvTo;
    TextView tvPaymentMethod;
    LinearLayout linearRideDetails;
    LinearLayout linearBtn;
    Button btnAccept;
    Button btnReject;
    View view;
    TextView tvName;

    ScrollView scrollView;

    ModelProfileDetails profileDetails;
    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        sessionManager = new SessionManager(this);
        manager = new ApiManager(this, this);
        profileDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelProfileDetails.class);
        setData();

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent() != null) {
                    if (getIntent().getStringExtra("driver") != null) {
                        if (!profileDetails.getData().getPhone().equals("")) {
                            if (!AppUtils.hasPermissions(ProfileActivity.this, PERMISSIONS)) {
                                ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSIONS, 1);
                            } else {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + profileDetails.getData().getPhone()));
                                startActivity(intent);
                            }
                        } else {
                            messageDialog(profileDetails.getData().getPhone_number_visiblity());
                        }
                    } else if(getIntent().getStringExtra("search") != null) {
                        messageDialog("");
                    }else if (!profileDetails.getData().getUserdetails().getPhone().equals("")) {
                        if (!AppUtils.hasPermissions(ProfileActivity.this, PERMISSIONS)) {
                            ActivityCompat.requestPermissions(ProfileActivity.this, PERMISSIONS, 1);
                     } else {
                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + profileDetails.getData().getUserdetails().getPhone()));
                            startActivity(intent);
                    }
                    } else {
                        messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                    }
                }
            }
        });

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent() != null) {
                    if (getIntent().getStringExtra("driver") != null) {
                        if (!profileDetails.getData().getPhone().equals("")) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("smsto:" + profileDetails.getData().getPhone())); // This ensures only SMS apps respond
                            startActivity(intent);
                        } else {
                            messageDialog(profileDetails.getData().getPhone_number_visiblity());
                        }
                    }else if(getIntent().getStringExtra("search") != null) {
                        messageDialog("");
                    } else if (!profileDetails.getData().getUserdetails().getPhone().equals("")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("smsto:" + profileDetails.getData().getUserdetails().getPhone())); // This ensures only SMS apps respond
                        startActivity(intent);
                    } else {
                        messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                    }
                }
            }
        });
    }

    void messageDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setCancelable(false);
        if(getIntent() != null) {
            if (getIntent().getStringExtra("search") != null) {
                builder.setMessage(getResources().getString(R.string.cannot_see_contact));
            }else {
                builder.setMessage(message);
            }
        }else {
            builder.setMessage(message);
        }
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
        });
        builder.create().show();
    }
    void setData(){
        try {
            rating.setEnabled(false);
            Glide.with(this).load(profileDetails.getData().getProfileimage()).into(imgProfile);
            rating.setRating(Float.parseFloat(profileDetails.getData().getRating()));
            if(getIntent() != null) {
                if (getIntent().getStringExtra("search") != null) {
                    tvPhone.setText("**********");
                    messageDialog("");
                }else if(getIntent().getStringExtra("driver") != null) {
                    if(profileDetails.getData().getPhone().equals("")){
                        tvPhone.setText("**********");
                        messageDialog(profileDetails.getData().getPhone_number_visiblity());
                    }else {
                        tvPhone.setText("" + profileDetails.getData().getPhone());
                    }
                }else {
                    if(profileDetails.getData().getUserdetails().getPhone().equals("")){
                        tvPhone.setText("**********");
                        messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                    }else {
                        tvPhone.setText("" + profileDetails.getData().getUserdetails().getPhone());
                    }
                }
            }else {
                if(profileDetails.getData().getUserdetails().getPhone().equals("")){
                    tvPhone.setText("**********");
                    messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                }else {
                    tvPhone.setText("" + profileDetails.getData().getUserdetails().getPhone());
                }
            }
            tvTotalRides.setText(""+profileDetails.getData().getTotal_rides());
            tvName.setText(profileDetails.getData().getName());
            tvMemberSince.setText(AppUtils.getDateWithYearViaTimestamp(""+profileDetails.getData().getMember_since()));
            if(profileDetails.getData().getDocument_verification_status() == 1){
                relativeDoc.setVisibility(View.VISIBLE);
            }else {
                relativeDoc.setVisibility(View.GONE);
            }
            if(getIntent().getStringExtra("for")!= null) {
                if (getIntent().getStringExtra("for").equals("0")) {
                    linearRideDetails.setVisibility(View.VISIBLE);
                    linearBtn.setVisibility(View.VISIBLE);
                    view.setVisibility(View.VISIBLE);
                    tvDate.setText(AppUtils.getDateTimeInDayFormat(profileDetails.getData().getUserdetails().getStart_timestamp()));
                    tvSeat.setText(profileDetails.getData().getUserdetails().getNo_of_seats() + " "+getResources().getString(R.string.seats));
                    tvAmt.setText(profileDetails.getData().getUserdetails().getTotal_amount());
                    tvStartTime.setText(AppUtils.getTimeViaTimestamp(profileDetails.getData().getUserdetails().getStart_timestamp()));
                    tvEndTime.setText(AppUtils.getTimeViaTimestamp(profileDetails.getData().getUserdetails().getEnd_timestamp()));
                    tvFrom.setText(profileDetails.getData().getUserdetails().getStart_location());
                    tvTo.setText(profileDetails.getData().getUserdetails().getDrop_location());
                    if (profileDetails.getData().getUserdetails().getPayment_type() == 2) {
                        tvPaymentMethod.setText(getResources().getString(R.string.cash));
                    } else if(profileDetails.getData().getUserdetails().getPayment_type() == 3) {
                        tvPaymentMethod.setText(getResources().getString(R.string.wallet_at_pickup));
                    }else {
                        tvPaymentMethod.setText(getResources().getString(R.string.wallet));
                    }
                    if (getIntent().getStringExtra("rider").equals("1")) {
                        linearBtn.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    } else {
                        linearBtn.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                    }
                    btnAccept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("carpooling_ride_user_detail_id", "" + profileDetails.getData().getUserdetails().getId());
                                map.put("action", "accept");
                                map.put("ride_id", getIntent().getStringExtra("ride_id"));
                                manager._post(API_S.Tags.REQUEST, API_S.Endpoints.REQUEST, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.hide();
                                Toast.makeText(ProfileActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    btnReject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                progressDialog.show();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("carpooling_ride_user_detail_id", "" + profileDetails.getData().getUserdetails().getId());
                                map.put("action", "reject");
                                map.put("ride_id", getIntent().getStringExtra("ride_id"));
                                manager._post(API_S.Tags.REQUEST, API_S.Endpoints.REQUEST, map, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                                progressDialog.hide();
                                Toast.makeText(ProfileActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    linearRideDetails.setVisibility(View.GONE);
                    linearBtn.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                }
            }else {
                linearRideDetails.setVisibility(View.GONE);
                linearBtn.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
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
        progressDialog.hide();
        ModelRequest request = SingletonGson.getInstance().fromJson(""+script, ModelRequest.class);
        Toast.makeText(this, ""+request.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}