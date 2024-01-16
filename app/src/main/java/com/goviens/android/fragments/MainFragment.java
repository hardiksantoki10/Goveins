package com.goviens.android.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.activities.NotificationActivity;
import com.goviens.android.activities.RideSearchResultActivity;
import com.goviens.android.activities.SaveAddressActivity;
import com.goviens.android.models.ModelSearch;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.ApporioLog;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import butterknife.ButterKnife;

public class MainFragment extends Fragment implements OnMapReadyCallback, ApiManager.APIFETCHER {

    private GoogleMap mGoogleMap;
    private ApiManager apiManagerNew;
    private SessionManager sessionManager;
    private LatLng PICK_LOCATION, DROP_LOCATION, FAV_LOCATION, CURRENT_LOCATION;
    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;

    Location targetLocation;
    View locationButtonView;
    SupportMapFragment mapFragment;
    Location userLocation;

    boolean todayDate = false;

    TextView tvPickup;
    TextView tvDrop;
    LinearLayout linearOption;
    LinearLayout linearPickupDtTm;

    TextView tvStartDt;
    TextView tvReturnDt;
    EditText etSeat;

    Button btnSearch;

    CheckBox checkReturn;


    String pickTimestamp = "";
    String returnTimestamp = "";


    String acChecked="0";
    String femaleChecked = "0";
    String cashChecked = "0";


    boolean[] checkedColors;


    int yer;
    int mnt,day;
    float bearing;
    ProgressDialog progressDialog;

    Context mContext;

    private String RETUENDATE = "", RETUENTIME = "", DEPARTUREDATE = "", DEPARTURETIME = "";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        sessionManager = new SessionManager(getActivity());
        apiManagerNew = new ApiManager(this,getActivity());
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_frag_main_screen);
        mapFragment.getMapAsync(this);
        locationButtonView = mapFragment.getView();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        tvPickup = rootView.findViewById(R.id.tv_pickup);
        tvDrop = rootView.findViewById(R.id.tv_drop);
        linearOption = rootView.findViewById(R.id.option_Linear);
        linearPickupDtTm = rootView.findViewById(R.id.linear_pickup_dt_tm);
        tvStartDt = rootView.findViewById(R.id.tv_start_dt);
        tvReturnDt = rootView.findViewById(R.id.tv_return_dt);
        etSeat = rootView.findViewById(R.id.et_seat);
        btnSearch = rootView.findViewById(R.id.btn_search);
        checkReturn = rootView.findViewById(R.id.id_check_return);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), RideSearchResultActivity.class);
