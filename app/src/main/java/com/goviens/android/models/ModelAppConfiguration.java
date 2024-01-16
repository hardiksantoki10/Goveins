package com.goviens.android.models;

import java.util.List;

public class ModelAppConfiguration {

    /**
     * version : 1.80
     * result : 1
     * message : api.appconfig
     * data : {"navigation_drawer":[],"register":{"smoker":false,"email":true,"user_email_otp":false,"phone":true,"user_phone_otp":true,"gender":false,"userImage_enable":true},"ride_later":{"ride_later_hours":"","outstation_time":"","rental_ride_later_hours":"","transfer_ride_later_hours":"","ride_later_max_num_days":""},"app_version":{"show_dialog":false,"mandatory":true,"dialog_message":"","ios_user_appid":"https://apporio.com"},"app_maintainance":{"show_dialog":true,"show_message":"api.message56"},"languages":[{"id":1,"name":"English","locale":"en","created_at":"2020-03-23 16:08:49","updated_at":"2020-03-23 16:08:49"}],"countries":[{"id":10,"merchant_id":8,"sequance":102,"country_code":"IN","isoCode":"INR","phonecode":"+91","distance_unit":1,"default_language":"","maxNumPhone":12,"minNumPhone":8,"transaction_code":"Branch Code","additional_details":0,"parameter_name":null,"placeholder":null,"country_status":1,"created_at":"2020-08-05 05:38:49","updated_at":"2020-08-06 13:22:46","short_code":null,"name":"India","currency":"INR"}],"ride_config":{"ride_button_now_text":"Ride Now","ride_button_later_text":"Ride Later","gender_matching":false,"category_vehicle_type_module":false,"multiple_rides":false,"add_note":true,"outstation_ride_now_enabled":false,"multi_destination":false,"total_distination":0,"normal":{"drop_location":{"ride_now":false,"ride_later":false}},"rental":{"drop_location":{"ride_now":false,"ride_later":false}},"pickup_color":null,"dropoff_color":null,"booking_eta":false,"drop_location_request":false,"change_payment_method":false,"drop_outside_area":false,"user_request_timeout":null},"general_config":{"corporate_enable":false,"additional_info":false,"chat":false,"googleKey":"AIzaSyAhERO8P64JSxbyEwFDxW98r9nt9QpxlHM","favourite_driver_module":false,"static_map":false,"wallet":true,"demo":false,"homescreen_estimate_fare":false,"default_language":"en","card":true,"splash_screen":"Goviens","user_wallet_package":[{"amount":"10"},{"amount":"20"},{"amount":"30"}],"vehicle_rating_enable":false,"security_question":false,"security_questions":[],"tip_enable":false,"user_document":true,"sur_charge":false,"emergency_contact":false,"show_logo_main":false,"autocomplete_start":0,"baby_seat_enable":false,"no_of_person":false,"no_of_children":false,"no_of_bags":false,"wheel_chair_enable":false,"family_member_enable":false,"user_number_track_screen":false,"no_of_pool_seats":[],"user_cpf_number_enable":false,"splash_screen_user":null,"banner_image_user":null,"otp_from_firebase":false,"vehicle_ac_enable":false,"network_code_visibility":false,"referral_code_mandatory_user_signup":true,"push_notification":1},"login":{"email":false,"phone":true,"otp":false},"customer_support":{"mail":"bhuvanesh@apporio.com","phone":"+918005750736"},"social":{"enable":false,"google":false,"facebook":false},"paymentOption":[],"theme_cofig":{"primary_color_user":null,"chat_button_color":null,"share_button_color":null,"call_button_color":null,"cancel_button_color":null},"business_logo":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/business_logo/1596534043_5f292d1bb3194_business_logo.jpeg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201022%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201022T052708Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=1063d27d8d9a4ef46cc4c9ef240b8a6e07a40e912a8c1ea0fb95351cc8fe6c29","advertise_banner":[],"advertise_banner_visibility":true,"additional_information":{"parameter_name":"Temperature","display":true},"user_signup_card_store":false,"user_card":true,"fare_policy_text":"","account_types":[{"id":3,"title":"Saving Account"},{"id":4,"title":"Current Account"}]}
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
         * navigation_drawer : []
         * register : {"smoker":false,"email":true,"user_email_otp":false,"phone":true,"user_phone_otp":true,"gender":false,"userImage_enable":true}
         * ride_later : {"ride_later_hours":"","outstation_time":"","rental_ride_later_hours":"","transfer_ride_later_hours":"","ride_later_max_num_days":""}
         * app_version : {"show_dialog":false,"mandatory":true,"dialog_message":"","ios_user_appid":"https://apporio.com"}
         * app_maintainance : {"show_dialog":true,"show_message":"api.message56"}
         * languages : [{"id":1,"name":"English","locale":"en","created_at":"2020-03-23 16:08:49","updated_at":"2020-03-23 16:08:49"}]
         * countries : [{"id":10,"merchant_id":8,"sequance":102,"country_code":"IN","isoCode":"INR","phonecode":"+91","distance_unit":1,"default_language":"","maxNumPhone":12,"minNumPhone":8,"transaction_code":"Branch Code","additional_details":0,"parameter_name":null,"placeholder":null,"country_status":1,"created_at":"2020-08-05 05:38:49","updated_at":"2020-08-06 13:22:46","short_code":null,"name":"India","currency":"INR"}]
         * ride_config : {"ride_button_now_text":"Ride Now","ride_button_later_text":"Ride Later","gender_matching":false,"category_vehicle_type_module":false,"multiple_rides":false,"add_note":true,"outstation_ride_now_enabled":false,"multi_destination":false,"total_distination":0,"normal":{"drop_location":{"ride_now":false,"ride_later":false}},"rental":{"drop_location":{"ride_now":false,"ride_later":false}},"pickup_color":null,"dropoff_color":null,"booking_eta":false,"drop_location_request":false,"change_payment_method":false,"drop_outside_area":false,"user_request_timeout":null}
         * general_config : {"corporate_enable":false,"additional_info":false,"chat":false,"googleKey":"AIzaSyAhERO8P64JSxbyEwFDxW98r9nt9QpxlHM","favourite_driver_module":false,"static_map":false,"wallet":true,"demo":false,"homescreen_estimate_fare":false,"default_language":"en","card":true,"splash_screen":"Goviens","user_wallet_package":[{"amount":"10"},{"amount":"20"},{"amount":"30"}],"vehicle_rating_enable":false,"security_question":false,"security_questions":[],"tip_enable":false,"user_document":true,"sur_charge":false,"emergency_contact":false,"show_logo_main":false,"autocomplete_start":0,"baby_seat_enable":false,"no_of_person":false,"no_of_children":false,"no_of_bags":false,"wheel_chair_enable":false,"family_member_enable":false,"user_number_track_screen":false,"no_of_pool_seats":[],"user_cpf_number_enable":false,"splash_screen_user":null,"banner_image_user":null,"otp_from_firebase":false,"vehicle_ac_enable":false,"network_code_visibility":false,"referral_code_mandatory_user_signup":true,"push_notification":1}
         * login : {"email":false,"phone":true,"otp":false}
         * customer_support : {"mail":"bhuvanesh@apporio.com","phone":"+918005750736"}
         * social : {"enable":false,"google":false,"facebook":false}
         * paymentOption : []
         * theme_cofig : {"primary_color_user":null,"chat_button_color":null,"share_button_color":null,"call_button_color":null,"cancel_button_color":null}
         * business_logo : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/business_logo/1596534043_5f292d1bb3194_business_logo.jpeg?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201022%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201022T052708Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=1063d27d8d9a4ef46cc4c9ef240b8a6e07a40e912a8c1ea0fb95351cc8fe6c29
         * advertise_banner : []
         * advertise_banner_visibility : true
         * additional_information : {"parameter_name":"Temperature","display":true}
         * user_signup_card_store : false
         * user_card : true
         * fare_policy_text :
         * account_types : [{"id":3,"title":"Saving Account"},{"id":4,"title":"Current Account"}]
         */

