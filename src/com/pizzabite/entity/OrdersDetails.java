package com.pizzabite.entity;

import java.util.Date;

public class OrdersDetails {

    private int orderId;
    private Date orderTime;
    private String status;
    private User customer;
    private PizzaItem pizzaItem;
    private PizzaPricing pizzaPricing;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public PizzaItem getPizzaItem() {
        return pizzaItem;
    }

    public void setPizzaItem(PizzaItem pizzaItem) {
        this.pizzaItem = pizzaItem;
    }

    public PizzaPricing getPizzaPricing() {
        return pizzaPricing;
    }

    public void setPizzaPricing(PizzaPricing pizzaPricing) {
        this.pizzaPricing = pizzaPricing;
    }

    @Override
    public String toString() {
        return "OrdersDetails [orderId=" + orderId + ", orderTime=" + orderTime + ", status=" + status + ", customer="
                + customer + ", pizzaItem=" + pizzaItem + ", pizzaPricing=" + pizzaPricing + "]";
    }

}
