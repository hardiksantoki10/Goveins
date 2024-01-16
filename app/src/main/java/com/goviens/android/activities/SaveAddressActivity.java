package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.models.ModelAddressList;
import com.goviens.android.models.ModelDeleteAddress;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.HashMap;


import butterknife.ButterKnife;

public class SaveAddressActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager manager ;
    SessionManager sessionManager;

    PlaceHolderView placeHolderAddress;
    ImageView imgAdd;

    ImageView imgBack;
    Button btnMap;
    float bearing;
    private FusedLocationProviderClient mFusedLocationClient;
    Location userLocation;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_address);
        ButterKnife.bind(this);
        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        if(getIntent().getStringExtra("for").equals("1")){
            btnMap.setVisibility(android.view.View.GONE);
            imgAdd.setVisibility(android.view.View.VISIBLE);
        }else {
            btnMap.setVisibility(android.view.View.VISIBLE);
            imgAdd.setVisibility(android.view.View.GONE);
        }
        imgAdd.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(SaveAddressActivity.this,LocationSelectionActivity.class)
                         .putExtra("for", "1")
                        .putExtra("user_bearing", ""+bearing));
            }
        });
        btnMap.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent  = new Intent(SaveAddressActivity.this, LocationSelectionActivity.class)
                        .putExtra("for", getIntent().getStringExtra("for"))
                        .putExtra("from",getIntent().getStringExtra("from"))
                        .putExtra("user_bearing", getIntent().getStringExtra("user_bearing"));
                startActivityForResult(intent,112);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        try{
            progressDialog.show();
            placeHolderAddress.removeAllViews();
            manager._post(API_S.Tags.ADDRESS_LIST, API_S.Endpoints.ADDRESS_LIST,null,sessionManager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==112){
            try {
                if (null != data.getStringExtra("pick_point")) {
                    if (getIntent().getStringExtra("from").equals("pickup")) {
                        Intent intent = new Intent();
                        intent.putExtra("pick_point", "" + data.getStringExtra("pick_point"))
                                .putExtra("lat", "" + data.getExtras().getString("lat"))
                                .putExtra("lng", "" + data.getExtras().getString("lng"));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else if (null != data.getStringExtra("drop_point")) {
                    if (getIntent().getStringExtra("from").equals("dropoff")) {
                        Intent intent = new Intent();
                        intent.putExtra("drop_point", "" + data.getStringExtra("drop_point"))
                                .putExtra("lat", "" + data.getExtras().getString("lat"))
                                .putExtra("lng", "" + data.getExtras().getString("lng"));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }else if (null != data.getStringExtra("stop")) {
                    Intent intent = new Intent();
                    intent.putExtra("drop_point", "" + data.getStringExtra("stop"))
                            .putExtra("lat", "" + data.getExtras().getString("lat"))
                            .putExtra("lng", "" + data.getExtras().getString("lng"));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void onLocationPermissionGranted() {
        if (!checkPermission()) return;
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(SaveAddressActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            bearing = userLocation.getBearing();
                        } else {
                            userLocation = null;
                        }
                    }
                });
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.ADDRESS_LIST)) {
            progressDialog.hide();
            ModelAddressList addressList = SingletonGson.getInstance().fromJson("" + script, ModelAddressList.class);
            if (addressList.getResult().equals("1")) {
                for (int i = 0; i < addressList.getData().size(); i++) {
                    placeHolderAddress.addView(new HolderAddress(addressList.getData().get(i)));
                }
            }
        }else if(APINAME.equals(API_S.Tags.DELETE_ADDRESS)) {
            progressDialog.hide();
            placeHolderAddress.removeAllViews();
            ModelDeleteAddress deleteAddress = SingletonGson.getInstance().fromJson(""+script, ModelDeleteAddress.class);
            if(deleteAddress.getResult().equals("1")){
                try{
                    progressDialog.show();
                    manager._post(API_S.Tags.ADDRESS_LIST, API_S.Endpoints.ADDRESS_LIST,null,sessionManager);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
        progressDialog.hide();
    }

    @Layout(R.layout.raw_save_address)
    class HolderAddress{
        @View(R.id.tv_address_type)
        TextView tvAddressType;
        @View(R.id.img_dlt)
        ImageView imgDlt;
        @View(R.id.img_edit)
        ImageView imgEdit;
        @View(R.id.root)
        LinearLayout root;

        ModelAddressList.DataBean dataBean;

        public HolderAddress(ModelAddressList.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        @Resolve
        void setData(){
            try {
                tvAddressType.setText(dataBean.getAddress());
                imgEdit.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        progressDialog.show();
                        try{
                            manager._post(API_S.Tags.ADDRESS_LIST, API_S.Endpoints.ADDRESS_LIST,null,sessionManager);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                imgDlt.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        progressDialog.show();
                        try{
                            HashMap<String, String> map = new HashMap<>();
                            map.put("address_id",""+dataBean.getId());
                            manager._post(API_S.Tags.DELETE_ADDRESS, API_S.Endpoints.DELETE_ADDRESS,map,sessionManager);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                root.setOnClickListener(new android.view.View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        if(!getIntent().getStringExtra("for").equals("1")) {
                            if (getIntent().getStringExtra("from").equals("pickup")) {
                                Intent intent = new Intent();
                                intent.putExtra("pick_point", "" + dataBean.getAddress())
                                        .putExtra("lat", "" + dataBean.getLatitude())
                                        .putExtra("lng", "" + dataBean.getLongitude());
                                setResult(RESULT_OK, intent);
                                finish();
                            }else if (getIntent().getStringExtra("from").equals("dropoff")) {
                                Intent intent = new Intent();
                                intent.putExtra("drop_point", "" + dataBean.getAddress())
                                        .putExtra("lat", "" + dataBean.getLatitude())
                                        .putExtra("lng", "" + dataBean.getLongitude());
                                setResult(RESULT_OK, intent);
                                finish();
                            }else {
                                Intent intent = new Intent();
                                intent.putExtra("drop_point", "" + dataBean.getAddress())
                                        .putExtra("lat", "" + dataBean.getLatitude())
                                        .putExtra("lng", "" + dataBean.getLongitude());
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(SaveAddressActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
