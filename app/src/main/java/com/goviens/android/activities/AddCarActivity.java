package com.goviens.android.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityLoginBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelAddVehicle;
import com.goviens.android.models.ModelCountryArea;
import com.goviens.android.models.ModelVehicleConfig;
import com.goviens.android.models.ModelVehicleModel;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.ImageCompressMode;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddCarActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    private ActivityAddCarBinding mBinding;

    ApiManager manager ;
    SessionManager sessionManager;
    ModelCountryArea modelCountryArea;
    ModelVehicleConfig vehicleConfig;
    ModelVehicleModel modelVehicleModel;

    private ContentValues values;
    private Uri imageUri;

    List<String> countryList, companyList, vehicleMakeList, modelList;

    private String SELECTED_CAR_TYPE = "", SELECTED_CAR_MAKE = "", SELECTED_CAR_MODEL = "", COMPRESSES_IMAGE_NUMBER_PLATE = "", COMPRESSES_IMAGE_VEHICLE = "", AREA_ID="";

    int SELECTED_IMAGE_TYPE, PICK_FOR_CAR = 1, PICK_FOR_NUMBER_PLATE = 2;

    private static final int RC_CAMERA_PERM = 123;
    private static final int CAMERS_PICKER = 122;
    private static final int GALLERY_PICKER = 124;
    ProgressDialog progressDialog;

    boolean isOtherSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityAddCarBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        countryList = new ArrayList<String>();
        companyList = new ArrayList<String>();
        vehicleMakeList = new ArrayList<String>();
        modelList = new ArrayList<String>();

        try {
            progressDialog.show();
            HashMap<String, String> data = new HashMap<>();
            data.put("country_id", "" + sessionManager.getcountryid());
            data.put("segment", "CARPOOLING");
            manager._post(API_S.Tags.GET_COUNTRY_AREA, API_S.Endpoints.GET_COUNTRY_AREA, data, sessionManager);
        } catch (Exception e) {
            Log.d("", "Exception caught while calling api for vehicle configuration " + e.getMessage());
        }

        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBinding.spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    progressDialog.show();
                    AREA_ID = ""+modelCountryArea.getData().get(position).getId();
                    HashMap<String, String> data = new HashMap<>();
                    data.put("country_area_id", ""+modelCountryArea.getData().get(position).getId());
                    manager._post(API_S.Tags.VEHICLE_CONFIGURATION, API_S.Endpoints.VEHICLE_CONFIGURATION, data, sessionManager);
                } catch (Exception e) {
                    Log.d("", "Exception caught while calling api for vehicle configuration " + e.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.spinnerCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SELECTED_CAR_TYPE = ""+vehicleConfig.getData().getVehicle_type().get(position).getId();
                vehicleMakeList.clear();
                for (int i = 0; i < vehicleConfig.getData().getVehicle_make().size(); i++) {
                    vehicleMakeList.add(vehicleConfig.getData().getVehicle_make().get(i).getVehicleMakeName());
                }
                ArrayAdapter aa = new ArrayAdapter(AddCarActivity.this, android.R.layout.simple_spinner_item,vehicleMakeList);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
                mBinding.spinnerModelYear.setAdapter(aa);
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBinding.spinnerModelYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressDialog.show();
                SELECTED_CAR_MAKE = ""+vehicleConfig.getData().getVehicle_make().get(position).getId();
                HashMap<String, String> data_selected = new HashMap<>();
                data_selected.put("vehicle_type", "" + SELECTED_CAR_TYPE);
                data_selected.put("vehicle_make", "" + SELECTED_CAR_MAKE);
                try {
                    manager._post(API_S.Tags.VEHICLE_MODEL, API_S.Endpoints.VEHICLE_MODEL, data_selected, sessionManager);
                } catch (Exception e) {
                    Log.d("", "Exception caught while calling vehicle make API " + e.getMessage());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mBinding.spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(modelList.get(position).equals(getResources().getString(R.string.other))){
                    mBinding.etOtherModel.setVisibility(View.VISIBLE);
                    isOtherSelected = true;
                }else {
                    mBinding.etOtherModel.setVisibility(View.GONE);
                    isOtherSelected = false;
                    SELECTED_CAR_MODEL = ""+modelVehicleModel.getData().get(position).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBinding.numberPlacetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPickerDialog(PICK_FOR_NUMBER_PLATE);
            }
        });

        mBinding.selectCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPickerDialog(PICK_FOR_CAR);
            }
        });

        mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SELECTED_CAR_TYPE.equals("") || SELECTED_CAR_MAKE.equals("") || COMPRESSES_IMAGE_NUMBER_PLATE.equals("") || COMPRESSES_IMAGE_VEHICLE.equals("")) {
                    Toast.makeText(AddCarActivity.this, AddCarActivity.this.getResources().getString(R.string.required_field_missing), Toast.LENGTH_SHORT).show();
                } else if(isOtherSelected && mBinding.etOtherModel.getText().toString().isEmpty()) {
                    Toast.makeText(AddCarActivity.this, AddCarActivity.this.getResources().getString(R.string.required_field_missing), Toast.LENGTH_SHORT).show();
                }else if(SELECTED_CAR_MODEL.equals("") && !isOtherSelected){
                    Toast.makeText(AddCarActivity.this, AddCarActivity.this.getResources().getString(R.string.required_field_missing), Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    HashMap<String, String> data = new HashMap<>();

                    data.put("area_id",""+AREA_ID);
                    data.put("vehicle_type_id", "" + SELECTED_CAR_TYPE);
                    data.put("vehicle_make_id", "" + SELECTED_CAR_MAKE);
                    if(isOtherSelected){
                        data.put("other_vehicle_model", mBinding.etOtherModel.getText().toString());
                    }else {
                        data.put("vehicle_model_id", "" + SELECTED_CAR_MODEL);
                    }
                    data.put("vehicle_number", "" + mBinding.etVehicleNo.getText().toString());
                    data.put("vehicle_color", "" + mBinding.etColour.getText().toString());
                    data.put("no_of_seats",mBinding.etSeat.getText().toString());

                    HashMap<String, File> data_image = new HashMap<>();
                    data_image.put("vehicle_image", new File(COMPRESSES_IMAGE_VEHICLE));
                    data_image.put("vehicle_number_plate_image", new File(COMPRESSES_IMAGE_NUMBER_PLATE));

                    try {
                        manager._post_image(API_S.Tags.ADD_VEHICLE, API_S.Endpoints.ADD_VEHICLE, data, data_image, sessionManager);
                    } catch (Exception e) {
                        Log.d("" , "Excreption caught while calling add vehicle API " + e.getMessage());
                    }
                }
            }
        });
    }


    private void openPickerDialog(int type) {
        SELECTED_IMAGE_TYPE = type;
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddCarActivity.this);
        builderSingle.setTitle(getResources().getString(R.string.upload_photo));
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCarActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(getResources().getString(R.string.camera));
        arrayAdapter.add(getResources().getString(R.string.gallery));
        builderSingle.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    try {
                        cameraTask();
                    } catch (Exception e) {
                    }
                } else if (which == 1) {
                    Intent i1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    i1.setType("image/*");
                    startActivityForResult(i1, GALLERY_PICKER);
                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }



    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() throws Exception {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            try { // Have permission, do the thing!
                values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERS_PICKER);
            } catch (Exception e) {
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notification_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                break;
                default:
                    return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case GALLERY_PICKER:
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    if (SELECTED_IMAGE_TYPE == PICK_FOR_CAR) {
                        Glide.with(AddCarActivity.this).load(data.getData()).into(mBinding.vehicleImage);
                        COMPRESSES_IMAGE_VEHICLE = imageCompressMode.compressImage(getRealPathFromURI(data.getData()));
                    } else if (SELECTED_IMAGE_TYPE == PICK_FOR_NUMBER_PLATE) {
                        Glide.with(AddCarActivity.this).load(data.getData()).into(mBinding.numberPlateImage);
                        COMPRESSES_IMAGE_NUMBER_PLATE = imageCompressMode.compressImage(getRealPathFromURI(data.getData()));
                    }
                    break;
                case CAMERS_PICKER:
                    ImageCompressMode imageCompressModee = new ImageCompressMode(this);
                    if (SELECTED_IMAGE_TYPE == PICK_FOR_CAR) {
                        mBinding.vehicleImage.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri));
                        COMPRESSES_IMAGE_VEHICLE = imageCompressModee.compressImage(getRealPathFromURI(imageUri));
                    } else if (SELECTED_IMAGE_TYPE == PICK_FOR_NUMBER_PLATE) {
                        mBinding.numberPlateImage.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri));
                        COMPRESSES_IMAGE_NUMBER_PLATE = imageCompressModee.compressImage(getRealPathFromURI(imageUri));
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        switch (APINAME){
            case API_S.Tags.GET_COUNTRY_AREA:
                modelCountryArea = SingletonGson.getInstance().fromJson(""+script, ModelCountryArea.class);
                if(modelCountryArea.getResult().equals("1")) {
                    progressDialog.hide();
                    countryList.clear();
                    for (int i = 0; i < modelCountryArea.getData().size(); i++) {
                        countryList.add(modelCountryArea.getData().get(i).getArea_name());
                    }
                    ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    mBinding.spinnerArea.setAdapter(aa);
                    aa.notifyDataSetChanged();
                }
                break;
            case API_S.Tags.VEHICLE_CONFIGURATION:
                vehicleConfig = SingletonGson.getInstance().fromJson(""+script, ModelVehicleConfig.class);
                if(vehicleConfig.getResult().equals("1")){
                    progressDialog.hide();
                    companyList.clear();
                    for (int i = 0; i < vehicleConfig.getData().getVehicle_type().size(); i++) {
                        companyList.add(vehicleConfig.getData().getVehicle_type().get(i).getVehicleTypeName());
                    }
                    ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, companyList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    mBinding.spinnerCompany.setAdapter(aa);
                    aa.notifyDataSetChanged();
                }
                break;
            case API_S.Tags.VEHICLE_MODEL:
                modelVehicleModel = SingletonGson.getInstance().fromJson(""+script, ModelVehicleModel.class);
                if(modelVehicleModel.getResult().equals("1")){
                    progressDialog.hide();
                    modelList.clear();
                    for (int i = 0; i < modelVehicleModel.getData().size(); i++) {
                        modelList.add(modelVehicleModel.getData().get(i).getVehicleTypeName());
                    }
                    modelList.add(getResources().getString(R.string.other));
                    ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, modelList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    mBinding.spinnerModel.setAdapter(aa);
                    aa.notifyDataSetChanged();
                }
                break;
            case API_S.Tags.ADD_VEHICLE:
                ModelAddVehicle addVehicle = SingletonGson.getInstance().fromJson(""+script,ModelAddVehicle.class);
                if(addVehicle.getResult().equals("1")){
                    startActivity(new Intent(this,UploadDocumentActivity.class).putExtra("for","driver"));
                    finish();
                }
                break;
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}
