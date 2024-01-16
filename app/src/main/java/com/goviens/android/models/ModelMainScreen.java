package com.goviens.android.models;

public class ModelMainScreen {

    /**
     * version : 7.10
     * result : 1
     * message : Carpooling Details
     * data : {"is_veh_upload":false,"is_veh_doc_upload":false,"is_user_offer_ride":false,"offer_ride_text":"Your Vehicle-documents are pending for verification","upload_document":"Please upload your driver\u2019s documents","upload_vehicle":"Please upload your Vehicle"}
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
         * is_veh_upload : false
         * is_veh_doc_upload : false
         * is_user_offer_ride : false
         * offer_ride_text : Your Vehicle-documents are pending for verification
         * upload_document : Please upload your driver’s documents
         * upload_vehicle : Please upload your Vehicle
         */

        private boolean is_veh_upload;
        private boolean is_veh_doc_upload;
        private boolean is_user_offer_ride;
        private boolean is_user_doc_upload;
        private String offer_ride_text;
        private String upload_document;
        private String upload_vehicle;
        private boolean is_user_personal_document_expired;
        private String is_user_personal_document_expired_text;
        private boolean is_user_vehicle_document_expired;
        private String is_user_vehicle_document_expired_text;

        private boolean is_user_vehicle_document_expire;
        private String is_user_vehicle_document_expire_text;
        private boolean is_user_personal_document_expire;
        private String is_user_personal_document_expire_text;

        private boolean is_user_minimum_balance;
        private String is_user_minimum_balance_text;

        public boolean isIs_user_vehicle_document_expire() {
            return is_user_vehicle_document_expire;
        }

        public void setIs_user_vehicle_document_expire(boolean is_user_vehicle_document_expire) {
            this.is_user_vehicle_document_expire = is_user_vehicle_document_expire;
        }

        public String getIs_user_vehicle_document_expire_text() {
            return is_user_vehicle_document_expire_text;
        }

        public void setIs_user_vehicle_document_expire_text(String is_user_vehicle_document_expire_text) {
            this.is_user_vehicle_document_expire_text = is_user_vehicle_document_expire_text;
        }

        public boolean isIs_user_personal_document_expire() {
            return is_user_personal_document_expire;
        }

        public void setIs_user_personal_document_expire(boolean is_user_personal_document_expire) {
            this.is_user_personal_document_expire = is_user_personal_document_expire;
        }

        public String getIs_user_personal_document_expire_text() {
            return is_user_personal_document_expire_text;
        }

        public void setIs_user_personal_document_expire_text(String is_user_personal_document_expire_text) {
            this.is_user_personal_document_expire_text = is_user_personal_document_expire_text;
        }

        public boolean isIs_user_personal_document_expired() {
            return is_user_personal_document_expired;
        }

        public void setIs_user_personal_document_expired(boolean is_user_personal_document_expired) {
            this.is_user_personal_document_expired = is_user_personal_document_expired;
        }

        public String getIs_user_personal_document_expired_text() {
            return is_user_personal_document_expired_text;
        }

        public void setIs_user_personal_document_expired_text(String is_user_personal_document_expired_text) {
            this.is_user_personal_document_expired_text = is_user_personal_document_expired_text;
        }

        public boolean isIs_user_vehicle_document_expired() {
            return is_user_vehicle_document_expired;
        }

        public void setIs_user_vehicle_document_expired(boolean is_user_vehicle_document_expired) {
            this.is_user_vehicle_document_expired = is_user_vehicle_document_expired;
        }

        public String getIs_user_vehicle_document_expired_text() {
            return is_user_vehicle_document_expired_text;
        }

        public void setIs_user_vehicle_document_expired_text(String is_user_vehicle_document_expired_text) {
            this.is_user_vehicle_document_expired_text = is_user_vehicle_document_expired_text;
        }

        public boolean isIs_user_minimum_balance() {
            return is_user_minimum_balance;
        }

        public void setIs_user_minimum_balance(boolean is_user_minimum_balance) {
            this.is_user_minimum_balance = is_user_minimum_balance;
        }

        public String getIs_user_minimum_balance_text() {
            return is_user_minimum_balance_text;
        }

        public void setIs_user_minimum_balance_text(String is_user_minimum_balance_text) {
            this.is_user_minimum_balance_text = is_user_minimum_balance_text;
        }

        public boolean isIs_veh_upload() {
            return is_veh_upload;
        }

        public void setIs_veh_upload(boolean is_veh_upload) {
            this.is_veh_upload = is_veh_upload;
        }

        public boolean isIs_veh_doc_upload() {
            return is_veh_doc_upload;
        }

        public void setIs_veh_doc_upload(boolean is_veh_doc_upload) {
            this.is_veh_doc_upload = is_veh_doc_upload;
        }

        public boolean isIs_user_offer_ride() {
            return is_user_offer_ride;
        }

        public void setIs_user_offer_ride(boolean is_user_offer_ride) {
            this.is_user_offer_ride = is_user_offer_ride;
        }

        public boolean isIs_user_doc_upload() {
            return is_user_doc_upload;
        }

        public void setIs_user_doc_upload(boolean is_user_doc_upload) {
            this.is_user_doc_upload = is_user_doc_upload;
        }

        public String getOffer_ride_text() {
            return offer_ride_text;
        }

        public void setOffer_ride_text(String offer_ride_text) {
            this.offer_ride_text = offer_ride_text;
        }

        public String getUpload_document() {
            return upload_document;
        }

        public void setUpload_document(String upload_document) {
            this.upload_document = upload_document;
        }

        public String getUpload_vehicle() {
            return upload_vehicle;
        }

        public void setUpload_vehicle(String upload_vehicle) {
            this.upload_vehicle = upload_vehicle;
        }
    }
}
