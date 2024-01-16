package com.goviens.android.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.activities.PastTakenRideDetailsActivity;
import com.goviens.android.models.ModelPastTaken;
import com.goviens.android.models.ModelTakenRideDetails;
import com.goviens.android.models.ModelTakenRideList;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;

import java.util.HashMap;


import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PastTakenFragment extends Fragment implements ApiManager.APIFETCHER {

    SessionManager manager;
    ApiManager apiManager;
    ProgressDialog progressDialog;

    PlaceHolderView placeHolderPast;

    TextView tvNoRide;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_past_taken, container, false);
        ButterKnife.bind(this, rootView);
        manager = new SessionManager(getActivity());
        apiManager = new ApiManager(this,getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);


        tvNoRide = rootView.findViewById(R.id.tv_no_ride);
        placeHolderPast = rootView.findViewById(R.id.placeHolder_past);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            progressDialog.show();
            placeHolderPast.removeAllViews();
            apiManager._post(API_S.Tags.PAST_TAKEN_RIDE, API_S.Endpoints.PAST_TAKEN_RIDE, null, manager);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()) {
                progressDialog.hide();
            }
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.PAST_TAKEN_RIDE)) {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                ModelPastTaken takenRideList = SingletonGson.getInstance().fromJson("" + script, ModelPastTaken.class);
                if (takenRideList.getResult().equals("1")) {
                    if(takenRideList.getData().getPast_take_ride().size() == 0){
                        tvNoRide.setVisibility(View.VISIBLE);
                    }else {
                        tvNoRide.setVisibility(View.GONE);
                    }
                    if (takenRideList.getData().getPast_take_ride().size() == 0) {
                        placeHolderPast.setVisibility(View.GONE);
                    } else {
                        placeHolderPast.setVisibility(View.VISIBLE);
                        for (int i = 0; i < takenRideList.getData().getPast_take_ride().size(); i++) {
                            placeHolderPast.addView(new HolderTakenRide(takenRideList.getData().getPast_take_ride().get(i)));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }
        }else {
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                ModelTakenRideDetails takenRideDetails = SingletonGson.getInstance().fromJson("" + script, ModelTakenRideDetails.class);
                if (takenRideDetails.getResult().equals("1")) {
                    startActivity(new Intent(getActivity(), PastTakenRideDetailsActivity.class)
                            .putExtra("script", "" + script)
                            .putExtra("id", id));
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(getActivity(), ""+script, Toast.LENGTH_SHORT).show();
        if(progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Layout(R.layout.raw_taken_ride_list)
    class HolderTakenRide{
        @com.mindorks.placeholderview.annotations.View(R.id.img_profile)
        CircleImageView imgProfile;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_name)
        TextView tvName;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_rating)
        TextView tvRating;
        @com.mindorks.placeholderview.annotations.View(R.id.img_female)
        ImageView imgFemale;
        @com.mindorks.placeholderview.annotations.View(R.id.img_ac)
        ImageView imgAc;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_amt)
        TextView tvAmt;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_from_time)
        TextView tvFromTime;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_to_time)
        TextView tvToTime;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_from)
        TextView tvFrom;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_to)
        TextView tvTo;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_date)
        TextView tvDate;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_otp)
        TextView tvOTP;
        @com.mindorks.placeholderview.annotations.View(R.id.btn_status_wait)
        Button btnStatusWait;
        @com.mindorks.placeholderview.annotations.View(R.id.btn_status_confirm)
        Button btnStatusConfirm;
        @com.mindorks.placeholderview.annotations.View(R.id.root)
        LinearLayout root;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_ride_id)
        TextView tvRideId;
        ModelPastTaken.DataBean.PastTakeRideBean upcomingRideBean;

        public HolderTakenRide(ModelPastTaken.DataBean.PastTakeRideBean upcomingRideBean) {
            this.upcomingRideBean = upcomingRideBean;
        }

        @Resolve
        void setData(){
            try{
                tvRideId.setText(getResources().getString(R.string.ride_id_)+upcomingRideBean.getUnique_id());
                Glide.with(getActivity()).load(upcomingRideBean.getProfile_image()).into(imgProfile);
                tvName.setText(upcomingRideBean.getOffer_user_name());
                tvRating.setText(upcomingRideBean.getOffer_user_rating());
                if(upcomingRideBean.getFemale_ride().equals("1")){
                    imgFemale.setVisibility(View.VISIBLE);
                }else {
                    imgFemale.setVisibility(View.GONE);
                }
                if(upcomingRideBean.getRide_status().equals("5")){
                    btnStatusWait.setVisibility(View.VISIBLE);
                    btnStatusConfirm.setVisibility(View.GONE);
                    btnStatusWait.setBackgroundColor(getResources().getColor(R.color.red));
                    btnStatusWait.setText(getResources().getString(R.string.cancel_by_driver));
                }else if(upcomingRideBean.getRide_status().equals("6")){
                    btnStatusWait.setVisibility(View.VISIBLE);
                    btnStatusConfirm.setVisibility(View.GONE);
                    btnStatusWait.setBackgroundColor(getResources().getColor(R.color.red));
                    btnStatusWait.setText(getResources().getString(R.string.cancelled));
                }else if(upcomingRideBean.getRide_status().equals("8") || upcomingRideBean.getRide_status().equals("7")){
                    btnStatusWait.setVisibility(View.VISIBLE);
                    btnStatusConfirm.setVisibility(View.GONE);
                    btnStatusWait.setBackgroundColor(getResources().getColor(R.color.red));
                    btnStatusWait.setText(getResources().getString(R.string.rejected));
                }else if(upcomingRideBean.getRide_status().equals("9")){
                    btnStatusWait.setVisibility(View.VISIBLE);
                    btnStatusConfirm.setVisibility(View.GONE);
                    btnStatusWait.setBackgroundColor(getResources().getColor(R.color.red));
                    btnStatusWait.setText(getResources().getString(R.string.auto_cancel));
                }else {
                    btnStatusWait.setVisibility(View.GONE);
                    btnStatusConfirm.setVisibility(View.VISIBLE);
                    btnStatusConfirm.setText(getResources().getString(R.string.completed));
                }
                if(upcomingRideBean.getAc_ride().equals("1")){
                    imgAc.setVisibility(View.VISIBLE);
                }else {
                    imgAc.setVisibility(View.GONE);
                }
                tvAmt.setText(upcomingRideBean.getFinal_charges());
                tvFrom.setText(upcomingRideBean.getStart_location());
                tvTo.setText(upcomingRideBean.getEnd_location());
                tvDate.setText(AppUtils.getDateTimeInDayFormat(upcomingRideBean.getRide_date()));
                tvFromTime.setText(AppUtils.getTimeViaTimestamp(upcomingRideBean.getRide_date()));
                tvToTime.setText(AppUtils.getTimeViaTimestamp(upcomingRideBean.getEnd_ride_date()));
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()) {
                    progressDialog.hide();
                }
            }
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        progressDialog.show();
                        id = ""+upcomingRideBean.getId();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("carpooling_ride_user_detail_id",""+upcomingRideBean.getId());
                        apiManager._post(API_S.Tags.PAST_TAKEN_RIDE_DETAILS, API_S.Endpoints.PAST_TAKEN_RIDE_DETAILS, map, manager);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()){
                            progressDialog.hide();
                        }
                    }
                }
            });
        }
    }
}