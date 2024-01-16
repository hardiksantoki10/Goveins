package com.goviens.android.fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.goviens.android.R;
import com.goviens.android.activities.AddCarActivity;
import com.goviens.android.activities.AddStopsActivity;
import com.goviens.android.activities.ChangeCarActivity;
import com.goviens.android.activities.SaveAddressActivity;
import com.goviens.android.models.ModelStepOne;
import com.goviens.android.models.ModelVehicleList;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.AppUtils;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import butterknife.ButterKnife;


public class OfferFragment extends Fragment implements OnMapReadyCallback, ApiManager.APIFETCHER {

    Button btnUploadDocument;
    private OnFragmentInteractionListener mListener;

    private GoogleMap mGoogleMap;
    View locationButtonView;

    TextView tvVehicleName;
    TextView tvVehicleColour;
    TextView tvVehicleNo;

    TextView tvPickup;
    TextView tvDrop;

    RelativeLayout relativeStop1;
    RelativeLayout relativeStop2;
    RelativeLayout relativeStop3;

    SupportMapFragment mapFragment;


    private FusedLocationProviderClient mFusedLocationClient;
    float bearing;

    private LatLng PICK_LOCATION, DROP_LOCATION, FAV_LOCATION, CURRENT_LOCATION;


    TextView tvStop1;
    TextView tvStop2;
    TextView tvStop3;

    ImageView imgStop1;
    ImageView imgStop2;
    ImageView imgStop3;

    EditText etSeat;
    TextView tvStartDt;

    Button btnContinue;

    LinearLayout linearPickupDtTm;

    LinearLayout linearOption;

    TextView tvReturnDt;

    CheckBox checkReturn;

    RelativeLayout relativeNoVehicle;
    RelativeLayout relativeVehicle;

    Button btnAddStops;
        SessionManager manager;
        ApiManager apiManager;
        ProgressDialog progressDialog;
    ModelVehicleList vehicleList;



    boolean todayDate = false;

    JSONArray dropLocationArray = new JSONArray();
    JSONObject dropObject;
    int yer;
    int mnt, day;
    String pickTimestamp = "";
    String returnTimestamp = "";

    String RETUENDATE = "", RETUENTIME = "", DEPARTUREDATE = "", DEPARTURETIME = "";

    private TimePickerDialog tpd;

    boolean stopsThree = false;

    String acChecked = "0";
    String femaleChecked = "0";
    String cashChecked = "0";


    boolean[] checkedColors;


    boolean vehicleActivated = false;

    public OfferFragment() {
        // Required empty public constructor
    }


    public static OfferFragment newInstance() {
        OfferFragment fragment = new OfferFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            progressDialog.show();
            apiManager._post(API_S.Tags.GET_VEHICLE_LIST, API_S.Endpoints.GET_VEHICLE_LIST, null, manager);
            apiManager._post(API_S.Tags.MAIN_SCREEN, API_S.Endpoints.MAIN_SCREEN, null, manager);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offer, container, false);
        ButterKnife.bind(this, rootView);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        manager = new SessionManager(getActivity());
        apiManager = new ApiManager(this, getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        relativeStop1.setVisibility(View.GONE);
        relativeStop2.setVisibility(View.GONE);
        relativeStop3.setVisibility(View.GONE);
        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_frag_main_screen);
        mapFragment.getMapAsync(this);
        locationButtonView = mapFragment.getView();


