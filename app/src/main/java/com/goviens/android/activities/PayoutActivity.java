package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.goviens.android.R;
import com.goviens.android.models.ModelWallet;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;


import butterknife.ButterKnife;

public class PayoutActivity extends AppCompatActivity {


    String PAYOUT;
    TextView tvHeading;
    PlaceHolderView placeHolderTransaction;

    ModelWallet modelWallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout);
        ButterKnife.bind(this);
        PAYOUT = getIntent().getStringExtra("payout");
        if(PAYOUT.equals("1")){
            tvHeading.setText("Payout");
        }else {
            tvHeading.setText("Trip Payments");
        }
        modelWallet = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelWallet.class);
        if(PAYOUT.equals("0")){

        }else {
            
        }
    }
}
