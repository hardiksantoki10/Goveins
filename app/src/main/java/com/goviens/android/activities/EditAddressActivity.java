package com.goviens.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

import com.goviens.android.R;



public class EditAddressActivity extends AppCompatActivity {

    RadioButton radioHome;
    RadioButton radioWork;
    RadioButton radioOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
    }
}