//                    intent.putExtra("script", "");
//                    intent.putExtra("from",tvPickup.getText().toString());
//                    intent.putExtra("to",tvDrop.getText().toString());
//                    intent.putExtra("pickup_latitude",""+PICK_LOCATION.latitude);
//                    intent.putExtra("pickup_longitude",""+PICK_LOCATION.longitude);
//                    intent.putExtra("pickup_location",tvPickup.getText().toString());
//                    intent.putExtra("drop_latitude",""+DROP_LOCATION.latitude);
//                    intent.putExtra("drop_longitude",""+DROP_LOCATION.longitude);
//                    intent.putExtra("drop_location",""+tvDrop.getText().toString());
//                    intent.putExtra("no_of_seats",""+etSeat.getText().toString());
//                    intent.putExtra("ride_timestamp",""+pickTimestamp);
//                    intent.putExtra("ac_ride",acChecked);
//                    intent.putExtra("female_ride",femaleChecked);
//                    intent.putExtra("payment_type_id",cashChecked);
//                    if(checkReturn.isChecked() || !returnTimestamp.equals("")){
//                        intent.putExtra("return_ride","1");
//                        intent.putExtra("return_ride_timestamp",""+returnTimestamp);
//                    }else {
//                        intent.putExtra("return_ride","0");
//                    }
//                    startActivity(intent);
                if(tvPickup.getText().toString().equals("")){
                    Toast.makeText(mContext, getResources().getString(R.string.enter_departure), Toast.LENGTH_SHORT).show();
                }else if(tvDrop.getText().toString().equals("")){
                    Toast.makeText(mContext, getResources().getString(R.string.enter_drop), Toast.LENGTH_SHORT).show();
                }else if(etSeat.getText().toString().equals("")){
                    Toast.makeText(mContext, getResources().getString(R.string.enter_seats), Toast.LENGTH_SHORT).show();
                }else if(pickTimestamp.equals("")){
                    Toast.makeText(mContext, getResources().getString(R.string.select_date_time), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        progressDialog.show();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("segment_id", "15");
                        map.put("pickup_latitude", "" + PICK_LOCATION.latitude);
                        map.put("pickup_longitude", "" + PICK_LOCATION.longitude);
                        map.put("pickup_location", tvPickup.getText().toString());
                        map.put("drop_latitude", "" + DROP_LOCATION.latitude);
                        map.put("drop_longitude", "" + DROP_LOCATION.longitude);
                        map.put("drop_location", "" + tvDrop.getText().toString());
                        map.put("no_of_seats", "" + etSeat.getText().toString());
                        map.put("ride_timestamp", "" + pickTimestamp);
                        map.put("ac_ride", acChecked);
                        map.put("female_ride", "0");
                        map.put("payment_type_id", cashChecked);
                        if (checkReturn.isChecked() || !returnTimestamp.equals("")) {
                            map.put("return_ride", "1");
                            map.put("return_ride_timestamp", "" + returnTimestamp);
                        } else {
                            map.put("return_ride", "0");
                        }
                        apiManagerNew._post(API_S.Tags.RIDE_SEARCH, API_S.Endpoints.RIDE_SEARCH, map, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                        progressDialog.hide();
                    }
                }
            }
        });
        checkReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkReturn.isChecked()){
                    tvReturnDt.setText(getResources().getString(R.string.select_date));
                    returnTimestamp="";
                }
            }
        });
        linearOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
                    builderSingle.setTitle(R.string.cancel);
                    String[] colors = new String[]{
                            getResources().getString(R.string.ac),
                            getResources().getString(R.string.cash_also),
//                            "Female Only",
                    };

                    if(acChecked.equals("1") && femaleChecked.equals("1") && cashChecked.equals("1")){
                        checkedColors = new boolean[]{
                                true, // Ac
                                true, // Cash
//                                true, // Female
                        };
                    }else if(femaleChecked.equals("1") && acChecked.equals("1")){
                        checkedColors = new boolean[]{
                                true, // Ac
                                false, // Cash
//                                true, // Female
                        };
                    }else if(femaleChecked.equals("1") && cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                false, // Ac
                                true, // Cash
//                                true, // Female
                        };
                    }else if(acChecked.equals("1") && cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                true, // Ac
                                true, // Cash
//                                false, // Female
                        };
                    }else if(cashChecked.equals("1")){
                        checkedColors = new boolean[]{
                                false, // Red
                                true, // Green
//                                false, // Blue
                        };
                    }else if(acChecked.equals("1")){
                        checkedColors = new boolean[]{
                                true, // Red
                                false, // Green
//                                false, // Blue
                        };
                    }
                    else if(femaleChecked.equals("1")){
                        checkedColors = new boolean[]{
                                false, // Red
                                false, // Green
//                                true, // Blue
                        };
                    }else {
                        checkedColors = new boolean[]{
                                false, // Red
                                false, // Green
//                                false, // Blue
                        };
                    }

                    // Convert the color array to list
                    final List<String> colorsList = Arrays.asList(colors);
                    builderSingle.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            // Update the current focused item's checked status
                            checkedColors[which] = isChecked;

                            // Get the current focused item
                            String currentItem = colorsList.get(which);
                            if (colorsList.get(which).equals(getResources().getString(R.string.ac))) {
                                if (isChecked) {
                                    acChecked = "1";
                                } else {
                                    acChecked = "0";
                                }
                            }
