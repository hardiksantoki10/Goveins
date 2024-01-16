package com.goviens.android.models;

import java.util.List;

public class ModelRideDetails {
    /**
     * version : 6.10
     * result : 1
     * message : Success
     * data : {"carpooling_ride_id":36,"ride_timestamp":"1608669000","ride_start_time_set_config":null,"ac_ride":false,"only_females":false,"booked_seats":4,"no_of_stops":0,"available_seats":1,"return_ride":false,"offer_user":{"id":249,"name":"Rohit Sharma","phone":"1234","email":"r@r.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c4e3b25bb6d38fd6425f9fba71f57ec366e866b5241763f5775f4a62ff13f46f","rating":"0.0"},"offer_user_vehicle":{"id":12,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d42466609b54b0e01bcaf1efa9dbf281aa9faea6c7c4ee0df33e3e307d83574e","vehicle_color":"Red","vehicle_number":"HP 8484"},"payment_type":"Online Payment","cancel_reason":[{"id":19,"reason":"CAncel due to work"},{"id":20,"reason":"cancel due to illness"},{"id":21,"reason":"Cancel due to insufficient oil"}],"total_amount":"INR 4","ride_details_list":[{"id":78,"drop_no":0,"location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1608669000","estimate_distance":null,"final_charges":null},{"id":78,"drop_no":1,"location":"Spaze itech Park Gate opposite wine n beer shop","ride_timestamp":"1608669000","estimate_distance":0,"final_charges":"INR 4"}],"request_users":[{"id":21,"merchant_id":8,"carpooling_ride_id":36,"carpooling_ride_detail_id":78,"user_id":249,"rating":"0.0","end_point_id":null,"pickup_id":78,"pickup_location":"77.0432061329484","pickup_latitude":"28.41203064688635","pickup_longitude":"77.0432061329484","drop_id":78,"drop_location":"spaze itech Park Gate opposite wine n beer shop","drop_latitude":"28.41203064688635","drop_longitude":"77.0432061329484","end_ride_id":78,"ride_timestamp":"1608669000","is_return_ride":"0","return_ride_timestamp":null,"ride_status":"2","booked_seats":"0","promo_code":null,"ride_booking_otp":4257,"payment_action":2,"payment_method_id":null,"user_card_id":null,"payment_status":0,"cancel_reason_id":0,"created_at":"2020-12-22 05:42:55","updated_at":"2020-12-22 05:42:55"},{"id":22,"merchant_id":8,"carpooling_ride_id":36,"carpooling_ride_detail_id":78,"user_id":249,"rating":"0.0","end_point_id":null,"pickup_id":78,"pickup_location":"77.0432061329484","pickup_latitude":"28.41203064688635","pickup_longitude":"77.0432061329484","drop_id":78,"drop_location":"spaze itech Park Gate opposite wine n beer shop","drop_latitude":"28.41203064688635","drop_longitude":"77.0432061329484","end_ride_id":78,"ride_timestamp":"1608669000","is_return_ride":"0","return_ride_timestamp":null,"ride_status":"2","booked_seats":"0","promo_code":null,"ride_booking_otp":5287,"payment_action":2,"payment_method_id":null,"user_card_id":null,"payment_status":0,"cancel_reason_id":0,"created_at":"2020-12-22 05:55:29","updated_at":"2020-12-22 05:55:29"}]}
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
         * carpooling_ride_id : 36
         * ride_timestamp : 1608669000
         * ride_start_time_set_config : null
         * ac_ride : false
         * only_females : false
         * booked_seats : 4
         * no_of_stops : 0
         * available_seats : 1
         * return_ride : false
         * offer_user : {"id":249,"name":"Rohit Sharma","phone":"1234","email":"r@r.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c4e3b25bb6d38fd6425f9fba71f57ec366e866b5241763f5775f4a62ff13f46f","rating":"0.0"}
         * offer_user_vehicle : {"id":12,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d42466609b54b0e01bcaf1efa9dbf281aa9faea6c7c4ee0df33e3e307d83574e","vehicle_color":"Red","vehicle_number":"HP 8484"}
         * payment_type : Online Payment
         * cancel_reason : [{"id":19,"reason":"CAncel due to work"},{"id":20,"reason":"cancel due to illness"},{"id":21,"reason":"Cancel due to insufficient oil"}]
         * total_amount : INR 4
         * ride_details_list : [{"id":78,"drop_no":0,"location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1608669000","estimate_distance":null,"final_charges":null},{"id":78,"drop_no":1,"location":"Spaze itech Park Gate opposite wine n beer shop","ride_timestamp":"1608669000","estimate_distance":0,"final_charges":"INR 4"}]
         * request_users : [{"id":21,"merchant_id":8,"carpooling_ride_id":36,"carpooling_ride_detail_id":78,"user_id":249,"rating":"0.0","end_point_id":null,"pickup_id":78,"pickup_location":"77.0432061329484","pickup_latitude":"28.41203064688635","pickup_longitude":"77.0432061329484","drop_id":78,"drop_location":"spaze itech Park Gate opposite wine n beer shop","drop_latitude":"28.41203064688635","drop_longitude":"77.0432061329484","end_ride_id":78,"ride_timestamp":"1608669000","is_return_ride":"0","return_ride_timestamp":null,"ride_status":"2","booked_seats":"0","promo_code":null,"ride_booking_otp":4257,"payment_action":2,"payment_method_id":null,"user_card_id":null,"payment_status":0,"cancel_reason_id":0,"created_at":"2020-12-22 05:42:55","updated_at":"2020-12-22 05:42:55"},{"id":22,"merchant_id":8,"carpooling_ride_id":36,"carpooling_ride_detail_id":78,"user_id":249,"rating":"0.0","end_point_id":null,"pickup_id":78,"pickup_location":"77.0432061329484","pickup_latitude":"28.41203064688635","pickup_longitude":"77.0432061329484","drop_id":78,"drop_location":"spaze itech Park Gate opposite wine n beer shop","drop_latitude":"28.41203064688635","drop_longitude":"77.0432061329484","end_ride_id":78,"ride_timestamp":"1608669000","is_return_ride":"0","return_ride_timestamp":null,"ride_status":"2","booked_seats":"0","promo_code":null,"ride_booking_otp":5287,"payment_action":2,"payment_method_id":null,"user_card_id":null,"payment_status":0,"cancel_reason_id":0,"created_at":"2020-12-22 05:55:29","updated_at":"2020-12-22 05:55:29"}]
         */

