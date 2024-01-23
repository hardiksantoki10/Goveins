package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.goviens.android.BuildConfig;
import com.goviens.android.R;
import com.goviens.android.databinding.ActivityPaymentOptionsBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelAddMoney;
import com.goviens.android.models.ModelGetPayment;
import com.goviens.android.models.ModelIntouch;
import com.goviens.android.models.ModelMonetbil;
import com.goviens.android.models.ModelOperatorList;
import com.goviens.android.models.ModelPaypal;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PaymentOptionsActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    ApiManager manager;
    SessionManager sessionManager;
    String amt;

    Spinner spinnerOperator;

    String Operator = "";
    String FROM;

    ProgressDialog progressDialog;
    ModelOperatorList modelOperatorList;

    List<String> operatorList;
    ModelGetPayment modelGetPayment;

    ActivityPaymentOptionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_payment_options);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        manager = new ApiManager(this, this);
        operatorList = new ArrayList<String>();
        sessionManager = new SessionManager(this);
        FROM = getIntent().getStringExtra("FROM");
        try{
            progressDialog.show();
            HashMap<String,String> map = new HashMap<>();
            if(FROM.equals("payout")){
                map.put("process","cashout");
            }else {
                map.put("process","cashin");
            }
            manager._post(API_S.Tags.OPERATOR_LIST, API_S.Endpoints.OPERATOR_LIST, map, sessionManager);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(FROM.equals("payout")){
            try{
                progressDialog.show();
                manager._post(API_S.Tags.GET_CASHOUT_METHOD, API_S.Endpoints.GET_CASHOUT_METHOD,null, sessionManager);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try{
                progressDialog.show();
                manager._post(API_S.Tags.GET_PAYMENT_METHOD, API_S.Endpoints.GET_PAYMENT_METHOD,null, sessionManager);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        binding.relativePaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForPaypalAddMoney(v);
            }
        });
        binding.relativeStripe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForStripeAddMoney(v);
            }
        });
        binding.relativeBankTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForPayout(v);
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.relativeMonet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FROM.equals("payout")){
                    openDialogForMonetPayout(v);
                }else {
                    openDialogForMonetAddMoney(v);
                }
            }
        });
        binding.relativeIntouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FROM.equals("payout")){
                    openDialogForIntouchPayout(v);
                }else {
                    openDialogForIntouchAddMoney(v);
                }
            }
        });
    }

    void openDialogForPayout(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        EditText etPhone = dialogView.findViewById(R.id.etMobileNumber);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        TextView text = dialogView.findViewById(R.id.text);
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        text.setText(getResources().getString(R.string.add_money_in_your_bank));
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (etAmt.getText().toString().equals("")) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        manager._post(API_S.Tags.PAYOUT, API_S.Endpoints.PAYOUT, map, sessionManager);
                    } catch (Exception e) {
                        if(progressDialog.isShowing()){
                            progressDialog.hide();
                        }
                        e.printStackTrace();
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                    }
                    alertDialog.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void openDialogForIntouchPayout(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        EditText etPhone = dialogView.findViewById(R.id.etMobileNumber);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView text = dialogView.findViewById(R.id.text);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        spinnerOperator = dialogView.findViewById(R.id.spinner_operator);
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        text.setText(getResources().getString(R.string.add_money_in_your_bank));
        etPhone.setText(sessionManager.getUserDetails().get(SessionManager.MOBILE));
        etPhone.setEnabled(false);
        RelativeLayout relativeNumber = dialogView.findViewById(R.id.relative_number);
        relativeNumber.setVisibility(View.VISIBLE);
        spinnerOperator.setVisibility(View.VISIBLE);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operatorList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerOperator.setAdapter(aa);
        aa.notifyDataSetChanged();
        spinnerOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Operator = modelOperatorList.getData().getService_id().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (etAmt.getText().toString().equals("")) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                    } else if(etPhone.getText().toString().equals("")){
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_LONG).show();
                    }else if(Operator.equals("")){
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_select_operator), Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMinimum_amount()) > Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_minimum)+" "+ modelGetPayment.getData().getMinimum_amount() , Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMaximum_amount()) < Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_maximum)+" "+ modelGetPayment.getData().getMaximum_amount() , Toast.LENGTH_LONG).show();
                    }else {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        map.put("phonenumber", etPhone.getText().toString());
                        map.put("operator", Operator);
                        manager._post(API_S.Tags.INTOUCH_PAYOUT, API_S.Endpoints.INTOUCH_PAYOUT, map, sessionManager);
                    }
                    alertDialog.dismiss();
                }catch (Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                    alertDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    void openDialogForIntouchAddMoney(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        EditText etPhone = dialogView.findViewById(R.id.etMobileNumber);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        spinnerOperator = dialogView.findViewById(R.id.spinner_operator);
        View view = dialogView.findViewById(R.id.view);
        RelativeLayout relativeNumber = dialogView.findViewById(R.id.relative_number);
        relativeNumber.setVisibility(View.VISIBLE);
        etPhone.setText(sessionManager.getUserDetails().get(SessionManager.MOBILE));
        etPhone.setEnabled(true);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        spinnerOperator.setVisibility(View.VISIBLE);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operatorList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerOperator.setAdapter(aa);
        aa.notifyDataSetChanged();
        spinnerOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Operator = modelOperatorList.getData().getService_id().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.setVisibility(View.VISIBLE);
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (etAmt.getText().toString().equals("")) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                } else if(Operator.equals("")){
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_select_operator), Toast.LENGTH_LONG).show();
                }else if(etPhone.getText().toString().equals("")){
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(modelGetPayment.getData().getMinimum_amount()) > Integer.parseInt(etAmt.getText().toString())) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_minimum)+" "+ modelGetPayment.getData().getMinimum_amount() , Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(modelGetPayment.getData().getMaximum_amount()) < Integer.parseInt(etAmt.getText().toString())) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_maximum)+" "+ modelGetPayment.getData().getMaximum_amount() , Toast.LENGTH_LONG).show();
                } else {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        map.put("operator", Operator);
                        map.put("phonenumber", etPhone.getText().toString());
                        manager._post(API_S.Tags.INTOUCH_PAYMENT, API_S.Endpoints.INTOUCH_PAYMENT, map, sessionManager);
                    }
                    alertDialog.dismiss();
                }catch (Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                    alertDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void openDialogForStripeAddMoney(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (etAmt.getText().toString().equals("")) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        amt = etAmt.getText().toString();
                        Intent intent = new Intent(PaymentOptionsActivity.this, CardListActivity.class);
                        intent.putExtra("amount", "" + etAmt.getText().toString());
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                    }
                    alertDialog.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void openDialogForMonetPayout(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        EditText etPhone = dialogView.findViewById(R.id.etMobileNumber);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        RelativeLayout relativeNumber = dialogView.findViewById(R.id.relative_number);
        relativeNumber.setVisibility(View.VISIBLE);
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        etPhone.setText(sessionManager.getUserDetails().get(SessionManager.MOBILE));
        etPhone.setEnabled(false);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        TextView text = dialogView.findViewById(R.id.text);
        text.setText(getResources().getString(R.string.add_money_in_your_bank));
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (etAmt.getText().toString().equals("")) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                    }else if(etPhone.getText().toString().equals("")){
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMinimum_amount()) > Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_minimum)+" "+ modelGetPayment.getData().getMinimum_amount() , Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMaximum_amount()) < Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_maximum)+" "+ modelGetPayment.getData().getMaximum_amount() , Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        map.put("phonenumber", sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE)+etPhone.getText().toString());
                        manager._post(API_S.Tags.MONETBIL_PAYOUT, API_S.Endpoints.MONETBIL_PAYOUT, map, sessionManager);
                    }
                    alertDialog.dismiss();
                }catch (Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                    alertDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void openDialogForMonetAddMoney(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        EditText etPhone = dialogView.findViewById(R.id.etMobileNumber);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        RelativeLayout relativeNumber = dialogView.findViewById(R.id.relative_number);
        relativeNumber.setVisibility(View.VISIBLE);
        etPhone.setText(sessionManager.getUserDetails().get(SessionManager.MOBILE));
        etPhone.setEnabled(true);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                try {
                    if (etAmt.getText().toString().equals("")) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMinimum_amount()) > Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_minimum)+" "+ modelGetPayment.getData().getMinimum_amount() , Toast.LENGTH_LONG).show();
                    }else if(Integer.parseInt(modelGetPayment.getData().getMaximum_amount()) < Integer.parseInt(etAmt.getText().toString())) {
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_maximum)+" "+ modelGetPayment.getData().getMaximum_amount() , Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        map.put("phonenumber", sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE)+etPhone.getText().toString());
                        manager._post(API_S.Tags.MONETBIL_PAYMENT, API_S.Endpoints.MONETBIL_PAYMENT, map, sessionManager);
                    }
                    alertDialog.dismiss();
                }catch (Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.hide();
                    }
                    alertDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void openDialogForPaypalAddMoney(android.view.View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PaymentOptionsActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        android.view.View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_add_money, viewGroup, false);
        builder.setView(dialogView);
        android.app.AlertDialog alertDialog = builder.create();
        EditText etAmt = dialogView.findViewById(R.id.etAmt);
        Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        Button btn_ok = dialogView.findViewById(R.id.btn_ok);
        TextView tvCurrency = dialogView.findViewById(R.id.tv_currency);
        TextView tvPhoneCode = dialogView.findViewById(R.id.tv_country_code);
        tvPhoneCode.setText(sessionManager.getUserDetails().get(SessionManager.USER_PHONE_CODE));
        TextView textCommission = dialogView.findViewById(R.id.text_percent);
        if(modelGetPayment.getData().getCommission_percentage().equals("0")){
            textCommission.setVisibility(View.GONE);
        }else {
            textCommission.setVisibility(View.VISIBLE);
            textCommission.setText(modelGetPayment.getData().getCommission_string());
        }
        tvCurrency.setText(sessionManager.getUserDetails().get(SessionManager.CURRENCY));
        btn_ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (etAmt.getText().toString().equals("")) {
                    Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.please_enter_amount), Toast.LENGTH_LONG).show();
                } else {
                    try {
                        progressDialog.show();
                        amt = etAmt.getText().toString();
                        HashMap<String,String> map = new HashMap<>();
                        map.put("amount", etAmt.getText().toString());
                        map.put("currency", sessionManager.getUserDetails().get(SessionManager.CURRENCY));
                        manager._post(API_S.Tags.PAYPAL_URL, API_S.Endpoints.PAYPAL_URL, map, sessionManager);
                    } catch (Exception e) {
                        if(progressDialog.isShowing()){
                            progressDialog.hide();
                        }
                        e.printStackTrace();
                        Toast.makeText(PaymentOptionsActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                    }
                    alertDialog.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            if (APINAME.equals("" + API_S.Tags.ADD_MONEY_IN_WALLET)) {
                progressDialog.dismiss();
                progressDialog.hide();
                ModelAddMoney modelAddMoney = SingletonGson.getInstance().fromJson("" + script, ModelAddMoney.class);
                //Toast.makeText(this, "" + modelAddMoney.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("ok", "Done");
                setResult(RESULT_OK, intent);
                finish();
            } else if (APINAME.equals(API_S.Tags.PAYPAL_URL)) {
                ModelPaypal modelPaypal = SingletonGson.getInstance().fromJson("" + script, ModelPaypal.class);
                if (!modelPaypal.getData().equals("")) {
                    progressDialog.hide();
                    progressDialog.dismiss();
                    Intent intent = new Intent(PaymentOptionsActivity.this, PaypalPaymentActivity.class);
                    intent.putExtra("url", modelPaypal.getData());
                    startActivityForResult(intent, 201);
                }
            } else if (APINAME.equals(API_S.Tags.MONETBIL_PAYMENT)) {
                ModelMonetbil modelMonetbil = SingletonGson.getInstance().fromJson("" + script, ModelMonetbil.class);
                progressDialog.hide();
                Toast.makeText(this, modelMonetbil.getMessage(), Toast.LENGTH_LONG).show();
            } else if (APINAME.equals(API_S.Tags.MONETBIL_PAYOUT)) {
                ModelMonetbil modelMonetbil = SingletonGson.getInstance().fromJson("" + script, ModelMonetbil.class);
                progressDialog.hide();
                Toast.makeText(this, modelMonetbil.getMessage(), Toast.LENGTH_LONG).show();
            } else if (APINAME.equals(API_S.Tags.INTOUCH_PAYOUT)) {
                ModelMonetbil modelMonetbil = SingletonGson.getInstance().fromJson("" + script, ModelMonetbil.class);
                progressDialog.hide();
                Toast.makeText(this, modelMonetbil.getMessage(), Toast.LENGTH_LONG).show();
            } else if (APINAME.equals(API_S.Tags.PAYOUT)) {
                ModelMonetbil modelMonetbil = SingletonGson.getInstance().fromJson("" + script, ModelMonetbil.class);
                progressDialog.hide();
                Toast.makeText(this, modelMonetbil.getMessage(), Toast.LENGTH_LONG).show();
            } else if (APINAME.equals(API_S.Tags.OPERATOR_LIST)) {
                modelOperatorList = SingletonGson.getInstance().fromJson("" + script, ModelOperatorList.class);
                progressDialog.hide();
                operatorList.clear();
                if (modelOperatorList.getData().getOperator().size() != 0) {
                    for (int i = 0; i < modelOperatorList.getData().getOperator().size(); i++) {
                        operatorList.add(modelOperatorList.getData().getOperator().get(i));
                    }
                }
            } else if (APINAME.equals(API_S.Tags.INTOUCH_PAYMENT)) {
                progressDialog.hide();
                progressDialog.dismiss();
                ModelIntouch modelIntouch = SingletonGson.getInstance().fromJson("" + script, ModelIntouch.class);
                Toast.makeText(this, modelIntouch.getMessage(), Toast.LENGTH_LONG).show();
            } else if (APINAME.equals(API_S.Tags.GET_PAYMENT_METHOD)) {
                progressDialog.hide();
                progressDialog.dismiss();
                modelGetPayment = SingletonGson.getInstance().fromJson("" + script, ModelGetPayment.class);
                for (int i = 0; i < modelGetPayment.getData().getGateway().size(); i++) {
                    if (modelGetPayment.getData().getGateway().get(i).equals("PAYPAL")) {
                        binding.relativePaypal.setVisibility(View.VISIBLE);
                    }
                    if (modelGetPayment.getData().getGateway().get(i).equals("INTOUCHGROUP")) {
                        binding.relativeIntouch.setVisibility(View.VISIBLE);
                    }
                    if (modelGetPayment.getData().getGateway().get(i).equals("Stripe")) {
                        binding.relativeStripe.setVisibility(View.VISIBLE);
                    }
                    if (modelGetPayment.getData().getGateway().get(i).equals("MONETBIL")) {
                        binding.relativeMonet.setVisibility(View.VISIBLE);
                    }
                }
            } else if (APINAME.equals(API_S.Tags.GET_CASHOUT_METHOD)) {
                progressDialog.hide();
                progressDialog.dismiss();
                modelGetPayment = SingletonGson.getInstance().fromJson("" + script, ModelGetPayment.class);
                for (int i = 0; i < modelGetPayment.getData().getGateway().size(); i++) {
                    if (modelGetPayment.getData().getGateway().get(i).equals("Bank Transfer")) {
                        binding.relativeBankTransfer.setVisibility(View.VISIBLE);
                    }
                    if (modelGetPayment.getData().getGateway().get(i).equals("INTOUCHGROUP")) {
                        binding.relativeIntouch.setVisibility(View.VISIBLE);
                    }
                    if (modelGetPayment.getData().getGateway().get(i).equals("MONETBIL")) {
                        binding.relativeMonet.setVisibility(View.VISIBLE);
                    }
                }

            }
        }catch (Exception e){
            if(BuildConfig.DEBUG){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        Toast.makeText(this, ""+script, Toast.LENGTH_LONG).show();
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode == 201 && resultCode == RESULT_OK) {
                if (data != null) {
                    if (data.getStringExtra("success") != null) {
                        if (data.getStringExtra("success").equals("1")) {
                            progressDialog.show();
                            HashMap<String, String> datas = new HashMap<>();
                            datas.put("amount", amt);
                            datas.put("payment_request", "Paypal");
                            try {
                                manager._post("" + API_S.Tags.ADD_MONEY_IN_WALLET, "" + API_S.Endpoints.ADD_MONEY_IN_WALLET, datas, sessionManager);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (data.getStringExtra("success").equals("0")) {
                            Toast.makeText(this, getResources().getString(R.string.payment_failed), Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.hide();
                        }
                    }
                }else {
                    Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.hide();
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show();
            if (progressDialog.isShowing()) {
                progressDialog.hide();
            }
        }
    }
}