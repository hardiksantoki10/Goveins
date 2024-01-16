package com.goviens.android.models;

import java.util.List;

public class ModelPayoutHistory {

    /**
     * version : 12.30
     * result : 1
     * message : CashOut Request User
     * data : [{"id":2,"amount":"XAF 100","cashout_status":"Pending","action_by":null,"transaction_id":null,"comment":null,"created_at":1616267433,"updated_at":1616267433},{"id":3,"amount":"XAF 150","cashout_status":"Success","action_by":"bank transfer","transaction_id":"233443","comment":"ban transfer","created_at":1616267529,"updated_at":1617424925},{"id":4,"amount":"XAF 100","cashout_status":"Pending","action_by":null,"transaction_id":null,"comment":null,"created_at":1616460999,"updated_at":1616460999},{"id":12,"amount":"XAF 200","cashout_status":"Pending","action_by":null,"transaction_id":null,"comment":null,"created_at":1617428671,"updated_at":1617428671},{"id":13,"amount":"XAF 59","cashout_status":"Pending","action_by":null,"transaction_id":null,"comment":null,"created_at":1617429269,"updated_at":1617429269},{"id":14,"amount":"XAF 87","cashout_status":"Pending","action_by":null,"transaction_id":null,"comment":null,"created_at":1617432911,"updated_at":1617432911}]
     */

    private String version;
    private String result;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * amount : XAF 100
         * cashout_status : Pending
         * action_by : null
         * transaction_id : null
         * comment : null
         * created_at : 1616267433
         * updated_at : 1616267433
         */

        private int id;
        private String amount;
        private String cashout_status;
        private Object action_by;
        private Object transaction_id;
        private Object comment;
        private int created_at;
        private int updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCashout_status() {
            return cashout_status;
        }

        public void setCashout_status(String cashout_status) {
            this.cashout_status = cashout_status;
        }

        public Object getAction_by() {
            return action_by;
        }

        public void setAction_by(Object action_by) {
            this.action_by = action_by;
        }

        public Object getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(Object transaction_id) {
            this.transaction_id = transaction_id;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }
    }
}
