package com.goviens.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.goviens.android.R;
import com.goviens.android.databinding.ActivityPaymentMethodBinding;
import com.goviens.android.databinding.ActivitySignUpBinding;

public class PaymentMethodActivity extends AppCompatActivity {


    View view;


    String cashAmt;

    ActivityPaymentMethodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cashAmt = getIntent().getStringExtra("cashAmt");
        binding.tvBalance.setText(getResources().getString(R.string.balance_)+"  "+getIntent().getStringExtra("balance"));
        if(cashAmt.equals("0")){
            binding.radioCash.setVisibility(View.GONE);
            binding.tvCashText.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }else {
            binding.radioCash.setVisibility(View.VISIBLE);
            binding.tvCashText.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }
        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.radioCash.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","2");
                    setResult(RESULT_OK, intent);
                    finish();
                }else if(binding.radioWallet.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","1");
                    setResult(RESULT_OK, intent);
                    finish();
                }else if(binding.radioWalletAtPickup.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","3");
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(PaymentMethodActivity.this, getResources().getString(R.string.please_select_any_payment_method), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
}
