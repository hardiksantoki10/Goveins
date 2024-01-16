package com.goviens.android.models;

public class ModelReferral {

    /**
     * result : 1
     * message : Old Password is Wrong
     * data : {"refer_image":"refer.png","refer_heading":"Refer Code","refer_explanation":"Refer this following code to your friends and family","start_date":"","end_date":"","refer_code":"REMFU","refer_status":"DEACTIVE","refer_offer":"","sharing_text":"Haven't tried Goviens User Application yet? Sign up with my code(REMFU) and enjoy the most affordable can rides!\r\nYou can download the application from Google store @fromserver and Apple store link @fromserver"}
     */

    private String result;
    private String message;
    private DataBean data;

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
         * refer_image : refer.png
         * refer_heading : Refer Code
         * refer_explanation : Refer this following code to your friends and family
         * start_date :
         * end_date :
         * refer_code : REMFU
         * refer_status : DEACTIVE
         * refer_offer :
         * sharing_text : Haven't tried Goviens User Application yet? Sign up with my code(REMFU) and enjoy the most affordable can rides!
         You can download the application from Google store @fromserver and Apple store link @fromserver
         */

        private String refer_image;
        private String refer_heading;
        private String refer_explanation;
        private String start_date;
        private String end_date;
        private String refer_code;
        private String refer_status;
        private String refer_offer;
        private String sharing_text;

        public String getRefer_image() {
            return refer_image;
        }

        public void setRefer_image(String refer_image) {
            this.refer_image = refer_image;
        }

        public String getRefer_heading() {
            return refer_heading;
        }

        public void setRefer_heading(String refer_heading) {
            this.refer_heading = refer_heading;
        }

        public String getRefer_explanation() {
            return refer_explanation;
        }

        public void setRefer_explanation(String refer_explanation) {
            this.refer_explanation = refer_explanation;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getRefer_code() {
            return refer_code;
        }

        public void setRefer_code(String refer_code) {
            this.refer_code = refer_code;
        }

        public String getRefer_status() {
            return refer_status;
        }

        public void setRefer_status(String refer_status) {
            this.refer_status = refer_status;
        }

        public String getRefer_offer() {
            return refer_offer;
        }

        public void setRefer_offer(String refer_offer) {
            this.refer_offer = refer_offer;
        }

        public String getSharing_text() {
            return sharing_text;
        }

        public void setSharing_text(String sharing_text) {
            this.sharing_text = sharing_text;
        }
    }
}
