package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityLocationSelectionBinding;
import com.goviens.android.models.ModelGetAddress;
import com.goviens.android.models.ModelGoogleApiStatus;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.ApporioLog;
import com.goviens.android.utils.IntentKeys;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;



import butterknife.ButterKnife;

public class LocationSelectionActivity extends AppCompatActivity implements OnMapReadyCallback,ApiManager.APIFETCHER {

    private ActivityLocationSelectionBinding mBinding;
    SupportMapFragment mapFragment;
    View locationButtonView;
    private GoogleMap mGoogleMap;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;
    private FusedLocationProviderClient mFusedLocationClient;
    Location userLocation;
    private LatLng PICK_LOCATION, DROP_LOCATION, FAV_LOCATION, CURRENT_LOCATION;
    Location targetLocation;
    SessionManager sessionManager;
    ApiManager apiManagerNew;
    private String SELECTED_CITY_DROP = "", SELECTED_CITY = "";
    private final String TAG = "DropLocation";
    private final int PLACE_PICKER_ACTIVITY = 111;
    private boolean IS_FAV_SHOW = false;

    String location_type;
    double lat, lng;

    private String set_country;

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 332;

    boolean favDrop = false;

    private HashMap<String, String> data = new HashMap<>();

    String forr = "";

    ProgressDialog progressDialog;

