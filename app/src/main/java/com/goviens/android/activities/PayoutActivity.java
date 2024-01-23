package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityPayoutBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;
import com.goviens.android.models.ModelWallet;
import com.goviens.android.utils.SingletonGson;
import com.mindorks.placeholderview.PlaceHolderView;



public class PayoutActivity extends AppCompatActivity {


    String PAYOUT;
    PlaceHolderView placeHolderTransaction;

    ModelWallet modelWallet;
    ActivityPayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        PAYOUT = getIntent().getStringExtra("payout");
        if(PAYOUT.equals("1")){
            binding.tvHeading.setText("Payout");
        }else {
            binding.tvHeading.setText("Trip Payments");
        }
        modelWallet = SingletonGson.getInstance().fromJson(getIntent().getStringExtra("script"), ModelWallet.class);
        if(PAYOUT.equals("0")){

        }else {
            
        }
    }
}
