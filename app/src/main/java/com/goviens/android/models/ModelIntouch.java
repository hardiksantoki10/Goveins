package com.goviens.android.models;

public class ModelIntouch {

    /**
     * result : 1
     * message : Payment Status
     * data : {"service_id":"CASHOUTOMCM","gu_transaction_id":"1617364152500","status":"PENDING","recipient_phone_number":"699966996","amount":100,"partner_transaction_id":"1617364152","message":"Operation being processed."}
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
         * service_id : CASHOUTOMCM
         * gu_transaction_id : 1617364152500
         * status : PENDING
         * recipient_phone_number : 699966996
         * amount : 100
         * partner_transaction_id : 1617364152
         * message : Operation being processed.
         */

        private String service_id;
        private String gu_transaction_id;
        private String status;
        private String recipient_phone_number;
        private int amount;
        private String partner_transaction_id;
        private String message;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getGu_transaction_id() {
            return gu_transaction_id;
        }

        public void setGu_transaction_id(String gu_transaction_id) {
            this.gu_transaction_id = gu_transaction_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRecipient_phone_number() {
            return recipient_phone_number;
        }

        public void setRecipient_phone_number(String recipient_phone_number) {
            this.recipient_phone_number = recipient_phone_number;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getPartner_transaction_id() {
            return partner_transaction_id;
        }

        public void setPartner_transaction_id(String partner_transaction_id) {
            this.partner_transaction_id = partner_transaction_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