//                            if (colorsList.get(which).equals("Female Only")) {
//                                if (isChecked) {
//                                    femaleChecked = "1";
//                                } else {
//                                    femaleChecked = "0";
//                                }
//                            }
                            if (colorsList.get(which).equals(getResources().getString(R.string.cash_also))) {
                                if (isChecked) {
                                    cashChecked = "1";
                                } else {
                                    cashChecked = "0";
                                }
                            }
                        }
                    });

                    // Specify the dialog is not cancelable
                    builderSingle.setCancelable(false);

                    // Set a title for alert dialog
                    builderSingle.setTitle(getResources().getString(R.string.options));

                    // Set the positive/yes button click listener
                    builderSingle.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when click positive button
                            //tv.setText("Your preferred colors..... \n");
                            for (int i = 0; i < checkedColors.length; i++) {
                                boolean checked = checkedColors[i];
                                if (checked) {
                                    // tv.setText(tv.getText() + colorsList.get(i) + ", ");
                                }
                            }
                        }
                    });

                    AlertDialog dialog = builderSingle.create();
                    // Display the alert dialog on interface
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        linearPickupDtTm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.DatePickerDialog.OnDateSetListener ondate = new android.app.DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        yer = year;
                        mnt = monthOfYear+1;
                        day = dayOfMonth;


                        java.text.DateFormat dateFormat = new SimpleDateFormat("dd-MM", Locale.US);
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.set(year, monthOfYear, dayOfMonth);
                        DEPARTUREDATE = dateFormat.format(cal.getTime());

                        Date chosenDate = cal.getTime();


                        if (new Date().before(chosenDate)) {
                            todayDate = false;
                        }else {
                            todayDate = true;
                        }

                        android.app.TimePickerDialog.OnTimeSetListener ontime = new android.app.TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar datetime = Calendar.getInstance();
                                Calendar c = Calendar.getInstance();
                                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                datetime.set(Calendar.MINUTE, minute);

                                if(todayDate) {
                                    if (datetime.getTimeInMillis() >= c.getTimeInMillis()) {
                                        //it's after current
                                        int hour = hourOfDay % 12;
                                        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                        String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                        DEPARTURETIME = hourString + ":" + minuteString;
                                        pickTimestamp = AppUtils.createTimestamp(mnt, day, yer, hourOfDay, minute);
                                        tvStartDt.setText(DEPARTUREDATE + " " + DEPARTURETIME);
                                    } else {
                                        Toast.makeText(getActivity(), getResources().getString(R.string.invalid_time), Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                    String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                    DEPARTURETIME = hourString + ":" + minuteString;
                                    pickTimestamp = AppUtils.createTimestamp(mnt, day, yer, hourOfDay, minute);
                                    tvStartDt.setText(DEPARTUREDATE + " " + DEPARTURETIME);
                                }
                            }
                        };
                        TimePickerFragment time = new TimePickerFragment();
                        Calendar calender = Calendar.getInstance();
                        Bundle args = new Bundle();
                        args.putInt("hour", calender.get(Calendar.HOUR));
                        args.putInt("minute", calender.get(Calendar.MINUTE));
                        time.setCallBack(ontime);
                        time.show(getFragmentManager(), "Date Picker");
                    }
                };
                DatePickerFragment date = new DatePickerFragment();
                /**
                 * Set Up Current Date Into dialog
                 */
                Calendar calender = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("year", calender.get(Calendar.YEAR));
                args.putInt("month", calender.get(Calendar.MONTH));
                args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
                date.setArguments(args);
                /**
                 * Set Call back to capture selected date
                 */
                date.setCallBack(ondate);
                date.show(getFragmentManager(), "Date Picker");
            }
        });

        tvReturnDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.DatePickerDialog.OnDateSetListener ondate = new android.app.DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        yer = year;
                        mnt = monthOfYear+1;
                        day = dayOfMonth;

                        java.text.DateFormat dateFormat = new SimpleDateFormat("dd-MM",Locale.US);
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.set(year, monthOfYear, dayOfMonth);

                        RETUENDATE = dateFormat.format(cal.getTime());

                        android.app.TimePickerDialog.OnTimeSetListener ontime = new android.app.TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                checkReturn.setChecked(true);
                                String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                RETUENTIME = hourString + ":" + minuteString;
                                returnTimestamp = AppUtils.createTimestamp(mnt,day,yer,hourOfDay,minute);
                                tvReturnDt.setText(RETUENDATE+" "+RETUENTIME);
                            }
                        };
                        TimePickerFragment time = new TimePickerFragment();
                        Calendar calender = Calendar.getInstance();
                        Bundle args = new Bundle();
                        args.putInt("hour", calender.get(Calendar.HOUR));
                        args.putInt("minute", calender.get(Calendar.MINUTE));
                        time.setCallBack(ontime);
                        time.show(getFragmentManager(), "Date Picker");
                    }
                };
                DatePickerFragment date = new DatePickerFragment();
                /**
                 * Set Up Current Date Into dialog
                 */
                Calendar calender = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("year", calender.get(Calendar.YEAR));
                args.putInt("month", calender.get(Calendar.MONTH));
                args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
                date.setArguments(args);
                /**
                 * Set Call back to capture selected date
                 */
                date.setCallBack(ondate);
                date.show(getFragmentManager(), "Date Picker");
            }
        });

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), SaveAddressActivity.class)
                        .putExtra("for", "0")
                        .putExtra("from","pickup")
                        .putExtra("user_bearing", ""+bearing);
                startActivityForResult(intent,112);
            }
        });

        tvDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SaveAddressActivity.class)
                        .putExtra("for", "0")
                        .putExtra("from","dropoff")
                        .putExtra("user_bearing", ""+bearing);
                startActivityForResult(intent,112);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(null!= data.getStringExtra("pick_point")){
                //setPickUpPoint =1;
                tvPickup.setText(data.getStringExtra("pick_point"));
                PICK_LOCATION = new LatLng(Double.parseDouble("" + data.getExtras().getString("lat")), Double.parseDouble("" + data.getExtras().getString("lng")));
            }else {
                tvDrop.setText(data.getStringExtra("drop_point"));
                DROP_LOCATION = new LatLng(Double.parseDouble("" + data.getExtras().getString("lat")), Double.parseDouble("" + data.getExtras().getString("lng")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification :
                Intent intent = new Intent(getActivity(),NotificationActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notification_menu   ,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onAttach(Context context) {
        this.mContext =context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }

        return true;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.setMaxZoomPreference(20);

        if (checkPermission())
            onLocationPermissionGranted();

        try {
            sessionManager.setLocation(String.valueOf(mGoogleMap.getCameraPosition().target.latitude), String.valueOf((mGoogleMap.getCameraPosition().target.longitude)));
        } catch (Exception e) {

        }

//        samLocationRequestService.executeService(new SamLocationRequestService.SamLocationListener() {
//            @Override
//            public void onLocationUpdate(Location location) {
//                CURRENT_LOCATION = new LatLng(location.getLatitude(), location.getLongitude());
//                OneSignal.sendTag("user_location", "" + location.getLatitude() + "," + location.getLongitude());
//                mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(Config.MapDefaultZoom).build()));
//            }
//        });

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                DROP_LOCATION = new LatLng(mGoogleMap.getCameraPosition().target.latitude, mGoogleMap.getCameraPosition().target.longitude);

                try {
                    if (CURRENT_LOCATION != null) {

                        targetLocation = new Location("");//provider name is unnecessary
                        targetLocation.setLatitude(mGoogleMap.getCameraPosition().target.latitude);//your coords of course
                        targetLocation.setLongitude(mGoogleMap.getCameraPosition().target.longitude);

                        sessionManager.setLocation(String.valueOf(mGoogleMap.getCameraPosition().target.latitude), String.valueOf((mGoogleMap.getCameraPosition().target.longitude)));

                        try {
                           // new GetAddressTask(mContext.getApplicationContext()).execute(targetLocation);
                        } catch (Exception e) {

                        }

//                        data.clear();
//                        data.put("latitude", "" + mGoogleMap.getCameraPosition().target.latitude);
//                        data.put("longitude", "" + mGoogleMap.getCameraPosition().target.longitude);
//                        apiManagerNew._post(API_S.Tags.REVERSE_GEOCODE, API_S.Endpoints.REVERSE_GEOCODE, data, sessionManager);
                    }
                } catch (Exception e) {
                    ApporioLog.logE("", "Exception caught while calling API " + e.getMessage());
                }
            }
        });
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
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            userLocation = location;
                            bearing = userLocation.getBearing();

                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()))
                                    .zoom(17)
                                    .build();

                            CURRENT_LOCATION = new LatLng(location.getLatitude(), location.getLongitude());
                            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
        if(APINAME.equals(API_S.Tags.RIDE_SEARCH)) {
            try {
                progressDialog.hide();
                ModelSearch search = SingletonGson.getInstance().fromJson("" + script, ModelSearch.class);
                if (search.getResult().equals("1")) {
                    Intent intent = new Intent(getActivity(), RideSearchResultActivity.class);
                    intent.putExtra("script", "" + script);
                    intent.putExtra("from", tvPickup.getText().toString());
                    intent.putExtra("to", tvDrop.getText().toString());
                    intent.putExtra("pickup_latitude", "" + PICK_LOCATION.latitude);
                    intent.putExtra("pickup_longitude", "" + PICK_LOCATION.longitude);
                    intent.putExtra("pickup_location", tvPickup.getText().toString());
                    intent.putExtra("drop_latitude", "" + DROP_LOCATION.latitude);
                    intent.putExtra("drop_longitude", "" + DROP_LOCATION.longitude);
                    intent.putExtra("drop_location", "" + tvDrop.getText().toString());
                    intent.putExtra("no_of_seats", "" + etSeat.getText().toString());
                    intent.putExtra("ride_timestamp", "" + pickTimestamp);
                    intent.putExtra("ac_ride", acChecked);
                    intent.putExtra("female_ride", "0");
                    intent.putExtra("payment_type_id", cashChecked);
                    if (checkReturn.isChecked() || !returnTimestamp.equals("")) {
                        intent.putExtra("return_ride", "1");
                        intent.putExtra("return_ride_timestamp", "" + returnTimestamp);
                    } else {
                        intent.putExtra("return_ride", "0");
                    }
                    startActivity(intent);
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(mContext, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(mContext, ""+script, Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class GetAddressTask extends AsyncTask<Location, Void, String> {
        Context mContext;

        public GetAddressTask(Context context) {
            super();
            mContext = context;
        }

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
                            targetLocation.getLongitude() + "&key=" + AppUtils.decodeMapKey(BuildConfig.MAP_KEY) + "&sensor=true");
                } catch (Exception e) {
                    ApporioLog.logE("" , "Exception caught while calling API " + e.getMessage());
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

               // SELECTED_CITY = address.getLocality();


                // Return the text
                return addressText.toString();
            } else {
                try {
                    apiManagerNew._getgoogleAPI(API_S.Tags.GOOGLE_PLACE_PICKER, "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + targetLocation.getLatitude() + "," +
                            targetLocation.getLongitude() + "&key=" + AppUtils.decodeMapKey(BuildConfig.MAP_KEY) + "&sensor=true");
                } catch (Exception e) {
                    ApporioLog.logE("" , "Exception caught while calling API " + e.getMessage());
                }
                return "";
            }
        }

        @Override
        protected void onPostExecute(String address) {
            try {

                if (!address.equals("")) {

                        if (address.contains("Unnamed")) {
                            try {
                                apiManagerNew._getgoogleAPI(API_S.Tags.GOOGLE_PLACE_PICKER, "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + targetLocation.getLatitude() + "," +
                                        targetLocation.getLongitude() + "&key=" + AppUtils.decodeMapKey(BuildConfig.MAP_KEY) + "&sensor=true");
                            } catch (Exception e) {
                                ApporioLog.logE("", "Exception caught while calling API " + e.getMessage());
                            }
                        } else {
                           // setAddressTet("" + address);
                        }
                    }
                    //progressBar.setVisibility(View.GONE);
                    if (sessionManager.getAppConfig().getData().getGeneral_config().isHomescreen_estimate_fare()) {
                        try {

                            //callViewCarsApi(address, SELECTED_CITY);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                           // callViewCarsApi(address, SELECTED_CITY);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

            } catch (Exception e) {

            }
        }
    }
}
