package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityChangeCarBinding;
import com.goviens.android.models.ModelVehicleDefault;
import com.goviens.android.models.ModelVehicleList;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.HashMap;


import butterknife.ButterKnife;

public class ChangeCarActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityChangeCarBinding mBinding;

    SessionManager manager;
    ApiManager apiManager;

    PlaceHolderView placeHolderVehicles;

    ModelVehicleList vehicleList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityChangeCarBinding.inflate(getLayoutInflater());
        android.view.View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);

        manager = new SessionManager(this);
        apiManager = new ApiManager(this,this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();

        try{
            apiManager._post(API_S.Tags.GET_VEHICLE_LIST, API_S.Endpoints.GET_VEHICLE_LIST,null,manager);
        }catch (Exception e){
            e.printStackTrace();
        }

        mBinding.imgAdd.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(ChangeCarActivity.this,AddCarActivity.class));
            }
        });
        mBinding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.GET_VEHICLE_LIST)){
            vehicleList = SingletonGson.getInstance().fromJson(""+script, ModelVehicleList.class);
            if(vehicleList.getResult().equals("1")){
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                if(vehicleList.getData().getVehicle_list().size() == 0){
                    placeHolderVehicles.setVisibility(android.view.View.GONE);
                    mBinding.tvNoVehicle.setVisibility(android.view.View.VISIBLE);
                }else {
                    placeHolderVehicles.setVisibility(android.view.View.VISIBLE);
                    mBinding.tvNoVehicle.setVisibility(android.view.View.GONE);
                }
                for(int i=0; i<vehicleList.getData().getVehicle_list().size(); i++) {
                    placeHolderVehicles.addView(new HolderChangeCar(vehicleList.getData().getVehicle_list().get(i)));
                }
            }
        }else if(APINAME.equals(API_S.Tags.VEHICLE_DEFAULT)){
            ModelVehicleDefault vehicleDefault = SingletonGson.getInstance().fromJson(""+script, ModelVehicleDefault.class);
            if(vehicleDefault.getResult().equals("1")) {
                if(progressDialog.isShowing()){
                    progressDialog.hide();
                }
                Toast.makeText(this, ""+vehicleDefault.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }else {
            if(progressDialog.isShowing()){
                progressDialog.hide();
            }
            Toast.makeText(this, getResources().getString(R.string.vehicle_delete_successfully), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }


    @Layout(R.layout.raw_change_car)
    class HolderChangeCar{

        @View(R.id.tv_vehicle_name)
        TextView tvVehicleName;
        @View(R.id.tv_vehicle_colour)
        TextView tvVehicleColour;
        @View(R.id.tv_vehicle_no)
        TextView tvVehicleNo;
        @View(R.id.img_vehicle)
        ImageView imgVehicle;
        @View(R.id.img_vehicle_no_plate)
        ImageView tvVehicleNoPlate;
        @View(R.id.textViewOptions)
        TextView tvViewOption;
        @View(R.id.root)
        LinearLayout root;

        @Position
        int mPosition;

        ModelVehicleList.DataBean.VehicleListBean vehicleListBean;

        public HolderChangeCar(ModelVehicleList.DataBean.VehicleListBean vehicleListBean) {
            this.vehicleListBean = vehicleListBean;
        }

        @Resolve
        void setData(){
            tvVehicleName.setText(vehicleListBean.getVehicle_type_name()+ " " +vehicleListBean.getVehicle_make_name() +" "+vehicleListBean.getVehicle_model_name());
            tvVehicleColour.setText(vehicleListBean.getVehicle_color());
            tvVehicleNo.setText(vehicleListBean.getVehicle_number());
            Glide.with(ChangeCarActivity.this).load(vehicleListBean.getVehicle_image()).into(imgVehicle);
            Glide.with(ChangeCarActivity.this).load(vehicleListBean.getVehicle_number_plate()).into(tvVehicleNoPlate);
            if(vehicleListBean.isVehicle_default()){
                root.setBackgroundColor(getResources().getColor(R.color.colorSelectedGrey));
            }else {
                root.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
            tvViewOption.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    PopupMenu popup = new PopupMenu(ChangeCarActivity.this, tvViewOption);
                    //inflating menu from xml resource
                    if(vehicleListBean.isVehicle_default()){
                        popup.inflate(R.menu.change_car_menu_2);
                    }else {
                        popup.inflate(R.menu.change_car_menu);
                    }
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_default:
                                    try{
                                        progressDialog.show();
                                        HashMap<String,String> map = new HashMap<>();
                                        map.put("vehicle_id",""+vehicleList.getData().getVehicle_list().get(mPosition).getId());
                                        apiManager._post(API_S.Tags.VEHICLE_DEFAULT, API_S.Endpoints.VEHICLE_DEFAULT,map,manager);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        if(progressDialog.isShowing()){
                                            progressDialog.hide();
                                        }
                                        Toast.makeText(ChangeCarActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case R.id.menu_dlt:
                                    try{
                                        progressDialog.show();
                                        HashMap<String,String> map = new HashMap<>();
                                        map.put("vehicle_id",""+vehicleList.getData().getVehicle_list().get(mPosition).getId());
                                        apiManager._post(API_S.Tags.VEHICLE_DELETE, API_S.Endpoints.VEHICLE_DELETE,map,manager);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        if(progressDialog.isShowing()){
                                            progressDialog.hide();
                                        }
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
        }
    }
}
