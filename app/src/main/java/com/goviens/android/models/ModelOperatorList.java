package com.goviens.android.models;

import java.util.List;

public class ModelOperatorList {

    /**
     * result : 1
     * message : Operator List
     * data : {"operator":["MTN CM","Orange CM"],"service_id":["CASHOUTMTNCM","CASHOUTOMCM"]}
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
        private List<String> operator;
        private List<String> service_id;

        public List<String> getOperator() {
            return operator;
        }

        public void setOperator(List<String> operator) {
            this.operator = operator;
        }

        public List<String> getService_id() {
            return service_id;
        }

        public void setService_id(List<String> service_id) {
            this.service_id = service_id;
        }
    }
}
