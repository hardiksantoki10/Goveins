package com.goviens.android.models;

import java.util.List;

public class ModelTakenRideList {

    /**
     * version : 6.10
     * result : 1
     * message : Success
     * data : {"upcoming_ride":[{"id":21,"start_location":"77.0432061329484","end_location":"spaze itech Park Gate opposite wine n beer shop","ac_ride":"No","ride_date":"1608669000","offer_user_name":"Rohit Sharma","offer_user_rating":null,"otp":4257,"booked_seat":"0","female_ride":"No","final_charges":"XAF 4","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201223%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201223T060204Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=79a3fd515a546bf6d0c81e636d67165ea8976670fbbc18344fce1a8a1a33761e"},{"id":22,"start_location":"77.0432061329484","end_location":"spaze itech Park Gate opposite wine n beer shop","ac_ride":"No","ride_date":"1608669000","offer_user_name":"Rohit Sharma","offer_user_rating":null,"otp":5287,"booked_seat":"0","female_ride":"No","final_charges":"XAF 4","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201223%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201223T060204Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=79a3fd515a546bf6d0c81e636d67165ea8976670fbbc18344fce1a8a1a33761e"},{"id":24,"start_location":"77.21908930689096","end_location":"New Delhi","ac_ride":"No","ride_date":"1608669000","offer_user_name":"Rohit Sharma","offer_user_rating":null,"otp":4009,"booked_seat":"0","female_ride":"No","final_charges":"XAF 24","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201223%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201223T060204Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=79a3fd515a546bf6d0c81e636d67165ea8976670fbbc18344fce1a8a1a33761e"}],"ongoing_ride":[]}
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
         * id : 21
         * start_location : 77.0432061329484
         * end_location : spaze itech Park Gate opposite wine n beer shop
         * ac_ride : No
         * ride_date : 1608669000
         * offer_user_name : Rohit Sharma
         * offer_user_rating : null
         * otp : 4257
         * booked_seat : 0
         * female_ride : No
         * final_charges : XAF 4
         * profile_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201223%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201223T060204Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=79a3fd515a546bf6d0c81e636d67165ea8976670fbbc18344fce1a8a1a33761e
         */

        private List<UpcomingRideBean> upcoming_ride;
        private List<OngoingRideBean> ongoing_ride;

        public List<UpcomingRideBean> getUpcoming_ride() {
            return upcoming_ride;
        }

        public void setUpcoming_ride(List<UpcomingRideBean> upcoming_ride) {
            this.upcoming_ride = upcoming_ride;
        }

        public List<OngoingRideBean> getOngoing_ride() {
            return ongoing_ride;
        }

        public void setOngoing_ride(List<OngoingRideBean> ongoing_ride) {
            this.ongoing_ride = ongoing_ride;
        }

        public static class UpcomingRideBean {
            private int id;
            private String start_location;
            private String end_location;
            private String ac_ride;
            private String ride_date;
            private String end_ride_date;
            private String offer_user_name;
            private Object offer_user_rating;
            private int otp;
            private String booked_seat;
            private String female_ride;
            private String final_charges;
            private boolean is_ride_confirm;
            private String ride_status;
            private String profile_image;
            private String unique_id;

            public String getUnique_id() {
                return unique_id;
            }

            public void setUnique_id(String unique_id) {
                this.unique_id = unique_id;
            }


            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }

            public boolean isIs_ride_confirm() {
                return is_ride_confirm;
            }

            public void setIs_ride_confirm(boolean is_ride_confirm) {
                this.is_ride_confirm = is_ride_confirm;
            }

            public String getEnd_ride_date() {
                return end_ride_date;
            }

            public void setEnd_ride_date(String end_ride_date) {
                this.end_ride_date = end_ride_date;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(String ac_ride) {
                this.ac_ride = ac_ride;
            }

            public String getRide_date() {
                return ride_date;
            }

            public void setRide_date(String ride_date) {
                this.ride_date = ride_date;
            }

            public String getOffer_user_name() {
                return offer_user_name;
            }

            public void setOffer_user_name(String offer_user_name) {
                this.offer_user_name = offer_user_name;
            }

            public Object getOffer_user_rating() {
                return offer_user_rating;
            }

            public void setOffer_user_rating(Object offer_user_rating) {
                this.offer_user_rating = offer_user_rating;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
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

            public String getFinal_charges() {
                return final_charges;
            }

            public void setFinal_charges(String final_charges) {
                this.final_charges = final_charges;
            }

            public String getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(String profile_image) {
                this.profile_image = profile_image;
            }
        }

        public static class OngoingRideBean {
            private int id;
            private String start_location;
            private String end_location;
            private String ac_ride;
            private String ride_date;
            private String end_ride_date;
            private String offer_user_name;
            private Object offer_user_rating;
            private int otp;
            private String booked_seat;
            private String female_ride;
            private String final_charges;
            private boolean is_ride_confirm;
            private String ride_status;
            private String profile_image;
            private String unique_id;

            public String getUnique_id() {
                return unique_id;
            }

            public void setUnique_id(String unique_id) {
                this.unique_id = unique_id;
            }

            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }

            public boolean isIs_ride_confirm() {
                return is_ride_confirm;
            }

            public void setIs_ride_confirm(boolean is_ride_confirm) {
                this.is_ride_confirm = is_ride_confirm;
            }

            public String getEnd_ride_date() {
                return end_ride_date;
            }

            public void setEnd_ride_date(String end_ride_date) {
                this.end_ride_date = end_ride_date;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(String ac_ride) {
                this.ac_ride = ac_ride;
            }

            public String getRide_date() {
                return ride_date;
            }

            public void setRide_date(String ride_date) {
                this.ride_date = ride_date;
            }

            public String getOffer_user_name() {
                return offer_user_name;
            }

            public void setOffer_user_name(String offer_user_name) {
                this.offer_user_name = offer_user_name;
            }

            public Object getOffer_user_rating() {
                return offer_user_rating;
            }

            public void setOffer_user_rating(Object offer_user_rating) {
                this.offer_user_rating = offer_user_rating;
            }

            public int getOtp() {
                return otp;
            }

            public void setOtp(int otp) {
                this.otp = otp;
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

            public String getFinal_charges() {
                return final_charges;
            }

            public void setFinal_charges(String final_charges) {
                this.final_charges = final_charges;
            }

            public String getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(String profile_image) {
                this.profile_image = profile_image;
            }
        }
    }
}
