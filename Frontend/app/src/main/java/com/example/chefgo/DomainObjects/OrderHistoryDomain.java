package com.example.chefgo.DomainObjects;

import java.util.Date;

public class OrderHistoryDomain {

        private String customer;
        private Double price;
        private Date orderDate;
        private Integer oid;
        private String dishName;
        private Integer isActive;
        private String chef;

        public OrderHistoryDomain() {
        }

        public OrderHistoryDomain(Integer oid, Date orderDate, Double price, String chef, String dishName, String customer) {
            this.price = price;
            this.oid = oid;
            this.orderDate = orderDate;
            this.dishName = dishName;
            this.chef = chef;
            this.customer = customer;
            this.isActive = 1;
        }



        /**
         * gets id of order
         * @return id of order
         */
        public Integer getoid() {
            return this.oid;
        }
        /**
         * sets id of order
         * @param oid id of order
         */
        public void setOid(Integer oid) {
            this.oid = oid;
        }
        /**
         * gets price of order
         * @return price of order
         */
        public Double getPrice() {
            return this.price;
        }
        /**
         * sets price of order
         * @param price new price of order
         */
        public void setPrice(Double price) {
            this.price = price;
        }
        /**
         * gets dish name of order
         * @return dish name of order
         */
        public String getDish() {
            return this.dishName;
        }
        /**
         * sets new dish name to dish
         * @param dish new dish name
         */
        public void setDish(String dish) {
            this.dishName= dish;
        }
        /**
         * gets date of order
         * @return date of order
         */
        public Date getDate() {
            return this.orderDate;
        }
        /**
         * sets date of order
         * @param date date of order
         */
        public void setDate(Date date) {
            this.orderDate = date;
        }
        /**
         * gets chef user
         * @return user
         */
        public String getChef() {
            if(this.chef != null) return this.chef;
            else return null;
        }
        /**
         * sets orders chef
         * @param chef user waiting to be assigned
         */
        public void setChef(String chef) {
            this.chef = chef;
        }
        /**
         * gets orders customer
         * @return customer
         */
        public String getCustomer() {
            if(this.customer != null) return this.customer;
            else return null;

        }
        /**
         * sets orders customer field
         * @param customer user
         */
        public void setCustomer(String customer) {
            this.customer = customer;
        }
        /**
         * gets status of order
         * @return status  1 being active 0 being inactive
         */
        public Integer getIsActive() {
            return this.isActive;
        }
        /**
         * sets status of order
         * @param isActive status of order 1 being active 0 being inactive
         */
        public void setActive(Integer isActive) {
            this.isActive = isActive;
        }

}
