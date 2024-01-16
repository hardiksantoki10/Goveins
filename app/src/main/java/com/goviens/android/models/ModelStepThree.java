package com.goviens.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelStepThree {

    /**
     * version : 3.10
     * result : 1
     * message : CheckOut Confirmation
     * data : {"offer_ride_checkout_id":18,"is_return":"1","departure":{"ride_date":"1607128200","available_seats":"5","available_seat_text":"5 Seats Available","total_amount":"INR 48","ac_ride":true,"routes":[{"id":209,"drop_no":0,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607128200","estimate_distance":null,"final_charges":null},{"id":209,"drop_no":1,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":6,"final_charges":"INR 28"},{"id":210,"drop_no":2,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":2,"final_charges":"INR 12"},{"id":211,"drop_no":3,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":1,"final_charges":"INR 8"}]},"return":{"ride_date":"1607207400","available_seats":"5","available_seat_text":"5 Seats Available","total_amount":"INR 48","ac_ride":true,"routes":[{"id":215,"drop_no":0,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":null,"final_charges":null},{"id":215,"drop_no":4,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":1,"final_charges":"INR 8"},{"id":216,"drop_no":5,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":2,"final_charges":"INR 12"},{"id":217,"drop_no":6,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607207400","estimate_distance":6,"final_charges":"INR 28"}]}}
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
         * offer_ride_checkout_id : 18
         * is_return : 1
         * departure : {"ride_date":"1607128200","available_seats":"5","available_seat_text":"5 Seats Available","total_amount":"INR 48","ac_ride":true,"routes":[{"id":209,"drop_no":0,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607128200","estimate_distance":null,"final_charges":null},{"id":209,"drop_no":1,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":6,"final_charges":"INR 28"},{"id":210,"drop_no":2,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":2,"final_charges":"INR 12"},{"id":211,"drop_no":3,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":1,"final_charges":"INR 8"}]}
         * return : {"ride_date":"1607207400","available_seats":"5","available_seat_text":"5 Seats Available","total_amount":"INR 48","ac_ride":true,"routes":[{"id":215,"drop_no":0,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":null,"final_charges":null},{"id":215,"drop_no":4,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":1,"final_charges":"INR 8"},{"id":216,"drop_no":5,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":2,"final_charges":"INR 12"},{"id":217,"drop_no":6,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607207400","estimate_distance":6,"final_charges":"INR 28"}]}
         */

        private int offer_ride_checkout_id;
        private String is_return;
        private DepartureBean departure;
        @SerializedName("return")
        private ReturnBean returnX;

        public int getOffer_ride_checkout_id() {
            return offer_ride_checkout_id;
        }

        public void setOffer_ride_checkout_id(int offer_ride_checkout_id) {
            this.offer_ride_checkout_id = offer_ride_checkout_id;
        }

        public String getIs_return() {
            return is_return;
        }

        public void setIs_return(String is_return) {
            this.is_return = is_return;
        }

        public DepartureBean getDeparture() {
            return departure;
        }

        public void setDeparture(DepartureBean departure) {
            this.departure = departure;
        }

        public ReturnBean getReturnX() {
            return returnX;
        }

        public void setReturnX(ReturnBean returnX) {
            this.returnX = returnX;
        }

        public static class DepartureBean {
            /**
             * ride_date : 1607128200
             * available_seats : 5
             * available_seat_text : 5 Seats Available
             * total_amount : INR 48
             * ac_ride : true
             * routes : [{"id":209,"drop_no":0,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607128200","estimate_distance":null,"final_charges":null},{"id":209,"drop_no":1,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":6,"final_charges":"INR 28"},{"id":210,"drop_no":2,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":2,"final_charges":"INR 12"},{"id":211,"drop_no":3,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607128200","estimate_distance":1,"final_charges":"INR 8"}]
             */

            private String ride_date;
            private String available_seats;
            private String available_seat_text;
            private String total_amount;
            private boolean ac_ride;
            private List<RoutesBean> routes;

            public String getRide_date() {
                return ride_date;
            }

            public void setRide_date(String ride_date) {
                this.ride_date = ride_date;
            }

            public String getAvailable_seats() {
                return available_seats;
            }

            public void setAvailable_seats(String available_seats) {
                this.available_seats = available_seats;
            }

            public String getAvailable_seat_text() {
                return available_seat_text;
            }

            public void setAvailable_seat_text(String available_seat_text) {
                this.available_seat_text = available_seat_text;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public boolean isAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(boolean ac_ride) {
                this.ac_ride = ac_ride;
            }

            public List<RoutesBean> getRoutes() {
                return routes;
            }

            public void setRoutes(List<RoutesBean> routes) {
                this.routes = routes;
            }

            public static class RoutesBean {
                /**
                 * id : 209
                 * drop_no : 0
                 * location : 482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India
                 * ride_timestamp : 1607128200
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
        }

        public static class ReturnBean {
            /**
             * ride_date : 1607207400
             * available_seats : 5
             * available_seat_text : 5 Seats Available
             * total_amount : INR 48
             * ac_ride : true
             * routes : [{"id":215,"drop_no":0,"location":"62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":null,"final_charges":null},{"id":215,"drop_no":4,"location":"Shop No. B-21, 1st Floor, Omaxe City Centre, Sohna Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":1,"final_charges":"INR 8"},{"id":216,"drop_no":5,"location":"Tower 8, Orchid Petals, South City II, Sector 49, Gurugram, Haryana 122018, India","ride_timestamp":"1607207400","estimate_distance":2,"final_charges":"INR 12"},{"id":217,"drop_no":6,"location":"482, Phase III, Jacobpura, Sector 19, Gurugram, Haryana 122016, India","ride_timestamp":"1607207400","estimate_distance":6,"final_charges":"INR 28"}]
             */

            private String ride_date;
            private String available_seats;
            private String available_seat_text;
            private String total_amount;
            private boolean ac_ride;
            private List<RoutesBeanX> routes;

            public String getRide_date() {
                return ride_date;
            }

            public void setRide_date(String ride_date) {
                this.ride_date = ride_date;
            }

            public String getAvailable_seats() {
                return available_seats;
            }

            public void setAvailable_seats(String available_seats) {
                this.available_seats = available_seats;
            }

            public String getAvailable_seat_text() {
                return available_seat_text;
            }

            public void setAvailable_seat_text(String available_seat_text) {
                this.available_seat_text = available_seat_text;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public boolean isAc_ride() {
                return ac_ride;
            }

            public void setAc_ride(boolean ac_ride) {
                this.ac_ride = ac_ride;
            }

            public List<RoutesBeanX> getRoutes() {
                return routes;
            }

            public void setRoutes(List<RoutesBeanX> routes) {
                this.routes = routes;
            }

            public static class RoutesBeanX {
                /**
                 * id : 215
                 * drop_no : 0
                 * location : 62, C Block Rd, South City II, Sector 49, Gurugram, Haryana 122018, India
                 * ride_timestamp : 1607207400
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
        }
    }
}
