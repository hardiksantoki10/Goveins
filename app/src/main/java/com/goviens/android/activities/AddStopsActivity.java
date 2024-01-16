package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityAddStopsBinding;
import com.goviens.android.models.ModelStepOne;
import com.goviens.android.models.ModelStepThree;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;


import butterknife.ButterKnife;

public class AddStopsActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    private ActivityAddStopsBinding mBinding;
    PlaceHolderView placeHolderStops;


    ModelStepOne modelStepOne;
    ApiManager manager;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    JSONArray array;
    JSONObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityAddStopsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);


        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        array = new JSONArray();
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        try {
            modelStepOne = SingletonGson.getInstance().fromJson("" + getIntent().getStringExtra("script"), ModelStepOne.class);
            for (int i = 0; i < modelStepOne.getData().getDrop_points_details().size(); i++) {
                placeHolderStops.addView(new HolderStop(modelStepOne.getData().getDrop_points_details().get(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mBinding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("offer_ride_checkout_id", "" + modelStepOne.getData().getOffer_ride_checkout_id());
                    map.put("updated_amount", ""+array);
                    map.put("additional_notes", mBinding.etInstruction.getText().toString());
                    manager._post(API_S.Tags.RIDE_STEP_THREE, API_S.Endpoints.RIDE_STEP_THREE, map, sessionManager);
                }catch (Exception e){
                    progressDialog.hide();
                    e.printStackTrace();
                }
            }
        });
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        progressDialog.hide();
        ModelStepThree stepThree = SingletonGson.getInstance().fromJson(""+script, ModelStepThree.class);
        if(stepThree.getResult().equals("1")){
            startActivity(new Intent(this,ConfirmActivity.class)
                    .putExtra("script",""+script)
                    .putExtra("checkout",""+modelStepOne.getData().getOffer_ride_checkout_id()));
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }



    @Layout(R.layout.raw_stop)
    class HolderStop{
        ModelStepOne.DataBean.DropPointsDetailsBean dropPointsDetailsBean;

        @com.mindorks.placeholderview.annotations.View(R.id.tv_from)
        TextView tvfrom;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_to)
        TextView tvTo;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_amt)
        EditText tvAmt;
        @com.mindorks.placeholderview.annotations.View(R.id.tv_updated_amt)
        EditText tvUpdatedAmt;

        @Position
        int mPosition;

        public HolderStop(ModelStepOne.DataBean.DropPointsDetailsBean dropPointsDetailsBean) {
            this.dropPointsDetailsBean = dropPointsDetailsBean;
        }

        @Resolve
        void setData(){
            try {
                tvfrom.setText(dropPointsDetailsBean.getFrom_location());
                tvTo.setText(dropPointsDetailsBean.getTo_location());
                tvAmt.setText(modelStepOne.getData().getCurrency()+" "+dropPointsDetailsBean.getEstimate_charges());
                object = new JSONObject();
                object.put("drop_no",""+dropPointsDetailsBean.getDrop_no());
                object.put("amount",""+dropPointsDetailsBean.getEstimate_charges());
                array.put(object);
                tvUpdatedAmt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {
                            if(!tvUpdatedAmt.getText().toString().equals("")) {
                                array.getJSONObject(mPosition).remove("amount");
                                array.getJSONObject(mPosition).put("amount", s);
                            }else {
                                if(array.getJSONObject(mPosition).getString("amount")!= null
                                        || array.getJSONObject(mPosition).getString("amount").equals("")){
                                    array.getJSONObject(mPosition).put("amount", ""+dropPointsDetailsBean.getEstimate_charges());
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
