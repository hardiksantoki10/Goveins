package com.goviens.android.models;

public class ModelProfileDetails {

    /**
     * version : 5.90
     * result : 1
     * message : Success
     * data : {"id":254,"name":"RohitNew","phone":"+1780","profileimage":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1608278244_5fdc60e4919fa_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201219%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201219T135000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=2b7b1ec52e0c5de2c8fc0dc01dc1666427d0a23b2b55f083fad3ebed318709aa","document_verification_status":1,"member_since":1608289044,"rating":0,"vehicles":{"id":19,"vehicle_type_name":"Sedan","vehicle_color":"df","vehicle_number":"nhhg","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1608278520_5fdc61f86a7b3_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201219%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201219T135000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=23e092ec227b54d6b07f6a128ab8584e6f48a1e7a79f799dfeb45836fc49c1a1"},"total_rides":2}
     */

    private String version;
    private String result;
    private String message;
    /**
     * id : 254
     * name : RohitNew
     * phone : +1780
     * profileimage : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/1608278244_5fdc60e4919fa_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201219%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201219T135000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=2b7b1ec52e0c5de2c8fc0dc01dc1666427d0a23b2b55f083fad3ebed318709aa
     * document_verification_status : 1
     * member_since : 1608289044
     * rating : 0
     * vehicles : {"id":19,"vehicle_type_name":"Sedan","vehicle_color":"df","vehicle_number":"nhhg","vehicle_image":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1608278520_5fdc61f86a7b3_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201219%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201219T135000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=23e092ec227b54d6b07f6a128ab8584e6f48a1e7a79f799dfeb45836fc49c1a1"}
     * total_rides : 2
     */

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
        private int id;
        private String name;
        private String phone;
        private String phone_number_visiblity;
        private String profileimage;
        private int document_verification_status;
        private int member_since;
        private String rating;
        private RideDetailsBean ride_details;

        public RideDetailsBean getUserdetails() {
            return ride_details;
        }

        public void setUserdetails(RideDetailsBean userdetails) {
            this.ride_details = userdetails;
        }

        /**
         * id : 19
         * vehicle_type_name : Sedan
         * vehicle_color : df
         * vehicle_number : nhhg
         * vehicle_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1608278520_5fdc61f86a7b3_user_vehicle_document.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZEWQQ7YL4%2F20201219%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201219T135000Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=23e092ec227b54d6b07f6a128ab8584e6f48a1e7a79f799dfeb45836fc49c1a1
         */

        private int total_rides;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone_number_visiblity() {
            return phone_number_visiblity;
        }

        public void setPhone_number_visiblity(String phone_number_visiblity) {
            this.phone_number_visiblity = phone_number_visiblity;
        }

        public String getProfileimage() {
            return profileimage;
        }

        public void setProfileimage(String profileimage) {
            this.profileimage = profileimage;
        }

        public int getDocument_verification_status() {
            return document_verification_status;
        }

        public void setDocument_verification_status(int document_verification_status) {
            this.document_verification_status = document_verification_status;
        }

        public int getMember_since() {
            return member_since;
        }

        public void setMember_since(int member_since) {
            this.member_since = member_since;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public int getTotal_rides() {
            return total_rides;
        }

        public void setTotal_rides(int total_rides) {
            this.total_rides = total_rides;
        }


        public static class RideDetailsBean {
            private int id;
            private String rating;
            private String start_timestamp;
            private String end_timestamp;
            private int no_of_seats;
            private String start_location;
            private String drop_location;
            private String total_amount;
            private int payment_type;
            private String phone;
            private String phone_number_visiblity;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone_number_visiblity() {
                return phone_number_visiblity;
            }

            public void setPhone_number_visiblity(String phone_number_visiblity) {
                this.phone_number_visiblity = phone_number_visiblity;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getStart_timestamp() {
                return start_timestamp;
            }

            public void setStart_timestamp(String start_timestamp) {
                this.start_timestamp = start_timestamp;
            }

            public String getEnd_timestamp() {
                return end_timestamp;
            }

            public void setEnd_timestamp(String end_timestamp) {
                this.end_timestamp = end_timestamp;
            }

            public int getNo_of_seats() {
                return no_of_seats;
            }

            public void setNo_of_seats(int no_of_seats) {
                this.no_of_seats = no_of_seats;
            }

            public String getStart_location() {
                return start_location;
            }

            public void setStart_location(String start_location) {
                this.start_location = start_location;
            }

            public String getDrop_location() {
                return drop_location;
            }

            public void setDrop_location(String drop_location) {
                this.drop_location = drop_location;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public int getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(int payment_type) {
                this.payment_type = payment_type;
            }
        }
    }
}
