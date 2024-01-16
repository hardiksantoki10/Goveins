package com.goviens.android.models;

public class ModelVerifyOtp {

    /**
     * version : 1.80
     * result : 1
     * message : Success
     * data : {"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJiNzU2ZjVlODQzMmZlMmUwODNiNDE1ZGJhNmY1OGI2MTA1YjBhOWIwNTIzOWNlOWM1ZDM5Mzg5Mzc3ZDBkNzAzYTM0MWQxNmIwZDkzZTBjIn0.eyJhdWQiOiI4IiwianRpIjoiYmI3NTZmNWU4NDMyZmUyZTA4M2I0MTVkYmE2ZjU4YjYxMDViMGE5YjA1MjM5Y2U5YzVkMzkzODkzNzdkMGQ3MDNhMzQxZDE2YjBkOTNlMGMiLCJpYXQiOjE2MDQzMjEyNDIsIm5iZiI6MTYwNDMyMTI0MiwiZXhwIjoxNjM1ODU3MjQyLCJzdWIiOiIxNzMiLCJzY29wZXMiOltdfQ.Y6kGpNVLtl7bVzftt2irYDPPyfrQ_P8Hg7xAMPFAHO1qm_MRN9jMaBiliN3XqlUHtan-vh2zyoZRRbPzAk9CpA"}
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
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImJiNzU2ZjVlODQzMmZlMmUwODNiNDE1ZGJhNmY1OGI2MTA1YjBhOWIwNTIzOWNlOWM1ZDM5Mzg5Mzc3ZDBkNzAzYTM0MWQxNmIwZDkzZTBjIn0.eyJhdWQiOiI4IiwianRpIjoiYmI3NTZmNWU4NDMyZmUyZTA4M2I0MTVkYmE2ZjU4YjYxMDViMGE5YjA1MjM5Y2U5YzVkMzkzODkzNzdkMGQ3MDNhMzQxZDE2YjBkOTNlMGMiLCJpYXQiOjE2MDQzMjEyNDIsIm5iZiI6MTYwNDMyMTI0MiwiZXhwIjoxNjM1ODU3MjQyLCJzdWIiOiIxNzMiLCJzY29wZXMiOltdfQ.Y6kGpNVLtl7bVzftt2irYDPPyfrQ_P8Hg7xAMPFAHO1qm_MRN9jMaBiliN3XqlUHtan-vh2zyoZRRbPzAk9CpA
         */

        private String access_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }
}
