package com.goviens.android.models;

import java.util.List;

public class ModelTracking {

    /**
     * version : 6.90
     * result : 1
     * message : Carpooling Details
     * data : {"Active_User_Ride":{"carpooling_ride_id":77,"ride_timestamp":"1609785000","ride_start_time_set_config":null,"ac_ride":false,"only_females":false,"booked_seats":1,"no_of_stops":0,"available_seats":4,"return_ride":false,"offer_user":{"id":274,"name":"Rr hdh","phone":"780714","email":"hdhh@dd.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1609333377_5fec7a81e758a_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=9a36356a566e4eb46bbc86f7520f9e1084b062a750ab9a1703e65b2472dce137","rating":"0.0"},"offer_user_vehicle":{"id":33,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1609418280_5fedc6286db9f_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=85684b8e5039ef62fb9f8d85905ceb125ef1a6ad8e79067f2bed0be1ddd9c088","vehicle_color":"Red","vehicle_number":"HP 8485"},"payment_type":"Online Payment","cancel_reason":[{"id":19,"reason":"Plan Changed"},{"id":20,"reason":"Having issues with Vehicle"},{"id":21,"reason":"Traffic Accidents"}],"total_amount":"XAF 104","ride_details_list":[{"id":125,"drop_no":0,"from_location":"Rohini, New Delhi, Delhi, India","from_latitude":"28.738267736728734","from_longitude":"77.08221517503262","ride_timestamp":"1609785000"},{"id":125,"drop_no":1,"to_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","to_latitude":"28.666570605632568","to_longitude":"77.22906779497862","ride_timestamp":"1609785000"}],"pickup_users":[{"pickup_user_id":249,"pickup_user_name":"Rohit Sharma","pickup_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","pickup_user_rating":"0.0","pickup_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"2"}],"drop_users":[{"drop_user_id":249,"drop_user_name":"Rohit Sharma","drop_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","drop_user_rating":"0.0","drop_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"3"}]}}
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
         * Active_User_Ride : {"carpooling_ride_id":77,"ride_timestamp":"1609785000","ride_start_time_set_config":null,"ac_ride":false,"only_females":false,"booked_seats":1,"no_of_stops":0,"available_seats":4,"return_ride":false,"offer_user":{"id":274,"name":"Rr hdh","phone":"780714","email":"hdhh@dd.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1609333377_5fec7a81e758a_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=9a36356a566e4eb46bbc86f7520f9e1084b062a750ab9a1703e65b2472dce137","rating":"0.0"},"offer_user_vehicle":{"id":33,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1609418280_5fedc6286db9f_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=85684b8e5039ef62fb9f8d85905ceb125ef1a6ad8e79067f2bed0be1ddd9c088","vehicle_color":"Red","vehicle_number":"HP 8485"},"payment_type":"Online Payment","cancel_reason":[{"id":19,"reason":"Plan Changed"},{"id":20,"reason":"Having issues with Vehicle"},{"id":21,"reason":"Traffic Accidents"}],"total_amount":"XAF 104","ride_details_list":[{"id":125,"drop_no":0,"from_location":"Rohini, New Delhi, Delhi, India","from_latitude":"28.738267736728734","from_longitude":"77.08221517503262","ride_timestamp":"1609785000","to_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","to_latitude":"28.666570605632568","to_longitude":"77.22906779497862"},{"id":125,"drop_no":1,"to_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","to_latitude":"28.666570605632568","to_longitude":"77.22906779497862","ride_timestamp":"1609785000"}],"pickup_users":[{"pickup_user_id":249,"pickup_user_name":"Rohit Sharma","pickup_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","pickup_user_rating":"0.0","pickup_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"2"}],"drop_users":[{"drop_user_id":249,"drop_user_name":"Rohit Sharma","drop_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","drop_user_rating":"0.0","drop_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"3"}]}
         */

        private ActiveUserRideBean Active_User_Ride;

