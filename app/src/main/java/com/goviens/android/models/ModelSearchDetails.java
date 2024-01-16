package com.goviens.android.models;

import com.goviens.android.TestModel;

import java.util.List;

public class ModelSearchDetails {

    /**
     * version : 3.10
     * result : 1
     * message : Success
     * data : {"carpooling_ride_id":13,"ride_timestamp":"1607373000","ride_start_time_set_config":null,"ac_ride":true,"only_females":false,"booked_seats":0,"no_of_stops":1,"available_seats":4,"return_ride":false,"offer_user":{"name":"ro sss","phone":"null+91l+917807346538","email":"rr@g.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607087180_5fca344c24d08_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c16d60eb03b1684c04b1e656e135dad93cb1d05bb565b8c8630ca817b191f675","rating":"0.0"},"offer_user_vehicle":{"id":9,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1606917839_5fc79ecfc6891_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=01bd748ad5a8a45cea20a0e5cfad204a7a407911643afd219a635268366fc90f","vehicle_color":"Red","vehicle_number":"HP1234"},"payment_type":"Online Payment","total_amount":"INR 28","ride_details_list":[{"id":42,"drop_no":0,"location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","ride_timestamp":"1607373000","estimate_distance":null,"final_charges":null},{"id":42,"drop_no":1,"location":"IFFCO Chowk","ride_timestamp":"1607373000","estimate_distance":6,"final_charges":"INR 28"}],"request_users":[],"instructions":"Carpooling ride instructions."}
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
         * carpooling_ride_id : 13
         * ride_timestamp : 1607373000
         * ride_start_time_set_config : null
         * ac_ride : true
         * only_females : false
         * booked_seats : 0
         * no_of_stops : 1
         * available_seats : 4
         * return_ride : false
         * offer_user : {"name":"ro sss","phone":"null+91l+917807346538","email":"rr@g.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607087180_5fca344c24d08_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c16d60eb03b1684c04b1e656e135dad93cb1d05bb565b8c8630ca817b191f675","rating":"0.0"}
         * offer_user_vehicle : {"id":9,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1606917839_5fc79ecfc6891_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=01bd748ad5a8a45cea20a0e5cfad204a7a407911643afd219a635268366fc90f","vehicle_color":"Red","vehicle_number":"HP1234"}
         * payment_type : Online Payment
         * total_amount : INR 28
         * ride_details_list : [{"id":42,"drop_no":0,"location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","ride_timestamp":"1607373000","estimate_distance":null,"final_charges":null},{"id":42,"drop_no":1,"location":"IFFCO Chowk","ride_timestamp":"1607373000","estimate_distance":6,"final_charges":"INR 28"}]
         * request_users : []
         * instructions : Carpooling ride instructions.
         */

        private int carpooling_ride_id;
        private String ride_timestamp;
        private Object ride_start_time_set_config;
        private boolean ac_ride;
        private boolean only_females;
        private int booked_seats;
        private int no_of_stops;
        private int available_seats;
        private boolean return_ride;
        private OfferUserBean offer_user;
        private OfferUserVehicleBean offer_user_vehicle;
        private String payment_type;
        private String total_amount;
        private String instructions;
        private String wallet_balance;
        private List<RideDetailsListBean> ride_details_list;
        private List<?> request_users;
        private List<AcceptUsersBean> accept_users;
        private int total_seats;


        public int getTotal_seats() {
            return total_seats;
        }

        public void setTotal_seats(int total_seats) {
            this.total_seats = total_seats;
        }


        public String getWallet_balance() {
            return wallet_balance;
        }

        public void setWallet_balance(String wallet_balance) {
            this.wallet_balance = wallet_balance;
        }

        public int getCarpooling_ride_id() {
            return carpooling_ride_id;
        }

        public void setCarpooling_ride_id(int carpooling_ride_id) {
            this.carpooling_ride_id = carpooling_ride_id;
        }

        public String getRide_timestamp() {
            return ride_timestamp;
        }

        public void setRide_timestamp(String ride_timestamp) {
            this.ride_timestamp = ride_timestamp;
        }

        public Object getRide_start_time_set_config() {
            return ride_start_time_set_config;
        }

        public void setRide_start_time_set_config(Object ride_start_time_set_config) {
            this.ride_start_time_set_config = ride_start_time_set_config;
        }

        public boolean isAc_ride() {
            return ac_ride;
        }

        public void setAc_ride(boolean ac_ride) {
            this.ac_ride = ac_ride;
        }

        public boolean isOnly_females() {
            return only_females;
        }

        public void setOnly_females(boolean only_females) {
            this.only_females = only_females;
        }

        public int getBooked_seats() {
            return booked_seats;
        }

        public void setBooked_seats(int booked_seats) {
            this.booked_seats = booked_seats;
        }

        public int getNo_of_stops() {
            return no_of_stops;
        }

        public void setNo_of_stops(int no_of_stops) {
            this.no_of_stops = no_of_stops;
        }

        public int getAvailable_seats() {
            return available_seats;
        }

        public void setAvailable_seats(int available_seats) {
            this.available_seats = available_seats;
        }

        public boolean isReturn_ride() {
            return return_ride;
        }

        public void setReturn_ride(boolean return_ride) {
            this.return_ride = return_ride;
        }

