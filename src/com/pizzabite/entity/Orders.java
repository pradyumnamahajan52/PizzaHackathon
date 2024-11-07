package com.pizzabite.entity;

import java.util.Date;

public class Orders {

    int id;
    int customerId;
    Date orderTime;
    String Status;
    

    public Orders() {

    }

    public Orders(int id, int customerId, Date orderTime, String status) {
        this.id = id;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.Status = status;
    }

    @Override
    public String toString() {
        return "Orders [id=" + id + ", customerId=" + customerId + ", orderTime=" + orderTime + ", Status=" + Status
                + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
