package com.goviens.android.utils;

import android.util.Log;

import org.json.JSONArray;

public class ApporioLog {
    public static JSONArray logs_when_app_in_foreground = new JSONArray();
    public static JSONArray logs_when_app_in_background = new JSONArray();

    public ApporioLog() {
    }

    public static void logI(String Tag, String message) {
        Log.i(Tag, message);
    }

    public static void logW(String Tag, String message) {
        Log.w(Tag, message);
    }

    public static void logD(String Tag, String message) {
        Log.d(Tag, message);
    }

    public static void logE(String Tag, String message) {
        Log.e(Tag, message);
    }

    private static void addLogToJSON(String TAG, String Message, String LogType) {

//        try {
//            JSONObject obj = new JSONObject();
//            obj.put("tag", "" + TAG);
//            obj.put("message", "" + Message);
//            obj.put("type", "" + LogType);
//            obj.put("timestamp", "" + System.currentTimeMillis());
//            obj.put("app_name", "" + ApporioApplication.CONTEXT.getPackageName());
//            obj.put("device_name", "" + Build.MANUFACTURER + " " + Build.MODEL);
//            obj.put("app_type", "android");
//            obj.put("unique_no", "" + Build.SERIAL);
//            if (Constants.SOCKET_CONNECTOIN_STATUS) {
//                ApporioApplication.mSocket.emit("log_message", new Object[]{"" + obj});
//            } else {
//                Log.e("**********", "No Socket connect, unable to RTL logs");
//            }
//
//            if (LogUtils.isAppOnForeground(ApporioApplication.CONTEXT)) {
//                logs_when_app_in_foreground.put(obj);
//                LogDatabaseUtil.getInstance(ApporioApplication.CONTEXT).insertLog("" + obj);
//            } else {
//                logs_when_app_in_background.put(obj);
//            }
//        } catch (Exception var4) {
//            ;
//        }

    }
}
