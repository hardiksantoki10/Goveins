package com.goviens.android.models;

public class ModelCheckout {

    /**
     * version : 6.10
     * result : 1
     * message : CheckOut Details added successfully
     * data : {"carpooling_ride_checkout_id":2}
     */

    private String version;
    private String result;
    private String message;
    /**
     * carpooling_ride_checkout_id : 2
     */

    private DataBean data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int carpooling_ride_checkout_id;

        public int getCarpooling_ride_checkout_id() {
            return carpooling_ride_checkout_id;
        }

        public void setCarpooling_ride_checkout_id(int carpooling_ride_checkout_id) {
            this.carpooling_ride_checkout_id = carpooling_ride_checkout_id;
        }
    }
}
