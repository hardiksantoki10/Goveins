package com.goviens.android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

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

public class PaymentMethodActivity extends AppCompatActivity {

    RadioButton radioCash;
    TextView tvCashText;
    View view;
    RadioButton radioWallet;
    RadioButton radioWalletAtPickup;
    Button btnContinue;
    ImageView imgBack;

    TextView tvBalance;

    String cashAmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);
        cashAmt = getIntent().getStringExtra("cashAmt");
        tvBalance.setText(getResources().getString(R.string.balance_)+"  "+getIntent().getStringExtra("balance"));
        if(cashAmt.equals("0")){
            radioCash.setVisibility(View.GONE);
            tvCashText.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }else {
            radioCash.setVisibility(View.VISIBLE);
            tvCashText.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioCash.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","2");
                    setResult(RESULT_OK, intent);
                    finish();
                }else if(radioWallet.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","1");
                    setResult(RESULT_OK, intent);
                    finish();
                }else if(radioWalletAtPickup.isChecked()){
                    Intent intent = new Intent();
                    intent.putExtra("payment","3");
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(PaymentMethodActivity.this, getResources().getString(R.string.please_select_any_payment_method), Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
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
