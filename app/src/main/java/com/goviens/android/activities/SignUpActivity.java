package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.baseClass.BaseFragment;
import com.goviens.android.models.ModelSignup;
import com.goviens.android.models.ModelUserDetails;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.ImageCompressMode;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.onesignal.OneSignal;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends BaseFragment implements ApiManager.APIFETCHER, DatePickerDialog.OnDateSetListener {

    Button btn_sign_up;

    EditText etFirstName;
    EditText etLastName;
    EditText etEmailAddress;
    EditText etReferralCode;

    EditText etDob;

    TextView tvTermsAndCondition;

    CircleImageView imgProfile;

    Spinner spinnerGender;

    ImageView imgBack;

    String DATE;
    private String PLAYER_ID = "";
    ApiManager apiManager;
    private SessionManager sessionManager;

    String[] gender = {"Male", "Female"};
    String selectedGender = "";

    private ContentValues values;
    private String COMPRESSES_IMAGE = "";
    private Uri imageUri;

    private Bitmap thumbnail;;
    String imagePath = "";

    private static final int RC_CAMERA_PERM = 123;
    private static final int CAMERS_PICKER = 122;
    private static final int GALLERY_PICKER = 124;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this,this);
        sessionManager = new SessionManager(SignUpActivity.this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        ArrayAdapter aa = new ArrayAdapter(SignUpActivity.this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerGender.setAdapter(aa);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    selectedGender = "1";
                }else {
                    selectedGender = "2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvTermsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,TermsAndConditionActivity.class)
                .putExtra("country_id",getIntent().getStringExtra("country_id")));
            }
        });



        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                PLAYER_ID = userId;
            }
        });

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                                cal.set(year, monthOfYear, dayOfMonth);
                                DATE = dateFormat.format(cal.getTime());
                                etDob.setText(""+dateFormat.format(cal.getTime()));

                            }
                        }, mYear, mMonth, mDay);
                Calendar cale = Calendar.getInstance();
                cale.add(Calendar.YEAR, -10); // subtract 2 years from now
                //datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                //c.add(Calendar.YEAR, 4); // add 4 years to min date to have 2 years after now
                datePickerDialog.getDatePicker().setMaxDate(cale.getTimeInMillis());
               // datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPickerDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case GALLERY_PICKER:
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    Glide.with(SignUpActivity.this).load(data.getData()).into(imgProfile);
                    COMPRESSES_IMAGE = imageCompressMode.compressImage(getRealPathFromURI(data.getData()));
                    break;
                case CAMERS_PICKER:
                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imgProfile.setImageBitmap(thumbnail);
                    imagePath = getRealPathFromURI(imageUri);

                    ImageCompressMode imageCompressModee = new ImageCompressMode(this);
                    COMPRESSES_IMAGE = imageCompressModee.compressImage(imagePath);

                    ExifInterface ei = new ExifInterface(imagePath);
                    int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_UNDEFINED);

                    Bitmap rotatedBitmap = null;
                    switch (orientation) {

                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotatedBitmap = rotateImage(thumbnail, 90);
                            imgProfile.setImageBitmap(rotatedBitmap);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotatedBitmap = rotateImage(thumbnail, 180);
                            imgProfile.setImageBitmap(rotatedBitmap);
                            break;

                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotatedBitmap = rotateImage(thumbnail, 270);
                            imgProfile.setImageBitmap(rotatedBitmap);
                            break;

                        case ExifInterface.ORIENTATION_NORMAL:
                            rotatedBitmap = thumbnail;
                            imgProfile.setImageBitmap(rotatedBitmap);
                            break;
                        default:
                            rotatedBitmap = thumbnail;
                            imgProfile.setImageBitmap(rotatedBitmap);
                    }




