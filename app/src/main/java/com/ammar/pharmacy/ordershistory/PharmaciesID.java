package com.ammar.pharmacy.ordershistory;

public class PharmaciesID {
        String status;
        String _id;
        String id;

        public PharmaciesID(String status, String _id, String id) {
                this.status = status;
                this._id = _id;
                this.id = id;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public String get_id() {
                return _id;
        }

        public void set_id(String _id) {
                this._id = _id;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }
}