        tvStop1 = rootView.findViewById(R.id.tv_stop_1);
        tvStop2 = rootView.findViewById(R.id.tv_stop_2);
        tvStop3 = rootView.findViewById(R.id.tv_stop_3);
        imgStop1 = rootView.findViewById(R.id.img_stop_1);
        imgStop2 = rootView.findViewById(R.id.img_stop_2);
        imgStop3 = rootView.findViewById(R.id.img_stop_3);
        etSeat = rootView.findViewById(R.id.et_seat);
        tvStartDt = rootView.findViewById(R.id.tv_start_dt);
        btnContinue = rootView.findViewById(R.id.btn_continue);
        linearOption = rootView.findViewById(R.id.option_Linear);
        tvReturnDt = rootView.findViewById(R.id.tv_return_dt);
        checkReturn = rootView.findViewById(R.id.id_check_return);
        linearPickupDtTm = rootView.findViewById(R.id.linear_pickup_dt_tm);
        relativeNoVehicle = rootView.findViewById(R.id.relative_no_vehicle);
        relativeVehicle = rootView.findViewById(R.id.relative_vehicle);
        btnAddStops = rootView.findViewById(R.id.btn_add_stops);
        tvVehicleName = rootView.findViewById(R.id.tv_vehicle_name);
        tvVehicleColour = rootView.findViewById(R.id.tv_vehicle_colour);
        tvVehicleNo = rootView.findViewById(R.id.tv_vehicle_no);
        tvPickup = rootView.findViewById(R.id.tv_pickup);
        tvDrop = rootView.findViewById(R.id.tv_drop);
        relativeStop1 = rootView.findViewById(R.id.relative_stop_1);
        relativeStop2 = rootView.findViewById(R.id.relative_stop_2);
        relativeStop3 = rootView.findViewById(R.id.relative_stop_3);
        btnUploadDocument = rootView.findViewById(R.id.btn_upload_document);

        btnUploadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddCarActivity.class);
                intent.putExtra("for", "driver");
                startActivity(intent);
            }
        });
        relativeVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangeCarActivity.class));
            }
        });
        btnAddStops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopsThree) {
                    Toast.makeText(getActivity(), "You can not add more stops", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), SaveAddressActivity.class)
                            .putExtra("for", "0")
                            .putExtra("from", "stop")
                            .putExtra("user_bearing", "" + bearing);
                    startActivityForResult(intent, 100);
                }
            }
        });
        imgStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeStop1.setVisibility(View.GONE);
                tvStop1.setText("");
                dropLocationArray.remove(0);
                stopsThree = false;
            }
        });
        imgStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeStop2.setVisibility(View.GONE);
                tvStop2.setText("");
                dropLocationArray.remove(1);
                stopsThree = false;
            }
        });
        imgStop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeStop3.setVisibility(View.GONE);
                tvStop3.setText("");
                dropLocationArray.remove(2);
                stopsThree = false;
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    map.put("female_ride", femaleChecked);
                    map.put("payment_type", cashChecked);
                    if (checkReturn.isChecked() || !returnTimestamp.equals("")) {
                        map.put("return_ride", "1");
                        map.put("return_ride_timestamp", "" + returnTimestamp);
                    } else {
                        map.put("return_ride", "0");
                    }
                    if (dropLocationArray != null || dropLocationArray.length() != 0) {
                        map.put("drop_points", "" + dropLocationArray);
                    }
                    apiManager._post(API_S.Tags.RIDE_STEP_ONE, API_S.Endpoints.RIDE_STEP_ONE, map, manager);
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.hide();
                }
            }
        });
        linearOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());
                    builderSingle.setTitle(R.string.cancel);
                    final String[] colors = new String[]{
                            getResources().getString(R.string.ac),
                            getResources().getString(R.string.cash_also),
                    };


                    if (acChecked.equals("1") && femaleChecked.equals("1") && cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                true, // Ac
                                true, // Cash
//                                true, // Female
                        };
                    } else if (femaleChecked.equals("1") && acChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                true, // Ac
                                false, // Cash
//                                true, // Female
                        };
                    } else if (femaleChecked.equals("1") && cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                false, // Ac
                                true, // Cash
//                                true, // Female
                        };
                    } else if (acChecked.equals("1") && cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                true, // Ac
                                true, // Cash
//                                false, // Female
                        };
                    } else if (cashChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                false, // Red
                                true, // Green
//                                false, // Blue
                        };
                    } else if (acChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                true, // Red
                                false, // Green
//                                false, // Blue
                        };
                    } else if (femaleChecked.equals("1")) {
                        checkedColors = new boolean[]{
                                false, // Red
                                false, // Green
//                                true, // Blue
                        };
                    } else {
                        checkedColors = new boolean[]{
                                false, // Red
                                false, // Green
//                                false, // Blue
                        };
                    }

                    final List<String> colorsList = Arrays.asList(colors);
                    builderSingle.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                            // Update the current focused item's checked status
                            checkedColors[which] = isChecked;

                            if (colorsList.get(which).equals(getResources().getString(R.string.ac))) {
                                if (isChecked) {
                                    acChecked = "1";
                                } else {
                                    acChecked = "0";
                                }
                            }
