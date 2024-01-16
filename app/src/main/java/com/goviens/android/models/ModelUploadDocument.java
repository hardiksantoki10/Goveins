package com.goviens.android.models;

public class ModelUploadDocument {

    /**
     * version : 2.40
     * result : 0
     * message : The document number field is required when document number required is 1.
     */

    private String version;
    private String result;
    private String message;

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
}
