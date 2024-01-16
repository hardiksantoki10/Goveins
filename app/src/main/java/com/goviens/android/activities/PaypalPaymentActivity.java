package com.goviens.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.goviens.android.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PaypalPaymentActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_payment);
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        String url = getIntent().getStringExtra("url");
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Toast.makeText(PaypalPaymentActivity.this, ""+url, Toast.LENGTH_SHORT).show();
            if(url.equals("https://goviens.com/ms-admin/public/public/api/paypal/success")){
                Toast.makeText(PaypalPaymentActivity.this, getResources().getString(R.string.payment_successful), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("success", "1");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else if(url.equals("https://goviens.com/ms-admin/public/public/api/paypal/fail")){
                Toast.makeText(PaypalPaymentActivity.this, getResources().getString(R.string.payment_failed), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("success", "0");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            return true;
        }
    }
}

