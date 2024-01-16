package com.goviens.android.models;

import java.util.List;

public class ModelTakenRideDetails {

    /**
     * version : 6.10
     * result : 1
     * message : Success
     * data : [{"taken_ride_details":{"id":61,"pickup_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","drop_location":"Spaze itech Park Gate opposite wine n beer shop","ride_time":"1609273800","end_ride_time":"1609273800","promo_code":null,"ride_amount":4,"otp":5374,"payment_status":"Cash Payment","ac_ride":"2","booked_seat":"0","female_ride":"0","is_ride_confirm":false},"take_vehicle_details":{"id":12,"vehicle_type_name":"Sedan","vehicle_color":"Red","vehicle_number":"HP 8484","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=113088d971192922b44cd4029fdd80b123fe9407f562689007c58d3e0c4eca6f"},"offer_user_detail":{"id":249,"name":"Rohit Sharma","phone":"+2371234","email":"r@r.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=cdba93e3189be96fd21d743f5e56d1dc6bf463297030bdc8d29fe2fcfe75b254"},"cancel_reason":[{"id":18,"reason":"Cancel due to some work."}]}]
     */

    private String version;
    private String result;
    private String message;
    /**
     * taken_ride_details : {"id":61,"pickup_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","drop_location":"Spaze itech Park Gate opposite wine n beer shop","ride_time":"1609273800","end_ride_time":"1609273800","promo_code":null,"ride_amount":4,"otp":5374,"payment_status":"Cash Payment","ac_ride":"2","booked_seat":"0","female_ride":"0","is_ride_confirm":false}
     * take_vehicle_details : {"id":12,"vehicle_type_name":"Sedan","vehicle_color":"Red","vehicle_number":"HP 8484","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=113088d971192922b44cd4029fdd80b123fe9407f562689007c58d3e0c4eca6f"}
     * offer_user_detail : {"id":249,"name":"Rohit Sharma","phone":"+2371234","email":"r@r.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=cdba93e3189be96fd21d743f5e56d1dc6bf463297030bdc8d29fe2fcfe75b254"}
     * cancel_reason : [{"id":18,"reason":"Cancel due to some work."}]
     */

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
         * id : 61
         * pickup_location : S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
         * drop_location : Spaze itech Park Gate opposite wine n beer shop
         * ride_time : 1609273800
         * end_ride_time : 1609273800
         * promo_code : null
         * ride_amount : 4
         * otp : 5374
         * payment_status : Cash Payment
         * ac_ride : 2
         * booked_seat : 0
         * female_ride : 0
         * is_ride_confirm : false
         */

        private TakenRideDetailsBean taken_ride_details;
        /**
         * id : 12
         * vehicle_type_name : Sedan
         * vehicle_color : Red
         * vehicle_number : HP 8484
         * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1607949296_5fd75bf0efc53_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=113088d971192922b44cd4029fdd80b123fe9407f562689007c58d3e0c4eca6f
         */

        private TakeVehicleDetailsBean take_vehicle_details;
        /**
         * id : 249
         * name : Rohit Sharma
         * phone : +2371234
         * email : r@r.com
         * image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201224%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201224T102327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=cdba93e3189be96fd21d743f5e56d1dc6bf463297030bdc8d29fe2fcfe75b254
         */

        private OfferUserDetailBean offer_user_detail;
        /**
         * id : 18
         * reason : Cancel due to some work.
         */

        private List<CancelReasonBean> cancel_reason;
        private List<AcceptUsersBean> accept_users;


        public List<AcceptUsersBean> getAccept_users() {
            return accept_users;
        }

        public void setAccept_users(List<AcceptUsersBean> accept_users) {
            this.accept_users = accept_users;
        }

        public TakenRideDetailsBean getTaken_ride_details() {
            return taken_ride_details;
        }

        public void setTaken_ride_details(TakenRideDetailsBean taken_ride_details) {
            this.taken_ride_details = taken_ride_details;
        }

        public TakeVehicleDetailsBean getTake_vehicle_details() {
            return take_vehicle_details;
        }

        public void setTake_vehicle_details(TakeVehicleDetailsBean take_vehicle_details) {
            this.take_vehicle_details = take_vehicle_details;
        }

        public OfferUserDetailBean getOffer_user_detail() {
            return offer_user_detail;
        }

        public void setOffer_user_detail(OfferUserDetailBean offer_user_detail) {
            this.offer_user_detail = offer_user_detail;
        }

