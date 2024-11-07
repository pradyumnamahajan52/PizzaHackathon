package com.pizzabite.dao;

import com.pizzabite.entity.Orders;
import com.pizzabite.entity.OrdersDetails;
import com.pizzabite.entity.PizzaItem;
import com.pizzabite.entity.PizzaPricing;
import com.pizzabite.entity.User;
import com.pizzabite.sql.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDao implements AutoCloseable {

    private Connection connection;

    public AdminDao() throws SQLException {
        connection = DbConnection.getConnection();
    }

    public List<Orders> getPurchasedOrders() throws SQLException {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "SELECT * from pizza_orders";
        try (PreparedStatement selectStmt = connection.prepareStatement(sql)) {
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int customerId = rs.getInt("CustomerId");
                Date orderTime = rs.getDate("OrderTime");
                String status = rs.getString("STATUS");

                Orders order = new Orders(id, customerId, orderTime, status);
                ordersList.add(order);
            }

            return ordersList;
        }
    }

    public int updateOrderDispatched(int id, String status) throws SQLException {
        String sql = "UPDATE pizza_orders SET STATUS = ? WHERE ID = ? ";
        try (PreparedStatement updateStmt = connection.prepareStatement(sql)) {
            updateStmt.setString(1, status);
            updateStmt.setInt(2, id);
            int count = updateStmt.executeUpdate();
            return count;
        }
    }

    public OrdersDetails getOrderDetails(int orderId) throws SQLException {
        OrdersDetails orderDetails = new OrdersDetails();
        String orderSql = "SELECT o.CustomerId, o.OrderTime, o.STATUS, c.Name, c.Email, c.Address, c.Mobile, p.ID, p.Name as PizzaName, pp.SIZES, pp.Price " + "FROM PIZZA_ORDERS o " + "JOIN pizza_customers c ON o.CustomerId = c.ID " + "JOIN PIZZA_ORDERDETAILS od ON o.ID = od.OrderId " + "JOIN PIZZA_PRICING pp ON od.PRICEID = pp.ID " + "JOIN PIZZA_ITEMS p ON pp.ITEMID = p.ID " + "WHERE o.ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(orderSql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User customer = new User();
                customer.setId(rs.getInt("CustomerId"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setAddress(rs.getString("Address"));
                customer.setMobile(rs.getString("Mobile"));
                orderDetails.setCustomer(customer);
                orderDetails.setOrderId(orderId);
                orderDetails.setOrderTime(rs.getTimestamp("OrderTime"));
                orderDetails.setStatus(rs.getString("STATUS"));
                PizzaItem pizza = new PizzaItem();
                pizza.setId(rs.getInt("ID"));
                pizza.setName(rs.getString("PizzaName"));
                PizzaPricing pricing = new PizzaPricing();
                pricing.setSize(rs.getString("SIZES"));
                pricing.setPrice(rs.getDouble("Price"));
                orderDetails.setPizzaItem(pizza);
                orderDetails.setPizzaPricing(pricing);
            }
        }
        return orderDetails;
    }

    public int updateOrderDelivered(int id, String status) throws SQLException {
        String sql = "UPDATE pizza_orders SET STATUS = ? WHERE ID = ? ";
        try (PreparedStatement updateStmt = connection.prepareStatement(sql)) {
            updateStmt.setString(1, status);
            updateStmt.setInt(2, id);
            int count = updateStmt.executeUpdate();
            return count;
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
