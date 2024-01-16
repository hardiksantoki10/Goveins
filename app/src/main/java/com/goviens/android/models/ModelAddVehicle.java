package com.goviens.android.models;

public class ModelAddVehicle {

    /**
     * version : 2.50
     * result : 1
     * message : Success
     * data : {"user_vehicle_id":5}
     */

    private String version;
    private String result;
    private String message;
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
        /**
         * user_vehicle_id : 5
         */

        private int user_vehicle_id;

        public int getUser_vehicle_id() {
            return user_vehicle_id;
        }

        public void setUser_vehicle_id(int user_vehicle_id) {
            this.user_vehicle_id = user_vehicle_id;
        }
    }
}
