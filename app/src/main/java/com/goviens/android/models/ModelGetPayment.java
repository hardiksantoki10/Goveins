package com.goviens.android.models;

import java.util.List;

public class ModelGetPayment {

    /**
     * version : 12.90
     * result : 1
     * message : Success
     * data : {"gateway":["PAYPAL","INTOUCHGROUP"]}
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
        private String commission_string;
        private String commission_percentage;
        private String minimum_amount;
        private String maximum_amount;
        private List<String> gateway;

        public String getMinimum_amount() {
            return minimum_amount;
        }

        public void setMinimum_amount(String minimum_amount) {
            this.minimum_amount = minimum_amount;
        }

        public String getMaximum_amount() {
            return maximum_amount;
        }

        public void setMaximum_amount(String maximum_amount) {
            this.maximum_amount = maximum_amount;
        }

        public String getCommission_percentage() {
            return commission_percentage;
        }

        public void setCommission_percentage(String commission_percentage) {
            this.commission_percentage = commission_percentage;
        }

        public String getCommission_string() {
            return commission_string;
        }

        public void setCommission_string(String commission_string) {
            this.commission_string = commission_string;
        }

        public List<String> getGateway() {
            return gateway;
        }

        public void setGateway(List<String> gateway) {
            this.gateway = gateway;
        }
    }
}