        private RegisterBean register;
        private RideLaterBean ride_later;
        private AppVersionBean app_version;
        private AppMaintainanceBean app_maintainance;
        private RideConfigBean ride_config;
        private GeneralConfigBean general_config;
        private LoginBean login;
        private CustomerSupportBean customer_support;
        private SocialBean social;
        private ThemeCofigBean theme_cofig;
        private String business_logo;
        private boolean advertise_banner_visibility;
        private AdditionalInformationBean additional_information;
        private boolean user_signup_card_store;
        private boolean user_card;
        private String fare_policy_text;
        private List<?> navigation_drawer;
        private List<LanguagesBean> languages;
        private List<CountriesBean> countries;
        private List<?> paymentOption;
        private List<?> advertise_banner;
        private List<AccountTypesBean> account_types;

        public RegisterBean getRegister() {
            return register;
        }

        public void setRegister(RegisterBean register) {
            this.register = register;
        }

        public RideLaterBean getRide_later() {
            return ride_later;
        }

        public void setRide_later(RideLaterBean ride_later) {
            this.ride_later = ride_later;
        }

        public AppVersionBean getApp_version() {
            return app_version;
        }

        public void setApp_version(AppVersionBean app_version) {
            this.app_version = app_version;
        }

        public AppMaintainanceBean getApp_maintainance() {
            return app_maintainance;
        }

        public void setApp_maintainance(AppMaintainanceBean app_maintainance) {
            this.app_maintainance = app_maintainance;
        }

        public RideConfigBean getRide_config() {
            return ride_config;
        }

        public void setRide_config(RideConfigBean ride_config) {
            this.ride_config = ride_config;
        }

        public GeneralConfigBean getGeneral_config() {
            return general_config;
        }

        public void setGeneral_config(GeneralConfigBean general_config) {
            this.general_config = general_config;
        }

