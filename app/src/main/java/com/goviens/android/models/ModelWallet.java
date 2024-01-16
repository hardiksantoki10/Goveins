package com.goviens.android.models;

import java.util.List;

public class ModelWallet {

    /**
     * version : 9.60
     * result : 1
     * message : Success
     * data : {"wallet_balance":"XAF 77670.00","hold_amount":"0.00","payout_details":[],"wallet_transaction":{"records":[{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612435116,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434886,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434859,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434778,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434284,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434249,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433867,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433696,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352611,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352329,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"}],"next_page_url":"https://delhitrial.apporioproducts.com/goviens/public/api/user/wallet/transaction?page=2","total_pages":3,"current_page":1},"hold_amount_details":[{"id":251,"status":1,"carpooling_ride_id":247,"amount":"XAF 60","hold_time":1611747731,"is_user_offer_ride":0},{"id":252,"status":1,"carpooling_ride_id":250,"amount":"XAF 5000","hold_time":1611752653,"is_user_offer_ride":0},{"id":254,"status":1,"carpooling_ride_id":248,"amount":"XAF 4000","hold_time":1611752995,"is_user_offer_ride":0},{"id":258,"status":1,"carpooling_ride_id":251,"amount":"XAF 10000","hold_time":1611754765,"is_user_offer_ride":0},{"id":262,"status":1,"carpooling_ride_id":252,"amount":"XAF 2000","hold_time":1611755043,"is_user_offer_ride":0},{"id":287,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263506,"is_user_offer_ride":0},{"id":288,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263606,"is_user_offer_ride":0},{"id":289,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263703,"is_user_offer_ride":0},{"id":292,"status":1,"carpooling_ride_id":272,"amount":"XAF 60","hold_time":1612356641,"is_user_offer_ride":0},{"id":300,"status":1,"carpooling_ride_id":274,"amount":"XAF 225","hold_time":1612418116,"is_user_offer_ride":0}]}
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
         * wallet_balance : XAF 77670.00
         * hold_amount : 0.00
         * payout_details : []
         * wallet_transaction : {"records":[{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612435116,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434886,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434859,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434778,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434284,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434249,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433867,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433696,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352611,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352329,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"}],"next_page_url":"https://delhitrial.apporioproducts.com/goviens/public/api/user/wallet/transaction?page=2","total_pages":3,"current_page":1}
         * hold_amount_details : [{"id":251,"status":1,"carpooling_ride_id":247,"amount":"XAF 60","hold_time":1611747731,"is_user_offer_ride":0},{"id":252,"status":1,"carpooling_ride_id":250,"amount":"XAF 5000","hold_time":1611752653,"is_user_offer_ride":0},{"id":254,"status":1,"carpooling_ride_id":248,"amount":"XAF 4000","hold_time":1611752995,"is_user_offer_ride":0},{"id":258,"status":1,"carpooling_ride_id":251,"amount":"XAF 10000","hold_time":1611754765,"is_user_offer_ride":0},{"id":262,"status":1,"carpooling_ride_id":252,"amount":"XAF 2000","hold_time":1611755043,"is_user_offer_ride":0},{"id":287,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263506,"is_user_offer_ride":0},{"id":288,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263606,"is_user_offer_ride":0},{"id":289,"status":1,"carpooling_ride_id":271,"amount":"XAF 500","hold_time":1612263703,"is_user_offer_ride":0},{"id":292,"status":1,"carpooling_ride_id":272,"amount":"XAF 60","hold_time":1612356641,"is_user_offer_ride":0},{"id":300,"status":1,"carpooling_ride_id":274,"amount":"XAF 225","hold_time":1612418116,"is_user_offer_ride":0}]
         */

        private String wallet_balance;
        private String hold_amount;
        private WalletTransactionBean wallet_transaction;
        private String currency;
        private List<?> payout_details;
        private List<HoldAmountDetailsBean> hold_amount_details;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getWallet_balance() {
            return wallet_balance;
        }