        public ActiveUserRideBean getActive_User_Ride() {
            return Active_User_Ride;
        }

        public void setActive_User_Ride(ActiveUserRideBean Active_User_Ride) {
            this.Active_User_Ride = Active_User_Ride;
        }

        public static class ActiveUserRideBean {
            /**
             * carpooling_ride_id : 77
             * ride_timestamp : 1609785000
             * ride_start_time_set_config : null
             * ac_ride : false
             * only_females : false
             * booked_seats : 1
             * no_of_stops : 0
             * available_seats : 4
             * return_ride : false
             * offer_user : {"id":274,"name":"Rr hdh","phone":"780714","email":"hdhh@dd.com","image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1609333377_5fec7a81e758a_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=9a36356a566e4eb46bbc86f7520f9e1084b062a750ab9a1703e65b2472dce137","rating":"0.0"}
             * offer_user_vehicle : {"id":33,"vehicle_name":"Sedan","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1609418280_5fedc6286db9f_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=85684b8e5039ef62fb9f8d85905ceb125ef1a6ad8e79067f2bed0be1ddd9c088","vehicle_color":"Red","vehicle_number":"HP 8485"}
             * payment_type : Online Payment
             * cancel_reason : [{"id":19,"reason":"Plan Changed"},{"id":20,"reason":"Having issues with Vehicle"},{"id":21,"reason":"Traffic Accidents"}]
             * total_amount : XAF 104
             * ride_details_list : [{"id":125,"drop_no":0,"from_location":"Rohini, New Delhi, Delhi, India","from_latitude":"28.738267736728734","from_longitude":"77.08221517503262","ride_timestamp":"1609785000"},{"id":125,"drop_no":1,"to_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","to_latitude":"28.666570605632568","to_longitude":"77.22906779497862","ride_timestamp":"1609785000"}]
             * pickup_users : [{"pickup_user_id":249,"pickup_user_name":"Rohit Sharma","pickup_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","pickup_user_rating":"0.0","pickup_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"2"}]
             * drop_users : [{"drop_user_id":249,"drop_user_name":"Rohit Sharma","drop_user_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291","drop_user_rating":"0.0","drop_user_distance":25,"start_location":"Rohini, New Delhi, Delhi, India","end_location":"Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India","ride_time":"1609785000","end_ride_time":"1609785000","payment_type":0,"final_amount":"XAF 104","UserPhone":"+237+2371234","ride_status":"3"}]
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
            private List<CancelReasonBean> cancel_reason;
            private List<RideDetailsListBean> ride_details_list;
            private List<PickupUsersBean> pickup_users;
            private List<DropUsersBean> drop_users;

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

            public List<PickupUsersBean> getPickup_users() {
                return pickup_users;
            }

            public void setPickup_users(List<PickupUsersBean> pickup_users) {
                this.pickup_users = pickup_users;
            }

            public List<DropUsersBean> getDrop_users() {
                return drop_users;
            }

            public void setDrop_users(List<DropUsersBean> drop_users) {
                this.drop_users = drop_users;
            }

            public static class OfferUserBean {
                /**
                 * id : 274
                 * name : Rr hdh
                 * phone : 780714
                 * email : hdhh@dd.com
                 * image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1609333377_5fec7a81e758a_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=9a36356a566e4eb46bbc86f7520f9e1084b062a750ab9a1703e65b2472dce137
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
                 * id : 33
                 * vehicle_name : Sedan
                 * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1609418280_5fedc6286db9f_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=85684b8e5039ef62fb9f8d85905ceb125ef1a6ad8e79067f2bed0be1ddd9c088
                 * vehicle_color : Red
                 * vehicle_number : HP 8485
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
                 * reason : Plan Changed
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
                 * id : 125
                 * drop_no : 0
                 * from_location : Rohini, New Delhi, Delhi, India
                 * from_latitude : 28.738267736728734
                 * from_longitude : 77.08221517503262
                 * ride_timestamp : 1609785000
                 * to_location : Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India
                 * to_latitude : 28.666570605632568
                 * to_longitude : 77.22906779497862
                 */

