package com.goviens.android.models;

import java.util.List;

public class ModelCustomerSupport {

    /**
     * version : 10.10
     * result : 1
     * message : Success
     * data : [{"id":12,"country":"India","service_area":"India","whatsapp_no":"13019004499","customer_support_no":"13019004499","email":"test@apporio.com"}]
     */

    private String version;
    private String result;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 12
         * country : India
         * service_area : India
         * whatsapp_no : 13019004499
         * customer_support_no : 13019004499
         * email : test@apporio.com
         */

        private int id;
        private String country;
        private String service_area;
        private String whatsapp_no;
        private String customer_support_no;
        private String email;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getService_area() {
            return service_area;
        }

        public void setService_area(String service_area) {
            this.service_area = service_area;
        }

        public String getWhatsapp_no() {
            return whatsapp_no;
        }

        public void setWhatsapp_no(String whatsapp_no) {
            this.whatsapp_no = whatsapp_no;
        }

        public String getCustomer_support_no() {
            return customer_support_no;
        }

        public void setCustomer_support_no(String customer_support_no) {
            this.customer_support_no = customer_support_no;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