    String finalLocation = "";
    boolean searchLocation = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLocationSelectionBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_frag_main);
        locationButtonView = mapFragment.getView();
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        apiManagerNew = new ApiManager(this, this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        forr = getIntent().getStringExtra("for");

        if(forr.equals("0")) {
            location_type = getIntent().getStringExtra("from");
        }else {
            favDrop = true;
            mBinding.dropFavImage.setImageResource(R.drawable.dark_heart);
        }

        mBinding.dropFavImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forr.equals("0")) {
                    if (favDrop) {
                        favDrop = false;
                        mBinding.dropFavImage.setImageResource(R.drawable.light_heart);
                    } else {
                        favDrop = true;
                        mBinding.dropFavImage.setImageResource(R.drawable.dark_heart);
                    }
                }
            }
        });

        mBinding.favLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startPlacePickerActivity();
            }
        });


        mBinding.dropPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    openGooglePlaceAPiDialoge();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        mBinding.selectLoaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forr.equals("0")) {
                    if (favDrop) {
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("house_name", "abc");
                        map.put("building", "abc");
                        map.put("address", finalLocation);
                        map.put("latitude", "" + lat);
                        map.put("longitude", "" + lng);
                        map.put("category", "3");
                        try {
                            apiManagerNew._post(API_S.Tags.SAVE_ADDRESS, API_S.Endpoints.SAVE_ADDRESS, map, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (location_type.equals("pickup")) {
                        Intent intent = new Intent();
                        intent.putExtra("pick_point", finalLocation)
                                .putExtra("lat", "" + lat)
                                .putExtra("lng", "" + lng);
                        setResult(RESULT_OK, intent);
                        finish();

                    } else if (location_type.equals("dropoff")) {
                        Intent intent = new Intent();
                        intent.putExtra("drop_point", finalLocation)
                                .putExtra("lat", "" + lat)
                                .putExtra("lng", "" + lng);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("stop", finalLocation)
                                .putExtra("lat", "" + lat)
                                .putExtra("lng", "" + lng);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } else {
                    progressDialog.show();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("house_name", "abc");
                    map.put("building", "abc");
                    map.put("address", "" + mBinding.dropLocation.getText().toString());
                    map.put("latitude", "" + lat);
                    map.put("longitude", "" + lng);
                    map.put("category", "3");
                    try {
                        apiManagerNew._post(API_S.Tags.SAVE_ADDRESS, API_S.Endpoints.SAVE_ADDRESS, map, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void openGooglePlaceAPiDialogee() throws Exception {

        String phone_code = "" + sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE);
        if (phone_code.equals("+91")) {
            set_country = "IN";
        } else if (phone_code.equals("+41")) {
            set_country = "CH";
        } else {
            set_country = "EG";
        }

        Double LAT, LOG;
        Double earth_radius = 6378.1;
        Double bearing = Math.toRadians(Double.parseDouble(getIntent().getStringExtra("user_bearing")));
        int distance = 50;

        Double LAT1 = Math.toRadians(mGoogleMap.getCameraPosition().target.latitude);
        Double LNG1 = Math.toRadians(mGoogleMap.getCameraPosition().target.longitude);

        LAT = Math.asin(Math.sin(LAT1) * Math.cos(distance / earth_radius) +
                Math.cos(LAT1) * Math.sin(distance / earth_radius) * Math.cos(bearing));

        LOG = LNG1 + Math.atan2(Math.sin(bearing) * Math.sin(distance / earth_radius) * Math.cos(LAT1),
                Math.cos(distance / earth_radius) - Math.sin(LAT1) * Math.sin(LAT));

        LAT = Math.toDegrees(LAT);
        LOG = Math.toDegrees(LOG);

        Log.d("MainACTIVITY", "latkkk" + LAT);
        Log.d("MainACTIVITY", "latkkk" + LOG);
        Log.d("USER_LOCATION", "" + mGoogleMap.getCameraPosition().target.latitude);
        Log.d("USER_LOCATION", "" + mGoogleMap.getCameraPosition().target.longitude);


        Log.e("ApiKey", "" + AppUtils.decodeMapKey(BuildConfig.MAP_KEY));
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), AppUtils.decodeMapKey(BuildConfig.MAP_KEY));
        }

        List<Place.Field> fields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, fields)
                .setLocationBias(RectangularBounds.newInstance(
                        new LatLng(mGoogleMap.getCameraPosition().target.latitude, mGoogleMap.getCameraPosition().target.longitude),
                        new LatLng(LAT, LOG)))
                .build(this);
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }

    private void openGooglePlaceAPiDialoge() throws Exception {

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), AppUtils.decodeMapKey(BuildConfig.MAP_KEY));
        }

        List<Place.Field> fields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields)
                .build(this);
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
    }

    private void startPlacePickerActivity() {


//        Intent intent = new Intent(LocationSelectionActivity.this, NewPlacePickerActivity.class)
//                .putExtra("" + IntentKeys.LATITUDE, "" + mGoogleMap.getCameraPosition().target.latitude)
//                .putExtra("" + IntentKeys.LONGITUDE, "" + mGoogleMap.getCameraPosition().target.longitude)
//                .putExtra("user_bearing", getIntent().getStringExtra("user_bearing"));
//        startActivityForResult(intent, PLACE_PICKER_ACTIVITY);

    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }


    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void onLocationPermissionGranted() {
        if (!checkPermission()) return;

        mGoogleMap.setMyLocationEnabled(true);

        View locationButton = ((View) locationButtonView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        // and next place it, on bottom right (as Google Maps app)
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                locationButton.getLayoutParams();
        // position on right bottom
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        layoutParams.setMargins(0, 0, 30, 200);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            userLocation = location;


                            CURRENT_LOCATION = new LatLng(location.getLatitude(), location.getLongitude());
                            //OneSignal.sendTag("user_location", "" + location.getLatitude() + "," + location.getLongitude());
                            if (lat == 0.0 && lng == 0.0) {
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()))
                                        .zoom(17)
                                        .build();
                                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            } else {
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()))
                                        .zoom(17)
                                        .build();
                                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            }
                        } else {
                            userLocation = null;
                        }
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        mGoogleMap.clear();

        mGoogleMap.setMaxZoomPreference(20);

        if (checkPermission())
            onLocationPermissionGranted();

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                // setLocationAndIcon(mGoogleMap.getCameraPosition().target);

                //calling API


                DROP_LOCATION = new LatLng(mGoogleMap.getCameraPosition().target.latitude, mGoogleMap.getCameraPosition().target.longitude);

                lat = mGoogleMap.getCameraPosition().target.latitude;
                lng = mGoogleMap.getCameraPosition().target.longitude;


                try {
                    if (CURRENT_LOCATION != null) {

                        targetLocation = new Location("");//provider name is unnecessary
                        targetLocation.setLatitude(mGoogleMap.getCameraPosition().target.latitude);//your coords of course
                        targetLocation.setLongitude(mGoogleMap.getCameraPosition().target.longitude);

                        sessionManager.setLocation(String.valueOf(mGoogleMap.getCameraPosition().target.latitude), String.valueOf((mGoogleMap.getCameraPosition().target.longitude)));

                        try {
                            new LocationSelectionActivity.GetAddressTask(LocationSelectionActivity.this.getApplicationContext()).execute(targetLocation);
                        } catch (Exception e) {

                        }

//                        data.clear();
//                        data.put("latitude", "" + mGoogleMap.getCameraPosition().target.latitude);
//                        data.put("longitude", "" + mGoogleMap.getCameraPosition().target.longitude);
//                        apiManagerNew._post(API_S.Tags.REVERSE_GEOCODE, API_S.Endpoints.REVERSE_GEOCODE, data, sessionManager);
                    }
                } catch (Exception e) {
                    ApporioLog.logE("error ", "Exception caught while calling API " + e.getMessage());
                }
            }
        });

    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        switch (APINAME) {

            case API_S.Tags.GOOGLE_PLACE_PICKER:

                Log.e("Obejetc", "" + script);

                ModelGoogleApiStatus modelGoogleApiStatus = SingletonGson.getInstance().fromJson("" + script, ModelGoogleApiStatus.class);
                if (modelGoogleApiStatus.getStatus().equals("OK")) {

                    ModelGetAddress modelGetAddress = SingletonGson.getInstance().fromJson("" + script, ModelGetAddress.class);

                    try {

                        try {
                            SELECTED_CITY = modelGetAddress.getResults().get(0).getAddress_components().get(3).getShort_name();
                        } catch (Exception e) {

                        }

                        String finalAddress = modelGetAddress.getResults().get(0).getFormatted_address();
                        //   Toast.makeText(MainActivity.this, ""+finalAddress, Toast.LENGTH_SHORT).show();
                        finalLocation = finalAddress;
                        setAddressTet("" + finalAddress);

                    } catch (Exception e) {

                    }

                }
                break;
//            case API_S.Tags.ADD_FAVOURITE_LOCATION:
//
//                if (location_type.equals("pickup")) {
//                    Intent intent = new Intent();
//                    intent.putExtra("pick_point", "" + drop_location.getText().toString())
//                            .putExtra("lat", "" + lat)
//                            .putExtra("lng", "" + lng);
//                    setResult(RESULT_OK, intent);
//                    finish();
//
//                } else {
//                    Intent intent = new Intent();
//                    intent.putExtra("drop_point", "" + drop_location.getText().toString())
//                            .putExtra("lat", "" + lat)
//                            .putExtra("lng", "" + lng);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//                break;
            case API_S.Tags.SAVE_ADDRESS:
                progressDialog.hide();
                if(!forr.equals("0")){
                    finish();
                }
                break;
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
        progressDialog.hide();
    }


    private class GetAddressTask extends AsyncTask<Location, Void, String> {
        Context mContext;

        public GetAddressTask(Context context) {
            super();
            mContext = context;
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Location... params) {
            Geocoder geocoder =
                    new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            Location loc = params[0];
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
            } catch (IOException e1) {
                Log.e("LocationSampleActivity",
                        "IO Exception in getFromLocation()");
                e1.printStackTrace();
                try {
                    apiManagerNew._getgoogleAPI(API_S.Tags.GOOGLE_PLACE_PICKER, "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + targetLocation.getLatitude() + "," +
                            targetLocation.getLongitude() + "&key=" + BuildConfig.MAP_KEY + "&sensor=true");
                } catch (Exception e) {
                    ApporioLog.logE("errorwhilecalling", "Exception caught while calling API " + e.getMessage());
                }
                return ("");
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments " +
                        Double.toString(loc.getLatitude()) +
                        " , " +
                        Double.toString(loc.getLongitude()) +
                        " passed to address service";
                Log.e("LocationSampleActivity", errorString);
                e2.printStackTrace();
                return errorString;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);


                /*
                 * Format the first line of address (if available),
                 * city, and country name.
                 */
                Log.i("***Address", "" + address);

                String addressText = null;

                addressText = String.format(
                        "%s",
                        // If there's a street address, add it
                        address.getMaxAddressLineIndex() > 0 ?
                                "" : address.getAddressLine(0));


                SELECTED_CITY = address.getLocality();


                Log.e("SELECTED_CITY", "" + SELECTED_CITY);

                // Return the text
                return addressText.toString();
            } else {
                try {
                    apiManagerNew._getgoogleAPI(API_S.Tags.GOOGLE_PLACE_PICKER, "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + targetLocation.getLatitude() + "," +
                            targetLocation.getLongitude() + "&key=" + BuildConfig.MAP_KEY + "&sensor=true");
                } catch (Exception e) {
                    ApporioLog.logE("" + TAG, "Exception caught while calling API " + e.getMessage());
                }
                return "";
            }
        }

        @Override
        protected void onPostExecute(String address) {
            try {
                // Toast.makeText(mContext, address, Toast.LENGTH_SHORT).show()
                if(searchLocation) {
                    searchLocation = false;
                }else {
                    finalLocation = address;
                    setAddressTet("" + address);
                }
                mBinding.pickupPoint.setText("" + SELECTED_CITY);

            } catch (Exception e) {

            }
        }
    }


    private void setAddressTet(String Address) {
        mBinding.dropLocation.setText("" + Address);
    }

    private void finalizeActivity(String addressdescription, String latitude, String longitude) {
        if (location_type.equals("pickup")) {
            Intent intent = new Intent();
            intent.putExtra("pick_point", "" + addressdescription)
                    .putExtra("lat", "" + latitude)
                    .putExtra("lng", "" + longitude);
            setResult(RESULT_OK, intent);
            finish();
        } else if (location_type.equals("dropoff")) {
            Intent intent = new Intent();
            intent.putExtra("drop_point", "" + addressdescription)
                    .putExtra("lat", "" + latitude)
                    .putExtra("lng", "" + longitude);
            setResult(RESULT_OK, intent);
            finish();
        }else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case PLACE_PICKER_ACTIVITY:

                try {
                    if (!data.getExtras().getString(IntentKeys.ADDRESS_NAME).equals("")) {

                        setAddressTet("" + data.getExtras().getString(IntentKeys.ADDRESS_NAME));
                        lat = Double.parseDouble(data.getExtras().getString(IntentKeys.LATITUDE));
                        lng = Double.parseDouble(data.getExtras().getString(IntentKeys.LONGITUDE));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(lat, lng))
                                .zoom(17)
                                .build();
                        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                } catch (Exception e) {

                }

                break;

            case PLACE_AUTOCOMPLETE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = Autocomplete.getPlaceFromIntent(data);
                    Log.d("*#*# getAddress", "" + place.getAddress());
                    Log.d("*#*# getAttributions", "" + place.getAttributions());

                    Log.d("*#*# getlatlng", "" + place.getLatLng());
                    Log.d("*#*# getname", "" + place.getName());
                    Log.d("*#*# getId", "" + place.getId());
                    Log.d("*#*# geWebsiteURI", "" + place.getWebsiteUri());

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(place.getLatLng().latitude, place.getLatLng().longitude))
                            .zoom(17)
                            .build();
                    mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    //finalizeActivity(place.getName(),String.valueOf(place.getLatLng().latitude),String.valueOf(place.getLatLng().longitude));
                    searchLocation = true;
                    mBinding.dropAddressTxt.setText("" + place.getName());
                    if(place.getAddress().contains(place.getName())){
                        mBinding.dropLocation.setText("" + place.getAddress());
                        finalLocation = place.getAddress();
                    }else {
                        mBinding.dropLocation.setText(place.getName() + " " + place.getAddress());
                        finalLocation = place.getName() + " " + place.getAddress();
                    }


                } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                    Status status = Autocomplete.getStatusFromIntent(data);
                    Log.i("*****", status.getStatusMessage());
                } else if (resultCode == RESULT_CANCELED) {
                } else if (resultCode == RESULT_CANCELED) {
                }
        }
    }
}