package com.goviens.android.models;

import java.util.List;

public class ModelRideOTP {

    /**
     * version : 6.90
     * result : 1
     * message : Ride Start Successfully
     * data : {"ride_details":[{"id":124,"from_latitude":"28.41385660040602","from_longitude":"77.04217851161957","from_location":"Tower A, Spaze iTech Park, Sohna - Gurgaon Rd, Block S, Sector 49, Gurugram, Haryana 122018, India","to_location":"Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","to_latitude":"28.459219308332287","to_longitude":"77.07248009741306","ride_time":"1609965000","female_ride":0,"ac_ride":0}],"user_data":[{"id":98,"name":"Rohit Sharma","start_location":"Spaze itech Park Gate opposite wine n beer shop Sector 49, Gurugram, Haryana 122018, India","end_location":"Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India","ride_time":"1609965000","end_ride_time":"1609965000","payment_type":0,"final_amount":"XAF 36","profile_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210106T071042Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=36fe3e0f60299d2a3b893a594fd70c58d6a96453d53e4b62c2b34bcbd7741499","UserPhone":"+2371234"}]}
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


    public class DataBean {
//        private List<RideDetailsBean> ride_details;
//        private List<UserDataBean> user_data;
//
//        public List<RideDetailsBean> getRide_details() {
//            return ride_details;
//        }
//
//        public void setRide_details(List<RideDetailsBean> ride_details) {
//            this.ride_details = ride_details;
//        }
//
//        public List<UserDataBean> getUser_data() {
//            return user_data;
//        }
//
//        public void setUser_data(List<UserDataBean> user_data) {
//            this.user_data = user_data;
//        }
//
//
//        public class RideDetailsBean {
//            /**
//             * id : 124
//             * from_latitude : 28.41385660040602
//             * from_longitude : 77.04217851161957
//             * from_location : Tower A, Spaze iTech Park, Sohna - Gurgaon Rd, Block S, Sector 49, Gurugram, Haryana 122018, India
//             * to_location : Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India
//             * to_latitude : 28.459219308332287
//             * to_longitude : 77.07248009741306
//             * ride_time : 1609965000
//             * female_ride : 0
//             * ac_ride : 0
//             */
//
//            private int id;
//            private String from_latitude;
//            private String from_longitude;
//            private String from_location;
//            private String to_location;
//            private String to_latitude;
//            private String to_longitude;
//            private String ride_time;
//            private int female_ride;
//            private int ac_ride;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getFrom_latitude() {
//                return from_latitude;
//            }
//
//            public void setFrom_latitude(String from_latitude) {
//                this.from_latitude = from_latitude;
//            }
//
//            public String getFrom_longitude() {
//                return from_longitude;
//            }
//
//            public void setFrom_longitude(String from_longitude) {
//                this.from_longitude = from_longitude;
//            }
//
//            public String getFrom_location() {
//                return from_location;
//            }
//
//            public void setFrom_location(String from_location) {
//                this.from_location = from_location;
//            }
//
//            public String getTo_location() {
//                return to_location;
//            }
//
//            public void setTo_location(String to_location) {
//                this.to_location = to_location;
//            }
//
//            public String getTo_latitude() {
//                return to_latitude;
//            }
//
//            public void setTo_latitude(String to_latitude) {
//                this.to_latitude = to_latitude;
//            }
//
//            public String getTo_longitude() {
//                return to_longitude;
//            }
//
//            public void setTo_longitude(String to_longitude) {
//                this.to_longitude = to_longitude;
//            }
//
//            public String getRide_time() {
//                return ride_time;
//            }
//
//            public void setRide_time(String ride_time) {
//                this.ride_time = ride_time;
//            }
//
//            public int getFemale_ride() {
//                return female_ride;
//            }
//
//            public void setFemale_ride(int female_ride) {
//                this.female_ride = female_ride;
//            }
//
//            public int getAc_ride() {
//                return ac_ride;
//            }
//
//            public void setAc_ride(int ac_ride) {
//                this.ac_ride = ac_ride;
//            }
//        }
//
//        public class UserDataBean {
//            /**
//             * id : 98
//             * name : Rohit Sharma
//             * start_location : Spaze itech Park Gate opposite wine n beer shop Sector 49, Gurugram, Haryana 122018, India
//             * end_location : Huda city centre metro Shalimar Huda City Center, Delhi, Sector 29, Gurugram, Haryana 122007, India
//             * ride_time : 1609965000
//             * end_ride_time : 1609965000
//             * payment_type : 0
//             * final_amount : XAF 36
//             * profile_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1607949019_5fd75adb40103_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20210106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20210106T071042Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=36fe3e0f60299d2a3b893a594fd70c58d6a96453d53e4b62c2b34bcbd7741499
//             * UserPhone : +2371234
//             */
//
//            private int id;
//            private String name;
//            private String start_location;
//            private String end_location;
//            private String ride_time;
//            private String end_ride_time;
//            private int payment_type;
//            private String final_amount;
//            private String profile_image;
//            private String UserPhone;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getStart_location() {
//                return start_location;
//            }
//
//            public void setStart_location(String start_location) {
//                this.start_location = start_location;
//            }
//
//            public String getEnd_location() {
//                return end_location;
//            }
//
//            public void setEnd_location(String end_location) {
//                this.end_location = end_location;
//            }
//
//            public String getRide_time() {
//                return ride_time;
//            }
//
//            public void setRide_time(String ride_time) {
//                this.ride_time = ride_time;
//            }
//
//            public String getEnd_ride_time() {
//                return end_ride_time;
//            }
//
//            public void setEnd_ride_time(String end_ride_time) {
//                this.end_ride_time = end_ride_time;
//            }
//
//            public int getPayment_type() {
//                return payment_type;
//            }
//
//            public void setPayment_type(int payment_type) {
//                this.payment_type = payment_type;
//            }
//
//            public String getFinal_amount() {
//                return final_amount;
//            }
//
//            public void setFinal_amount(String final_amount) {
//                this.final_amount = final_amount;
//            }
//
//            public String getProfile_image() {
//                return profile_image;
//            }
//
//            public void setProfile_image(String profile_image) {
//                this.profile_image = profile_image;
//            }
//
//            public String getUserPhone() {
//                return UserPhone;
//            }
//
//            public void setUserPhone(String UserPhone) {
//                this.UserPhone = UserPhone;
//            }
//        }
//    }
    }
}
