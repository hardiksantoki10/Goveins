package com.goviens.android.models;

public class ModelNotification {

    /**
     * notification_type : Booking_Request
     * ride_id : 384
     * notification_gen_time : 1614153371
     * ride_status : 2
     */

    private String notification_type;
    private int ride_id;
    private int notification_gen_time;
    private int ride_status;

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

    public int getNotification_gen_time() {
        return notification_gen_time;
    }

    public void setNotification_gen_time(int notification_gen_time) {
        this.notification_gen_time = notification_gen_time;
    }

    public int getRide_status() {
        return ride_status;
    }

    public void setRide_status(int ride_status) {
        this.ride_status = ride_status;
    }
}
