package com.goviens.android.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class AppUtils {
    public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; ++i)
            {
                if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890]*").matcher(String.valueOf(source.charAt(i))).matches())
                {
                    return "";
                }
            }

            return null;
        }
    };


    public static String uriToBase64(Uri imageUri , Context context) throws Exception {
        final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        return encodeImage(selectedImage);
    }


    private static String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }

    public static String decodeMapKey(String key) {
        byte[] decrypt= Base64.decode(key, Base64.DEFAULT);
        String text = null;
        try {
            text = new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }

    public  static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    // User validate while creating new User
    public static String validateUserWhileSignUp(String username , String useremail , String userphone , String userpassword){
        String value = "OK" ;
        if(!validateUsername(username).equals("OK")){
            value = validateUsername(username) ;
        }else if (!validatePhone(userphone).equals("OK")){
            value = validatePhone(userphone) ;
        }else if (!validateEmail(useremail).equals("OK")){
            value = validateEmail(useremail) ;
        }else if (!validatePassword(userpassword).equals("OK")){
            value = validatePassword(userpassword) ;
        }
        return value;
    }

    private static String validateEmail (String email){
        if(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "OK" ;
        }else{
            return "Email Not valid";
        }
    }

    private static String validatePhone(String phone){

        if(phone.contains("+") && phone.length()==13){
            return "OK";
        }else{
            return "Phone No Not valid , Should be of 12 digit with <+> sign";
        }
    }

    private static String validatePassword(String password){
        if(password.length() < 6){
            return "Please set atleast 6 digit in pasword";
        }else{
            return  "OK";
        }
    }

    private static String validateUsername (String username ){
        if(username.length() < 1){
            return "Username not Valid";
        }else{
            return "OK";
        }
    }

    public static boolean checkGPSisOnOrNot(Context context){
        LocationManager lm = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!network_enabled&& !gps_enabled){
            return false;
        }else return true;
    }



    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public static String createTimestamp(int month, int day, int yr, int hr, int mint){
        String str = "";
        try {
            String str_date = month + "-" + day + "-" + yr + " "+ hr + ":" +mint +":"+ "00";
            DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            Date date = formatter.parse(str_date);
            long output = date.getTime() / 1000L;
            str = Long.toString(output);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }


    public static long creatTimeStampViaDate(String myDate  , String Formatter) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(Formatter, Locale.US);
        Date date = sdf.parse(myDate);
        return date.getTime();
    }

    public static String getDateViaTimestampFormat(long time , String formatter) throws Exception {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        DateFormat mformatter = new SimpleDateFormat(formatter,Locale.US);
        return mformatter.format(time);
    }
    public static String getDateTimeInDayFormat(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("EEE,dd MMM");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }
    public static String getDateTimeInFormat(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("MMM dd, hh:mm a");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }

    public static String getDateTimeInYearFormat(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("yyyy MMM dd, hh:mm a");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }

    public static String getDateWithYearViaTimestamp(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }

    public static String getDateTimeViaTimestamp(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("dd,MM  HH:mm");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }

    public static String getTimeViaTimestamp(String timeStamp){
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(new Date(Long.parseLong(timeStamp) * 1000L));
        return dateString;
    }
}
