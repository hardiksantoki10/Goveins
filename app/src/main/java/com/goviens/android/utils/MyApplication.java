package com.goviens.android.utils;

import android.app.Application;

import com.onesignal.OneSignal;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        super.onCreate();
        OneSignal.startInit(this)
                .autoPromptLocation(true)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