        public LoginBean getLogin() {
            return login;
        }

        public void setLogin(LoginBean login) {
            this.login = login;
        }

        public CustomerSupportBean getCustomer_support() {
            return customer_support;
        }

        public void setCustomer_support(CustomerSupportBean customer_support) {
            this.customer_support = customer_support;
        }

        public SocialBean getSocial() {
            return social;
        }

        public void setSocial(SocialBean social) {
            this.social = social;
        }

        public ThemeCofigBean getTheme_cofig() {
            return theme_cofig;
        }

        public void setTheme_cofig(ThemeCofigBean theme_cofig) {
            this.theme_cofig = theme_cofig;
        }

        public String getBusiness_logo() {
            return business_logo;
        }

        public void setBusiness_logo(String business_logo) {
            this.business_logo = business_logo;
        }

        public boolean isAdvertise_banner_visibility() {
            return advertise_banner_visibility;
        }

        public void setAdvertise_banner_visibility(boolean advertise_banner_visibility) {
            this.advertise_banner_visibility = advertise_banner_visibility;
        }

        public AdditionalInformationBean getAdditional_information() {
            return additional_information;
        }

        public void setAdditional_information(AdditionalInformationBean additional_information) {
            this.additional_information = additional_information;
        }

        public boolean isUser_signup_card_store() {
            return user_signup_card_store;
        }

        public void setUser_signup_card_store(boolean user_signup_card_store) {
            this.user_signup_card_store = user_signup_card_store;
        }

        public boolean isUser_card() {
            return user_card;
        }

        public void setUser_card(boolean user_card) {
            this.user_card = user_card;
        }

        public String getFare_policy_text() {
            return fare_policy_text;
        }

        public void setFare_policy_text(String fare_policy_text) {
            this.fare_policy_text = fare_policy_text;
        }

        public List<?> getNavigation_drawer() {
            return navigation_drawer;
        }

        public void setNavigation_drawer(List<?> navigation_drawer) {
            this.navigation_drawer = navigation_drawer;
        }

        public List<LanguagesBean> getLanguages() {
            return languages;
        }

        public void setLanguages(List<LanguagesBean> languages) {
            this.languages = languages;
        }

        public List<CountriesBean> getCountries() {
            return countries;
        }

        public void setCountries(List<CountriesBean> countries) {
            this.countries = countries;
        }

        public List<?> getPaymentOption() {
            return paymentOption;
        }

        public void setPaymentOption(List<?> paymentOption) {
            this.paymentOption = paymentOption;
        }

        public List<?> getAdvertise_banner() {
            return advertise_banner;
        }

        public void setAdvertise_banner(List<?> advertise_banner) {
            this.advertise_banner = advertise_banner;
        }

        public List<AccountTypesBean> getAccount_types() {
            return account_types;
        }

        public void setAccount_types(List<AccountTypesBean> account_types) {
            this.account_types = account_types;
        }

        public static class RegisterBean {
            /**
             * smoker : false
             * email : true
             * user_email_otp : false
             * phone : true
             * user_phone_otp : true
             * gender : false
             * userImage_enable : true
             */

            private boolean smoker;
            private boolean email;
            private boolean user_email_otp;
            private boolean phone;
            private boolean user_phone_otp;
            private boolean gender;
            private boolean userImage_enable;

            public boolean isSmoker() {
                return smoker;
            }

            public void setSmoker(boolean smoker) {
                this.smoker = smoker;
            }

            public boolean isEmail() {
                return email;
            }

            public void setEmail(boolean email) {
                this.email = email;
            }

            public boolean isUser_email_otp() {
                return user_email_otp;
            }

            public void setUser_email_otp(boolean user_email_otp) {
                this.user_email_otp = user_email_otp;
            }

            public boolean isPhone() {
                return phone;
            }

            public void setPhone(boolean phone) {
                this.phone = phone;
            }

            public boolean isUser_phone_otp() {
                return user_phone_otp;
            }

            public void setUser_phone_otp(boolean user_phone_otp) {
                this.user_phone_otp = user_phone_otp;
            }

            public boolean isGender() {
                return gender;
            }

            public void setGender(boolean gender) {
                this.gender = gender;
            }

            public boolean isUserImage_enable() {
                return userImage_enable;
            }

            public void setUserImage_enable(boolean userImage_enable) {
                this.userImage_enable = userImage_enable;
            }
        }

        public static class RideLaterBean {
            /**
             * ride_later_hours :
             * outstation_time :
             * rental_ride_later_hours :
             * transfer_ride_later_hours :
             * ride_later_max_num_days :
             */

            private String ride_later_hours;
            private String outstation_time;
            private String rental_ride_later_hours;
            private String transfer_ride_later_hours;
            private String ride_later_max_num_days;

            public String getRide_later_hours() {
                return ride_later_hours;
            }

            public void setRide_later_hours(String ride_later_hours) {
                this.ride_later_hours = ride_later_hours;
            }

