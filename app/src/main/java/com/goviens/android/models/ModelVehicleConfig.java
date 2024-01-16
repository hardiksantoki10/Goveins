package com.goviens.android.models;

import java.util.List;

public class ModelVehicleConfig {

    /**
     * version : 2.50
     * result : 1
     * message : api.vehicleconfig
     * data : {"vehicle_type":[{"id":17,"vehicleTypeName":"Sedan"}],"vehicle_make":[{"id":12,"vehicleMakeName":"ALTO"},{"id":13,"vehicleMakeName":"Honda"}]}
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
        private List<VehicleTypeBean> vehicle_type;
        private List<VehicleMakeBean> vehicle_make;

        public List<VehicleTypeBean> getVehicle_type() {
            return vehicle_type;
        }

        public void setVehicle_type(List<VehicleTypeBean> vehicle_type) {
            this.vehicle_type = vehicle_type;
        }

        public List<VehicleMakeBean> getVehicle_make() {
            return vehicle_make;
        }

        public void setVehicle_make(List<VehicleMakeBean> vehicle_make) {
            this.vehicle_make = vehicle_make;
        }

        public static class VehicleTypeBean {
            /**
             * id : 17
             * vehicleTypeName : Sedan
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

        public static class VehicleMakeBean {
            /**
             * id : 12
             * vehicleMakeName : ALTO
             */

            private int id;
            private String vehicleMakeName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVehicleMakeName() {
                return vehicleMakeName;
            }

            public void setVehicleMakeName(String vehicleMakeName) {
                this.vehicleMakeName = vehicleMakeName;
            }
        }
    }
}
