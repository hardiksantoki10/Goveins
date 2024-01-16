package com.goviens.android.models;

public class ModelLogin {

    /**
     * version : 1.80
     * result : 1
     * message : api.otpsend
     * data : {"auto_fill":true,"otp":"2018","is_register":false}
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
         * auto_fill : true
         * otp : 2018
         * is_register : false
         */

        private boolean auto_fill;
        private String otp;
        private boolean is_register;
        private String automatic_cashout;

        public String getAutomatic_cashout() {
            return automatic_cashout;
        }

        public void setAutomatic_cashout(String automatic_cashout) {
            this.automatic_cashout = automatic_cashout;
        }

        public boolean isAuto_fill() {
            return auto_fill;
        }

        public void setAuto_fill(boolean auto_fill) {
            this.auto_fill = auto_fill;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public boolean isIs_register() {
            return is_register;
        }

        public void setIs_register(boolean is_register) {
            this.is_register = is_register;
        }
    }
}
