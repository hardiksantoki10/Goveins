package com.goviens.android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.databinding.ActivityUploadDocumentBinding;
import com.goviens.android.models.ModelDocument;
import com.goviens.android.models.ModelUserDetails;
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



public class UploadDocumentActivity extends AppCompatActivity implements ApiManager.APIFETCHER {


    ApiManager manager;
    SessionManager sessionManager;

    PlaceHolderView placeHolderDocument;
    ModelDocument modelDocument;
    String forr="";




    String userVehicleId;
    ProgressDialog progressDialog;
    boolean pendingDoc = true;
    boolean checkDoc = true;

    ActivityUploadDocumentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUploadDocumentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        manager = new ApiManager(this,this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        forr = getIntent().getStringExtra("for");

        binding.imgBack.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });
        binding.btnSubmit.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(forr.equals("user")) {
                    if (pendingDoc) {
                        Toast.makeText(UploadDocumentActivity.this, getResources().getString(R.string.upload_all_doc), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            progressDialog.show();
                            manager._post(API_S.Tags.USER_DETAILS, API_S.Endpoints.USER_DETAILS, null, sessionManager);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    Intent intent = new Intent(UploadDocumentActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> map = new HashMap<>();
        if(forr.equals("user")) {
            map.put("document_for", "PERSONAL");
        }else {
            map.put("document_for", "VEHICLE");
        }
        try {
            placeHolderDocument.removeAllViews();
            checkDoc = true;
            progressDialog.show();
            manager._post(API_S.Tags.USER_DOC, API_S.Endpoints.USER_DOC, map, sessionManager);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        if(APINAME.equals(API_S.Tags.USER_DETAILS)){
            progressDialog.hide();
            ModelUserDetails modelUserDetails = SingletonGson.getInstance().fromJson("" + script, ModelUserDetails.class);
            sessionManager.createLoginSession(""+modelUserDetails.getData().getDob(),"" + modelUserDetails.getData().getId(),
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
            sessionManager.makUserLoggedIn();
            Intent intent = new Intent(UploadDocumentActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            progressDialog.hide();
            modelDocument = SingletonGson.getInstance().fromJson("" + script, ModelDocument.class);
            if (modelDocument.getResult().equals("1")) {
                if (forr.equals("user")) {
                    for (int i = 0; i < modelDocument.getData().getPersonal_doc().size(); i++) {
                        if (checkDoc) {
                            if (modelDocument.getData().getPersonal_doc().get(i).getDocument_verification_status().equals("PENDING")) {
                                pendingDoc = true;
                                checkDoc = false;
                            } else {
                                pendingDoc = false;
                            }
                        }
                        placeHolderDocument.addView(new HolderDocuments(modelDocument.getData().getPersonal_doc().get(i)));
                    }
                } else {
                    if(modelDocument.getData().getVehicle_doc().size() != 0) {
                        binding.tvUploadMessage.setVisibility(android.view.View.GONE);
                        placeHolderDocument.setVisibility(android.view.View.VISIBLE);
                        binding.btnSubmit.setVisibility(android.view.View.VISIBLE);
                        for (int i = 0; i < modelDocument.getData().getVehicle_doc().size(); i++) {
                            placeHolderDocument.addView(new HolderDocumentsVehicleHeader(modelDocument.getData().getVehicle_doc().get(i)));
                        }
                    }else {
                        binding.btnSubmit.setVisibility(android.view.View.GONE);
                        placeHolderDocument.setVisibility(android.view.View.GONE);
                        binding.tvUploadMessage.setVisibility(android.view.View.VISIBLE);
                    }
                }
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        progressDialog.hide();
        Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Layout(R.layout.raw_upload_document)
    class HolderDocuments{
        @View(R.id.root_relative)
        RelativeLayout root;

        @View(R.id.tv_document_name)
        TextView documentName;
        @View(R.id.tv_status)
        TextView tvStatus;

        ModelDocument.DataBean.PersonalDocBean data;

        @Position
        int position;
        public HolderDocuments(ModelDocument.DataBean.PersonalDocBean data) {
            this.data = data;
        }

        @Resolve
        void setdata(){
            documentName.setText(data.getDocumentname());
            if(data.getDocument_verification_status().equals("Approved")
                    || data.getDocument_verification_status().equals("Approuvé")){
                tvStatus.setTextColor(Color.GREEN);
            }else {
                tvStatus.setTextColor(Color.RED);
            }
            tvStatus.setText(data.getDocument_verification_status());
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    if (data.getDocument_verification_status().equals("Approved")
                            || data.getDocument_verification_status().equals("Approuvé")) {
                        Toast.makeText(UploadDocumentActivity.this, getResources().getString(R.string.document_approved), Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(UploadDocumentActivity.this, UploadDocumentPhotoActivity.class);
                        intent.putExtra("id", "" + data.getId());
                        intent.putExtra("expire_status", "" + data.getExpire_status());
                        intent.putExtra("document_for", "0");
                        intent.putExtra("Document_number_required", "" + data.getDocument_number_required());
                        intent.putExtra("date", "" + modelDocument.getData().getUpload_from_date());
                        startActivityForResult(intent, 101);
                    }
                }
            });
        }
    }

    @Layout(R.layout.raw_vehicle_doc)
    class HolderDocumentsVehicleHeader{


        @View(R.id.tv_heading)
        TextView tvHeading;
        @View(R.id.placeHolderVehicleDoc)
        PlaceHolderView placeHolderVehicleDoc;

        ModelDocument.DataBean.VehicleDocBean data;

        @Position
        int position;
        public HolderDocumentsVehicleHeader(ModelDocument.DataBean.VehicleDocBean data) {
            this.data = data;
        }

        @Resolve
        void setdata(){
            tvHeading.setText(data.getVehicle_number());
            for(int i =0;i<data.getDocument_list().size(); i++)
                placeHolderVehicleDoc.addView(new HolderDocumentsVehicle(data.getDocument_list().get(i), data));
        }
    }

    @Layout(R.layout.raw_upload_document)
    class HolderDocumentsVehicle{
        @View(R.id.root_relative)
        RelativeLayout root;

        @View(R.id.tv_heading)
        TextView tvHeading;
        @View(R.id.placeHolderVehicleDoc)
        PlaceHolderView placeHolderVehicleDoc;

        @View(R.id.tv_document_name)
        TextView documentName;
        @View(R.id.tv_status)
        TextView tvStatus;

        ModelDocument.DataBean.VehicleDocBean.DocumentListBean data;
        ModelDocument.DataBean.VehicleDocBean vehicleDocBean;

        @Position
        int position;
        public HolderDocumentsVehicle(ModelDocument.DataBean.VehicleDocBean.DocumentListBean data,ModelDocument.DataBean.VehicleDocBean vehicleDocBean) {
            this.data = data;
            this.vehicleDocBean = vehicleDocBean;
        }

        @Resolve
        void setdata(){
            tvStatus.setVisibility(android.view.View.VISIBLE);
            documentName.setText(data.getDocumentname());
            if(data.getDocument_verification_status().equals("Approved")
                    || data.getDocument_verification_status().equals("Approuvé")){
                tvStatus.setTextColor(Color.GREEN);
            }else if(data.getDocument_verification_status().equals("Pending for Verification")
                    || data.getDocument_verification_status().equals("En attente de vérification")){
                tvStatus.setTextColor(getResources().getColor(R.color.colorMustard));
            }else {
                tvStatus.setTextColor(Color.RED);
            }
            tvStatus.setText(data.getDocument_verification_status());
            root.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    if(data.getDocument_verification_status().equals("Approved")
                            || data.getDocument_verification_status().equals("Approuvé")){
                        Toast.makeText(UploadDocumentActivity.this, getResources().getString(R.string.document_approved), Toast.LENGTH_SHORT).show();
                    }else if(data.getDocument_verification_status().equals("Pending for Verification")
                            || data.getDocument_verification_status().equals("En attente de vérification")){
                        Toast.makeText(UploadDocumentActivity.this, getResources().getString(R.string.pending_for_verification), Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(UploadDocumentActivity.this, UploadDocumentPhotoActivity.class);
                        intent.putExtra("id", "" + data.getId());
                        intent.putExtra("expire_status", "" + data.getExpire_status());
                        intent.putExtra("document_for", "1");
                        intent.putExtra("Document_number_required", "" + data.getDocument_number_required());
                        intent.putExtra("expire_status", "" + data.getExpire_status());
                        intent.putExtra("user_vehicle_id", "" + vehicleDocBean.getVehicle_id());
                        intent.putExtra("date",""+modelDocument.getData().getUpload_from_date());
                        startActivityForResult(intent, 101);
                    }
                }
            });
        }
    }
}