            public String getOutstation_time() {
                return outstation_time;
            }

            public void setOutstation_time(String outstation_time) {
                this.outstation_time = outstation_time;
            }

            public String getRental_ride_later_hours() {
                return rental_ride_later_hours;
            }

            public void setRental_ride_later_hours(String rental_ride_later_hours) {
                this.rental_ride_later_hours = rental_ride_later_hours;
            }

            public String getTransfer_ride_later_hours() {
                return transfer_ride_later_hours;
            }

            public void setTransfer_ride_later_hours(String transfer_ride_later_hours) {
                this.transfer_ride_later_hours = transfer_ride_later_hours;
            }

            public String getRide_later_max_num_days() {
                return ride_later_max_num_days;
            }

            public void setRide_later_max_num_days(String ride_later_max_num_days) {
                this.ride_later_max_num_days = ride_later_max_num_days;
            }
        }

        public static class AppVersionBean {
            /**
             * show_dialog : false
             * mandatory : true
             * dialog_message :
             * ios_user_appid : https://apporio.com
             */

            private boolean show_dialog;
            private boolean mandatory;
            private String dialog_message;
            private String ios_user_appid;

            public boolean isShow_dialog() {
                return show_dialog;
            }

            public void setShow_dialog(boolean show_dialog) {
                this.show_dialog = show_dialog;
            }

            public boolean isMandatory() {
                return mandatory;
            }

            public void setMandatory(boolean mandatory) {
                this.mandatory = mandatory;
            }

            public String getDialog_message() {
                return dialog_message;
            }

            public void setDialog_message(String dialog_message) {
                this.dialog_message = dialog_message;
            }

            public String getIos_user_appid() {
                return ios_user_appid;
            }

            public void setIos_user_appid(String ios_user_appid) {
                this.ios_user_appid = ios_user_appid;
            }
        }

        public static class AppMaintainanceBean {
            /**
             * show_dialog : true
             * show_message : api.message56
             */

            private boolean show_dialog;
            private String show_message;

            public boolean isShow_dialog() {
                return show_dialog;
            }

            public void setShow_dialog(boolean show_dialog) {
                this.show_dialog = show_dialog;
            }

            public String getShow_message() {
                return show_message;
            }

            public void setShow_message(String show_message) {
                this.show_message = show_message;
            }
        }

        public static class RideConfigBean {
            /**
             * ride_button_now_text : Ride Now
             * ride_button_later_text : Ride Later
             * gender_matching : false
             * category_vehicle_type_module : false
             * multiple_rides : false
             * add_note : true
             * outstation_ride_now_enabled : false
             * multi_destination : false
             * total_distination : 0
             * normal : {"drop_location":{"ride_now":false,"ride_later":false}}
             * rental : {"drop_location":{"ride_now":false,"ride_later":false}}
             * pickup_color : null
             * dropoff_color : null
             * booking_eta : false
             * drop_location_request : false
             * change_payment_method : false
             * drop_outside_area : false
             * user_request_timeout : null
             */

            private String ride_button_now_text;
            private String ride_button_later_text;
            private boolean gender_matching;
            private boolean category_vehicle_type_module;
            private boolean multiple_rides;
            private boolean add_note;
            private boolean outstation_ride_now_enabled;
            private boolean multi_destination;
            private int total_distination;
            private NormalBean normal;
            private RentalBean rental;
            private Object pickup_color;
            private Object dropoff_color;
            private boolean booking_eta;
            private boolean drop_location_request;
            private boolean change_payment_method;
            private boolean drop_outside_area;
            private Object user_request_timeout;

            public String getRide_button_now_text() {
                return ride_button_now_text;
            }

            public void setRide_button_now_text(String ride_button_now_text) {
                this.ride_button_now_text = ride_button_now_text;
            }

            public String getRide_button_later_text() {
                return ride_button_later_text;
            }

            public void setRide_button_later_text(String ride_button_later_text) {
                this.ride_button_later_text = ride_button_later_text;
            }

            public boolean isGender_matching() {
                return gender_matching;
            }

            public void setGender_matching(boolean gender_matching) {
                this.gender_matching = gender_matching;
            }

            public boolean isCategory_vehicle_type_module() {
                return category_vehicle_type_module;
            }

            public void setCategory_vehicle_type_module(boolean category_vehicle_type_module) {
                this.category_vehicle_type_module = category_vehicle_type_module;
            }

            public boolean isMultiple_rides() {
                return multiple_rides;
            }

            public void setMultiple_rides(boolean multiple_rides) {
                this.multiple_rides = multiple_rides;
            }

            public boolean isAdd_note() {
                return add_note;
            }

            public void setAdd_note(boolean add_note) {
                this.add_note = add_note;
            }

            public boolean isOutstation_ride_now_enabled() {
                return outstation_ride_now_enabled;
            }

            public void setOutstation_ride_now_enabled(boolean outstation_ride_now_enabled) {
                this.outstation_ride_now_enabled = outstation_ride_now_enabled;
            }

