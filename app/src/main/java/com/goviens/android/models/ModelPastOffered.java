package com.goviens.android.models;

import java.util.List;

public class ModelPastOffered {

    /**
     * version : 7.30
     * result : 1
     * message : Success
     * data : {"past_ride_data":[{"id":194,"user_vehicle_id":53,"start_location":"Face Total Bonateki, Douala, Cameroun","end_location":"Boulangerie Basson Unnamed Rd,, Douala, Cameroun","ride_timestamp":"1611140400","ride_status":"4","return_ride":"1","return_ride_timestamp":"1611176400","ac_ride":0,"female_ride":0,"available_seats":2,"booked_seats":2,"no_of_stops":1,"ride_amount":"XAF 500"},{"id":195,"user_vehicle_id":53,"start_location":"Total Wouri 1 Rond-Point 4eme, Douala, Cameroun","end_location":"Carrefour Total Bafoussam, Cameroun","ride_timestamp":"1611118800","ride_status":"4","return_ride":"1","return_ride_timestamp":"1611205200","ac_ride":1,"female_ride":0,"available_seats":4,"booked_seats":0,"no_of_stops":3,"ride_amount":"XAF 0"}]}
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
        private List<PastRideDataBean> past_ride_data;

        public List<PastRideDataBean> getPast_ride_data() {
            return past_ride_data;
        }

        public void setPast_ride_data(List<PastRideDataBean> past_ride_data) {
            this.past_ride_data = past_ride_data;
        }

        public static class PastRideDataBean {
            /**
             * id : 194
             * user_vehicle_id : 53
             * start_location : Face Total Bonateki, Douala, Cameroun
             * end_location : Boulangerie Basson Unnamed Rd,, Douala, Cameroun
             * ride_timestamp : 1611140400
             * ride_status : 4
             * return_ride : 1
             * return_ride_timestamp : 1611176400
             * ac_ride : 0
             * female_ride : 0
             * available_seats : 2
             * booked_seats : 2
             * no_of_stops : 1
             * ride_amount : XAF 500
             */

            private int id;
            private int user_vehicle_id;
            private String start_location;
            private String end_location;
            private String ride_timestamp;
            private String ride_status;
            private String return_ride;
            private String return_ride_timestamp;
            private int ac_ride;
            private int female_ride;
            private int available_seats;
            private int booked_seats;
            private int no_of_stops;
            private String ride_amount;
            private String payment_method;
            private String unique_id;

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

            public int getUser_vehicle_id() {
                return user_vehicle_id;
            }

            public void setUser_vehicle_id(int user_vehicle_id) {
                this.user_vehicle_id = user_vehicle_id;
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

            public String getRide_timestamp() {
                return ride_timestamp;
            }

            public void setRide_timestamp(String ride_timestamp) {
                this.ride_timestamp = ride_timestamp;
            }

            public String getRide_status() {
                return ride_status;
            }

            public void setRide_status(String ride_status) {
                this.ride_status = ride_status;
            }

            public String getReturn_ride() {
                return return_ride;
            }

            public void setReturn_ride(String return_ride) {
                this.return_ride = return_ride;
            }

            public String getReturn_ride_timestamp() {
                return return_ride_timestamp;
            }

            public void setReturn_ride_timestamp(String return_ride_timestamp) {
                this.return_ride_timestamp = return_ride_timestamp;
            }

            public int getAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(int ac_ride) {
                this.ac_ride = ac_ride;
            }

            public int getFemale_ride() {
                return female_ride;
            }

            public void setFemale_ride(int female_ride) {
                this.female_ride = female_ride;
            }

            public int getAvailable_seats() {
                return available_seats;
            }

            public void setAvailable_seats(int available_seats) {
                this.available_seats = available_seats;
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

            public String getRide_amount() {
                return ride_amount;
            }

            public void setRide_amount(String ride_amount) {
                this.ride_amount = ride_amount;
            }

            public String getPayment_method() {
                return payment_method;
            }

            public void setPayment_method(String payment_method) {
                this.payment_method = payment_method;
            }
        }
    }
}
