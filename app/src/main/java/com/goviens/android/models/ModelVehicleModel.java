package com.goviens.android.models;

import java.util.List;

public class ModelVehicleModel {

    /**
     * version : 2.50
     * result : 1
     * message : api.vehiclemodel
     * data : [{"id":19,"vehicleTypeName":"800"}]
     */

    private String version;
    private String result;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 19
         * vehicleTypeName : 800
         */

        private int id;
        private String vehicleTypeName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVehicleTypeName() {
            return vehicleTypeName;
        }

        public void setVehicleTypeName(String vehicleTypeName) {
            this.vehicleTypeName = vehicleTypeName;
        }
    }
}