            public boolean isMulti_destination() {
                return multi_destination;
            }

            public void setMulti_destination(boolean multi_destination) {
                this.multi_destination = multi_destination;
            }

            public int getTotal_distination() {
                return total_distination;
            }

            public void setTotal_distination(int total_distination) {
                this.total_distination = total_distination;
            }

            public NormalBean getNormal() {
                return normal;
            }

            public void setNormal(NormalBean normal) {
                this.normal = normal;
            }

            public RentalBean getRental() {
                return rental;
            }

            public void setRental(RentalBean rental) {
                this.rental = rental;
            }

            public Object getPickup_color() {
                return pickup_color;
            }

            public void setPickup_color(Object pickup_color) {
                this.pickup_color = pickup_color;
            }

            public Object getDropoff_color() {
                return dropoff_color;
            }

            public void setDropoff_color(Object dropoff_color) {
                this.dropoff_color = dropoff_color;
            }

            public boolean isBooking_eta() {
                return booking_eta;
            }

            public void setBooking_eta(boolean booking_eta) {
                this.booking_eta = booking_eta;
            }

            public boolean isDrop_location_request() {
                return drop_location_request;
            }

            public void setDrop_location_request(boolean drop_location_request) {
                this.drop_location_request = drop_location_request;
            }

            public boolean isChange_payment_method() {
                return change_payment_method;
            }

            public void setChange_payment_method(boolean change_payment_method) {
                this.change_payment_method = change_payment_method;
            }

            public boolean isDrop_outside_area() {
                return drop_outside_area;
            }

            public void setDrop_outside_area(boolean drop_outside_area) {
                this.drop_outside_area = drop_outside_area;
            }

            public Object getUser_request_timeout() {
                return user_request_timeout;
            }

            public void setUser_request_timeout(Object user_request_timeout) {
                this.user_request_timeout = user_request_timeout;
            }

            public static class NormalBean {
                /**
                 * drop_location : {"ride_now":false,"ride_later":false}
                 */

                private DropLocationBean drop_location;

                public DropLocationBean getDrop_location() {
                    return drop_location;
                }

                public void setDrop_location(DropLocationBean drop_location) {
                    this.drop_location = drop_location;
                }

                public static class DropLocationBean {
                    /**
                     * ride_now : false
                     * ride_later : false
                     */

                    private boolean ride_now;
                    private boolean ride_later;

                    public boolean isRide_now() {
                        return ride_now;
                    }

                    public void setRide_now(boolean ride_now) {
                        this.ride_now = ride_now;
                    }

                    public boolean isRide_later() {
                        return ride_later;
                    }

                    public void setRide_later(boolean ride_later) {
                        this.ride_later = ride_later;
                    }
                }
            }

            public static class RentalBean {
                /**
                 * drop_location : {"ride_now":false,"ride_later":false}
                 */

                private DropLocationBeanX drop_location;

                public DropLocationBeanX getDrop_location() {
                    return drop_location;
                }

                public void setDrop_location(DropLocationBeanX drop_location) {
                    this.drop_location = drop_location;
                }

                public static class DropLocationBeanX {
                    /**
                     * ride_now : false
                     * ride_later : false
                     */

                    private boolean ride_now;
                    private boolean ride_later;

                    public boolean isRide_now() {
                        return ride_now;
                    }

                    public void setRide_now(boolean ride_now) {
                        this.ride_now = ride_now;
                    }

                    public boolean isRide_later() {
                        return ride_later;
                    }

                    public void setRide_later(boolean ride_later) {
                        this.ride_later = ride_later;
                    }
                }
            }
        }

        public static class GeneralConfigBean {
            /**
             * corporate_enable : false
             * additional_info : false
             * chat : false
             * googleKey : AIzaSyAhERO8P64JSxbyEwFDxW98r9nt9QpxlHM
             * favourite_driver_module : false
             * static_map : false
             * wallet : true
             * demo : false
             * homescreen_estimate_fare : false
             * default_language : en
             * card : true
             * splash_screen : Goviens
             * user_wallet_package : [{"amount":"10"},{"amount":"20"},{"amount":"30"}]
             * vehicle_rating_enable : false
             * security_question : false
             * security_questions : []
             * tip_enable : false
             * user_document : true
             * sur_charge : false
             * emergency_contact : false
             * show_logo_main : false
             * autocomplete_start : 0
             * baby_seat_enable : false
             * no_of_person : false
             * no_of_children : false
             * no_of_bags : false
             * wheel_chair_enable : false
             * family_member_enable : false
             * user_number_track_screen : false
             * no_of_pool_seats : []
             * user_cpf_number_enable : false
             * splash_screen_user : null
             * banner_image_user : null
             * otp_from_firebase : false
             * vehicle_ac_enable : false
             * network_code_visibility : false
             * referral_code_mandatory_user_signup : true
             * push_notification : 1
             */