        public List<CancelReasonBean> getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(List<CancelReasonBean> cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public static class AcceptUsersBean {


            private int accept_user_id;
            private String accept_user_name;
            private String accept_user_image;
            private String accept_user_rating;
            private String cancel_reason;

            public String getCancel_reason() {
                return cancel_reason;
            }

            public void setCancel_reason(String cancel_reason) {
                this.cancel_reason = cancel_reason;
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

        public static class TakenRideDetailsBean {
            private int id;
            private int ride_id;
            private String pickup_location;
            private String drop_location;
            private String ride_time;
            private String end_ride_time;
            private Object promo_code;
            private String ride_amount;
            private int otp;
            private int payment_status;
            private String ac_ride;
            private String booked_seat;
            private String female_ride;
            private boolean is_ride_confirm;
            private String instructions;
            private boolean is_user_rated;
            private String cancel_reason;
            private String ride_status;
            private int available_seats;
            private String cancel_amount;

            public String getCancel_reason() {
                return cancel_reason;
            }

            public void setCancel_reason(String cancel_reason) {
                this.cancel_reason = cancel_reason;
            }

            public String getCancel_amount() {
                return cancel_amount;
            }

            public void setCancel_amount(String cancel_amount) {
                this.cancel_amount = cancel_amount;
            }

            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }

            public int getAvailable_seats() {
                return available_seats;
            }

            public void setAvailable_seats(int available_seats) {
                this.available_seats = available_seats;
            }

            public boolean isIs_user_rated() {
                return is_user_rated;
            }

            public void setIs_user_rated(boolean is_user_rated) {
                this.is_user_rated = is_user_rated;
            }

            public String getInstructions() {
                return instructions;
            }

            public void setInstructions(String instructions) {
                this.instructions = instructions;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRide_id() {
                return ride_id;
            }

            public void setRide_id(int ride_id) {
                this.ride_id = ride_id;
            }

            public String getPickup_location() {
                return pickup_location;
            }

            public void setPickup_location(String pickup_location) {
                this.pickup_location = pickup_location;
            }

            public String getDrop_location() {
                return drop_location;
            }

            public void setDrop_location(String drop_location) {
                this.drop_location = drop_location;
            }

            public String getRide_time() {
                return ride_time;
            }

            public void setRide_time(String ride_time) {
                this.ride_time = ride_time;
            }

            public String getEnd_ride_time() {
                return end_ride_time;
            }

            public void setEnd_ride_time(String end_ride_time) {
                this.end_ride_time = end_ride_time;
            }

            public Object getPromo_code() {
                return promo_code;
            }

            public void setPromo_code(Object promo_code) {
                this.promo_code = promo_code;
            }

            public String getRide_amount() {
                return ride_amount;
            }

            public void setRide_amount(String ride_amount) {
                this.ride_amount = ride_amount;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
            }

            public int getPayment_status() {
                return payment_status;
            }

            public void setPayment_status(int payment_status) {
                this.payment_status = payment_status;
            }

            public String getAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(String ac_ride) {
                this.ac_ride = ac_ride;
            }

            public String getBooked_seat() {
                return booked_seat;
            }

            public void setBooked_seat(String booked_seat) {
                this.booked_seat = booked_seat;
            }

            public String getFemale_ride() {
                return female_ride;
            }

            public void setFemale_ride(String female_ride) {
                this.female_ride = female_ride;
            }

            public boolean isIs_ride_confirm() {
                return is_ride_confirm;
            }

            public void setIs_ride_confirm(boolean is_ride_confirm) {
                this.is_ride_confirm = is_ride_confirm;
            }
        }

        public static class TakeVehicleDetailsBean {
            private int id;
            private String vehicle_type_name;
            private String vehicle_color;
            private String vehicle_number;
            private String vehicle_image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVehicle_type_name() {
                return vehicle_type_name;
            }

            public void setVehicle_type_name(String vehicle_type_name) {
                this.vehicle_type_name = vehicle_type_name;
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

            public String getVehicle_image() {
                return vehicle_image;
            }

            public void setVehicle_image(String vehicle_image) {
                this.vehicle_image = vehicle_image;
            }
        }

        public static class OfferUserDetailBean {
            private int id;
            private String name;
            private String phone;
            private String email;
            private String image;
            private String ride_status;

            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }

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
        }

        public static class CancelReasonBean {
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
    }
}
