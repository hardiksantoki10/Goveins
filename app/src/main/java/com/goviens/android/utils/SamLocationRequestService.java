package com.goviens.android.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class SamLocationRequestService implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    Context context;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;

    boolean mShoeDialog;

    SamLocationListener samLocationListener;


    public SamLocationRequestService(Context context, boolean showDialog) {
        this.context = context;
        this.mShoeDialog = showDialog;
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    public interface SamLocationListener {

        public void onLocationUpdate(Location location);
    }

    public void executeService(final SamLocationListener samLocationListener) {
        if (checkGPSisOnOrNot()) {
            this.samLocationListener = samLocationListener;
            mGoogleApiClient.connect();
            if (mGoogleApiClient.isConnected()) {
                startLocationUpdates();
                Log.d("", "Location update resumed .....................");
            } else {
                startLocationUpdates();
            }
        } else {

        }
    }

    public void getsamlocationlistener(final SamLocationListener samLocationListener) {
        this.samLocationListener = samLocationListener;
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onLocationChanged(Location location) {
        Log.e("Lat", " " + String.valueOf(location.getLatitude()));
        Log.e("Long", " " + String.valueOf(location.getLongitude()));
        if ((String.valueOf(location.getLatitude())).equals(null)) {

        } else {
            stopLocationUpdates();
//            LocationAddress locationAddress = new LocationAddress();
//
//            geolocation = locationAddress.getAddressFromLocation(location.getLatitude(),location.getLongitude(), context);
//            Log.e("********************geolocation", " " + geolocation);
            samLocationListener.onLocationUpdate(location);
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        Log.d("LocationRequestService", "Location update started ..............: ");
    }


    public void stopLocationUpdates() {
        //LocationServices.FusedLocationApi.removeLocationUpdates(
               // mGoogleApiClient, this);
        Log.d("LocationRequestService", "Location update stopped .......................");
    }


    private boolean checkGPSisOnOrNot() {
        LocationManager lm = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            if (!gps_enabled && !network_enabled) {
                // notify user
////                final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
////                dialog.setMessage(R.string.enable_app_location);
////                dialog.setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
////                        // TODO Auto-generated method stub
////                        Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////                        context.startActivity(myIntent);
////                    }
////                });
////                dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
////
////                    }
////                });
//                if(mShoeDialog){dialog.show();}
//

            }
        } catch (Exception e) {

        }

        if (!network_enabled && !gps_enabled) {
            return false;
        } else return true;

    }
}