            private boolean corporate_enable;
            private boolean additional_info;
            private boolean chat;
            private String googleKey;
            private boolean favourite_driver_module;
            private boolean static_map;
            private boolean wallet;
            private boolean demo;
            private boolean homescreen_estimate_fare;
            private String default_language;
            private boolean card;
            private String splash_screen;
            private boolean vehicle_rating_enable;
            private boolean security_question;
            private boolean tip_enable;
            private boolean user_document;
            private boolean sur_charge;
            private boolean emergency_contact;
            private boolean show_logo_main;
            private int autocomplete_start;
            private boolean baby_seat_enable;
            private boolean no_of_person;
            private boolean no_of_children;
            private boolean no_of_bags;
            private boolean wheel_chair_enable;
            private boolean family_member_enable;
            private boolean user_number_track_screen;
            private boolean user_cpf_number_enable;
            private Object splash_screen_user;
            private Object banner_image_user;
            private boolean otp_from_firebase;
            private boolean vehicle_ac_enable;
            private boolean network_code_visibility;
            private boolean referral_code_mandatory_user_signup;
            private int push_notification;
            private List<UserWalletPackageBean> user_wallet_package;
            private List<?> security_questions;
            private List<?> no_of_pool_seats;



            public boolean isCorporate_enable() {
                return corporate_enable;
            }

            public void setCorporate_enable(boolean corporate_enable) {
                this.corporate_enable = corporate_enable;
            }

            public boolean isAdditional_info() {
                return additional_info;
            }

            public void setAdditional_info(boolean additional_info) {
                this.additional_info = additional_info;
            }

            public boolean isChat() {
                return chat;
            }

            public void setChat(boolean chat) {
                this.chat = chat;
            }

            public String getGoogleKey() {
                return googleKey;
            }

            public void setGoogleKey(String googleKey) {
                this.googleKey = googleKey;
            }

            public boolean isFavourite_driver_module() {
                return favourite_driver_module;
            }

            public void setFavourite_driver_module(boolean favourite_driver_module) {
                this.favourite_driver_module = favourite_driver_module;
            }

            public boolean isStatic_map() {
                return static_map;
            }

            public void setStatic_map(boolean static_map) {
                this.static_map = static_map;
            }

            public boolean isWallet() {
                return wallet;
            }

            public void setWallet(boolean wallet) {
                this.wallet = wallet;
            }

            public boolean isDemo() {
                return demo;
            }

            public void setDemo(boolean demo) {
                this.demo = demo;
            }

            public boolean isHomescreen_estimate_fare() {
                return homescreen_estimate_fare;
            }

            public void setHomescreen_estimate_fare(boolean homescreen_estimate_fare) {
                this.homescreen_estimate_fare = homescreen_estimate_fare;
            }

            public String getDefault_language() {
                return default_language;
            }

            public void setDefault_language(String default_language) {
                this.default_language = default_language;
            }

            public boolean isCard() {
                return card;
            }

            public void setCard(boolean card) {
                this.card = card;
            }

            public String getSplash_screen() {
                return splash_screen;
            }

            public void setSplash_screen(String splash_screen) {
                this.splash_screen = splash_screen;
            }

            public boolean isVehicle_rating_enable() {
                return vehicle_rating_enable;
            }

            public void setVehicle_rating_enable(boolean vehicle_rating_enable) {
                this.vehicle_rating_enable = vehicle_rating_enable;
            }

            public boolean isSecurity_question() {
                return security_question;
            }

            public void setSecurity_question(boolean security_question) {
                this.security_question = security_question;
            }

            public boolean isTip_enable() {
                return tip_enable;
            }

            public void setTip_enable(boolean tip_enable) {
                this.tip_enable = tip_enable;
            }

            public boolean isUser_document() {
                return user_document;
            }

            public void setUser_document(boolean user_document) {
                this.user_document = user_document;
            }

            public boolean isSur_charge() {
                return sur_charge;
            }

            public void setSur_charge(boolean sur_charge) {
                this.sur_charge = sur_charge;
            }

            public boolean isEmergency_contact() {
                return emergency_contact;
            }

            public void setEmergency_contact(boolean emergency_contact) {
                this.emergency_contact = emergency_contact;
            }

            public boolean isShow_logo_main() {
                return show_logo_main;
            }

            public void setShow_logo_main(boolean show_logo_main) {
                this.show_logo_main = show_logo_main;
            }

            public int getAutocomplete_start() {
                return autocomplete_start;
            }

            public void setAutocomplete_start(int autocomplete_start) {
                this.autocomplete_start = autocomplete_start;
            }

            public boolean isBaby_seat_enable() {
                return baby_seat_enable;
            }

            public void setBaby_seat_enable(boolean baby_seat_enable) {
                this.baby_seat_enable = baby_seat_enable;
            }

            public boolean isNo_of_person() {
                return no_of_person;
            }

            public void setNo_of_person(boolean no_of_person) {
                this.no_of_person = no_of_person;
            }