                private int id;
                private int drop_no;
                private String from_location;
                private String from_latitude;
                private String from_longitude;
                private String ride_timestamp;
                private String to_location;
                private String to_latitude;
                private String to_longitude;

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

                public String getFrom_location() {
                    return from_location;
                }

                public void setFrom_location(String from_location) {
                    this.from_location = from_location;
                }

                public String getFrom_latitude() {
                    return from_latitude;
                }

                public void setFrom_latitude(String from_latitude) {
                    this.from_latitude = from_latitude;
                }

                public String getFrom_longitude() {
                    return from_longitude;
                }

                public void setFrom_longitude(String from_longitude) {
                    this.from_longitude = from_longitude;
                }

                public String getRide_timestamp() {
                    return ride_timestamp;
                }

                public void setRide_timestamp(String ride_timestamp) {
                    this.ride_timestamp = ride_timestamp;
                }

                public String getTo_location() {
                    return to_location;
                }

                public void setTo_location(String to_location) {
                    this.to_location = to_location;
                }

                public String getTo_latitude() {
                    return to_latitude;
                }

                public void setTo_latitude(String to_latitude) {
                    this.to_latitude = to_latitude;
                }

                public String getTo_longitude() {
                    return to_longitude;
                }

                public void setTo_longitude(String to_longitude) {
                    this.to_longitude = to_longitude;
                }
            }

            public static class PickupUsersBean {
                /**
                 * pickup_user_id : 249
                 * pickup_user_name : Rohit Sharma
                 * pickup_user_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291
                 * pickup_user_rating : 0.0
                 * pickup_user_distance : 25
                 * start_location : Rohini, New Delhi, Delhi, India
                 * end_location : Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India
                 * ride_time : 1609785000
                 * end_ride_time : 1609785000
                 * payment_type : 0
                 * final_amount : XAF 104
                 * UserPhone : +237+2371234
                 * ride_status : 2
                 */

                private int pickup_user_id;
                private String pickup_user_name;
                private String pickup_user_image;
                private String pickup_user_rating;
                private int pickup_user_distance;
                private String start_location;
                private String end_location;
                private String ride_time;
                private String end_ride_time;
                private int payment_type;
                private int payment_action;
                private String final_amount;
                private String UserPhone;
                private String ride_status;
                private String no_of_seats;
                private String unique_id;

                public String getUnique_id() {
                    return unique_id;
                }

                public void setUnique_id(String unique_id) {
                    this.unique_id = unique_id;
                }

                public String getNo_of_seats() {
                    return no_of_seats;
                }

                public void setNo_of_seats(String no_of_seats) {
                    this.no_of_seats = no_of_seats;
                }

                public int getPayment_action() {
                    return payment_action;
                }

                public void setPayment_action(int payment_action) {
                    this.payment_action = payment_action;
                }

                public int getPickup_user_id() {
                    return pickup_user_id;
                }

                public void setPickup_user_id(int pickup_user_id) {
                    this.pickup_user_id = pickup_user_id;
                }

                public String getPickup_user_name() {
                    return pickup_user_name;
                }

                public void setPickup_user_name(String pickup_user_name) {
                    this.pickup_user_name = pickup_user_name;
                }

                public String getPickup_user_image() {
                    return pickup_user_image;
                }

                public void setPickup_user_image(String pickup_user_image) {
                    this.pickup_user_image = pickup_user_image;
                }

                public String getPickup_user_rating() {
                    return pickup_user_rating;
                }

                public void setPickup_user_rating(String pickup_user_rating) {
                    this.pickup_user_rating = pickup_user_rating;
                }

                public int getPickup_user_distance() {
                    return pickup_user_distance;
                }

                public void setPickup_user_distance(int pickup_user_distance) {
                    this.pickup_user_distance = pickup_user_distance;
                }

