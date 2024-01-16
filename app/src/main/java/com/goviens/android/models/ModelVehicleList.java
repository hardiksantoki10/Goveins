package com.goviens.android.models;

import java.util.List;

public class ModelVehicleList {
    /**
     * version : 2.70
     * result : 1
     * message : Success
     * data : {"vehicle_list":[{"id":5,"vehicle_number":"abc","vehicle_model_name":"800","vehicle_make_name":"ALTO","vehicle_type_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604667910_5fa54a061f563_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=3816600eaa8a2937649dd3f96a281f5ea6ddf4e69325ca120db75a102747e34d","vehicle_number_plate":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604667910_5fa54a06e0970_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=4082000a42a039cc6d87d2ed88d1e9b3623660d9af1d38f5bc9c8c45eb81c979","vehicle_color":"red","no_of_seats":null,"vehicle_default":true,"vehicle_active":false},{"id":6,"vehicle_number":"abcfd","vehicle_model_name":"800","vehicle_make_name":"ALTO","vehicle_type_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604668223_5fa54b3f4c2a2_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=a5a3a0ba05d76064a7fa0c0ca9de3e2211d87261999551dcc4be30af722a3f4a","vehicle_number_plate":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604668224_5fa54b400d655_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=fc41fe759f079d47971747053a6cbfce9fe23e660038b02c0bdc91516bd4eb32","vehicle_color":"red","no_of_seats":null,"vehicle_default":false,"vehicle_active":false}]}
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
        private List<VehicleListBean> vehicle_list;

        public List<VehicleListBean> getVehicle_list() {
            return vehicle_list;
        }

        public void setVehicle_list(List<VehicleListBean> vehicle_list) {
            this.vehicle_list = vehicle_list;
        }

        public static class VehicleListBean {
            /**
             * id : 5
             * vehicle_number : abc
             * vehicle_model_name : 800
             * vehicle_make_name : ALTO
             * vehicle_type_name : Sedan
             * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604667910_5fa54a061f563_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=3816600eaa8a2937649dd3f96a281f5ea6ddf4e69325ca120db75a102747e34d
             * vehicle_number_plate : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1604667910_5fa54a06e0970_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T105219Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=4082000a42a039cc6d87d2ed88d1e9b3623660d9af1d38f5bc9c8c45eb81c979
             * vehicle_color : red
             * no_of_seats : null
             * vehicle_default : true
             * vehicle_active : false
             */

            private int id;
            private String vehicle_number;
            private String vehicle_model_name;
            private String vehicle_make_name;
            private String vehicle_type_name;
            private String vehicle_image;
            private String vehicle_number_plate;
            private String vehicle_color;
            private Object no_of_seats;
            private boolean vehicle_default;
            private boolean vehicle_active;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVehicle_number() {
                return vehicle_number;
            }

            public void setVehicle_number(String vehicle_number) {
                this.vehicle_number = vehicle_number;
            }

            public String getVehicle_model_name() {
                return vehicle_model_name;
            }

            public void setVehicle_model_name(String vehicle_model_name) {
                this.vehicle_model_name = vehicle_model_name;
            }

            public String getVehicle_make_name() {
                return vehicle_make_name;
            }

            public void setVehicle_make_name(String vehicle_make_name) {
                this.vehicle_make_name = vehicle_make_name;
            }

            public String getVehicle_type_name() {
                return vehicle_type_name;
            }

            public void setVehicle_type_name(String vehicle_type_name) {
                this.vehicle_type_name = vehicle_type_name;
            }

            public String getVehicle_image() {
                return vehicle_image;
            }

            public void setVehicle_image(String vehicle_image) {
                this.vehicle_image = vehicle_image;
            }

            public String getVehicle_number_plate() {
                return vehicle_number_plate;
            }

            public void setVehicle_number_plate(String vehicle_number_plate) {
                this.vehicle_number_plate = vehicle_number_plate;
            }

            public String getVehicle_color() {
                return vehicle_color;
            }

            public void setVehicle_color(String vehicle_color) {
                this.vehicle_color = vehicle_color;
            }

            public Object getNo_of_seats() {
                return no_of_seats;
            }

            public void setNo_of_seats(Object no_of_seats) {
                this.no_of_seats = no_of_seats;
            }

            public boolean isVehicle_default() {
                return vehicle_default;
            }

            public void setVehicle_default(boolean vehicle_default) {
                this.vehicle_default = vehicle_default;
            }

            public boolean isVehicle_active() {
                return vehicle_active;
            }

            public void setVehicle_active(boolean vehicle_active) {
                this.vehicle_active = vehicle_active;
            }
        }
    }
}