        public void setWallet_balance(String wallet_balance) {
            this.wallet_balance = wallet_balance;
        }

        public String getHold_amount() {
            return hold_amount;
        }

        public void setHold_amount(String hold_amount) {
            this.hold_amount = hold_amount;
        }

        public WalletTransactionBean getWallet_transaction() {
            return wallet_transaction;
        }

        public void setWallet_transaction(WalletTransactionBean wallet_transaction) {
            this.wallet_transaction = wallet_transaction;
        }

        public List<?> getPayout_details() {
            return payout_details;
        }

        public void setPayout_details(List<?> payout_details) {
            this.payout_details = payout_details;
        }

        public List<HoldAmountDetailsBean> getHold_amount_details() {
            return hold_amount_details;
        }

        public void setHold_amount_details(List<HoldAmountDetailsBean> hold_amount_details) {
            this.hold_amount_details = hold_amount_details;
        }

        public static class WalletTransactionBean {
            /**
             * records : [{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612435116,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434886,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434859,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434778,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434284,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612434249,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433867,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612433696,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352611,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"},{"transaction_name":"Money added in your wallet successfully","type":"Credit","amount":"XAF 25.00","date":1612352329,"value_color":"2ecc71","icon":"https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png"}]
             * next_page_url : https://delhitrial.apporioproducts.com/goviens/public/api/user/wallet/transaction?page=2
             * total_pages : 3
             * current_page : 1
             */

            private String next_page_url;
            private int total_pages;
            private int current_page;
            private List<RecordsBean> records;

            public String getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(String next_page_url) {
                this.next_page_url = next_page_url;
            }

            public int getTotal_pages() {
                return total_pages;
            }

            public void setTotal_pages(int total_pages) {
                this.total_pages = total_pages;
            }

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public List<RecordsBean> getRecords() {
                return records;
            }

            public void setRecords(List<RecordsBean> records) {
                this.records = records;
            }

            public static class RecordsBean {
                /**
                 * transaction_name : Money added in your wallet successfully
                 * type : Credit
                 * amount : XAF 25.00
                 * date : 1612435116
                 * value_color : 2ecc71
                 * icon : https://s3.ap-south-1.amazonaws.com/apporiotaxi-bucket/static-images/dollar1.png
                 */

                private String transaction_name;
                private String type;
                private String amount;
                private int date;
                private String value_color;
                private String icon;
                private String payment_request;
                private String display_payment;

                public String getDisplay_payment() {
                    return display_payment;
                }

                public void setDisplay_payment(String display_payment) {
                    this.display_payment = display_payment;
                }

                public String getPayment_request() {
                    return payment_request;
                }

                public void setPayment_request(String payment_request) {
                    this.payment_request = payment_request;
                }

                public String getTransaction_name() {
                    return transaction_name;
                }

                public void setTransaction_name(String transaction_name) {
                    this.transaction_name = transaction_name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public String getValue_color() {
                    return value_color;
                }

                public void setValue_color(String value_color) {
                    this.value_color = value_color;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }

        public static class HoldAmountDetailsBean {
            /**
             * id : 251
             * status : 1
             * carpooling_ride_id : 247
             * amount : XAF 60
             * hold_time : 1611747731
             * is_user_offer_ride : 0
             */

            private int id;
            private int status;
            private int carpooling_ride_id;
            private String amount;
            private int hold_time;
            private int is_user_offer_ride;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCarpooling_ride_id() {
                return carpooling_ride_id;
            }

            public void setCarpooling_ride_id(int carpooling_ride_id) {
                this.carpooling_ride_id = carpooling_ride_id;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getHold_time() {
                return hold_time;
            }

            public void setHold_time(int hold_time) {
                this.hold_time = hold_time;
            }

            public int getIs_user_offer_ride() {
                return is_user_offer_ride;
            }

            public void setIs_user_offer_ride(int is_user_offer_ride) {
                this.is_user_offer_ride = is_user_offer_ride;
            }
        }
    }
}
