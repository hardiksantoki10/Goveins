package com.goviens.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.goviens.android.BuildConfig;
import com.goviens.android.models.ModelAppConfiguration;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    SharedPreferences pref1;
    SharedPreferences.Editor editor1;
    int PRIVATE_MODE = 0;

    Context _context;
    private static final String PREF_NAME = "LoginPrefrences";
    private static final String PREF_NAME_1 = "LanguagePrefrences";
    public static final String KEY_STRING_VERSION = "string_version";
    private String TAG = "SessionManager";
    public static final String DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE";
    public static final String SHOW_LANGUAGE_LIST = "SHOW_LANGUAGE_LIST";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String USER_ID = "user_id";
    public static final String USER_FIRST_NAME = "user_first_name";
    public static final String USER_LAST_NAME = "user_last_name";
    public static final String USER_DOB = "user_dob";
    public static final String USER_GENDER = "user_gender";
    public static final String USER_SMOKER_TYPE = "user_smoker_type";
    public static final String USER_ALLOW_SMOKER = "user_allow_smoker";
    public static final String USER_PHONE = "user_phone_number";
    public static final String USER_LOGIN_VIA = "USER_LOGIN_VIA";
    public static final String USER_PHONE_CODE = "user_phone_code";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_IMAGE = "user_image";
    public static final String LOGIN_TYPE = "logintype";
    public static final String CURRENCY = "currency";
    public static final String MOBILE = "mobile";


    public static final String COUNTRYID = "COUNTRY_ID";

    public static final String CURRENT_LAT = "CURRENT_LAT";
    public static final String CURRENT_LNG = "CURRENT_LNG";
    public static final String KEY_APP_CONFIG = "countries";



    public static final String LANGUAGE = "Language";
    public static final String KEY_ACCESS_TOKEN = "access_token";

    private HashMap<String, String> headers = new HashMap<>();




    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        pref1 = _context.getSharedPreferences(PREF_NAME_1, PRIVATE_MODE);
        editor1 = pref1.edit();
    }

    public HashMap<String, String> getLOcation(){
        HashMap<String, String> user = new HashMap<>();
        user.put(CURRENT_LAT, pref.getString(CURRENT_LAT, ""));
        user.put(CURRENT_LNG, pref.getString(CURRENT_LNG, ""));

        return user;
    }

    public void setLocation(String lat,String lng) {
        editor.putString(CURRENT_LAT, lat);
        editor.putString(CURRENT_LNG, lng);
        editor.commit();
    }

    public String getLanguage() {
        return pref1.getString(LANGUAGE, "");
    }


    public void setLanguage(String locale) {
        editor1.putString(LANGUAGE, "" + locale);
        editor1.commit();
        Locale myLocale = new Locale("" + locale);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        _context.getResources().updateConfiguration(config, _context.getResources().getDisplayMetrics());
    }
    public void logoutUser() {
        editor.clear();
        editor.commit();
    }

    @SuppressLint("LongLogTag")
    public void cleartAccessToken(String accessToken) {

        editor.putString(KEY_ACCESS_TOKEN, "" + accessToken);
        editor.commit();
    }

    @SuppressLint("LongLogTag")
    public HashMap<String, String> getHeader() throws Exception {
        headers.clear();
        if (!getAccessToken().equals("")) {
            headers.put("Authorization", "" + getAccessToken());
            return headers;
        } else {
            headers.put(IntentKeys.PUBLIC_KEY, "" + BuildConfig.PUBLIC_KEY);
            headers.put(IntentKeys.SECRET_KEY, "" + BuildConfig.SECRET_KEY);
            return headers;
        }
    }@SuppressLint("LongLogTag")
    public void createLoginSession(String dob, String user_id, String fist_name,String last_name, String user_email, String user_password, String user_phone,String user_phone_code, String userimage, String user_gender,String smoker_type,String allow_smoker, String login_type,String login_via, String currency, String mobile) {
        editor.putString(USER_ID, user_id);
        editor.putString(USER_FIRST_NAME, fist_name);
        editor.putString(USER_LAST_NAME, last_name);
        editor.putString(USER_DOB, dob);
        editor.putString(USER_GENDER, user_gender);
        editor.putString(USER_SMOKER_TYPE, smoker_type);
        editor.putString(USER_ALLOW_SMOKER, allow_smoker);
        editor.putString(USER_EMAIL, user_email);
        editor.putString(USER_PASSWORD, user_password);
        editor.putString(USER_PHONE, user_phone);
        editor.putString(USER_LOGIN_VIA, login_via);
        editor.putString(USER_PHONE_CODE,user_phone_code);
        editor.putString(USER_IMAGE, userimage);
        editor.putString(LOGIN_TYPE, login_type);
        editor.putString(CURRENCY,currency);
        editor.putString(MOBILE, mobile);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_ID, pref.getString(USER_ID, ""));
        user.put(USER_FIRST_NAME, pref.getString(USER_FIRST_NAME, ""));
        user.put(USER_LAST_NAME, pref.getString(USER_LAST_NAME, ""));
        user.put(USER_DOB, pref.getString(USER_DOB,""));
        user.put(USER_GENDER, pref.getString(USER_GENDER, ""));
        user.put(USER_SMOKER_TYPE, pref.getString(USER_SMOKER_TYPE, ""));
        user.put(USER_ALLOW_SMOKER, pref.getString(USER_ALLOW_SMOKER, ""));
        user.put(USER_EMAIL, pref.getString(USER_EMAIL, "default@gmail.com"));
        user.put(USER_PASSWORD, pref.getString(USER_PASSWORD, ""));
        user.put(USER_PHONE, pref.getString(USER_PHONE, ""));
        user.put(USER_LOGIN_VIA, pref.getString(USER_LOGIN_VIA, ""));
        user.put(USER_PHONE_CODE, pref.getString(USER_PHONE_CODE, ""));
        user.put(USER_IMAGE, pref.getString(USER_IMAGE, ""));
        user.put(LOGIN_TYPE, pref.getString(LOGIN_TYPE, ""));
        user.put(CURRENCY, pref.getString(CURRENCY, ""));
        user.put(MOBILE, pref.getString(MOBILE, ""));
        return user;
    }



    public void makUserLoggedIn(){
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


    @SuppressLint("LongLogTag")
    public void setUpdatedStringVersion(String version) {

        editor.putString(KEY_STRING_VERSION, version);
        editor.commit();
    }

    @SuppressLint("LongLogTag")
    public String getUpdateStringVersion() {
        return "" + pref.getString(KEY_STRING_VERSION, "0.0");
    }

    public boolean isShowLanguageList() {
        return pref.getBoolean(SHOW_LANGUAGE_LIST, true);
    }

    public void setShowLanguageList(boolean defaultLanguage) {
        editor.putBoolean(SHOW_LANGUAGE_LIST, defaultLanguage);
        editor.commit();
    }


    public String getDefaultLanguage() {
        return pref.getString(DEFAULT_LANGUAGE, "en");
    }

    public void setDefaultLanguage(String defaultLanguage) {
        editor.putString(DEFAULT_LANGUAGE, defaultLanguage);
        editor.commit();
    }

    public void setAppConfig(String appConfig) {

        Log.d("" + TAG, "Adding countries in prefrences");
        editor.putString(KEY_APP_CONFIG, "" + appConfig);
        editor.commit();
    }


    public ModelAppConfiguration getAppConfig() {
        ModelAppConfiguration data = SingletonGson.getInstance().fromJson("" + pref.getString(KEY_APP_CONFIG, ""), ModelAppConfiguration.class);
        return data;
    }

    public void setcountryid(int id)
    {
        editor.putInt(COUNTRYID, id);
        editor.commit();

    }

    public int getcountryid()
    {
        return pref.getInt(COUNTRYID, 1);
    }

    @SuppressLint("LongLogTag")
    public void setAccessToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, "Bearer " + accessToken);
        editor.commit();
    }

    public String getAccessToken() {
        return "" + pref.getString(KEY_ACCESS_TOKEN, "");
    }

}
