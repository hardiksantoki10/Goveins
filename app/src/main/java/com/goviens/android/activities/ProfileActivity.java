package com.goviens.android.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.app.ActivityCompat;


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
import com.goviens.android.databinding.ActivityProfileBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelProfileDetails;
import com.goviens.android.models.ModelRequest;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    View view;


    ModelProfileDetails profileDetails;
    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};

    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setContentView(R.layout.activity_profile);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        sessionManager = new SessionManager(this);
        manager = new ApiManager(this, this);
        profileDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelProfileDetails.class);
        setData();

        binding.imgCall.setOnClickListener(new View.OnClickListener() {
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

        binding.imgMessage.setOnClickListener(new View.OnClickListener() {
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
            binding.rating.setEnabled(false);
            Glide.with(this).load(profileDetails.getData().getProfileimage()).into(binding.profileImg);
            binding.rating.setRating(Float.parseFloat(profileDetails.getData().getRating()));
            if(getIntent() != null) {
                if (getIntent().getStringExtra("search") != null) {
                    binding.tvPhone.setText("**********");
                    messageDialog("");
                }else if(getIntent().getStringExtra("driver") != null) {
                    if(profileDetails.getData().getPhone().equals("")){
                        binding.tvPhone.setText("**********");
                        messageDialog(profileDetails.getData().getPhone_number_visiblity());
                    }else {
                        binding.tvPhone.setText("" + profileDetails.getData().getPhone());
                    }
                }else {
                    if(profileDetails.getData().getUserdetails().getPhone().equals("")){
                        binding.tvPhone.setText("**********");
                        messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                    }else {
                        binding.tvPhone.setText("" + profileDetails.getData().getUserdetails().getPhone());
                    }
                }
            }else {
                if(profileDetails.getData().getUserdetails().getPhone().equals("")){
                    binding.tvPhone.setText("**********");
                    messageDialog(profileDetails.getData().getUserdetails().getPhone_number_visiblity());
                }else {
                    binding.tvPhone.setText("" + profileDetails.getData().getUserdetails().getPhone());
                }
            }
            binding.tvTotalRides.setText(""+profileDetails.getData().getTotal_rides());
            binding.tvName.setText(profileDetails.getData().getName());
            binding.tvMemberSince.setText(AppUtils.getDateWithYearViaTimestamp(""+profileDetails.getData().getMember_since()));
            if(profileDetails.getData().getDocument_verification_status() == 1){
                binding.relativeDocument.setVisibility(View.VISIBLE);
            }else {
                binding.relativeDocument.setVisibility(View.GONE);
            }
            if(getIntent().getStringExtra("for")!= null) {
                if (getIntent().getStringExtra("for").equals("0")) {
                    binding.linearRideDetails.setVisibility(View.VISIBLE);
                    binding.linearBtn.setVisibility(View.VISIBLE);
                    view.setVisibility(View.VISIBLE);
                    binding.tvDt.setText(AppUtils.getDateTimeInDayFormat(profileDetails.getData().getUserdetails().getStart_timestamp()));
                    binding.tvSeat.setText(profileDetails.getData().getUserdetails().getNo_of_seats() + " "+getResources().getString(R.string.seats));
                    binding.tvAmt.setText(profileDetails.getData().getUserdetails().getTotal_amount());
                    binding.tvStartTime.setText(AppUtils.getTimeViaTimestamp(profileDetails.getData().getUserdetails().getStart_timestamp()));
                    binding.tvEndTime.setText(AppUtils.getTimeViaTimestamp(profileDetails.getData().getUserdetails().getEnd_timestamp()));
                    binding.tvFrom.setText(profileDetails.getData().getUserdetails().getStart_location());
                    binding.tvTo.setText(profileDetails.getData().getUserdetails().getDrop_location());
                    if (profileDetails.getData().getUserdetails().getPayment_type() == 2) {
                        binding.tvPaymentMethod.setText(getResources().getString(R.string.cash));
                    } else if(profileDetails.getData().getUserdetails().getPayment_type() == 3) {
                        binding.tvPaymentMethod.setText(getResources().getString(R.string.wallet_at_pickup));
                    }else {
                        binding.tvPaymentMethod.setText(getResources().getString(R.string.wallet));
                    }
                    if (getIntent().getStringExtra("rider").equals("1")) {
                        binding.linearBtn.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                        binding.scroll.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                    } else {
                        binding.linearBtn.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                    }
                    binding.btnAccept.setOnClickListener(new View.OnClickListener() {
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
                    binding.btnReject.setOnClickListener(new View.OnClickListener() {
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
                    binding.linearRideDetails.setVisibility(View.GONE);
                    binding.linearBtn.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                }
            }else {
                binding.linearRideDetails.setVisibility(View.GONE);
                binding.linearBtn.setVisibility(View.GONE);
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