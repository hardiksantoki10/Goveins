package com.goviens.android.models;

public class ModelEditProfile {

    /**
     * data : {"country_id":"13","phone_code":"+91","first_name":"ag","last_name":"vhh","email":"and@g.com","rating":"0.0","merchant_id":"8","PhoneVerified":"0","id":"185","ReferralCode":"DRFKV","password":"$2y$10$ucnOV8L/BzRNkSHC0bNwSuL9u6MTPeNDNSYQMUY.taGZ6j6NSeTlK","UserPhone":"11233","wallet_balance":"","UserProfileImage":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/https%3A//s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/goviens/user/1605157688_5facc338c1549_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T050809Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=736abf0294cfdc91a84f5bc6e53e424b4289d3a8d3a79befaaa9304a2b762ff5","user_gender":"1","signup_status":"","outstanding_amount":"","smoker_type":"0","allow_other_smoker":"0","UserSignupType":"1","login_via":"1","user_card":true,"user_signup_card_store":true,"no_of_favorite_drivers":0,"home_location":"","work_location":"","no_of_bookings_done":0,"no_of_emergency_contacts":0,"bank_details":{"bank_name":"","account_type":"","account_type_id":"","online_code":"","account_holder_name":"","account_number":"","transaction_code_text":"Branch Code"},"default_vehicle":true,"personal_document_verification_status":null,"vehicle_document_verification_status":null}
     * result : 1
     * message : api.userdetails
     */

    private DataBean data;
    private String result;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * country_id : 13
         * phone_code : +91
         * first_name : ag
         * last_name : vhh
         * email : and@g.com
         * rating : 0.0
         * merchant_id : 8
         * PhoneVerified : 0
         * id : 185
         * ReferralCode : DRFKV
         * password : $2y$10$ucnOV8L/BzRNkSHC0bNwSuL9u6MTPeNDNSYQMUY.taGZ6j6NSeTlK
         * UserPhone : 11233
         * wallet_balance :
         * UserProfileImage : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user/https%3A//s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/goviens/user/1605157688_5facc338c1549_user.jpg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201112%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201112T050809Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=736abf0294cfdc91a84f5bc6e53e424b4289d3a8d3a79befaaa9304a2b762ff5
         * user_gender : 1
         * signup_status :
         * outstanding_amount :
         * smoker_type : 0
         * allow_other_smoker : 0
         * UserSignupType : 1
         * login_via : 1
         * user_card : true
         * user_signup_card_store : true
         * no_of_favorite_drivers : 0
         * home_location :
         * work_location :
         * no_of_bookings_done : 0
         * no_of_emergency_contacts : 0
         * bank_details : {"bank_name":"","account_type":"","account_type_id":"","online_code":"","account_holder_name":"","account_number":"","transaction_code_text":"Branch Code"}
         * default_vehicle : true
         * personal_document_verification_status : null
         * vehicle_document_verification_status : null
         */

        private String country_id;
        private String phone_code;
        private String first_name;
        private String last_name;
        private String email;
        private String rating;
        private String merchant_id;
        private String PhoneVerified;
        private String id;
        private String ReferralCode;
        private String password;
        private String UserPhone;
        private String wallet_balance;
        private String UserProfileImage;
        private String user_gender;
        private String signup_status;
        private String outstanding_amount;
        private String smoker_type;
        private String allow_other_smoker;
        private String UserSignupType;
        private String login_via;
        private boolean user_card;
        private boolean user_signup_card_store;
        private int no_of_favorite_drivers;
        private String home_location;
        private String work_location;
        private int no_of_bookings_done;
        private int no_of_emergency_contacts;
        private BankDetailsBean bank_details;
        private boolean default_vehicle;
        private Object personal_document_verification_status;
        private Object vehicle_document_verification_status;

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getPhone_code() {
            return phone_code;
        }

