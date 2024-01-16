package com.goviens.android.models;

import java.util.List;

public class ModelReceiptDriver {

    /**
     * version : 9.60
     * result : 1
     * message : Success
     * data : {"Driver_Receipt":{"header":{"left_text":"","center_text":"Driver Receipt 305","right_text":""},"body":{"ride_details":[{"left_text":"Trip ID #305","center_text":"","right_text":"rahul rahul"},{"left_text":"Trip Start","center_text":"","right_text":"27/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"27/02/2021 04:30:23"},{"left_text":"Pickup Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"Drop Location","center_text":"","right_text":"Delhi, India"},{"left_text":"No of Stops","center_text":"","right_text":1},{"left_text":"No of Bookings","center_text":"","right_text":2}],"bill_details":[{"left_text":"Wallet Amount","center_text":"","right_text":"16748.00"},{"left_text":"Cash Amount","center_text":"","right_text":0},{"left_text":"Gross Pay","center_text":"","right_text":"1984"},{"left_text":"Booking Fees","center_text":"","right_text":"4"}]},"footer":{"left_text":"Total Earning :","center_text":"","right_text":"INR 1976"}}}
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
         * Driver_Receipt : {"header":{"left_text":"","center_text":"Driver Receipt 305","right_text":""},"body":{"ride_details":[{"left_text":"Trip ID #305","center_text":"","right_text":"rahul rahul"},{"left_text":"Trip Start","center_text":"","right_text":"27/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"27/02/2021 04:30:23"},{"left_text":"Pickup Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"Drop Location","center_text":"","right_text":"Delhi, India"},{"left_text":"No of Stops","center_text":"","right_text":1},{"left_text":"No of Bookings","center_text":"","right_text":2}],"bill_details":[{"left_text":"Wallet Amount","center_text":"","right_text":"16748.00"},{"left_text":"Cash Amount","center_text":"","right_text":0},{"left_text":"Gross Pay","center_text":"","right_text":"1984"},{"left_text":"Booking Fees","center_text":"","right_text":"4"}]},"footer":{"left_text":"Total Earning :","center_text":"","right_text":"INR 1976"}}
         */

        private DriverReceiptBean Driver_Receipt;

        public DriverReceiptBean getDriver_Receipt() {
            return Driver_Receipt;
        }

        public void setDriver_Receipt(DriverReceiptBean Driver_Receipt) {
            this.Driver_Receipt = Driver_Receipt;
        }

        public static class DriverReceiptBean {
            /**
             * header : {"left_text":"","center_text":"Driver Receipt 305","right_text":""}
             * body : {"ride_details":[{"left_text":"Trip ID #305","center_text":"","right_text":"rahul rahul"},{"left_text":"Trip Start","center_text":"","right_text":"27/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"27/02/2021 04:30:23"},{"left_text":"Pickup Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"Drop Location","center_text":"","right_text":"Delhi, India"},{"left_text":"No of Stops","center_text":"","right_text":1},{"left_text":"No of Bookings","center_text":"","right_text":2}],"bill_details":[{"left_text":"Wallet Amount","center_text":"","right_text":"16748.00"},{"left_text":"Cash Amount","center_text":"","right_text":0},{"left_text":"Gross Pay","center_text":"","right_text":"1984"},{"left_text":"Booking Fees","center_text":"","right_text":"4"}]}
             * footer : {"left_text":"Total Earning :","center_text":"","right_text":"INR 1976"}
             */

            private HeaderBean header;
            private BodyBean body;
            private FooterBean footer;

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public BodyBean getBody() {
                return body;
            }

            public void setBody(BodyBean body) {
                this.body = body;
            }

            public FooterBean getFooter() {
                return footer;
            }

            public void setFooter(FooterBean footer) {
                this.footer = footer;
            }

            public static class HeaderBean {
                /**
                 * left_text :
                 * center_text : Driver Receipt 305
                 * right_text :
                 */

                private String left_text;
                private String center_text;
                private String right_text;

                public String getLeft_text() {
                    return left_text;
                }

                public void setLeft_text(String left_text) {
                    this.left_text = left_text;
                }

                public String getCenter_text() {
                    return center_text;
                }

                public void setCenter_text(String center_text) {
                    this.center_text = center_text;
                }

                public String getRight_text() {
                    return right_text;
                }

                public void setRight_text(String right_text) {
                    this.right_text = right_text;
                }
            }

            public static class BodyBean {
                private List<RideDetailsBean> ride_details;
                private List<BillDetailsBean> bill_details;

                public List<RideDetailsBean> getRide_details() {
                    return ride_details;
                }

                public void setRide_details(List<RideDetailsBean> ride_details) {
                    this.ride_details = ride_details;
                }

                public List<BillDetailsBean> getBill_details() {
                    return bill_details;
                }

                public void setBill_details(List<BillDetailsBean> bill_details) {
                    this.bill_details = bill_details;
                }

                public static class RideDetailsBean {
                    /**
                     * left_text : Trip ID #305
                     * center_text :
                     * right_text : rahul rahul
                     */

                    private String left_text;
                    private String center_text;
                    private String right_text;

                    public String getLeft_text() {
                        return left_text;
                    }

                    public void setLeft_text(String left_text) {
                        this.left_text = left_text;
                    }

                    public String getCenter_text() {
                        return center_text;
                    }

                    public void setCenter_text(String center_text) {
                        this.center_text = center_text;
                    }

                    public String getRight_text() {
                        return right_text;
                    }

                    public void setRight_text(String right_text) {
                        this.right_text = right_text;
                    }
                }

                public static class BillDetailsBean {
                    /**
                     * left_text : Wallet Amount
                     * center_text :
                     * right_text : 16748.00
                     */

                    private String left_text;
                    private String center_text;
                    private String right_text;

                    public String getLeft_text() {
                        return left_text;
                    }

                    public void setLeft_text(String left_text) {
                        this.left_text = left_text;
                    }

                    public String getCenter_text() {
                        return center_text;
                    }

                    public void setCenter_text(String center_text) {
                        this.center_text = center_text;
                    }

                    public String getRight_text() {
                        return right_text;
                    }

                    public void setRight_text(String right_text) {
                        this.right_text = right_text;
                    }
                }
            }

            public static class FooterBean {
                /**
                 * left_text : Total Earning :
                 * center_text :
                 * right_text : INR 1976
                 */

                private String left_text;
                private String center_text;
                private String right_text;

                public String getLeft_text() {
                    return left_text;
                }

                public void setLeft_text(String left_text) {
                    this.left_text = left_text;
                }

                public String getCenter_text() {
                    return center_text;
                }

                public void setCenter_text(String center_text) {
                    this.center_text = center_text;
                }

                public String getRight_text() {
                    return right_text;
                }

                public void setRight_text(String right_text) {
                    this.right_text = right_text;
                }
            }
        }
    }
}
