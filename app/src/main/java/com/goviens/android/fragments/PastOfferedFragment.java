package com.goviens.android.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.activities.PastOfferedRideDetailsActivity;
import com.goviens.android.activities.PastTakenRideDetailsActivity;
import com.goviens.android.activities.RideDetailsActivity;
import com.goviens.android.models.ModelOffererdRideList;
import com.goviens.android.models.ModelPastOffered;
import com.goviens.android.models.ModelRideDetails;
import com.goviens.android.models.ModelTakenRideDetails;
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


public class PastOfferedFragment extends Fragment implements ApiManager.APIFETCHER {

    SessionManager manager;
    ApiManager apiManager;
    ProgressDialog progressDialog;

    PlaceHolderView placeHolderPast;

    TextView tvNoRide;
    String ride_status;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_past_offered, container, false);
        ButterKnife.bind(this, rootView);
        manager = new SessionManager(getActivity());
        apiManager = new ApiManager(this,getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        placeHolderPast = rootView.findViewById(R.id.placeHolder_past);
        tvNoRide = rootView.findViewById(R.id.tv_no_ride);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            placeHolderPast.removeAllViews();
            progressDialog.show();
            apiManager._post(API_S.Tags.PAST_OFFERED_RIDE, API_S.Endpoints.PAST_OFFERED_RIDE,null,manager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.PAST_OFFERED_RIDE_DETAILS)){
            try {
                if (progressDialog.isShowing()) {
                    progressDialog.hide();
                }
                ModelRideDetails takenRideDetails = SingletonGson.getInstance().fromJson("" + script, ModelRideDetails.class);
                if (takenRideDetails.getResult().equals("1")) {
                    startActivity(new Intent(getActivity(), PastOfferedRideDetailsActivity.class)
                            .putExtra("script", "" + script)
                    .putExtra("ride_status",ride_status));
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
                ModelPastOffered offererdRideList = SingletonGson.getInstance().fromJson("" + script, ModelPastOffered.class);
                if (offererdRideList.getResult().equals("1")) {
                    if (offererdRideList.getData().getPast_ride_data().size() == 0) {
                        tvNoRide.setVisibility(View.VISIBLE);
                    } else {
                        tvNoRide.setVisibility(View.GONE);
                        for (int i = 0; i < offererdRideList.getData().getPast_ride_data().size(); i++) {
                            placeHolderPast.addView(new HolderUpcoming(offererdRideList.getData().getPast_ride_data().get(i)));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        if(progressDialog.isShowing()) {
            progressDialog.hide();
        }
        Toast.makeText(getActivity(), ""+script, Toast.LENGTH_SHORT).show();
    }

    @Layout(R.layout.raw_ride)
    class HolderUpcoming{
        @com.mindorks.placeholderview.annotations.View(R.id.tv_from)
        TextView tvFrom;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_to)
        TextView tvTo;
        @com.mindorks.placeholderview.annotations.View(R.id.img_ac)
        ImageView imgAc;
        @com.mindorks.placeholderview.annotations.View(R.id.img_female)
        ImageView imgFemale;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_amt)
        TextView tvAmt;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_time)
        TextView tvTime;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_date)
        TextView tvDate;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_request)
        TextView tvRequest;
        @com.mindorks.placeholderview.annotations.View(R.id.root)
        LinearLayout root;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_stop)
        TextView tvStop;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_payment_method)
        TextView tvPaymentMethod;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_ride_id)
        TextView tvRideId;

        ModelPastOffered.DataBean.PastRideDataBean upcomingRideBean;

        HolderUpcoming(ModelPastOffered.DataBean.PastRideDataBean upcomingRideBean){
            this.upcomingRideBean = upcomingRideBean;
        }
        @Resolve
        void setData(){
            try{
                tvRideId.setText(getResources().getString(R.string.ride_id_)+upcomingRideBean.getId());
                tvFrom.setText(upcomingRideBean.getStart_location());
                tvTo.setText(upcomingRideBean.getEnd_location());
                if(upcomingRideBean.getAc_ride()==1){
                    imgAc.setVisibility(View.VISIBLE);
                }else {
                    imgAc.setVisibility(View.GONE);
                }
                tvRequest.setVisibility(View.GONE);
                //tvRequest.setText(upcomingRideBean.getRide_status());
                tvTime.setText(AppUtils.getTimeViaTimestamp(upcomingRideBean.getRide_timestamp()));
                tvStop.setText(""+upcomingRideBean.getNo_of_stops());
                tvPaymentMethod.setText(""+upcomingRideBean.getPayment_method());
                tvDate.setText(AppUtils.getDateTimeInDayFormat(upcomingRideBean.getRide_timestamp()));
                if(upcomingRideBean.getFemale_ride()==1){
                    imgFemale.setVisibility(View.VISIBLE);
                }else {
                    imgFemale.setVisibility(View.GONE);
                }
                tvAmt.setText(""+upcomingRideBean.getRide_amount());
            }catch (Exception e){
                e.printStackTrace();
            }

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        ride_status = upcomingRideBean.getRide_status();
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("ride_id",""+upcomingRideBean.getId());
                        apiManager._post(API_S.Tags.PAST_OFFERED_RIDE_DETAILS, API_S.Endpoints.PAST_OFFERED_RIDE_DETAILS, map, manager);
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