        public void setPhone_code(String phone_code) {
            this.phone_code = phone_code;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getPhoneVerified() {
            return PhoneVerified;
        }

        public void setPhoneVerified(String PhoneVerified) {
            this.PhoneVerified = PhoneVerified;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReferralCode() {
            return ReferralCode;
        }

        public void setReferralCode(String ReferralCode) {
            this.ReferralCode = ReferralCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String UserPhone) {
            this.UserPhone = UserPhone;
        }

        public String getWallet_balance() {
            return wallet_balance;
        }

        public void setWallet_balance(String wallet_balance) {
            this.wallet_balance = wallet_balance;
        }

        public String getUserProfileImage() {
            return UserProfileImage;
        }

        public void setUserProfileImage(String UserProfileImage) {
            this.UserProfileImage = UserProfileImage;
        }

        public String getUser_gender() {
            return user_gender;
        }

        public void setUser_gender(String user_gender) {
            this.user_gender = user_gender;
        }

        public String getSignup_status() {
            return signup_status;
        }

        public void setSignup_status(String signup_status) {
            this.signup_status = signup_status;
        }

        public String getOutstanding_amount() {
            return outstanding_amount;
        }

        public void setOutstanding_amount(String outstanding_amount) {
            this.outstanding_amount = outstanding_amount;
        }

        public String getSmoker_type() {
            return smoker_type;
        }

        public void setSmoker_type(String smoker_type) {
            this.smoker_type = smoker_type;
        }

        public String getAllow_other_smoker() {
            return allow_other_smoker;
        }

        public void setAllow_other_smoker(String allow_other_smoker) {
            this.allow_other_smoker = allow_other_smoker;
        }

        public String getUserSignupType() {
            return UserSignupType;
        }

        public void setUserSignupType(String UserSignupType) {
            this.UserSignupType = UserSignupType;
        }

        public String getLogin_via() {
            return login_via;
        }

        public void setLogin_via(String login_via) {
            this.login_via = login_via;
        }

        public boolean isUser_card() {
            return user_card;
        }

        public void setUser_card(boolean user_card) {
            this.user_card = user_card;
        }

        public boolean isUser_signup_card_store() {
            return user_signup_card_store;
        }

        public void setUser_signup_card_store(boolean user_signup_card_store) {
            this.user_signup_card_store = user_signup_card_store;
        }

        public int getNo_of_favorite_drivers() {
            return no_of_favorite_drivers;
        }

        public void setNo_of_favorite_drivers(int no_of_favorite_drivers) {
            this.no_of_favorite_drivers = no_of_favorite_drivers;
        }

        public String getHome_location() {
            return home_location;
        }

        public void setHome_location(String home_location) {
            this.home_location = home_location;
        }

        public String getWork_location() {
            return work_location;
        }

        public void setWork_location(String work_location) {
            this.work_location = work_location;
        }

        public int getNo_of_bookings_done() {
            return no_of_bookings_done;
        }

        public void setNo_of_bookings_done(int no_of_bookings_done) {
            this.no_of_bookings_done = no_of_bookings_done;
        }

        public int getNo_of_emergency_contacts() {
            return no_of_emergency_contacts;
        }

        public void setNo_of_emergency_contacts(int no_of_emergency_contacts) {
            this.no_of_emergency_contacts = no_of_emergency_contacts;
        }

        public BankDetailsBean getBank_details() {
            return bank_details;
        }

        public void setBank_details(BankDetailsBean bank_details) {
            this.bank_details = bank_details;
        }

        public boolean isDefault_vehicle() {
            return default_vehicle;
        }

        public void setDefault_vehicle(boolean default_vehicle) {
            this.default_vehicle = default_vehicle;
        }

        public Object getPersonal_document_verification_status() {
            return personal_document_verification_status;
        }

        public void setPersonal_document_verification_status(Object personal_document_verification_status) {
            this.personal_document_verification_status = personal_document_verification_status;
        }

        public Object getVehicle_document_verification_status() {
            return vehicle_document_verification_status;
        }

        public void setVehicle_document_verification_status(Object vehicle_document_verification_status) {
            this.vehicle_document_verification_status = vehicle_document_verification_status;
        }

        public static class BankDetailsBean {
            /**
             * bank_name :
             * account_type :
             * account_type_id :
             * online_code :
             * account_holder_name :
             * account_number :
             * transaction_code_text : Branch Code
             */

            private String bank_name;
            private String account_type;
            private String account_type_id;
            private String online_code;
            private String account_holder_name;
            private String account_number;
            private String transaction_code_text;

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getAccount_type() {
                return account_type;
            }

            public void setAccount_type(String account_type) {
                this.account_type = account_type;
            }

            public String getAccount_type_id() {
                return account_type_id;
            }

            public void setAccount_type_id(String account_type_id) {
                this.account_type_id = account_type_id;
            }

            public String getOnline_code() {
                return online_code;
            }

            public void setOnline_code(String online_code) {
                this.online_code = online_code;
            }

            public String getAccount_holder_name() {
                return account_holder_name;
            }

            public void setAccount_holder_name(String account_holder_name) {
                this.account_holder_name = account_holder_name;
            }

            public String getAccount_number() {
                return account_number;
            }

            public void setAccount_number(String account_number) {
                this.account_number = account_number;
            }

            public String getTransaction_code_text() {
                return transaction_code_text;
            }

            public void setTransaction_code_text(String transaction_code_text) {
                this.transaction_code_text = transaction_code_text;
            }
        }
    }
}
