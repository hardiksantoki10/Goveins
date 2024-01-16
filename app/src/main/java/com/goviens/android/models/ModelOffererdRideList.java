package com.goviens.android.models;

import java.util.List;

public class ModelOffererdRideList {

    /**
     * version : 4.20
     * result : 1
     * message : Success
     * data : {"upcoming_ride":{"data":[{"id":9,"user_vehicle_id":9,"start_location":"Tower A, Spaze iTech Park,Sector 49,Gurugram","end_location":"Shalimar Huda City Center, Delhi, Sector 29,Gurugram, Haryana 122007","ride_timestamp":"1599309000","return_ride":"1","return_ride_timestamp":"1599399000","ac_ride":0,"female_ride":0,"available_seats":5,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 144","user_requests":0,"payment_method_text":"Cash"},{"id":10,"user_vehicle_id":9,"start_location":"Sector 47, Near Subhash Chowk, Sector 47, Gurugram, Haryana 122001, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1608748200","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":9,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 16","user_requests":0,"payment_method_text":"Cash"},{"id":11,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1609353000","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":2,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 48","user_requests":0,"payment_method_text":"Cash"},{"id":12,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1609353000","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":3,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 48","user_requests":0,"payment_method_text":"Cash"},{"id":13,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"IFFCO Chowk","ride_timestamp":"1607373000","return_ride":"0","return_ride_timestamp":null,"ac_ride":1,"female_ride":0,"available_seats":2,"booked_seats":2,"no_of_stops":1,"ride_amount":"INR 28","user_requests":0,"payment_method_text":"Cash"}]},"ongoing_ride":{"data":[]}}
     */

    private String version;
    private String result;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * upcoming_ride : {"data":[{"id":9,"user_vehicle_id":9,"start_location":"Tower A, Spaze iTech Park,Sector 49,Gurugram","end_location":"Shalimar Huda City Center, Delhi, Sector 29,Gurugram, Haryana 122007","ride_timestamp":"1599309000","return_ride":"1","return_ride_timestamp":"1599399000","ac_ride":0,"female_ride":0,"available_seats":5,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 144","user_requests":0,"payment_method_text":"Cash"},{"id":10,"user_vehicle_id":9,"start_location":"Sector 47, Near Subhash Chowk, Sector 47, Gurugram, Haryana 122001, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1608748200","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":9,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 16","user_requests":0,"payment_method_text":"Cash"},{"id":11,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1609353000","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":2,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 48","user_requests":0,"payment_method_text":"Cash"},{"id":12,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"S 349, Uppal Southend, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1609353000","return_ride":"0","return_ride_timestamp":null,"ac_ride":0,"female_ride":0,"available_seats":3,"booked_seats":0,"no_of_stops":1,"ride_amount":"INR 48","user_requests":0,"payment_method_text":"Cash"},{"id":13,"user_vehicle_id":9,"start_location":"Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","end_location":"IFFCO Chowk","ride_timestamp":"1607373000","return_ride":"0","return_ride_timestamp":null,"ac_ride":1,"female_ride":0,"available_seats":2,"booked_seats":2,"no_of_stops":1,"ride_amount":"INR 28","user_requests":0,"payment_method_text":"Cash"}]}
         * ongoing_ride : {"data":[]}
         */

        private UpcomingRideBean upcoming_ride;
        private OngoingRideBean ongoing_ride;

        public UpcomingRideBean getUpcoming_ride() {
            return upcoming_ride;
        }

        public void setUpcoming_ride(UpcomingRideBean upcoming_ride) {
            this.upcoming_ride = upcoming_ride;
        }

        public OngoingRideBean getOngoing_ride() {
            return ongoing_ride;
        }

        public void setOngoing_ride(OngoingRideBean ongoing_ride) {
            this.ongoing_ride = ongoing_ride;
        }

        public static class UpcomingRideBean {
            private List<DataBean> data;

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * id : 9
                 * user_vehicle_id : 9
                 * start_location : Tower A, Spaze iTech Park,Sector 49,Gurugram
                 * end_location : Shalimar Huda City Center, Delhi, Sector 29,Gurugram, Haryana 122007
                 * ride_timestamp : 1599309000
                 * return_ride : 1
                 * return_ride_timestamp : 1599399000
                 * ac_ride : 0
                 * female_ride : 0
                 * available_seats : 5
                 * booked_seats : 0
                 * no_of_stops : 1
                 * ride_amount : INR 144
                 * user_requests : 0
                 * payment_method_text : Cash
                 */

                private int id;
                private int user_vehicle_id;
                private String start_location;
                private String end_location;
                private String ride_timestamp;
                private String return_ride;
                private String return_ride_timestamp;
                private int ac_ride;
                private int female_ride;
                private int available_seats;
                private int booked_seats;
                private int no_of_stops;
                private String ride_amount;
                private int user_requests;
                private String payment_method_text;

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

                public int getUser_requests() {
                    return user_requests;
                }

                public void setUser_requests(int user_requests) {
                    this.user_requests = user_requests;
                }

                public String getPayment_method_text() {
                    return payment_method_text;
                }

                public void setPayment_method_text(String payment_method_text) {
                    this.payment_method_text = payment_method_text;
                }
            }
        }

        public static class OngoingRideBean {
            private List<DataBean> data;

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * id : 9
                 * user_vehicle_id : 9
                 * start_location : Tower A, Spaze iTech Park,Sector 49,Gurugram
                 * end_location : Shalimar Huda City Center, Delhi, Sector 29,Gurugram, Haryana 122007
                 * ride_timestamp : 1599309000
                 * return_ride : 1
                 * return_ride_timestamp : 1599399000
                 * ac_ride : 0
                 * female_ride : 0
                 * available_seats : 5
                 * booked_seats : 0
                 * no_of_stops : 1
                 * ride_amount : INR 144
                 * user_requests : 0
                 * payment_method_text : Cash
                 */

                private int id;
                private int user_vehicle_id;
                private String start_location;
                private String end_location;
                private String ride_timestamp;
                private String return_ride;
                private String return_ride_timestamp;
                private int ac_ride;
                private int female_ride;
                private int available_seats;
                private int booked_seats;
                private int no_of_stops;
                private String ride_amount;
                private int user_requests;
                private String payment_method_text;

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

                public int getUser_requests() {
                    return user_requests;
                }

                public void setUser_requests(int user_requests) {
                    this.user_requests = user_requests;
                }

                public String getPayment_method_text() {
                    return payment_method_text;
                }

                public void setPayment_method_text(String payment_method_text) {
                    this.payment_method_text = payment_method_text;
                }
            }
        }
    }
}