            public boolean isNo_of_children() {
                return no_of_children;
            }

            public void setNo_of_children(boolean no_of_children) {
                this.no_of_children = no_of_children;
            }

            public boolean isNo_of_bags() {
                return no_of_bags;
            }

            public void setNo_of_bags(boolean no_of_bags) {
                this.no_of_bags = no_of_bags;
            }

            public boolean isWheel_chair_enable() {
                return wheel_chair_enable;
            }

            public void setWheel_chair_enable(boolean wheel_chair_enable) {
                this.wheel_chair_enable = wheel_chair_enable;
            }

            public boolean isFamily_member_enable() {
                return family_member_enable;
            }

            public void setFamily_member_enable(boolean family_member_enable) {
                this.family_member_enable = family_member_enable;
            }

            public boolean isUser_number_track_screen() {
                return user_number_track_screen;
            }

            public void setUser_number_track_screen(boolean user_number_track_screen) {
                this.user_number_track_screen = user_number_track_screen;
            }

            public boolean isUser_cpf_number_enable() {
                return user_cpf_number_enable;
            }

            public void setUser_cpf_number_enable(boolean user_cpf_number_enable) {
                this.user_cpf_number_enable = user_cpf_number_enable;
            }

            public Object getSplash_screen_user() {
                return splash_screen_user;
            }

            public void setSplash_screen_user(Object splash_screen_user) {
                this.splash_screen_user = splash_screen_user;
            }

            public Object getBanner_image_user() {
                return banner_image_user;
            }

            public void setBanner_image_user(Object banner_image_user) {
                this.banner_image_user = banner_image_user;
            }

            public boolean isOtp_from_firebase() {
                return otp_from_firebase;
            }

            public void setOtp_from_firebase(boolean otp_from_firebase) {
                this.otp_from_firebase = otp_from_firebase;
            }

            public boolean isVehicle_ac_enable() {
                return vehicle_ac_enable;
            }

            public void setVehicle_ac_enable(boolean vehicle_ac_enable) {
                this.vehicle_ac_enable = vehicle_ac_enable;
            }

            public boolean isNetwork_code_visibility() {
                return network_code_visibility;
            }

            public void setNetwork_code_visibility(boolean network_code_visibility) {
                this.network_code_visibility = network_code_visibility;
            }

            public boolean isReferral_code_mandatory_user_signup() {
                return referral_code_mandatory_user_signup;
            }

            public void setReferral_code_mandatory_user_signup(boolean referral_code_mandatory_user_signup) {
                this.referral_code_mandatory_user_signup = referral_code_mandatory_user_signup;
            }

            public int getPush_notification() {
                return push_notification;
            }

            public void setPush_notification(int push_notification) {
                this.push_notification = push_notification;
            }

            public List<UserWalletPackageBean> getUser_wallet_package() {
                return user_wallet_package;
            }

            public void setUser_wallet_package(List<UserWalletPackageBean> user_wallet_package) {
                this.user_wallet_package = user_wallet_package;
            }

            public List<?> getSecurity_questions() {
                return security_questions;
            }

            public void setSecurity_questions(List<?> security_questions) {
                this.security_questions = security_questions;
            }

            public List<?> getNo_of_pool_seats() {
                return no_of_pool_seats;
            }

            public void setNo_of_pool_seats(List<?> no_of_pool_seats) {
                this.no_of_pool_seats = no_of_pool_seats;
            }

            public static class UserWalletPackageBean {
                /**
                 * amount : 10
                 */

