package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityEditProfileBinding;
import com.goviens.android.models.ModelEditProfile;
import com.goviens.android.models.ModelUserDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.ImageCompressMode;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;



public class EditProfileActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    private ActivityEditProfileBinding mBinding;

    SessionManager manager;
    ApiManager apiManager;
    String[] gender ;

    String selectedGender;

    private Bitmap thumbnail;;
    String imagePath = "";

    private ContentValues values;
    private String COMPRESSES_IMAGE = "";
    private Uri imageUri;
    private static final int RC_CAMERA_PERM = 123;
    private static final int CAMERS_PICKER = 122;
    private static final int GALLERY_PICKER = 124;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);
        manager = new SessionManager(this);
        apiManager = new ApiManager(this,this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        if(manager.getUserDetails().get(SessionManager.USER_GENDER).equals("1")){
            gender = new String[]{"Male","Female"};
        }else {
            gender = new String[]{"Female","Male"};
        }
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ArrayAdapter aa = new ArrayAdapter(EditProfileActivity.this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        mBinding.spinnerGender.setAdapter(aa);

       mBinding.etEmail.setText(manager.getUserDetails().get(SessionManager.USER_EMAIL));
       mBinding.etFirstName.setText(manager.getUserDetails().get(SessionManager.USER_FIRST_NAME));
       mBinding.etLastName.setText(manager.getUserDetails().get(SessionManager.USER_LAST_NAME));
        //etCode.setText(manager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        mBinding.etPhone.setText(manager.getUserDetails().get(SessionManager.USER_PHONE));
        Glide.with(this).load(manager.getUserDetails().get(SessionManager.USER_IMAGE)).into(mBinding.profileImg);
        mBinding.etDob.setText(manager.getUserDetails().get(SessionManager.USER_DOB));

        mBinding.etPhone.setClickable(false);
        mBinding.etPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(EditProfileActivity.this,LoginActivity.class)
//                        .putExtra("for","1"),111);
            }
        });

        mBinding.etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                                cal.set(year, monthOfYear, dayOfMonth);
                                mBinding.etDob.setText(""+dateFormat.format(cal.getTime()));

                            }
                        }, mYear, mMonth, mDay);
                Calendar cale = Calendar.getInstance();
                cale.add(Calendar.YEAR, -10);
                datePickerDialog.getDatePicker().setMaxDate(cale.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        mBinding.spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(manager.getUserDetails().get(SessionManager.USER_GENDER).equals("1")){
                    if(position == 0){
                        selectedGender = "1";
                    }else {
                        selectedGender = "2";
                    }
                }else {
                    if(position == 0){
                        selectedGender = "2";
                    }else {
                        selectedGender = "1";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";

                if (mBinding.etFirstName.getText().toString().isEmpty() || mBinding.etLastName.getText().toString().isEmpty()
                        || mBinding.etEmail.getText().toString().isEmpty() || !mBinding.etEmail.getText().toString().trim().matches(emailPattern)
                        || mBinding.etDob.getText().toString().isEmpty() || selectedGender.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, getResources().getString(R.string.please_enter_correct_details), Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    HashMap<String, File> fileMap = new HashMap<>();
                    if (!COMPRESSES_IMAGE.equals("")) {
                        fileMap.put("profile_image", new File(COMPRESSES_IMAGE));
                    }
                    HashMap<String, String> map = new HashMap<>();
                    map.put("first_name", mBinding.etFirstName.getText().toString());
                    map.put("last_name", mBinding.etLastName.getText().toString());
                    map.put("user_gender", selectedGender);
                    map.put("email", mBinding.etEmail.getText().toString());
                    map.put("phone",  mBinding.etPhone.getText().toString());
                    map.put("dob", mBinding.etDob.getText().toString());
                    try {
                        if (!COMPRESSES_IMAGE.equals("")) {
                            apiManager._post_image(API_S.Tags.EDIT_PROFILE, API_S.Endpoints.EDIT_PROFILE, map, fileMap, manager);
                        } else {
                            apiManager._post(API_S.Tags.EDIT_PROFILE, API_S.Endpoints.EDIT_PROFILE, map, manager);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mBinding.profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPickerDialog();
            }
        });
    }


    private void openPickerDialog() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(EditProfileActivity.this);
        builderSingle.setTitle(R.string.upload_photo);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditProfileActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add(getString(R.string.camera));
        arrayAdapter.add(getString(R.string.gallery));
        builderSingle.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
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
                        e.printStackTrace();
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
    public void cameraTask() {
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
                e.printStackTrace();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case GALLERY_PICKER:
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    Glide.with(EditProfileActivity.this).load(data.getData()).into(mBinding.profileImg);
                    COMPRESSES_IMAGE = imageCompressMode.compressImage(getRealPathFromURI(data.getData()));
                    break;
                case CAMERS_PICKER:


                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                    circular_image.setImageBitmap(thumbnail);
                    imagePath = getRealPathFromURI(imageUri);

                    ExifInterface ei = new ExifInterface(imagePath);
                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);

                    Bitmap rotatedBitmap = null;
                    switch (orientation) {

                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotatedBitmap = rotateImage(thumbnail, 90);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(thumbnail, 180);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(thumbnail, 270);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                        default:
                            rotatedBitmap = thumbnail;
                    }
                    mBinding.profileImg.setImageBitmap(rotatedBitmap);

                    ImageCompressMode imageCompressModee = new ImageCompressMode(this);

//                    ivProfilePicUpload.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri));
                    COMPRESSES_IMAGE = imageCompressModee.compressImage(getRealPathFromURI(imageUri));
                    break;
                case 111:
                    if (resultCode == RESULT_OK) {
                        if (data != null) {
                            if (data.getStringExtra("phone") != null) {
                                mBinding.etPhone.setText(data.getStringExtra("phone"));
                            }
                        }
                    }
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.EDIT_PROFILE)) {
            ModelEditProfile modelEditProfile = SingletonGson.getInstance().fromJson("" + script, ModelEditProfile.class);
            Toast.makeText(this, ""+modelEditProfile.getMessage(), Toast.LENGTH_SHORT).show();
            if(modelEditProfile.getResult().equals("1")){
                try {
                    progressDialog.hide();
                    progressDialog.show();
                    apiManager._post(API_S.Tags.USER_DETAILS, API_S.Endpoints.USER_DETAILS, null, manager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if(APINAME.equals(API_S.Tags.USER_DETAILS)){
            progressDialog.hide();
            ModelUserDetails modelUserDetails = SingletonGson.getInstance().fromJson("" + script, ModelUserDetails.class);
            manager.createLoginSession("" + modelUserDetails.getData().getDob(),"" + modelUserDetails.getData().getId(),
                    modelUserDetails.getData().getFirst_name(),
                    modelUserDetails.getData().getLast_name(),
                    modelUserDetails.getData().getEmail(),
                    modelUserDetails.getData().getPassword(),
                    modelUserDetails.getData().getUserPhone(),
                    "" + modelUserDetails.getData().getPhone_code(),
                    "" + modelUserDetails.getData().getUserProfileImage(),
                    "" + modelUserDetails.getData().getUser_gender(),
                    "" + modelUserDetails.getData().getSmoker_type(),
                    "" + modelUserDetails.getData().getAllow_other_smoker(),
                    "" + modelUserDetails.getData().getUserSignupType()
                    , ""
                    ,modelUserDetails.getData().getCurrency()
                    ,modelUserDetails.getData().getMobile_number());
            finish();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}
