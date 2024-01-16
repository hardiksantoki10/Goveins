package com.goviens.android.utils;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.goviens.android.R;
import com.goviens.android.activities.MainActivity;
import com.goviens.android.activities.PastRidesActivity;
import com.goviens.android.activities.PastTakenRideDetailsActivity;
import com.goviens.android.activities.PaymentMethodActivity;
import com.goviens.android.activities.RideDetailsActivity;
import com.goviens.android.activities.SplashActivity;
import com.goviens.android.activities.TransactionListActivity;
import com.goviens.android.activities.WalletActivity;
import com.goviens.android.models.ModelNotification;
import com.goviens.android.models.ModelPromoteNotification;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by Lap0010 on 26-04-2018.
 */

public class OneSignalServiceClass extends NotificationExtenderService {

    private String TAG = "OneSignalServiceClass";
    private int MAINACTIVITY = 1, TRACKING_ACTIVITY = 2;
    int count = 2;

    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        try {
            ApporioLog.logD("" + TAG, "RECEIVED_RESPONSE--> " + receivedResult.payload.additionalData);
            EventBus.getDefault().post("" + receivedResult.payload.additionalData);

            ModelNotification modelNotification = SingletonGson.getInstance().fromJson("" + receivedResult.payload.additionalData, ModelNotification.class);
            EventBus.getDefault().post(new EventNotification("" + receivedResult.payload.title, "" + receivedResult.payload.body, "" + receivedResult.payload.additionalData, "" + modelNotification.getNotification_type()));
            switch (modelNotification.getNotification_type()) {
                case "Booking_Request":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, RideDetailsActivity.class)
                            .putExtra("ride_id", "" + modelNotification.getRide_id())
                            .putExtra("from","0").addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;

                case "Driver_Cancel_Request":
                        generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "MONEY_ADDED_WALLET":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, WalletActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "Ride_Accept_Request":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "Ride_Reject_Request":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, PastRidesActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "User_Cancel_Request":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, PastRidesActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "Passenger_Ride_Start":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "END_TAKEN_RIDE":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, PastTakenRideDetailsActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra("ride_id", "" + modelNotification.getRide_id()));
                    break;
                case "RIDE_START":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "DRIVER_RIDE_END":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "VEHICLE_APPROVED":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "AUTO_REJECT":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, PastTakenRideDetailsActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra("ride_id", "" + modelNotification.getRide_id()));
                    break;
                case "PAYMENT_SUCCESS":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, WalletActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "PAYMENT_FAILED":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, WalletActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "NOTIFICATION":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, SplashActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "PROMOTION_NOTIFICATION":
                    ModelPromoteNotification modelPromoteNotification = SingletonGson.getInstance().fromJson("" + receivedResult.payload.additionalData, ModelPromoteNotification.class);
                    Bitmap bitmap = getBitmapfromUrl(modelPromoteNotification.getSegment_data().getImage().replace("\\", ""));
                    displayImageNotification(bitmap, ""+modelPromoteNotification.getSegment_data().getTitle());
                    break;
                case "Wallet_Notification":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, TransactionListActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            .putExtra("notification","1"));
                    break;
                case "REFER_AMOUNT":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, WalletActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "DOCUMENT_EXPIRED_REMINDER":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, SplashActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "DOCUMENT_EXPIRE_REMINDER":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, SplashActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "AUTO_CANCEL":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, SplashActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case "WALLET_UPDATE":
                    generateNotification(receivedResult.payload.title, receivedResult.payload.body, new Intent(this, WalletActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
            }
        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception caught while parsing " + e.getMessage());
        }

        return true;
    }



    private void generateNotification(String tittle, String message, Intent intent) {
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(tittle)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setContentIntent(pendingIntent);
            System.out.println("RANDOM NUMBER  "+m);
            notificationManager.notify(m, notificationBuilder.build());
        } else {


            NotificationChannel channel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel("my_channel_01",
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
            }

            String mChannel = "Message";
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(channel);
            }

//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.app_logo)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message)
                    .setAutoCancel(true)
//                    .setColor(Color.parseColor("#0x008000"))
                    .setSound(alarmSound)
                    .setVibrate(pattern)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                    .setContentIntent(pendingIntent)
                    .setChannelId("my_channel_01");
            System.out.println("RANDOM NUMBER  "+m);
            notificationManager.notify(m, notificationBuilder.build());
        }


//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
//            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo);
//
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                    .setSmallIcon(R.drawable.app_logo)
//                    .setLargeIcon(largeIcon)
//                    .setContentTitle(tittle)
//                    .setContentText(message)
//                    .setAutoCancel(true)
////                    .setColor(Color.parseColor("#0x008000"))
//                    .setSound(alarmSound)
//                    .setVibrate(pattern)
//                    .setContentIntent(pendingIntent);
//            notificationManager.notify(0, notificationBuilder.build());


    }

    private void generateNotificationPromotional(Bitmap bitmap, String message, Intent intent) {

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(alarmSound)
                    .addAction(android.R.drawable.ic_menu_info_details, "Get More Details", pendingIntent)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent);

            if (bitmap != null) {
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()   //Set the Image in Big picture Style with text.
                        .bigPicture(bitmap)
                        .setSummaryText(message)
                        .bigLargeIcon(null));
            }

            System.out.println("RANDOM NUMBER  "+m);
            notificationManager.notify(m, notificationBuilder.build());
        } else {


            NotificationChannel channel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel("my_channel_01",
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
            }

            String mChannel = "Message";
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(channel);
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            long[] pattern = {500, 500, 500, 500, 500, 500, 500, 500, 500};
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
                notificationBuilder.setColor(0x169AB9);
            } else {
                notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            }


            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(largeIcon)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(message)
                    .setAutoCancel(true)
//                    .setColor(Color.parseColor("#0x008000"))
                    .setSound(alarmSound)
                    .addAction(android.R.drawable.ic_menu_info_details, "Get More Details", pendingIntent)
                    .setVibrate(pattern)
                    .setContentIntent(pendingIntent)
                    .setChannelId("my_channel_01");

            if (bitmap != null) {
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()   //Set the Image in Big picture Style with text.
                        .bigPicture(bitmap)
                        .setSummaryText(message)
                        .bigLargeIcon(null));
            }

            System.out.println("RANDOM NUMBER  "+m);
            notificationManager.notify(m, notificationBuilder.build());
        }
    }

    private void displayImageNotification(Bitmap bitmap, String notificationTittle) {

        if (new SessionManager(this).isLoggedIn()) {
            generateNotificationPromotional(bitmap, "" + notificationTittle, new Intent(this, SplashActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else {
            generateNotificationPromotional(bitmap, "" + notificationTittle, new Intent(this, SplashActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP));

        }

    }

    public Bitmap getBitmapfromUrl(String imageUrl) {     //This method returns the Bitmap from Url;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}