                private String amount;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }
            }
        }

        public static class LoginBean {
            /**
             * email : false
             * phone : true
             * otp : false
             */

            private boolean email;
            private boolean phone;
            private boolean otp;

            public boolean isEmail() {
                return email;
            }

            public void setEmail(boolean email) {
                this.email = email;
            }

            public boolean isPhone() {
                return phone;
            }

            public void setPhone(boolean phone) {
                this.phone = phone;
            }

            public boolean isOtp() {
                return otp;
            }

            public void setOtp(boolean otp) {
                this.otp = otp;
            }
        }

        public static class CustomerSupportBean {
            /**
             * mail : bhuvanesh@apporio.com
             * phone : +918005750736
             */

            private String mail;
            private String phone;

            public String getMail() {
                return mail;
            }

            public void setMail(String mail) {
                this.mail = mail;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class SocialBean {
            /**
             * enable : false
             * google : false
             * facebook : false
             */

            private boolean enable;
            private boolean google;
            private boolean facebook;

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public boolean isGoogle() {
                return google;
            }

            public void setGoogle(boolean google) {
                this.google = google;
            }

            public boolean isFacebook() {
                return facebook;
            }

            public void setFacebook(boolean facebook) {
                this.facebook = facebook;
            }
        }

        public static class ThemeCofigBean {
            /**
             * primary_color_user : null
             * chat_button_color : null
             * share_button_color : null
             * call_button_color : null
             * cancel_button_color : null
             */

            private Object primary_color_user;
            private Object chat_button_color;
            private Object share_button_color;
            private Object call_button_color;
            private Object cancel_button_color;

            public Object getPrimary_color_user() {
                return primary_color_user;
            }

            public void setPrimary_color_user(Object primary_color_user) {
                this.primary_color_user = primary_color_user;
            }

            public Object getChat_button_color() {
                return chat_button_color;
            }

            public void setChat_button_color(Object chat_button_color) {
                this.chat_button_color = chat_button_color;
            }

            public Object getShare_button_color() {
                return share_button_color;
            }

            public void setShare_button_color(Object share_button_color) {
                this.share_button_color = share_button_color;
            }

            public Object getCall_button_color() {
                return call_button_color;
            }

            public void setCall_button_color(Object call_button_color) {
                this.call_button_color = call_button_color;
            }

            public Object getCancel_button_color() {
                return cancel_button_color;
            }

            public void setCancel_button_color(Object cancel_button_color) {
                this.cancel_button_color = cancel_button_color;
            }
        }

        public static class AdditionalInformationBean {
            /**
             * parameter_name : Temperature
             * display : true
             */

            private String parameter_name;
            private boolean display;

            public String getParameter_name() {
                return parameter_name;
            }

            public void setParameter_name(String parameter_name) {
                this.parameter_name = parameter_name;
            }

            public boolean isDisplay() {
                return display;
            }

            public void setDisplay(boolean display) {
                this.display = display;
            }
        }

        public static class LanguagesBean {
            /**
             * id : 1
             * name : English
             * locale : en
             * created_at : 2020-03-23 16:08:49
             * updated_at : 2020-03-23 16:08:49
             */

            private int id;
            private String name;
            private String locale;
            private String created_at;
            private String updated_at;

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

            public String getLocale() {
                return locale;
            }

            public void setLocale(String locale) {
                this.locale = locale;
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

        public static class CountriesBean {
            /**
             * id : 10
             * merchant_id : 8
             * sequance : 102
             * country_code : IN
             * isoCode : INR
             * phonecode : +91
             * distance_unit : 1
             * default_language :
             * maxNumPhone : 12
             * minNumPhone : 8
             * transaction_code : Branch Code
             * additional_details : 0
             * parameter_name : null
             * placeholder : null
             * country_status : 1
             * created_at : 2020-08-05 05:38:49
             * updated_at : 2020-08-06 13:22:46
             * short_code : null
             * name : India
             * currency : INR
             */

            private int id;
            private int merchant_id;
            private int sequance;
            private String country_code;
            private String isoCode;
            private String phonecode;
            private int distance_unit;
            private String default_language;
            private int maxNumPhone;
            private int minNumPhone;
            private String transaction_code;
            private int additional_details;
            private Object parameter_name;
            private Object placeholder;
            private int country_status;
            private String created_at;
            private String updated_at;
            private Object short_code;
            private String name;
            private String currency;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public int getSequance() {
                return sequance;
            }

            public void setSequance(int sequance) {
                this.sequance = sequance;
            }

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
            }

            public String getIsoCode() {
                return isoCode;
            }

            public void setIsoCode(String isoCode) {
                this.isoCode = isoCode;
            }

            public String getPhonecode() {
                return phonecode;
            }

            public void setPhonecode(String phonecode) {
                this.phonecode = phonecode;
            }

            public int getDistance_unit() {
                return distance_unit;
            }

            public void setDistance_unit(int distance_unit) {
                this.distance_unit = distance_unit;
            }

            public String getDefault_language() {
                return default_language;
            }

            public void setDefault_language(String default_language) {
                this.default_language = default_language;
            }

            public int getMaxNumPhone() {
                return maxNumPhone;
            }

            public void setMaxNumPhone(int maxNumPhone) {
                this.maxNumPhone = maxNumPhone;
            }

            public int getMinNumPhone() {
                return minNumPhone;
            }

            public void setMinNumPhone(int minNumPhone) {
                this.minNumPhone = minNumPhone;
            }

            public String getTransaction_code() {
                return transaction_code;
            }

            public void setTransaction_code(String transaction_code) {
                this.transaction_code = transaction_code;
            }

            public int getAdditional_details() {
                return additional_details;
            }

            public void setAdditional_details(int additional_details) {
                this.additional_details = additional_details;
            }

            public Object getParameter_name() {
                return parameter_name;
            }

            public void setParameter_name(Object parameter_name) {
                this.parameter_name = parameter_name;
            }

            public Object getPlaceholder() {
                return placeholder;
            }

            public void setPlaceholder(Object placeholder) {
                this.placeholder = placeholder;
            }

            public int getCountry_status() {
                return country_status;
            }

            public void setCountry_status(int country_status) {
                this.country_status = country_status;
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

            public Object getShort_code() {
                return short_code;
            }

            public void setShort_code(Object short_code) {
                this.short_code = short_code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }
        }

        public static class AccountTypesBean {
            /**
             * id : 3
             * title : Saving Account
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