        public OfferUserBean getOffer_user() {
            return offer_user;
        }

        public void setOffer_user(OfferUserBean offer_user) {
            this.offer_user = offer_user;
        }

        public OfferUserVehicleBean getOffer_user_vehicle() {
            return offer_user_vehicle;
        }

        public void setOffer_user_vehicle(OfferUserVehicleBean offer_user_vehicle) {
            this.offer_user_vehicle = offer_user_vehicle;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public List<RideDetailsListBean> getRide_details_list() {
            return ride_details_list;
        }

        public void setRide_details_list(List<RideDetailsListBean> ride_details_list) {
            this.ride_details_list = ride_details_list;
        }

        public List<?> getRequest_users() {
            return request_users;
        }

        public void setRequest_users(List<?> request_users) {
            this.request_users = request_users;
        }
        public List<AcceptUsersBean> getAccept_users() {
            return accept_users;
        }

        public void setAccept_users(List<AcceptUsersBean> accept_users) {
            this.accept_users = accept_users;
        }

        public static class OfferUserBean {
            /**
             * name : ro sss
             * phone : null+91l+917807346538
             * email : rr@g.com
             * image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607087180_5fca344c24d08_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c16d60eb03b1684c04b1e656e135dad93cb1d05bb565b8c8630ca817b191f675
             * rating : 0.0
             */

            private int id;
            private String name;
            private String phone;
            private String email;
            private String image;
            private String rating;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }
        }

        public static class OfferUserVehicleBean {
            /**
             * id : 9
             * vehicle_name : Sedan
             * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1606917839_5fc79ecfc6891_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201208%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201208T135537Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=01bd748ad5a8a45cea20a0e5cfad204a7a407911643afd219a635268366fc90f
             * vehicle_color : Red
             * vehicle_number : HP1234
             */

            private int id;
            private String vehicle_name;
            private String vehicle_image;
            private String vehicle_color;
            private String vehicle_number;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVehicle_name() {
                return vehicle_name;
            }

            public void setVehicle_name(String vehicle_name) {
                this.vehicle_name = vehicle_name;
            }

            public String getVehicle_image() {
                return vehicle_image;
            }

            public void setVehicle_image(String vehicle_image) {
                this.vehicle_image = vehicle_image;
            }

            public String getVehicle_color() {
                return vehicle_color;
            }

            public void setVehicle_color(String vehicle_color) {
                this.vehicle_color = vehicle_color;
            }

            public String getVehicle_number() {
                return vehicle_number;
            }

            public void setVehicle_number(String vehicle_number) {
                this.vehicle_number = vehicle_number;
            }
        }

        public static class RideDetailsListBean {
            /**
             * id : 42
             * drop_no : 0
             * location : Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India
             * ride_timestamp : 1607373000
             * estimate_distance : null
             * final_charges : null
             */

            private int id;
            private int drop_no;
            private String location;
            private String ride_timestamp;
            private Object estimate_distance;
            private Object final_charges;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDrop_no() {
                return drop_no;
            }

            public void setDrop_no(int drop_no) {
                this.drop_no = drop_no;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getRide_timestamp() {
                return ride_timestamp;
            }

            public void setRide_timestamp(String ride_timestamp) {
                this.ride_timestamp = ride_timestamp;
            }

            public Object getEstimate_distance() {
                return estimate_distance;
            }

            public void setEstimate_distance(Object estimate_distance) {
                this.estimate_distance = estimate_distance;
            }

            public Object getFinal_charges() {
                return final_charges;
            }

            public void setFinal_charges(Object final_charges) {
                this.final_charges = final_charges;
            }
        }
        public static class AcceptUsersBean {
            /**
             * carpooling_ride_user_detail_id : 178
             * accept_user_id : 314
             * accept_user_name : Rohit Sharma
             * accept_user_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1610544345_5ffef4d9d93e7_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210119%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210119T071710Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=b56c954cca16bbc0f6fe8e52365b51378154a0a27e18df0d08f7519e9ce5c604
             * accept_user_rating : 3.3
             */

            private int carpooling_ride_user_detail_id;
            private int accept_user_id;
            private String accept_user_name;
            private String accept_user_image;
            private double accept_user_rating;

            public int getCarpooling_ride_user_detail_id() {
                return carpooling_ride_user_detail_id;
            }

            public void setCarpooling_ride_user_detail_id(int carpooling_ride_user_detail_id) {
                this.carpooling_ride_user_detail_id = carpooling_ride_user_detail_id;
            }

            public int getAccept_user_id() {
                return accept_user_id;
            }

            public void setAccept_user_id(int accept_user_id) {
                this.accept_user_id = accept_user_id;
            }

            public String getAccept_user_name() {
                return accept_user_name;
            }

            public void setAccept_user_name(String accept_user_name) {
                this.accept_user_name = accept_user_name;
            }

            public String getAccept_user_image() {
                return accept_user_image;
            }

            public void setAccept_user_image(String accept_user_image) {
                this.accept_user_image = accept_user_image;
            }

            public double getAccept_user_rating() {
                return accept_user_rating;
            }

            public void setAccept_user_rating(double accept_user_rating) {
                this.accept_user_rating = accept_user_rating;
            }
        }
    }
}
