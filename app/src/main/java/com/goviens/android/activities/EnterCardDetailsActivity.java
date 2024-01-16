package com.goviens.android.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.baseClass.BaseActivity;
import com.goviens.android.databinding.ActivityAddCarBinding;
import com.goviens.android.databinding.ActivityEnterCardDetailsBinding;
import com.goviens.android.models.ModelGetStripeToken;
import com.goviens.android.utils.API_S;
import com.goviens.android.utils.ApiManager;
import com.goviens.android.utils.SessionManager;
import com.goviens.android.utils.SingletonGson;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.util.HashMap;


import butterknife.ButterKnife;

public class EnterCardDetailsActivity extends BaseActivity implements ApiManager.APIFETCHER, View.OnClickListener, TextWatcher {

    private ActivityEnterCardDetailsBinding mBinding;
    SessionManager sessionmanager;
    ApiManager apiManagerNew;


    int keyDel;
    String a;
    public int pos = 0;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityEnterCardDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();          //Root xml or viewGroup will be a part of converted view over here
        setContentView(view);
        initialization();
    }

    private void initialization() {
        ButterKnife.bind(this);
        sessionmanager = new SessionManager(EnterCardDetailsActivity.this);
        apiManagerNew = new ApiManager(this, this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));

        mBinding.btnNext.setOnClickListener(this);
        mBinding.imgBack.setOnClickListener(this);

       mBinding.edtCardNumber.addTextChangedListener(this);
       mBinding.dateEdit.addTextChangedListener(this);

    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        switch (APINAME) {
            case API_S.Tags.SEND_STRIPE_TOKEN:
                ModelGetStripeToken modelGetStripeToken = SingletonGson.getInstance().fromJson("" + script, ModelGetStripeToken.class);
                if (modelGetStripeToken.getResult().equals("1")) {
                    Toast.makeText(EnterCardDetailsActivity.this, "" + modelGetStripeToken.getMessage(), Toast.LENGTH_SHORT).show();
                    EnterCardDetailsActivity.this.finish();
                } else {
                    Toast.makeText(EnterCardDetailsActivity.this, "" + modelGetStripeToken.getMessage(), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onFetchResultZero(String script, String APINAME) {
        try {
            progressDialog.hide();
            Toast.makeText(this, ""+script, Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnNext:
                String card_number = mBinding.edtCardNumber.getText().toString();
                String expiry = mBinding.dateEdit.getText().toString();
                String cvv = mBinding.cvvEdit.getText().toString();

                if (card_number.equals("") || expiry.equals("") || cvv.equals("")) {
                    Toast.makeText(EnterCardDetailsActivity.this, "Please Enter Details first", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    String[] parts = expiry.split("/");
                    String month = parts[0];
                    String year = parts[1];

                    Card card = new Card(card_number, Integer.parseInt(month), Integer.parseInt(year), cvv);
                    try {
                        Stripe stripe = new Stripe(getApplicationContext(), EnterCardDetailsActivity.this.getString(R.string.stripe_key));
                        stripe.createToken(card,
                                new TokenCallback() {
                                    @Override
                                    public void onError(Exception e) {
                                        progressDialog.hide();
                                        Toast.makeText(EnterCardDetailsActivity.this, getString(R.string.enter_card_number), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onSuccess(Token token) {
                                        String stripe_token = token.getId() + "";

                                        progressDialog.hide();

                                        HashMap<String, String> data = new HashMap<>();
                                        data.put("payment_option", "STRIPE");
                                        data.put("token", "" + stripe_token);
                                        try {
                                            progressDialog.show();
                                            apiManagerNew._post(API_S.Tags.SEND_STRIPE_TOKEN, API_S.Endpoints.SEND_STRIPE_TOKEN, data, sessionmanager);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    } catch (Exception e) {
                        progressDialog.hide();
                        Toast.makeText(EnterCardDetailsActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.img_back:
                EnterCardDetailsActivity.this.finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        EditText date_edit = (EditText) findViewById(R.id.date_edit);
        pos = date_edit.getText().length();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        EditText edt_card_number = (EditText) findViewById(R.id.edt_card_number);
        EditText date_edit = (EditText) findViewById(R.id.date_edit);

        onTextChangedEditText(edt_card_number, date_edit);
    }

    private void onTextChangedEditText(EditText edt_card_number, EditText date_edit) {

        if (edt_card_number.hasFocus()) {

            boolean flag = true;
            String eachBlock[] = edt_card_number.getText().toString().split("-");
            for (int i = 0; i < eachBlock.length; i++) {
                if (eachBlock[i].length() > 4) {
                    flag = false;
                }
            }
            if (flag) {

                edt_card_number.setOnKeyListener(new View.OnKeyListener() {

                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    if (((edt_card_number.getText().length() + 1) % 5) == 0) {

                        if (edt_card_number.getText().toString().split("-").length <= 3) {
                            edt_card_number.setText(edt_card_number.getText() + "-");
                            edt_card_number.setSelection(edt_card_number.getText().length());
                        }
                    }
                    a = edt_card_number.getText().toString();
                } else {
                    a = edt_card_number.getText().toString();
                    keyDel = 0;
                }

            } else {
                edt_card_number.setText(a);
            }
        } else if (date_edit.hasFocus()) {
            if (date_edit.getText().length() == 2 && pos != 3) {
                date_edit.setText(date_edit.getText().toString() + "/");
                date_edit.setSelection(3);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}