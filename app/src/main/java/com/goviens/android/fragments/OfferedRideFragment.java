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
import com.goviens.android.activities.RideDetailsActivity;
import com.goviens.android.models.ModelOffererdRideList;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;


import butterknife.ButterKnife;

public class OfferedRideFragment extends Fragment implements ApiManager.APIFETCHER {

    SessionManager manager;
    ApiManager apiManager;
    ProgressDialog progressDialog;

    PlaceHolderView placeHolderOngoing;
    PlaceHolderView placeHolderUpcoming;

    TextView tvOngoingRide;

    TextView tvUpcomingRide;
    TextView tvNoRide;


    public OfferedRideFragment() {
        // Required empty public constructor
    }
    public static OfferedRideFragment newInstance(String param1, String param2) {
        OfferedRideFragment fragment = new OfferedRideFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            placeHolderUpcoming.removeAllViews();
            placeHolderOngoing.removeAllViews();
            progressDialog.show();
            apiManager._post(API_S.Tags.OFFERED_RIDE_LIST, API_S.Endpoints.OFFERED_RIDE_LIST,null,manager);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offered_ride, container, false);
        ButterKnife.bind(this, rootView);
        manager = new SessionManager(getActivity());
        apiManager = new ApiManager(this,getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        placeHolderOngoing = rootView.findViewById(R.id.placeHolder_ongoing);
        placeHolderUpcoming = rootView.findViewById(R.id.placeHolder_upcoming);
        tvOngoingRide = rootView.findViewById(R.id.tv_ongoing_ride);
        tvUpcomingRide = rootView.findViewById(R.id.tv_upcoming_ride);
        tvNoRide = rootView.findViewById(R.id.tv_no_ride);


        return rootView;
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            if(progressDialog.isShowing()) {
                progressDialog.hide();
            }
            ModelOffererdRideList offererdRideList = SingletonGson.getInstance().fromJson("" + script, ModelOffererdRideList.class);
            if (offererdRideList.getResult().equals("1")) {
                if (offererdRideList.getData().getUpcoming_ride().getData().size() == 0) {
                    tvUpcomingRide.setVisibility(View.GONE);
                } else {
                    tvUpcomingRide.setVisibility(View.VISIBLE);
                    for (int i = 0; i < offererdRideList.getData().getUpcoming_ride().getData().size(); i++) {
                        placeHolderUpcoming.addView(new HolderUpcoming(offererdRideList.getData().getUpcoming_ride().getData().get(i)));
                    }
                }
                if (offererdRideList.getData().getOngoing_ride().getData().size() == 0) {
                    tvOngoingRide.setVisibility(View.GONE);
                } else {
                    tvOngoingRide.setVisibility(View.VISIBLE);
                    for (int i = 0; i < offererdRideList.getData().getOngoing_ride().getData().size(); i++) {
                        placeHolderOngoing.addView(new HolderOngoing(offererdRideList.getData().getOngoing_ride().getData().get(i)));
                    }
                }
                if (offererdRideList.getData().getUpcoming_ride().getData().size() == 0
                        && offererdRideList.getData().getOngoing_ride().getData().size() == 0){
                    tvNoRide.setVisibility(View.VISIBLE);
                }else {
                    tvNoRide.setVisibility(View.GONE);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
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

        ModelOffererdRideList.DataBeanX.UpcomingRideBean.DataBean upcomingRideBean;

        HolderUpcoming(ModelOffererdRideList.DataBeanX.UpcomingRideBean.DataBean upcomingRideBean){
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
                if(upcomingRideBean.getUser_requests() != 0) {
                    tvRequest.setVisibility(View.VISIBLE);
                    tvRequest.setText(upcomingRideBean.getUser_requests() + " "+getResources().getString(R.string.requests));
                }else {
                    tvRequest.setVisibility(View.GONE);
                }
                tvTime.setText(AppUtils.getTimeViaTimestamp(upcomingRideBean.getRide_timestamp()));
                tvStop.setText(""+upcomingRideBean.getNo_of_stops());
                tvPaymentMethod.setText(""+upcomingRideBean.getPayment_method_text());
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
                    startActivity(new Intent(getActivity(), RideDetailsActivity.class)
                            .putExtra("ride_id",""+upcomingRideBean.getId())
                    .putExtra("from", "0")); //0 for upcoming 1 for ongoing
                }
            });
        }
    }

    @Layout(R.layout.raw_ride)
    class HolderOngoing{
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

        ModelOffererdRideList.DataBeanX.OngoingRideBean.DataBean dataBean;
        public HolderOngoing(ModelOffererdRideList.DataBeanX.OngoingRideBean.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Resolve
        void setData(){
            try{
                tvRideId.setText(getResources().getString(R.string.ride_id_)+dataBean.getId());
                tvFrom.setText(dataBean.getStart_location());
                tvTo.setText(dataBean.getEnd_location());
                if(dataBean.getAc_ride()==1){
                    imgAc.setVisibility(View.VISIBLE);
                }else {
                    imgAc.setVisibility(View.GONE);
                }
                if(dataBean.getUser_requests() != 0) {
                    tvRequest.setVisibility(View.VISIBLE);
                    tvRequest.setText(dataBean.getUser_requests() + " "+getResources().getString(R.string.requests));
                }else {
                    tvRequest.setVisibility(View.GONE);
                }
                tvTime.setText(AppUtils.getTimeViaTimestamp(dataBean.getRide_timestamp()));
                tvStop.setVisibility(View.GONE);
                tvPaymentMethod.setText(""+dataBean.getPayment_method_text());
                tvDate.setText(AppUtils.getDateTimeInDayFormat(dataBean.getRide_timestamp()));
                if(dataBean.getFemale_ride()==1){
                    imgFemale.setVisibility(View.VISIBLE);
                }else {
                    imgFemale.setVisibility(View.GONE);
                }
                tvAmt.setText(""+dataBean.getRide_amount());
            }catch (Exception e){
                e.printStackTrace();
            }

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), RideDetailsActivity.class)
                            .putExtra("ride_id",""+dataBean.getId())
                            .putExtra("from", "1")); //0 for upcoming 1 for ongoing
                }
            });
        }
    }
}