//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
//                            byte[] b = baos.toByteArray();
//                            String base64 = Base64.encodeToString(b, Base64.DEFAULT);
//
//                            b = Base64.decode(base64, Base64.DEFAULT);
//                            Bitmap decodedImage = BitmapFactory.decodeByteArray(b, 0, b.length);
//                            driver_image.setImageBitmap(decodedImage);

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

    private void openPickerDialog() {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(SignUpActivity.this);
        builderSingle.setTitle(R.string.upload_photo);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.select_dialog_singlechoice);
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
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    public void btnSignUp(View view) {
        callApi();
    }

    void callApi(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";
        if(COMPRESSES_IMAGE.equals("")){
            Toast.makeText(this, getResources().getString(R.string.please_upload_photo), Toast.LENGTH_SHORT).show();
        }else if(etFirstName.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_first_name), Toast.LENGTH_SHORT).show();
        }else if(etLastName.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.please_enter_last_name), Toast.LENGTH_SHORT).show();
        }else if(etEmailAddress.getText().toString().isEmpty() || !etEmailAddress.getText().toString().trim().matches(emailPattern)){
            Toast.makeText(this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
        }else if(etDob.getText().toString().isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.enter_DOB), Toast.LENGTH_SHORT).show();
        }else if(selectedGender.isEmpty()){
            Toast.makeText(this, getResources().getString(R.string.select_gender), Toast.LENGTH_SHORT).show();
        } else if(PLAYER_ID.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.restart_the_app), Toast.LENGTH_SHORT).show();
        }else {
            try {
                HashMap<String, File> fileMap = new HashMap<>();
                if (!COMPRESSES_IMAGE.equals("")) {
                    fileMap.put("profile_image", new File(COMPRESSES_IMAGE));
                }
                progressDialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("country_id", getIntent().getStringExtra("country_id"));
                map.put("player_id", PLAYER_ID);
                map.put("first_name", etFirstName.getText().toString());
                map.put("last_name", etLastName.getText().toString());
                map.put("user_gender",selectedGender);
                map.put("dob",etDob.getText().toString());
                map.put("phone", getIntent().getStringExtra("phone"));
                map.put("email", etEmailAddress.getText().toString());
                map.put("unique_no", "" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
                map.put("package_name", "" + BuildConfig.APPLICATION_ID);
                map.put("apk_version", BuildConfig.VERSION_NAME);
                map.put("device", "1");
                if(!etReferralCode.getText().toString().equals("")) {
                    map.put("referral_code", etReferralCode.getText().toString());
                }
                map.put("operating_system", "" + Build.VERSION.SDK_INT);
                map.put("is_register","true");
                if (!COMPRESSES_IMAGE.equals("")) {
                    apiManager._post_image_with_secreteonly(API_S.Tags.SIGN_UP, API_S.Endpoints.SIGN_UP, map, fileMap);
                }else {
                    apiManager._post_with_secreteonly(API_S.Tags.SIGN_UP, API_S.Endpoints.SIGN_UP, map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        switch (APINAME){
            case API_S.Tags.SIGN_UP:
                progressDialog.hide();
                ModelSignup modelSignupLogin = SingletonGson.getInstance().fromJson("" + script, ModelSignup.class);
                sessionManager.setAccessToken("" + modelSignupLogin.getData().getAccess_token());
                Toast.makeText(SignUpActivity.this, "" + modelSignupLogin.getMessage(), Toast.LENGTH_SHORT).show();
                if(sessionManager.getAppConfig().getData().getGeneral_config().isUser_document()){
                    Intent intent = new Intent(SignUpActivity.this,UploadDocumentActivity.class);
                    intent.putExtra("for", "user");
                    startActivity(intent);
                    finish();
                }else {
                    progressDialog.show();
                    try {
                        apiManager._post(API_S.Tags.USER_DETAILS, API_S.Endpoints.USER_DETAILS, null, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case API_S.Tags.USER_DETAILS:
                progressDialog.hide();
                ModelUserDetails modelUserDetails = SingletonGson.getInstance().fromJson("" + script, ModelUserDetails.class);
                sessionManager.createLoginSession("" + modelUserDetails.getData().getDob(),"" + modelUserDetails.getData().getId(),
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
                sessionManager.makUserLoggedIn();
                try {
                    sessionManager.setcountryid(Integer.parseInt(modelUserDetails.getData().getCountry_id()));
                } catch (Exception e) {

                }
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
