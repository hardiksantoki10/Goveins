package com.goviens.android.models;

import java.util.List;

public class ModelStepOne {

    /**
     * version : 2.70
     * result : 1
     * message : CheckOut Created Successfully
     * data : {"offer_ride_checkout_id":3,"drop_points_details":[{"drop_no":1,"from_location":"604, Sispal Vihar Internal Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122002, India","to_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":8},{"drop_no":2,"from_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","to_location":"627,6th Floor, Tower A, Spaze iTechPark, Sohna Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":4},{"drop_no":3,"from_location":"627,6th Floor, Tower A, Spaze iTechPark, Sohna Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","to_location":"San Felipe Marg, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":12},{"drop_no":4,"from_location":"San Felipe Marg, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","to_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":4}],"map_image":"https://maps.googleapis.com/maps/api/staticmap?center=&maptype=roadmap&path=color:0x000000%7Cweight:10%7Cenc:ezllDkjfuMy@PiCd@qCh@MTKn@Wz@IRYNTz@v@LhA\\|Dw@OuBhDq@PIj@}@FQ?MCGICK@aANwAXYVM^Q\\CRBRDl@FbAr@MtGqAl@KBZmB^mPbDy@NwKxBcB^cKnBeDp@Ko@`HwAdB_@XLzE{@bI_B|@m@r@[HIHW]gAaBsGk@yBNEPl@^~AvA~FTz@v@LhA\\|Dw@OuBAWJURm@DKJI&markers=color:green%7Clabel:P%7C28.41524108390677,77.04252015799284&markers=color:red%7Clabel:D%7C28.414682574529856,77.04185061156751|28.415023460223136,77.04232905060053|28.41787199500538,77.04253491014242&markers=color:red%7Clabel:D%7C28.41547993865448,77.04203367233276&key=AIzaSyAhERO8P64JSxbyEwFDxW98r9nt9QpxlHM"}
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
         * offer_ride_checkout_id : 3
         * drop_points_details : [{"drop_no":1,"from_location":"604, Sispal Vihar Internal Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122002, India","to_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":8},{"drop_no":2,"from_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","to_location":"627,6th Floor, Tower A, Spaze iTechPark, Sohna Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":4},{"drop_no":3,"from_location":"627,6th Floor, Tower A, Spaze iTechPark, Sohna Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","to_location":"San Felipe Marg, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":12},{"drop_no":4,"from_location":"San Felipe Marg, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122018, India","to_location":"Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India","estimate_charges":4}]
         * map_image : https://maps.googleapis.com/maps/api/staticmap?center=&maptype=roadmap&path=color:0x000000%7Cweight:10%7Cenc:ezllDkjfuMy@PiCd@qCh@MTKn@Wz@IRYNTz@v@LhA\|Dw@OuBhDq@PIj@}@FQ?MCGICK@aANwAXYVM^Q\CRBRDl@FbAr@MtGqAl@KBZmB^mPbDy@NwKxBcB^cKnBeDp@Ko@`HwAdB_@XLzE{@bI_B|@m@r@[HIHW]gAaBsGk@yBNEPl@^~AvA~FTz@v@LhA\|Dw@OuBAWJURm@DKJI&markers=color:green%7Clabel:P%7C28.41524108390677,77.04252015799284&markers=color:red%7Clabel:D%7C28.414682574529856,77.04185061156751|28.415023460223136,77.04232905060053|28.41787199500538,77.04253491014242&markers=color:red%7Clabel:D%7C28.41547993865448,77.04203367233276&key=AIzaSyAhERO8P64JSxbyEwFDxW98r9nt9QpxlHM
         */

        private int offer_ride_checkout_id;
        private String map_image;
        private String currency;
        private List<DropPointsDetailsBean> drop_points_details;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getOffer_ride_checkout_id() {
            return offer_ride_checkout_id;
        }

        public void setOffer_ride_checkout_id(int offer_ride_checkout_id) {
            this.offer_ride_checkout_id = offer_ride_checkout_id;
        }

        public String getMap_image() {
            return map_image;
        }

        public void setMap_image(String map_image) {
            this.map_image = map_image;
        }

        public List<DropPointsDetailsBean> getDrop_points_details() {
            return drop_points_details;
        }

        public void setDrop_points_details(List<DropPointsDetailsBean> drop_points_details) {
            this.drop_points_details = drop_points_details;
        }

        public static class DropPointsDetailsBean {
            /**
             * drop_no : 1
             * from_location : 604, Sispal Vihar Internal Rd, Block S, Sispal Vihar, Sector 49, Gurugram, Haryana 122002, India
             * to_location : Unnamed Road, Block S, Sector 49, Gurugram, Haryana 122018, India
             * estimate_charges : 8
             */

            private int drop_no;
            private String from_location;
            private String to_location;
            private int estimate_charges;

            public int getDrop_no() {
                return drop_no;
            }

            public void setDrop_no(int drop_no) {
                this.drop_no = drop_no;
            }

            public String getFrom_location() {
                return from_location;
            }

            public void setFrom_location(String from_location) {
                this.from_location = from_location;
            }

            public String getTo_location() {
                return to_location;
            }

            public void setTo_location(String to_location) {
                this.to_location = to_location;
            }

            public int getEstimate_charges() {
                return estimate_charges;
            }

            public void setEstimate_charges(int estimate_charges) {
                this.estimate_charges = estimate_charges;
            }
        }
    }
}
