package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.goviens.android.R;
import com.goviens.android.models.ModelSearch;
import com.goviens.android.models.ModelSearchDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mohammedalaa.seekbar.OnRangeSeekBarChangeListener;
import com.mohammedalaa.seekbar.RangeSeekBarView;

import java.util.HashMap;


import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RideSearchResultActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    PlaceHolderView placeHolderSearchRide;
    ImageView imgBack;
    ModelSearch modelSearch;

    TextView tvTo;
    TextView tvFrom;
    Button btnFilter;

    TextView tvDtTime;

    int radius;
    String pickup_id,drop_id;


    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_search_result);
        ButterKnife.bind(this);
        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        modelSearch = SingletonGson.getInstance().fromJson(""+getIntent().getStringExtra("script"),ModelSearch.class);
        for(int i =0; i<modelSearch.getData().size(); i++) {
            placeHolderSearchRide.addView(new HolderSearchRide(modelSearch.getData().get(i)));
        }
        imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        tvFrom.setText(getIntent().getStringExtra("from"));
        tvTo.setText(getIntent().getStringExtra("to"));
        tvDtTime.setText(AppUtils.getDateTimeInDayFormat(getIntent().getStringExtra("ride_timestamp"))+" | "
                +getIntent().getStringExtra("no_of_seats")+" "+getResources().getString(R.string.seats));
        btnFilter.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    openDialogForFilter(view);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(RideSearchResultActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void openDialogForFilter(android.view.View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(RideSearchResultActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_filter, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        RangeSeekBarView radiusSeekBar = dialogView.findViewById(R.id.range_seekbar);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnApply = dialogView.findViewById(R.id.btn_apply);
       // radiusSeekBar.getCurrentValue();
        radiusSeekBar.setOnRangeSeekBarViewChangeListener(new OnRangeSeekBarChangeListener() {
            @Override
            public void onProgressChanged(@Nullable RangeSeekBarView seekBar, int progress, boolean fromUser) {
               // Toast.makeText(RideSearchResultActivity.this, "2"+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(@Nullable RangeSeekBarView seekBar, int progress) {
            }

            @Override
            public void onStopTrackingTouch(@Nullable RangeSeekBarView seekBar, int progress) {
                radius = progress;
            }
        });
        btnCancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                alertDialog.dismiss();
            }
        });
        btnApply.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try{
                    progressDialog.show();
                    placeHolderSearchRide.removeAllViews();
                    HashMap<String,String> map = new HashMap<>();
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
                    map.put("payment_type_id",getIntent().getStringExtra("payment_type_id"));
                    map.put("distance",""+radius);
                    if(getIntent().getStringExtra("return_ride").equals("1")){
                        map.put("return_ride",getIntent().getStringExtra("pickup_latitude"));
                        map.put("return_ride_timestamp",getIntent().getStringExtra("pickup_latitude"));
                    }else {
                        map.put("return_ride",getIntent().getStringExtra("pickup_latitude"));
                    }
                    manager._post(API_S.Tags.RIDE_SEARCH,API_S.Endpoints.RIDE_SEARCH,map,sessionManager);
                    alertDialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(RideSearchResultActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    progressDialog.hide();
                }
            }
        });
        alertDialog.show();
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.RIDE_SEARCH)){
            progressDialog.hide();
            modelSearch = SingletonGson.getInstance().fromJson(""+getIntent().getStringExtra("script"),ModelSearch.class);
            if(modelSearch.getResult().equals("1")) {
                for (int i = 0; i < modelSearch.getData().size(); i++) {
                    placeHolderSearchRide.addView(new HolderSearchRide(modelSearch.getData().get(i)));
                }
            }
        }else {
            try {
                progressDialog.hide();
                ModelSearchDetails searchDetails = SingletonGson.getInstance().fromJson("" + script, ModelSearchDetails.class);
                if (searchDetails.getResult().equals("1")) {
                    startActivity(new Intent(RideSearchResultActivity.this, SearchRideDetailsActivity.class)
                            .putExtra("script", "" + script)
                            .putExtra("pickup_id", pickup_id)
                            .putExtra("drop_id", drop_id)
                            .putExtra("amt", modelSearch.getData().get(0).getTotal_charges())
                            .putExtra("pickup_latitude", getIntent().getStringExtra("pickup_latitude"))
                            .putExtra("pickup_longitude", getIntent().getStringExtra("pickup_longitude"))
                            .putExtra("pickup_location", getIntent().getStringExtra("pickup_location"))
                            .putExtra("drop_latitude", getIntent().getStringExtra("drop_latitude"))
                            .putExtra("drop_longitude", getIntent().getStringExtra("drop_longitude"))
                            .putExtra("drop_location", getIntent().getStringExtra("drop_location"))
                            .putExtra("no_of_seats", getIntent().getStringExtra("no_of_seats"))
                            .putExtra("ac_ride", getIntent().getStringExtra("ac_ride"))
                            .putExtra("female_ride", getIntent().getStringExtra("female_ride"))
                            .putExtra("ride_timestamp", getIntent().getStringExtra("ride_timestamp"))
                            .putExtra("payment_type_id", getIntent().getStringExtra("payment_type_id")));
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }

    @Layout(R.layout.raw_search_ride)
    class HolderSearchRide{

        @View(R.id.img_profile)
        CircleImageView imgDriver;
        @View(R.id.tv_name)
        TextView tvDriverName;
        @View(R.id.tv_rating)
        TextView tvRating;
        @View(R.id.img_female)
        ImageView imgFemale;
        @View(R.id.img_ac)
        ImageView imgAc;
        @View(R.id.tv_from)
        TextView tvfrom;
        @View(R.id.tv_to)
        TextView tvto;
        @View(R.id.tv_start_time)
        TextView tvStartTime;
        @View(R.id.tv_end_time)
        TextView tvEndTime;
        @View(R.id.root)
        LinearLayout root;
        @View(R.id.tv_amt)
        TextView tvAmt;
        @View(R.id.tv_available_seats)
        TextView tvAvailableSeats;
        @View(R.id.relative_return)
        RelativeLayout relativeReturn;
        @View(R.id.tv_start_time_return)
        TextView tvStartTimeReturn;
        @View(R.id.tv_end_time_return)
        TextView tvEndTimeReturn;

        ModelSearch.DataBean dataBean;

        public HolderSearchRide(ModelSearch.DataBean dataBean) {
            this.dataBean = dataBean;
        }
        @Resolve
        void setData(){
            try {
                if(dataBean.getReturn_ride() != null) {
                    if (dataBean.getReturn_ride().equals("1")) {
                        relativeReturn.setVisibility(android.view.View.VISIBLE);
                    } else {
                        relativeReturn.setVisibility(android.view.View.GONE);
                    }
                }else {
                    relativeReturn.setVisibility(android.view.View.GONE);
                }
                tvAvailableSeats.setText(""+dataBean.getAvailable_seats());
                Glide.with(RideSearchResultActivity.this).load(dataBean.getProfile_image()).into(imgDriver);
                tvDriverName.setText(dataBean.getFull_name());
                tvRating.setText(dataBean.getRating());
                tvAmt.setText(dataBean.getTotal_charges());
                if(dataBean.getFemale_ride() == 1){
                    imgFemale.setVisibility(android.view.View.VISIBLE);
                }else {
                    imgFemale.setVisibility(android.view.View.GONE);
                }
                if(dataBean.getAc_ride() == 1){
                    imgAc.setVisibility(android.view.View.VISIBLE);
                }else {
                    imgAc.setVisibility(android.view.View.GONE);
                }
                tvfrom.setText(dataBean.getRoute().get(0).getFrom_location());
                tvto.setText(dataBean.getRoute().get(0).getTo_location());
                tvStartTime.setText(AppUtils.getDateTimeInFormat(dataBean.getRoute().get(0).getRide_timestamp()));
                tvEndTime.setText(AppUtils.getDateTimeInFormat(dataBean.getRoute().get(0).getEnd_timestamp()));
            }catch (Exception e){
                e.printStackTrace();
            }
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    try {
                        pickup_id = ""+dataBean.getPickup_id();
                        drop_id = ""+dataBean.getDrop_id();
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("ride_id",""+dataBean.getCarpooling_ride_id());
                        manager._post(API_S.Tags.RIDE_DETAILS, API_S.Endpoints.RIDE_DETAILS, map, sessionManager);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
