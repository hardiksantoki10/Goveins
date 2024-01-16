package com.goviens.android.baseClass;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.goviens.android.utils.SessionManager;

import java.util.Locale;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale myLocale = new Locale("" + new SessionManager(newBase).getLanguage());
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        newBase.getResources().updateConfiguration(config, newBase.getResources().getDisplayMetrics());
        super.attachBaseContext(newBase);
    }
}