//                            if(colorsList.get(which).equals("Female Only")){
//                                if(isChecked){
//                                    femaleChecked = "1";
//                                }else {
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
                    dialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        checkReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkReturn.isChecked()) {
                    tvReturnDt.setText(getResources().getString(R.string.select_date));
                    returnTimestamp = "";
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


                        //Toast.makeText(getActivity(), ""+year+monthOfYear, Toast.LENGTH_SHORT).show();
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
                //Calendar cale = Calendar.getInstance();
                //datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                //c.add(Calendar.YEAR, 4); // add 4 years to min date to have 2 years after now
                //date.getDatePicker().setMaxDate(cale.getTimeInMillis());
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
                        mnt = monthOfYear + 1;
                        day = dayOfMonth;

                        java.text.DateFormat dateFormat = new SimpleDateFormat("dd-MM", Locale.US);
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.set(year, monthOfYear, dayOfMonth);

                        RETUENDATE = dateFormat.format(cal.getTime());


                        //   Toast.makeText(getActivity(), ""+year+monthOfYear, Toast.LENGTH_SHORT).show();

                        android.app.TimePickerDialog.OnTimeSetListener ontime = new android.app.TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Toast.makeText(getActivity(), ""+hourOfDay+minute, Toast.LENGTH_SHORT).show();
                                checkReturn.setChecked(true);
                                String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                RETUENTIME = hourString + ":" + minuteString;
                                returnTimestamp = AppUtils.createTimestamp(mnt, day, yer, hourOfDay, minute);
                                tvReturnDt.setText(RETUENDATE + " " + RETUENTIME);

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

        tvPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaveAddressActivity.class)
                        .putExtra("for", "0")
                        .putExtra("from", "pickup")
                        .putExtra("user_bearing", "" + bearing);
                startActivityForResult(intent, 112);
            }
        });

        tvDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SaveAddressActivity.class)
                        .putExtra("for", "0")
                        .putExtra("from", "dropoff")
                        .putExtra("user_bearing", "" + bearing);
                startActivityForResult(intent, 112);
            }
        });
        return rootView;
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
                            bearing = location.getBearing();
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .zoom(17)
                                    .build();

                            CURRENT_LOCATION = new LatLng(location.getLatitude(), location.getLongitude());
                            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        }
                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notification_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 100) {
                if (null != data.getStringExtra("drop_point")) {
                    dropObject = new JSONObject();
                    dropObject.put("latitude", data.getExtras().getString("lat"));
                    dropObject.put("longitude", data.getExtras().getString("lng"));
                    dropObject.put("location", data.getStringExtra("drop_point"));
                    dropLocationArray.put(dropObject);
                    if (dropLocationArray.length() == 1) {
                        if (tvStop1.getText().toString().isEmpty()) {
                            relativeStop1.setVisibility(View.VISIBLE);
                            tvStop1.setText(data.getStringExtra("drop_point"));
                        } else if (tvStop2.getText().toString().isEmpty()) {
                            relativeStop2.setVisibility(View.VISIBLE);
                            tvStop2.setText(data.getStringExtra("drop_point"));
                        } else {
                            relativeStop3.setVisibility(View.VISIBLE);
                            tvStop3.setText(data.getStringExtra("drop_point"));
                            stopsThree = true;
                        }
                    }
                    if (dropLocationArray.length() == 2) {
                        if (tvStop1.getText().toString().isEmpty()) {
                            relativeStop1.setVisibility(View.VISIBLE);
                            tvStop1.setText(data.getStringExtra("drop_point"));
                        } else if (tvStop2.getText().toString().isEmpty()) {
                            relativeStop2.setVisibility(View.VISIBLE);
                            tvStop2.setText(data.getStringExtra("drop_point"));
                        } else {
                            relativeStop3.setVisibility(View.VISIBLE);
                            tvStop3.setText(data.getStringExtra("drop_point"));
                            stopsThree = true;
                        }
                    }
                    if (dropLocationArray.length() == 3) {
                        if (tvStop1.getText().toString().isEmpty()) {
                            relativeStop1.setVisibility(View.VISIBLE);
                            tvStop1.setText(data.getStringExtra("drop_point"));
                        } else if (tvStop2.getText().toString().isEmpty()) {
                            relativeStop2.setVisibility(View.VISIBLE);
                            tvStop2.setText(data.getStringExtra("drop_point"));
                        } else {
                            relativeStop3.setVisibility(View.VISIBLE);
                            tvStop3.setText(data.getStringExtra("drop_point"));
                            stopsThree = true;
                        }
                    }
                }
            } else {
                if (null != data.getStringExtra("pick_point")) {
                    //setPickUpPoint =1;
                    tvPickup.setText(data.getStringExtra("pick_point"));
                    PICK_LOCATION = new LatLng(Double.parseDouble("" + data.getExtras().getString("lat")), Double.parseDouble("" + data.getExtras().getString("lng")));
                } else {
                    tvDrop.setText(data.getStringExtra("drop_point"));
                    DROP_LOCATION = new LatLng(Double.parseDouble("" + data.getExtras().getString("lat")), Double.parseDouble("" + data.getExtras().getString("lng")));
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if (APINAME.equals(API_S.Tags.GET_VEHICLE_LIST)) {
            progressDialog.hide();
            vehicleList = SingletonGson.getInstance().fromJson("" + script, ModelVehicleList.class);
            if (vehicleList.getResult().equals("1")) {
                for (int i = 0; i < vehicleList.getData().getVehicle_list().size(); i++) {
                    if (vehicleList.getData().getVehicle_list().get(i).isVehicle_default()) {
                        vehicleActivated = true;
                        tvVehicleName.setText(vehicleList.getData().getVehicle_list().get(i).getVehicle_type_name()
                                + " " + vehicleList.getData().getVehicle_list().get(i).getVehicle_model_name());
                        tvVehicleColour.setText(vehicleList.getData().getVehicle_list().get(i).getVehicle_color());
                        tvVehicleNo.setText(vehicleList.getData().getVehicle_list().get(i).getVehicle_number());
                    }
                }
                if (vehicleActivated) {
                    relativeNoVehicle.setVisibility(View.GONE);
                    relativeVehicle.setVisibility(View.VISIBLE);
                } else {
                    relativeNoVehicle.setVisibility(View.VISIBLE);
                    relativeVehicle.setVisibility(View.GONE);
                }
            }
        } else if (APINAME.equals(API_S.Tags.RIDE_STEP_ONE)) {
            progressDialog.hide();
            ModelStepOne stepOne = SingletonGson.getInstance().fromJson("" + script, ModelStepOne.class);
            if (stepOne.getResult().equals("1")) {
                startActivity(new Intent(getActivity(), AddStopsActivity.class)
                        .putExtra("script", "" + script));
            }
        } else if (APINAME.equals(API_S.Tags.MAIN_SCREEN)) {
            progressDialog.hide();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(getActivity(), "" + script, Toast.LENGTH_SHORT).show();
    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
            return false;
        }

        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mGoogleMap.setMaxZoomPreference(20);
        if (checkPermission()) {
            onLocationPermissionGranted();
        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
