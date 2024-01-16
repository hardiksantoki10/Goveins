package com.goviens.android.models;


import java.util.List;

public class ModelReceiptUser {

    /**
     * version : 9.60
     * result : 1
     * message : Success
     * data : {"User_Receipt":{"header":{"left_text":"","center_text":"User Receipt #284","right_text":""},"body":{"ride_details":[{"left_text":"Trip Start","center_text":"","right_text":"28/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"28/02/2021 11:30:51"},{"left_text":"Start Location","center_text":"","right_text":"Lucknow, Uttar Pradesh, India"},{"left_text":"End Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"No of Seats","center_text":"","right_text":"2"},{"left_text":"Per seat charges","center_text":"","right_text":456}],"bill_details":[{"left_text":"Ride Amount","center_text":"","right_text":912},{"left_text":"Discount Amount","center_text":"","right_text":0},{"left_text":"Net Ride Amount","center_text":"","right_text":912},{"left_text":"Booking Fees","center_text":"","right_text":0},{"left_text":"State Charges","center_text":"","right_text":0},{"left_text":"Total_Amount","center_text":"","right_text":920}]},"footer":{"left_text":"Payment Method :","center_text":"","right_text":3}}}
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
         * User_Receipt : {"header":{"left_text":"","center_text":"User Receipt #284","right_text":""},"body":{"ride_details":[{"left_text":"Trip Start","center_text":"","right_text":"28/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"28/02/2021 11:30:51"},{"left_text":"Start Location","center_text":"","right_text":"Lucknow, Uttar Pradesh, India"},{"left_text":"End Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"No of Seats","center_text":"","right_text":"2"},{"left_text":"Per seat charges","center_text":"","right_text":456}],"bill_details":[{"left_text":"Ride Amount","center_text":"","right_text":912},{"left_text":"Discount Amount","center_text":"","right_text":0},{"left_text":"Net Ride Amount","center_text":"","right_text":912},{"left_text":"Booking Fees","center_text":"","right_text":0},{"left_text":"State Charges","center_text":"","right_text":0},{"left_text":"Total_Amount","center_text":"","right_text":920}]},"footer":{"left_text":"Payment Method :","center_text":"","right_text":3}}
         */

        private UserReceiptBean User_Receipt;

        public UserReceiptBean getUser_Receipt() {
            return User_Receipt;
        }

        public void setUser_Receipt(UserReceiptBean User_Receipt) {
            this.User_Receipt = User_Receipt;
        }

        public static class UserReceiptBean {
            /**
             * header : {"left_text":"","center_text":"User Receipt #284","right_text":""}
             * body : {"ride_details":[{"left_text":"Trip Start","center_text":"","right_text":"28/02/2021 00:00:00"},{"left_text":"Trip End","center_text":"","right_text":"28/02/2021 11:30:51"},{"left_text":"Start Location","center_text":"","right_text":"Lucknow, Uttar Pradesh, India"},{"left_text":"End Location","center_text":"","right_text":"Chandigarh, India"},{"left_text":"No of Seats","center_text":"","right_text":"2"},{"left_text":"Per seat charges","center_text":"","right_text":456}],"bill_details":[{"left_text":"Ride Amount","center_text":"","right_text":912},{"left_text":"Discount Amount","center_text":"","right_text":0},{"left_text":"Net Ride Amount","center_text":"","right_text":912},{"left_text":"Booking Fees","center_text":"","right_text":0},{"left_text":"State Charges","center_text":"","right_text":0},{"left_text":"Total_Amount","center_text":"","right_text":920}]}
             * footer : {"left_text":"Payment Method :","center_text":"","right_text":3}
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
                 * center_text : User Receipt #284
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
                     * left_text : Trip Start
                     * center_text :
                     * right_text : 28/02/2021 00:00:00
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
                     * left_text : Ride Amount
                     * center_text :
                     * right_text : 912
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

                    public String  getRight_text() {
                        return right_text;
                    }

                    public void setRight_text(String right_text) {
                        this.right_text = right_text;
                    }
                }
            }

            public static class FooterBean {
                /**
                 * left_text : Payment Method :
                 * center_text :
                 * right_text : 3
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
