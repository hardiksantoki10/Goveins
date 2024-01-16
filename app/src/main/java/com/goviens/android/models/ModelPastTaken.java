package com.goviens.android.models;

import java.util.List;

public class ModelPastTaken {

    /**
     * version : 7.30
     * result : 1
     * message : Success
     * data : {"past_take_ride":[{"id":181,"start_location":"Spaze itech Park Gate opposite wine n beer shop Sector 49, Gurugram, Haryana 122018, India","end_location":"Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","ac_ride":"0","ride_date":"1611513000","end_ride_date":"1611514251","offer_user_name":"klmn klmn","offer_user_rating":0,"female_ride":"0","final_charges":"XAF 36","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1610965985_600563e1ba89b_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210120%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210120T070932Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=195e37fd669c19bb6ef50dfe9f97cf28e4061e39a42853626c4bee3ba3e4f467","ride_status":"Completed"}]}
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
        private List<PastTakeRideBean> past_take_ride;

        public List<PastTakeRideBean> getPast_take_ride() {
            return past_take_ride;
        }

        public void setPast_take_ride(List<PastTakeRideBean> past_take_ride) {
            this.past_take_ride = past_take_ride;
        }

        public static class PastTakeRideBean {
            /**
             * id : 181
             * start_location : Spaze itech Park Gate opposite wine n beer shop Sector 49, Gurugram, Haryana 122018, India
             * end_location : Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India
             * ac_ride : 0
             * ride_date : 1611513000
             * end_ride_date : 1611514251
             * offer_user_name : klmn klmn
             * offer_user_rating : 0
             * female_ride : 0
             * final_charges : XAF 36
             * profile_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1610965985_600563e1ba89b_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210120%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210120T070932Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=195e37fd669c19bb6ef50dfe9f97cf28e4061e39a42853626c4bee3ba3e4f467
             * ride_status : Completed
             */

            private int id;
            private String start_location;
            private String end_location;
            private String ac_ride;
            private String ride_date;
            private String end_ride_date;
            private String offer_user_name;
            private String offer_user_rating;
            private String female_ride;
            private String final_charges;
            private String profile_image;
            private String unique_id;
            private String ride_status;

            public String getUnique_id() {
                return unique_id;
            }

            public void setUnique_id(String unique_id) {
                this.unique_id = unique_id;
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

            public String getEnd_ride_date() {
                return end_ride_date;
            }

            public void setEnd_ride_date(String end_ride_date) {
                this.end_ride_date = end_ride_date;
            }

            public String getOffer_user_name() {
                return offer_user_name;
            }

            public void setOffer_user_name(String offer_user_name) {
                this.offer_user_name = offer_user_name;
            }

            public String getOffer_user_rating() {
                return offer_user_rating;
            }

            public void setOffer_user_rating(String offer_user_rating) {
                this.offer_user_rating = offer_user_rating;
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

            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }
        }
    }
}
