package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.models.ModelUploadDocument;
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


import butterknife.ButterKnife;

public class UploadDocumentPhotoActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    LinearLayout linearCamera;
    LinearLayout linearGallery;

    ImageView image;
    Button btnSubmit;
    EditText etDate;

    EditText etDocumentNumber;

    ImageView imgExpiry;
    ImageView imgDocument;
    TextView labelExpiry;
    TextView labelDocument;
    String DATE;

    private ContentValues values;
    private Bitmap thumbnail;
    Uri selectedImage;
    private Uri imageUri;
    String imagePath = "", COMPRESSES_IMAGE_VEHICLE = "";
    Bitmap bitmap1;

    String DOCUMENT_FOR = "";
    String EXPIRY_DATE = "";
    String DOCUMENT_NUMBER="";
    ApiManager manager;
    SessionManager sessionManager;

    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


    ImageView imgBack;

    private static final int RC_CAMERA_PERM = 123;
    private static final int PICK_IMAGE = 124;
    private static final int CAMERS_PICKER = 122;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_document_photo);
        ButterKnife.bind(this);

        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        DOCUMENT_FOR = getIntent().getStringExtra("document_for");
        EXPIRY_DATE = getIntent().getStringExtra("expire_status");
        DOCUMENT_NUMBER = getIntent().getStringExtra("Document_number_required");

        if(DOCUMENT_NUMBER.equals("1")){
            etDocumentNumber.setVisibility(View.VISIBLE);
            imgDocument.setVisibility(View.VISIBLE);
            labelDocument.setVisibility(View.VISIBLE);
        }else {
            etDocumentNumber.setVisibility(View.GONE);
            imgDocument.setVisibility(View.GONE);
            labelDocument.setVisibility(View.GONE);
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(EXPIRY_DATE.equals("1")){
            etDate.setVisibility(View.VISIBLE);
            imgExpiry.setVisibility(View.VISIBLE);
            labelExpiry.setVisibility(View.VISIBLE);
        }else {
            etDate.setVisibility(View.GONE);
            imgExpiry.setVisibility(View.GONE);
            labelExpiry.setVisibility(View.GONE);
        }

        linearCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cameraTask();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openDateFDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        linearGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectFromgalery();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(COMPRESSES_IMAGE_VEHICLE.equals("")){
                    Toast.makeText(UploadDocumentPhotoActivity.this, getResources().getString(R.string.please_upload_photo), Toast.LENGTH_SHORT).show();
                } else if(EXPIRY_DATE.equals("1") && etDate.getText().toString().equals("")){
                    Toast.makeText(UploadDocumentPhotoActivity.this, getResources().getString(R.string.please_enter_expiry), Toast.LENGTH_SHORT).show();
                }else if(DOCUMENT_NUMBER.equals("1") && etDocumentNumber.getText().toString().equals("")){
                    Toast.makeText(UploadDocumentPhotoActivity.this, getResources().getString(R.string.please_enter_document), Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, String> map = new HashMap<>();
                    HashMap<String, File> images_data = new HashMap<>();
                    if (DOCUMENT_FOR.equals("0")) {
                        map.put("document_for", "PERSONAL");
                        map.put("document_id", "" + getIntent().getStringExtra("id"));
                        if (DOCUMENT_NUMBER.equals("1")) {
                            map.put("document_number", "" + etDocumentNumber.getText().toString());
                            map.put("document_number_required", "1");
                        }
                        images_data.put("document_image", new File(COMPRESSES_IMAGE_VEHICLE));
                        if (EXPIRY_DATE.equals("1")) {
                            map.put("expire_date", etDate.getText().toString());
                        }
                    } else {
                        map.put("document_for", "VEHICLE");
                        map.put("document_id", "" + getIntent().getStringExtra("id"));
                        if (DOCUMENT_NUMBER.equals("1")) {
                            map.put("document_number", "" + etDocumentNumber.getText().toString());
                            map.put("document_number_required", "1");
                        }
                        if (EXPIRY_DATE.equals("1")) {
                            map.put("expire_date", etDate.getText().toString());
                        }
                        map.put("expire_status", "" + getIntent().getStringExtra("expire_status"));
                        images_data.put("document_image", new File(COMPRESSES_IMAGE_VEHICLE));
                        map.put("user_vehicle_id", "" + getIntent().getStringExtra("user_vehicle_id"));
                    }
                    try {
                        progressDialog.show();
                        manager._post_image(API_S.Tags.UPLOAD_DOC, API_S.Endpoints.UPLOAD_DOC, map, images_data, sessionManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() throws Exception {
        if (EasyPermissions.hasPermissions(this, PERMISSIONS)) {
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

    public void selectFromgalery() throws Exception {
        Intent i1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i1.setType("image/*");
        startActivityForResult(i1, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case CAMERS_PICKER:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        if (resultCode == RESULT_OK) {
                            ImageCompressMode imageCompressModeee = new ImageCompressMode(this);
//
//                            Bitmap photo = (Bitmap) data.getExtras().get("data");
//                            image.setImageBitmap(photo);
//                            Uri tempUri = getImageUri(getApplicationContext(), photo);
//                            imagePath = getPath(tempUri);
                            thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            image.setImageBitmap(thumbnail);
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
                            image.setImageBitmap(rotatedBitmap);

                            ImageCompressMode imageCompressModee = new ImageCompressMode(this);
                            COMPRESSES_IMAGE_VEHICLE = imageCompressModee.compressImage(imagePath);


                            // COMPRESSES_IMAGE_VEHICLE = imageCompressModeee.compressImage(getRealPathFromURI(imageUri));
                            if(EXPIRY_DATE.equals("1")) {
                                openDateFDialog();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;


            case PICK_IMAGE:
                try {
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);
                    COMPRESSES_IMAGE_VEHICLE = imageCompressMode.compressImage(getRealPathFromURI(data.getData()));
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    final int REQUIRED_SIZE = 300;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bitmap1 = BitmapFactory.decodeFile(filePath, options);
                    image.setImageBitmap(bitmap1);
                    if(EXPIRY_DATE.equals("1")) {
                        openDateFDialog();
                    }
                } catch (Exception e) {
//                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void openDateFDialog() throws Exception {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(UploadDocumentPhotoActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                        cal.set(year, monthOfYear, dayOfMonth);

                        DATE = dateFormat.format(cal.getTime());
                        etDate.setText(""+dateFormat.format(cal.getTime()));

                    }
                }, mYear, mMonth, mDay);
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.DAY_OF_MONTH, Integer.parseInt(getIntent().getStringExtra("date")));
        datePickerDialog.getDatePicker().setMinDate(cale.getTimeInMillis());
        datePickerDialog.show();
    }


    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        ModelUploadDocument uploadDocument = SingletonGson.getInstance().fromJson(""+script, ModelUploadDocument.class);
        if(uploadDocument.getResult().equals("1")){
            progressDialog.hide();
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }
}
