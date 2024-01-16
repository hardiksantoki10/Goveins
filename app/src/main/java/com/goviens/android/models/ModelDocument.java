package com.goviens.android.models;

import java.util.List;

public class ModelDocument {

    /**
     * version : 2.30
     * result : 1
     * message : User Document List
     * data : {"personal_doc":[{"id":12,"expire_status":1,"document_mandatory":2,"document_number_required":1,"document_file":"","document_verification_status":"PENDING","document_status_int":0,"documentname":"License"}],"vehicle_doc":[]}
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
        private int upload_from_date;
        private List<PersonalDocBean> personal_doc;
        private List<VehicleDocBean> vehicle_doc;

        public int getUpload_from_date() {
            return upload_from_date;
        }

        public void setUpload_from_date(int upload_from_date) {
            this.upload_from_date = upload_from_date;
        }

        public List<PersonalDocBean> getPersonal_doc() {
            return personal_doc;
        }

        public void setPersonal_doc(List<PersonalDocBean> personal_doc) {
            this.personal_doc = personal_doc;
        }

        public List<VehicleDocBean> getVehicle_doc() {
            return vehicle_doc;
        }

        public void setVehicle_doc(List<VehicleDocBean> vehicle_doc) {
            this.vehicle_doc = vehicle_doc;
        }

        public static class PersonalDocBean {
            /**
             * id : 12
             * expire_status : 1
             * document_mandatory : 2
             * document_number_required : 1
             * document_file :
             * document_verification_status : PENDING
             * document_status_int : 0
             * documentname : License
             */

            private int id;
            private int expire_status;
            private int document_mandatory;
            private int document_number_required;
            private String document_file;
            private String document_verification_status;
            private int document_status_int;
            private String documentname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getExpire_status() {
                return expire_status;
            }

            public void setExpire_status(int expire_status) {
                this.expire_status = expire_status;
            }

            public int getDocument_mandatory() {
                return document_mandatory;
            }

            public void setDocument_mandatory(int document_mandatory) {
                this.document_mandatory = document_mandatory;
            }

            public int getDocument_number_required() {
                return document_number_required;
            }

            public void setDocument_number_required(int document_number_required) {
                this.document_number_required = document_number_required;
            }

            public String getDocument_file() {
                return document_file;
            }

            public void setDocument_file(String document_file) {
                this.document_file = document_file;
            }

            public String getDocument_verification_status() {
                return document_verification_status;
            }

            public void setDocument_verification_status(String document_verification_status) {
                this.document_verification_status = document_verification_status;
            }

            public int getDocument_status_int() {
                return document_status_int;
            }

            public void setDocument_status_int(int document_status_int) {
                this.document_status_int = document_status_int;
            }

            public String getDocumentname() {
                return documentname;
            }

            public void setDocumentname(String documentname) {
                this.documentname = documentname;
            }
        }
        public static class VehicleDocBean {
            /**
             * vehicle_id : 5
             * vehicle_type : Sedan
             * vehicle_type_image : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/goviens/user-vehicle-document/1596615112_5f2a69c8d3629_vehicle.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201106T131026Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=9bcb8491f7b57823c5a8ed11e37c447508b21791554b896b55236304833469d5
             * vehicle_number : abc
             * vehicle_status : PENDING_WITH_DOCUMENT
             * document_list : [{"id":12,"expire_status":1,"document_mandatory":2,"document_number_required":1,"document_file":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/static-images/stub_document.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201106T131026Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=e5d7ea220c071f71b87685e5b2308e35707f4e69e08434792724b1c674535a2f","documentname":"License","document_verification_status":"PENDING","document_status_int":0},{"id":13,"expire_status":2,"document_mandatory":1,"document_number_required":1,"document_file":"https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/static-images/stub_document.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201106T131026Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=e5d7ea220c071f71b87685e5b2308e35707f4e69e08434792724b1c674535a2f","documentname":"ID","document_verification_status":"PENDING","document_status_int":0}]
             */

            private int vehicle_id;
            private String vehicle_type;
            private String vehicle_type_image;
            private String vehicle_number;
            private String vehicle_status;
            private List<DocumentListBean> document_list;

            public int getVehicle_id() {
                return vehicle_id;
            }

            public void setVehicle_id(int vehicle_id) {
                this.vehicle_id = vehicle_id;
            }

            public String getVehicle_type() {
                return vehicle_type;
            }

            public void setVehicle_type(String vehicle_type) {
                this.vehicle_type = vehicle_type;
            }

            public String getVehicle_type_image() {
                return vehicle_type_image;
            }

            public void setVehicle_type_image(String vehicle_type_image) {
                this.vehicle_type_image = vehicle_type_image;
            }

            public String getVehicle_number() {
                return vehicle_number;
            }

            public void setVehicle_number(String vehicle_number) {
                this.vehicle_number = vehicle_number;
            }

            public String getVehicle_status() {
                return vehicle_status;
            }

            public void setVehicle_status(String vehicle_status) {
                this.vehicle_status = vehicle_status;
            }

            public List<DocumentListBean> getDocument_list() {
                return document_list;
            }

            public void setDocument_list(List<DocumentListBean> document_list) {
                this.document_list = document_list;
            }

            public static class DocumentListBean {
                /**
                 * id : 12
                 * expire_status : 1
                 * document_mandatory : 2
                 * document_number_required : 1
                 * document_file : https://apporiotaxi-bucket.s3.ap-south-1.amazonaws.com/static-images/stub_document.png?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWN3MC6WZIP6YA4V6%2F20201106%2Fap-south-1%2Fs3%2Faws4_request&X-Amz-Date=20201106T131026Z&X-Amz-SignedHeaders=host&X-Amz-Expires=600&X-Amz-Signature=e5d7ea220c071f71b87685e5b2308e35707f4e69e08434792724b1c674535a2f
                 * documentname : License
                 * document_verification_status : PENDING
                 * document_status_int : 0
                 */

                private int id;
                private int expire_status;
                private int document_mandatory;
                private int document_number_required;
                private String document_file;
                private String documentname;
                private String document_verification_status;
                private int document_status_int;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getExpire_status() {
                    return expire_status;
                }

                public void setExpire_status(int expire_status) {
                    this.expire_status = expire_status;
                }

                public int getDocument_mandatory() {
                    return document_mandatory;
                }

                public void setDocument_mandatory(int document_mandatory) {
                    this.document_mandatory = document_mandatory;
                }

                public int getDocument_number_required() {
                    return document_number_required;
                }

                public void setDocument_number_required(int document_number_required) {
                    this.document_number_required = document_number_required;
                }

                public String getDocument_file() {
                    return document_file;
                }

                public void setDocument_file(String document_file) {
                    this.document_file = document_file;
                }

                public String getDocumentname() {
                    return documentname;
                }

                public void setDocumentname(String documentname) {
                    this.documentname = documentname;
                }

                public String getDocument_verification_status() {
                    return document_verification_status;
                }

                public void setDocument_verification_status(String document_verification_status) {
                    this.document_verification_status = document_verification_status;
                }

                public int getDocument_status_int() {
                    return document_status_int;
                }

                public void setDocument_status_int(int document_status_int) {
                    this.document_status_int = document_status_int;
                }
            }
        }
    }
}
