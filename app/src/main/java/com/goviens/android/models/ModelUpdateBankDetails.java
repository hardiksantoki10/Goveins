package com.goviens.android.models;

import java.util.List;

public class ModelUpdateBankDetails {

    /**
     * version : 2.70
     * result : 1
     * message : Bank Details Details updated successfully
     * data : []
     */

    private String version;
    private String result;
    private String message;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