        private int carpooling_ride_id;
        private String ride_timestamp;
        private boolean can_ride_start;
        private String can_ride_start_text;
        private boolean ac_ride;
        private boolean only_females;
        private int booked_seats;
        private int no_of_stops;
        private int available_seats;
        private boolean return_ride;
        private OfferUserBean offer_user;
        private OfferUserVehicleBean offer_user_vehicle;
        private String payment_type;
        private String instructions;
        private String per_seat_charge;
        private String total_amount;
        private List<CancelReasonBean> cancel_reason;
        private List<RideDetailsListBean> ride_details_list;
        private List<RequestUsersBean> request_users;
        private List<AcceptUsersBean> accept_users;
        private String total_seats;
        private String cancel_amount;

        public String getCancel_amount() {
            return cancel_amount;
        }

        public void setCancel_amount(String cancel_amount) {
            this.cancel_amount = cancel_amount;
        }

        public String getPer_seat_charge() {
            return per_seat_charge;
        }

        public void setPer_seat_charge(String per_seat_charge) {
            this.per_seat_charge = per_seat_charge;
        }

        public String getTotal_seats() {
            return total_seats;
        }

        public void setTotal_seats(String total_seats) {
            this.total_seats = total_seats;
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

        public boolean isCan_ride_start() {
            return can_ride_start;
        }

        public void setCan_ride_start(boolean can_ride_start) {
            this.can_ride_start = can_ride_start;
        }

        public String getCan_ride_start_text() {
            return can_ride_start_text;
        }

        public void setCan_ride_start_text(String can_ride_start_text) {
            this.can_ride_start_text = can_ride_start_text;
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

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public List<CancelReasonBean> getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(List<CancelReasonBean> cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public List<RideDetailsListBean> getRide_details_list() {
            return ride_details_list;
        }

        public void setRide_details_list(List<RideDetailsListBean> ride_details_list) {
            this.ride_details_list = ride_details_list;
        }

        public List<RequestUsersBean> getRequest_users() {
            return request_users;
        }

        public void setRequest_users(List<RequestUsersBean> request_users) {
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
             * id : 249
             * name : Rohit Sharma
             * phone : 1234
             * email : r@r.com
             * image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=c4e3b25bb6d38fd6425f9fba71f57ec366e866b5241763f5775f4a62ff13f46f
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
             * id : 12
             * vehicle_name : Sedan
             * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201222%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201222T083037Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d42466609b54b0e01bcaf1efa9dbf281aa9faea6c7c4ee0df33e3e307d83574e
             * vehicle_color : Red
             * vehicle_number : HP 8484
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

        public static class CancelReasonBean {
            /**
             * id : 19
             * reason : CAncel due to work
             */

            private int id;
            private String reason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }
        }

        public static class RideDetailsListBean {
            /**
             * id : 78
             * drop_no : 0
             * location : S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
             * ride_timestamp : 1608669000
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

        public static class RequestUsersBean {
            /**
             * id : 21
             * merchant_id : 8
             * carpooling_ride_id : 36
             * carpooling_ride_detail_id : 78
             * user_id : 249
             * rating : 0.0
             * end_point_id : null
             * pickup_id : 78
             * pickup_location : 77.0432061329484
             * pickup_latitude : 28.41203064688635
             * pickup_longitude : 77.0432061329484
             * drop_id : 78
             * drop_location : spaze itech Park Gate opposite wine n beer shop
             * drop_latitude : 28.41203064688635
             * drop_longitude : 77.0432061329484
             * end_ride_id : 78
             * ride_timestamp : 1608669000
             * is_return_ride : 0
             * return_ride_timestamp : null
             * ride_status : 2
             * booked_seats : 0
             * promo_code : null
             * ride_booking_otp : 4257
             * payment_action : 2
             * payment_method_id : null
             * user_card_id : null
             * payment_status : 0
             * cancel_reason_id : 0
             * created_at : 2020-12-22 05:42:55
             * updated_at : 2020-12-22 05:42:55
             */

            private int request_user_id;
            private String request_user_name;
            private String request_user_image;
            private String request_user_rating;
            private int carpooling_ride_user_detail_id;

            public int getCarpooling_ride_user_detail_id() {
                return carpooling_ride_user_detail_id;
            }

            public void setCarpooling_ride_user_detail_id(int carpooling_ride_user_detail_id) {
                this.carpooling_ride_user_detail_id = carpooling_ride_user_detail_id;
            }

            public int getRequest_user_id() {
                return request_user_id;
            }

            public void setRequest_user_id(int request_user_id) {
                this.request_user_id = request_user_id;
            }

            public String getRequest_user_name() {
                return request_user_name;
            }

            public void setRequest_user_name(String request_user_name) {
                this.request_user_name = request_user_name;
            }

            public String getRequest_user_image() {
                return request_user_image;
            }

            public void setRequest_user_image(String request_user_image) {
                this.request_user_image = request_user_image;
            }

            public String getRequest_user_rating() {
                return request_user_rating;
            }

            public void setRequest_user_rating(String request_user_rating) {
                this.request_user_rating = request_user_rating;
            }
        }
    }

    public static class AcceptUsersBean {


        private int accept_user_id;
        private int carpooling_ride_user_detail_id;
        private String accept_user_name;
        private String accept_user_image;
        private String accept_user_rating;
        private String unique_id;
        private int ride_status;
        private String cancel_reason;

        public String getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(String cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public int getRide_status() {
            return ride_status;
        }

        public void setRide_status(int ride_status) {
            this.ride_status = ride_status;
        }

        public String getUnique_id() {
            return unique_id;
        }

        public void setUnique_id(String unique_id) {
            this.unique_id = unique_id;
        }

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

        public String getAccept_user_rating() {
            return accept_user_rating;
        }

        public void setAccept_user_rating(String accept_user_rating) {
            this.accept_user_rating = accept_user_rating;
        }
    }
}
