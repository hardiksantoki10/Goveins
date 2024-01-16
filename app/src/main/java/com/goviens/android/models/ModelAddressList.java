package com.goviens.android.models;

import java.util.List;

public class ModelAddressList {

    /**
     * result : 1
     * message : Data fetched successfully
     * data : [{"id":32,"user_id":185,"house_name":"abc","floor":"","building":"abc","land_mark":"","address":"799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,","latitude":"28.415586980792522","longitude":"77.04159781336784","category":null,"address_title":null,"deleted_at":null,"created_at":"2020-11-17 18:55:43","updated_at":"2020-11-17 18:55:43"},{"id":33,"user_id":185,"house_name":"abc","floor":"","building":"abc","land_mark":"","address":"799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,","latitude":"28.415586980792522","longitude":"77.04159781336784","category":null,"address_title":null,"deleted_at":null,"created_at":"2020-11-17 18:55:44","updated_at":"2020-11-17 18:55:44"},{"id":34,"user_id":185,"house_name":"abc","floor":"","building":"abc","land_mark":"","address":"799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,","latitude":"28.415586980792522","longitude":"77.04159781336784","category":null,"address_title":null,"deleted_at":null,"created_at":"2020-11-17 18:55:45","updated_at":"2020-11-17 18:55:45"},{"id":35,"user_id":185,"house_name":"abc","floor":"","building":"abc","land_mark":"","address":"799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,","latitude":"28.415586980792522","longitude":"77.04159781336784","category":null,"address_title":null,"deleted_at":null,"created_at":"2020-11-17 18:56:22","updated_at":"2020-11-17 18:56:22"},{"id":36,"user_id":185,"house_name":"abc","floor":"","building":"abc","land_mark":"","address":"799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,","latitude":"28.415586980792522","longitude":"77.04159781336784","category":null,"address_title":null,"deleted_at":null,"created_at":"2020-11-17 19:28:53","updated_at":"2020-11-17 19:28:53"}]
     */

    private String result;
    private String message;
    private List<DataBean> data;

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
         * id : 32
         * user_id : 185
         * house_name : abc
         * floor :
         * building : abc
         * land_mark :
         * address : 799, Sohna Rd, Block S, Sispal Vihar, Sector 47, Gurugram, Haryana 122004, India,
         * latitude : 28.415586980792522
         * longitude : 77.04159781336784
         * category : null
         * address_title : null
         * deleted_at : null
         * created_at : 2020-11-17 18:55:43
         * updated_at : 2020-11-17 18:55:43
         */

        private int id;
        private int user_id;
        private String house_name;
        private String floor;
        private String building;
        private String land_mark;
        private String address;
        private String latitude;
        private String longitude;
        private Object category;
        private Object address_title;
        private Object deleted_at;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getHouse_name() {
            return house_name;
        }

        public void setHouse_name(String house_name) {
            this.house_name = house_name;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getLand_mark() {
            return land_mark;
        }

        public void setLand_mark(String land_mark) {
            this.land_mark = land_mark;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Object getCategory() {
            return category;
        }

        public void setCategory(Object category) {
            this.category = category;
        }

        public Object getAddress_title() {
            return address_title;
        }

        public void setAddress_title(Object address_title) {
            this.address_title = address_title;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
