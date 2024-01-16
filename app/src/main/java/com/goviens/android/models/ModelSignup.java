package com.goviens.android.models;

public class ModelSignup {

    /**
     * version : 1.80
     * result : 1
     * message : Signup done successfully
     * data : {"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVmMjZiYzNkNThhNDRlZThhZmJlNWY1ZjM1NGE4NjAxMTA5NTQxNzdiODE2ZWI3MDQ0YmMyNTNiODVmZmM1NmE2ODRkZThkNzUyZjk5OTU0In0.eyJhdWQiOiI4IiwianRpIjoiNWYyNmJjM2Q1OGE0NGVlOGFmYmU1ZjVmMzU0YTg2MDExMDk1NDE3N2I4MTZlYjcwNDRiYzI1M2I4NWZmYzU2YTY4NGRlOGQ3NTJmOTk5NTQiLCJpYXQiOjE2MDQzMTQ5ODMsIm5iZiI6MTYwNDMxNDk4MywiZXhwIjoxNjM1ODUwOTgzLCJzdWIiOiIxNzMiLCJzY29wZXMiOltdfQ.DTkwOeFk4HhVIZ06ICiOj4YlXP32xD27aRgfx_zTQkIuGOgcyXS_UdJDZ480XS7aCnMjKO5h6zn8OdJAGvk_tg"}
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
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjVmMjZiYzNkNThhNDRlZThhZmJlNWY1ZjM1NGE4NjAxMTA5NTQxNzdiODE2ZWI3MDQ0YmMyNTNiODVmZmM1NmE2ODRkZThkNzUyZjk5OTU0In0.eyJhdWQiOiI4IiwianRpIjoiNWYyNmJjM2Q1OGE0NGVlOGFmYmU1ZjVmMzU0YTg2MDExMDk1NDE3N2I4MTZlYjcwNDRiYzI1M2I4NWZmYzU2YTY4NGRlOGQ3NTJmOTk5NTQiLCJpYXQiOjE2MDQzMTQ5ODMsIm5iZiI6MTYwNDMxNDk4MywiZXhwIjoxNjM1ODUwOTgzLCJzdWIiOiIxNzMiLCJzY29wZXMiOltdfQ.DTkwOeFk4HhVIZ06ICiOj4YlXP32xD27aRgfx_zTQkIuGOgcyXS_UdJDZ480XS7aCnMjKO5h6zn8OdJAGvk_tg
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
