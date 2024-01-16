package com.goviens.android.models;

import java.util.List;

public class ModelSearch {

    /**
     * version : 4.30
     * result : 1
     * message : Data fetched successfully
     * data : [{"pickup_id":67,"available_seats":6,"carpooling_ride_id":29,"user_id":251,"female_ride":0,"ac_ride":0,"full_name":"hduj hdhjd","rating":"0.0","pickup_distance":"3440.10 km","drop_distance":"0.00 km","drop_id":67,"total_estimate_distance":"33.00 km","total_charges":"CAD 136","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1608020568_5fd87258e8dde_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201215%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201215T123737Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=4e67bf9a24ef803a276d749cfa76190d7f0d4e711cdd1caab8b5d1edf19ae39b","route":[{"drop_no":1,"from_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","to_location":"1956, Rajpath Area, Malka Ganj, New Delhi, Delhi 110011, India","estimate_distance_text":"33 km","ride_timestamp":"1608057000"}]}]
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
         * pickup_id : 67
         * available_seats : 6
         * carpooling_ride_id : 29
         * user_id : 251
         * female_ride : 0
         * ac_ride : 0
         * full_name : hduj hdhjd
         * rating : 0.0
         * pickup_distance : 3440.10 km
         * drop_distance : 0.00 km
         * drop_id : 67
         * total_estimate_distance : 33.00 km
         * total_charges : CAD 136
         * profile_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1608020568_5fd87258e8dde_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201215%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201215T123737Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=4e67bf9a24ef803a276d749cfa76190d7f0d4e711cdd1caab8b5d1edf19ae39b
         * route : [{"drop_no":1,"from_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","to_location":"1956, Rajpath Area, Malka Ganj, New Delhi, Delhi 110011, India","estimate_distance_text":"33 km","ride_timestamp":"1608057000"}]
         */

        private int pickup_id;
        private int available_seats;
        private int carpooling_ride_id;
        private int user_id;
        private int female_ride;
        private int ac_ride;
        private String return_ride;
        private String full_name;
        private String rating;
        private String pickup_distance;
        private String drop_distance;
        private int drop_id;
        private String total_estimate_distance;
        private String total_charges;
        private String profile_image;
        private List<RouteBean> route;

        public String getReturn_ride() {
            return return_ride;
        }

        public void setReturn_ride(String return_ride) {
            this.return_ride = return_ride;
        }

        public int getPickup_id() {
            return pickup_id;
        }

        public void setPickup_id(int pickup_id) {
            this.pickup_id = pickup_id;
        }

        public int getAvailable_seats() {
            return available_seats;
        }

        public void setAvailable_seats(int available_seats) {
            this.available_seats = available_seats;
        }

        public int getCarpooling_ride_id() {
            return carpooling_ride_id;
        }

        public void setCarpooling_ride_id(int carpooling_ride_id) {
            this.carpooling_ride_id = carpooling_ride_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getFemale_ride() {
            return female_ride;
        }

        public void setFemale_ride(int female_ride) {
            this.female_ride = female_ride;
        }

        public int getAc_ride() {
            return ac_ride;
        }

        public void setAc_ride(int ac_ride) {
            this.ac_ride = ac_ride;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getPickup_distance() {
            return pickup_distance;
        }

        public void setPickup_distance(String pickup_distance) {
            this.pickup_distance = pickup_distance;
        }

        public String getDrop_distance() {
            return drop_distance;
        }

        public void setDrop_distance(String drop_distance) {
            this.drop_distance = drop_distance;
        }

        public int getDrop_id() {
            return drop_id;
        }

        public void setDrop_id(int drop_id) {
            this.drop_id = drop_id;
        }

        public String getTotal_estimate_distance() {
            return total_estimate_distance;
        }

        public void setTotal_estimate_distance(String total_estimate_distance) {
            this.total_estimate_distance = total_estimate_distance;
        }

        public String getTotal_charges() {
            return total_charges;
        }

        public void setTotal_charges(String total_charges) {
            this.total_charges = total_charges;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public List<RouteBean> getRoute() {
            return route;
        }

        public void setRoute(List<RouteBean> route) {
            this.route = route;
        }

        public static class RouteBean {
            /**
             * drop_no : 1
             * from_location : S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India
             * to_location : 1956, Rajpath Area, Malka Ganj, New Delhi, Delhi 110011, India
             * estimate_distance_text : 33 km
             * ride_timestamp : 1608057000
             */

            private int drop_no;
            private String from_location;
            private String to_location;
            private String estimate_distance_text;
            private String ride_timestamp;
            private String end_timestamp;

            public String getEnd_timestamp() {
                return end_timestamp;
            }

            public void setEnd_timestamp(String end_timestamp) {
                this.end_timestamp = end_timestamp;
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

            public String getTo_location() {
                return to_location;
            }

            public void setTo_location(String to_location) {
                this.to_location = to_location;
            }

            public String getEstimate_distance_text() {
                return estimate_distance_text;
            }

            public void setEstimate_distance_text(String estimate_distance_text) {
                this.estimate_distance_text = estimate_distance_text;
            }

            public String getRide_timestamp() {
                return ride_timestamp;
            }

            public void setRide_timestamp(String ride_timestamp) {
                this.ride_timestamp = ride_timestamp;
            }
        }
    }
}