                public String getStart_location() {
                    return start_location;
                }

                public void setStart_location(String start_location) {
                    this.start_location = start_location;
                }

                public String getEnd_location() {
                    return end_location;
                }

                public void setEnd_location(String end_location) {
                    this.end_location = end_location;
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

                public int getPayment_type() {
                    return payment_type;
                }

                public void setPayment_type(int payment_type) {
                    this.payment_type = payment_type;
                }

                public String getFinal_amount() {
                    return final_amount;
                }

                public void setFinal_amount(String final_amount) {
                    this.final_amount = final_amount;
                }

                public String getUserPhone() {
                    return UserPhone;
                }

                public void setUserPhone(String UserPhone) {
                    this.UserPhone = UserPhone;
                }

                public String getRide_status() {
                    return ride_status;
                }

                public void setRide_status(String ride_status) {
                    this.ride_status = ride_status;
                }
            }

            public static class DropUsersBean {
                /**
                 * drop_user_id : 249
                 * drop_user_name : Rohit Sharma
                 * drop_user_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210108%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210108T102804Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=d705091ab134aec77109d69771b507b44a4f257958f73d8bff0518517d474291
                 * drop_user_rating : 0.0
                 * drop_user_distance : 25
                 * start_location : Rohini, New Delhi, Delhi, India
                 * end_location : Kashmiri Gate Near, Lothian Rd, Inter State Bus Terminal, Mori Gate, New Delhi, Delhi 110006, India
                 * ride_time : 1609785000
                 * end_ride_time : 1609785000
                 * payment_type : 0
                 * final_amount : XAF 104
                 * UserPhone : +237+2371234
                 * ride_status : 3
                 */

                private int drop_user_id;
                private String drop_user_name;
                private String drop_user_image;
                private String drop_user_rating;
                private int drop_user_distance;
                private String start_location;
                private String end_location;
                private String ride_time;
                private String end_ride_time;
                private int payment_type;
                private String final_amount;
                private String UserPhone;
                private String ride_status;

                public int getDrop_user_id() {
                    return drop_user_id;
                }

                public void setDrop_user_id(int drop_user_id) {
                    this.drop_user_id = drop_user_id;
                }

                public String getDrop_user_name() {
                    return drop_user_name;
                }

                public void setDrop_user_name(String drop_user_name) {
                    this.drop_user_name = drop_user_name;
                }

                public String getDrop_user_image() {
                    return drop_user_image;
                }

                public void setDrop_user_image(String drop_user_image) {
                    this.drop_user_image = drop_user_image;
                }

                public String getDrop_user_rating() {
                    return drop_user_rating;
                }

                public void setDrop_user_rating(String drop_user_rating) {
                    this.drop_user_rating = drop_user_rating;
                }

                public int getDrop_user_distance() {
                    return drop_user_distance;
                }

                public void setDrop_user_distance(int drop_user_distance) {
                    this.drop_user_distance = drop_user_distance;
                }

                public String getStart_location() {
                    return start_location;
                }

                public void setStart_location(String start_location) {
                    this.start_location = start_location;
                }

                public String getEnd_location() {
                    return end_location;
                }

                public void setEnd_location(String end_location) {
                    this.end_location = end_location;
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

                public int getPayment_type() {
                    return payment_type;
                }

                public void setPayment_type(int payment_type) {
                    this.payment_type = payment_type;
                }

                public String getFinal_amount() {
                    return final_amount;
                }

                public void setFinal_amount(String final_amount) {
                    this.final_amount = final_amount;
                }

                public String getUserPhone() {
                    return UserPhone;
                }

                public void setUserPhone(String UserPhone) {
                    this.UserPhone = UserPhone;
                }

                public String getRide_status() {
                    return ride_status;
                }

                public void setRide_status(String ride_status) {
                    this.ride_status = ride_status;
                }
            }
        }
    }
}
