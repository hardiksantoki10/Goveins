package com.goviens.android.models;

public class ModelPromoteNotification {

    /**
     * notification_type : PROMOTION_NOTIFICATION
     * segment_data : {"notification_type":1,"image":"https://goviens.s3.ca-central-1.amazonaws.com/goviens/promotions/1621087572_609fd55475bae_promotions.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVNV2J6VPWHLRY5Z7%2F20210515%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20210515T140612Z&X-Amz-SignedHeaders=host&X-Amz-Expires=36000&X-Amz-Signature=915c00e77a9c180443bc386e1126bc94559bb004d34ac3c4425ed77028ccae82","application":"2","updated_at":"2021-05-15 14:06:12","expiry_date":"2021-05-31","created_at":"2021-05-15 14:06:12","id":53,"merchant_id":8,"message":"hi","title":"test","show_promotion":1,"url":"https://www.google.com"}
     * notification_gen_time : 1621087572
     * segment_group_id : null
     * segment_sub_group : null
     * segment_type : NOTIFICATION
     */

    private String notification_type;
    private SegmentDataBean segment_data;
    private int notification_gen_time;
    private Object segment_group_id;
    private Object segment_sub_group;
    private String segment_type;

    public String getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public SegmentDataBean getSegment_data() {
        return segment_data;
    }

    public void setSegment_data(SegmentDataBean segment_data) {
        this.segment_data = segment_data;
    }

    public int getNotification_gen_time() {
        return notification_gen_time;
    }

    public void setNotification_gen_time(int notification_gen_time) {
        this.notification_gen_time = notification_gen_time;
    }

    public Object getSegment_group_id() {
        return segment_group_id;
    }

    public void setSegment_group_id(Object segment_group_id) {
        this.segment_group_id = segment_group_id;
    }

    public Object getSegment_sub_group() {
        return segment_sub_group;
    }

    public void setSegment_sub_group(Object segment_sub_group) {
        this.segment_sub_group = segment_sub_group;
    }

    public String getSegment_type() {
        return segment_type;
    }

    public void setSegment_type(String segment_type) {
        this.segment_type = segment_type;
    }

    public static class SegmentDataBean {
        /**
         * notification_type : 1
         * image : https://goviens.s3.ca-central-1.amazonaws.com/goviens/promotions/1621087572_609fd55475bae_promotions.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVNV2J6VPWHLRY5Z7%2F20210515%2Fca-central-1%2Fs3%2Faws4_request&X-Amz-Date=20210515T140612Z&X-Amz-SignedHeaders=host&X-Amz-Expires=36000&X-Amz-Signature=915c00e77a9c180443bc386e1126bc94559bb004d34ac3c4425ed77028ccae82
         * application : 2
         * updated_at : 2021-05-15 14:06:12
         * expiry_date : 2021-05-31
         * created_at : 2021-05-15 14:06:12
         * id : 53
         * merchant_id : 8
         * message : hi
         * title : test
         * show_promotion : 1
         * url : https://www.google.com
         */

        private int notification_type;
        private String image;
        private String application;
        private String updated_at;
        private String expiry_date;
        private String created_at;
        private int id;
        private int merchant_id;
        private String message;
        private String title;
        private int show_promotion;
        private String url;

        public int getNotification_type() {
            return notification_type;
        }

        public void setNotification_type(int notification_type) {
            this.notification_type = notification_type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getApplication() {
            return application;
        }

        public void setApplication(String application) {
            this.application = application;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

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

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getShow_promotion() {
            return show_promotion;
        }

        public void setShow_promotion(int show_promotion) {
            this.show_promotion = show_promotion;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
