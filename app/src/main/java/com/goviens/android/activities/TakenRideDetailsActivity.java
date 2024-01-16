package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.models.ModelProfileDetails;
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

public class TakenRideDetailsActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager manager;
    SessionManager sessionManager;

    Button btnCancel;
    Button btnRating;
    TextView tvDate;
    TextView tvAmt;
    TextView tvOTP;
    LinearLayout linearFemale;
    LinearLayout linearAc;
    TextView tvStartTime;
    TextView tvEndTime;
    TextView tvFrom;
    TextView tvTo;
    LinearLayout linearDriver;
    CircleImageView imageProfile;
    TextView tvName;
    TextView tvRating;
    CircleImageView imgVehicle;
    TextView tvVehicleName;
    TextView tvVehicleNumber;
    TextView tvVehicleColor;
    PlaceHolderView placeHolderRiders;
    LinearLayout linearRiders;
    TextView tvAmountType;
    TextView tvInstruction;
    TextView tvLabelInstruction;
    ImageView imgBack;
    TextView tvTakenSeats;
    TextView tvAvailableSeats;
    ModelTakenRideDetails rideDetails;
    ProgressDialog progressDialog;
    String cancelReasonId = "null";

    String from;

    PlaceHolderView placeholder_cancel_reason;
    RadioButton radioOther;
    EditText etOtherReason;

    String other_reason = "";
    Integer position = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_ride_details);
        ButterKnife.bind(this);
        manager = new ApiManager(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        from = getIntent().getStringExtra("from"); // 0 for upcoming 1 for ongoing
        if(from.equals("1")){
            btnRating.setVisibility(View.GONE);
        }else {
            btnRating.setVisibility(View.GONE);
        }
        imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForRating(v);
            }
        });
        rideDetails = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"),ModelTakenRideDetails.class);
        setData();
        linearDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("user_id",""+rideDetails.getData().get(0).getOffer_user_detail().getId());
                    map.put("type","driver");
                    map.put("ride id",""+rideDetails.getData().get(0).getTaken_ride_details().getRide_id());
                    map.put("carpooling_ride_user_detail_id",""+rideDetails.getData().get(0).getTaken_ride_details().getId());
                    manager._post(API_S.Tags.PROFILE_DETAILS, API_S.Endpoints.PROFILE_DETAILS, map, sessionManager);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    if(progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builderSingle = new AlertDialog.Builder(TakenRideDetailsActivity.this);
//                builderSingle.setTitle("Select Reason");
//                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TakenRideDetailsActivity.this, android.R.layout.select_dialog_singlechoice);
//                for (int i = 0; i < rideDetails.getData().get(0).getCancel_reason().size(); i++) {
//                    arrayAdapter.add(rideDetails.getData().get(0).getCancel_reason().get(i).getReason() + " " + sessionManager.getAppConfig().getData().getCountries().get(i).getName());
//                }
//                builderSingle.setNegativeButton(TakenRideDetailsActivity.this.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        cancelReasonId = ""+rideDetails.getData().get(0).getCancel_reason().get(which).getId();
//                        try {
//                            HashMap<String, String> map = new HashMap<>();
//                            map.put("ride_id", ""+rideDetails.getData().get(0).getTaken_ride_details().getId());
//                            map.put("cancel_reason_id", cancelReasonId);
//                            manager._post(API_S.Tags.CANCEL_TAKEN_RIDE, API_S.Endpoints.CANCEL_TAKEN_RIDE,map,sessionManager);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Toast.makeText(TakenRideDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                        }
//                        dialog.dismiss();
//                    }
//                });
//                builderSingle.show();
                openDialogForCancel(view);
            }
        } );
    }

    void openDialogForCancel(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TakenRideDetailsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_cancel_reasons, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        placeholder_cancel_reason = dialogView.findViewById(R.id.placeholder_cancel_reason);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        radioOther = dialogView.findViewById(R.id.radio_other);
        etOtherReason = dialogView.findViewById(R.id.et_other_reason);
        TextView tvCancelAmt = dialogView.findViewById(R.id.tv_cancellation_amt);
        if(rideDetails.getData().get(0).getTaken_ride_details().getCancel_amount().equals("0")
                || rideDetails.getData().get(0).getTaken_ride_details().getCancel_amount() == null){
            tvCancelAmt.setVisibility(View.GONE);
        }else {
            tvCancelAmt.setVisibility(View.VISIBLE);
            tvCancelAmt.setText(rideDetails.getData().get(0).getTaken_ride_details().getCancel_amount()+ " "+getResources().getString(R.string.cancellation_fee));
        }
        etOtherReason.setEnabled(false);
        for (int i = 0; i < rideDetails.getData().get(0).getCancel_reason().size(); i++) {
            placeholder_cancel_reason.addView(new HolderCancelReasons(rideDetails.getData().get(0).getCancel_reason().get(i)));
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
                        Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.please_specify_reason), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("ride_id", "" + rideDetails.getData().get(0).getTaken_ride_details().getId());
                            map.put("other_reason", etOtherReason.getText().toString());
                            manager._post(API_S.Tags.CANCEL_TAKEN_RIDE, API_S.Endpoints.CANCEL_TAKEN_RIDE, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                    }
                } else {
                    if (cancelReasonId.equals("null")) {
                        Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.please_select_reason), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("ride_id", "" + rideDetails.getData().get(0).getTaken_ride_details().getId());
                            map.put("cancel_reason_id", cancelReasonId);
                            manager._post(API_S.Tags.CANCEL_TAKEN_RIDE, API_S.Endpoints.CANCEL_TAKEN_RIDE, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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

    void openDialogForRating(android.view.View v){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(TakenRideDetailsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_rating, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        TextView tvComments = dialogView.findViewById(R.id.comments);
        LinearLayout btnDone = dialogView.findViewById(R.id.lldone);
        btnDone.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try{
                    progressDialog.show();
                    HashMap<String,String> map = new HashMap<>();
                    map.put("ride_id",""+rideDetails.getData().get(0).getTaken_ride_details().getRide_id());
                    map.put("carpooling_ride_user_detail_id",""+rideDetails.getData().get(0).getOffer_user_detail().getId());
                    map.put("rating",""+ratingBar.getRating());
                    map.put("comment",tvComments.getText().toString());
                    manager._post(API_S.Tags.DRIVER_RATING,API_S.Endpoints.DRIVER_RATING,map,sessionManager);
                    alertDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(TakenRideDetailsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    progressDialog.hide();
                }
            }
        });
        alertDialog.show();
    }

    private void setData() {
        try {
            tvTakenSeats.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getBooked_seat());
            tvAvailableSeats.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getAvailable_seats());
            tvDate.setText(AppUtils.getDateTimeInDayFormat("" + rideDetails.getData().get(0).getTaken_ride_details().getRide_time()));
            tvAmt.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getRide_amount());
            if(rideDetails.getData().get(0).getTaken_ride_details().getPayment_status() == 1){
                tvAmountType.setText(getResources().getString(R.string.wallet));
            }else if(rideDetails.getData().get(0).getTaken_ride_details().getPayment_status() == 3){
                tvAmountType.setText(getResources().getString(R.string.wallet_at_pickup));
            }else{
                tvAmountType.setText(getResources().getString(R.string.cash));
            }
            tvOTP.setText(""+rideDetails.getData().get(0).getTaken_ride_details().getOtp());
            if(Integer.parseInt(rideDetails.getData().get(0).getOffer_user_detail().getRide_status()) >2){
                btnCancel.setVisibility(View.GONE);
            }else {
                btnCancel.setVisibility(View.VISIBLE);
            }
            btnRating.setVisibility(View.GONE);
            if(rideDetails.getData().get(0).getTaken_ride_details().getInstructions() != null){
                if(!rideDetails.getData().get(0).getTaken_ride_details().getInstructions().equals("")) {
                    tvLabelInstruction.setVisibility(View.VISIBLE);
                    tvInstruction.setVisibility(View.VISIBLE);
                    tvInstruction.setText(rideDetails.getData().get(0).getTaken_ride_details().getInstructions());
                }else {
                    tvLabelInstruction.setVisibility(View.GONE);
                    tvInstruction.setVisibility(View.GONE);
                }
            }else {
                tvLabelInstruction.setVisibility(View.GONE);
                tvInstruction.setVisibility(View.GONE);
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getFemale_ride().equals("1")){
                linearFemale.setVisibility(View.VISIBLE);
            }else {
                linearFemale.setVisibility(View.GONE);
            }
            if(rideDetails.getData().get(0).getTaken_ride_details().getAc_ride().equals("1")){
                linearAc.setVisibility(View.VISIBLE);
            }else {
                linearAc.setVisibility(View.GONE);
            }
            tvStartTime.setText(AppUtils.getTimeViaTimestamp(rideDetails.getData().get(0).getTaken_ride_details().getRide_time()));
            tvEndTime.setText(AppUtils.getTimeViaTimestamp(rideDetails.getData().get(0).getTaken_ride_details().getEnd_ride_time()));
            tvFrom.setText(rideDetails.getData().get(0).getTaken_ride_details().getPickup_location());
            tvTo.setText(rideDetails.getData().get(0).getTaken_ride_details().getDrop_location());
            Glide.with(this).load(rideDetails.getData().get(0).getOffer_user_detail().getImage()).into(imageProfile);
            tvName.setText(rideDetails.getData().get(0).getOffer_user_detail().getName());
            Glide.with(this).load(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_image()).into(imgVehicle);
            tvVehicleName.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_type_name());
            tvVehicleColor.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_color());
            tvVehicleNumber.setText(rideDetails.getData().get(0).getTake_vehicle_details().getVehicle_number());
            if (rideDetails.getData().get(0).getAccept_users().size() != 0) {
                linearRiders.setVisibility(android.view.View.VISIBLE);
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
        if(APINAME.equals(API_S.Tags.PROFILE_DETAILS)){
            try {
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                ModelProfileDetails profileDetails = SingletonGson.getInstance().fromJson("" + script, ModelProfileDetails.class);
                if (profileDetails.getResult().equals("1")) {
                    startActivity(new Intent(TakenRideDetailsActivity.this, ProfileActivity.class)
                            .putExtra("script", "" + script)
                            .putExtra("driver","1"));
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

        @Position
        int mPostion;

        ModelTakenRideDetails.DataBean.AcceptUsersBean rideDetailsListBean;

        public HolderRiders(ModelTakenRideDetails.DataBean.AcceptUsersBean rideDetailsListBean) {
            this.rideDetailsListBean = rideDetailsListBean;
        }

        @Resolve
        void setData() {
            imgIndicator.setVisibility(View.GONE);
            try {
                Glide.with(TakenRideDetailsActivity.this).load(rideDetailsListBean.getAccept_user_image()).into(imgProfile);
                tvName.setText(rideDetailsListBean.getAccept_user_name());
                tvRating.setText(rideDetailsListBean.getAccept_user_rating());
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

    @Layout(R.layout.raw_cancel_reason)
    class HolderCancelReasons {
        @com.mindorks.placeholderview.annotations.View(R.id.radio_reason)
        RadioButton radioReason;


        @Position
        int mPostion;
        ModelTakenRideDetails.DataBean.CancelReasonBean cancelReasonBean;


        public HolderCancelReasons(ModelTakenRideDetails.DataBean.CancelReasonBean cancelReasonBean) {
            this.cancelReasonBean = cancelReasonBean;
        }


        @Resolve
        void setData() {
            try {
                if(position == null){
                    radioReason.setChecked(false);
                }else {
                    if (mPostion == position) {
                        radioReason.setChecked(true);
                        cancelReasonId = ""+cancelReasonBean.getId();
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