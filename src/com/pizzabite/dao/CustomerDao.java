package com.pizzabite.dao;

import com.pizzabite.entity.PizzaItem;
import com.pizzabite.entity.PizzaPricing;
import com.pizzabite.sql.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements AutoCloseable {

    private Connection connection;
    private List<PizzaPricing> cart;

    public CustomerDao() throws SQLException {
        connection = DbConnection.getConnection();
        cart = new ArrayList<>();
    }

    public List<PizzaItem> getVegItems() throws SQLException {
        List<PizzaItem> vegItems = new ArrayList<>();
        String sql = "SELECT * FROM PIZZA_ITEMS WHERE Type = 'Veg'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vegItems.add(new PizzaItem(rs.getInt("ID"), rs.getString("Name"), rs.getString("Type"), rs.getString("Category"), rs.getString("Description")));
            }
        }
        return vegItems;
    }

    public List<PizzaItem> getNonVegItems() throws SQLException {
        List<PizzaItem> nonVegItems = new ArrayList<>();
        String sql = "SELECT * FROM PIZZA_ITEMS WHERE Type = 'NonVeg'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                nonVegItems.add(new PizzaItem(rs.getInt("ID"), rs.getString("Name"), rs.getString("Type"), rs.getString("Category"), rs.getString("Description")));
            }
        }
        return nonVegItems;
    }

    public List<PizzaPricing> getAvailableSizes(int itemId) throws SQLException {
        List<PizzaPricing> sizes = new ArrayList<>();
        String sql = "SELECT * FROM PIZZA_PRICING WHERE ITEMID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sizes.add(new PizzaPricing(rs.getInt("ID"), rs.getInt("ITEMID"), rs.getString("SIZES"), rs.getDouble("Price")));
            }
        }
        return sizes;
    }

    public PizzaPricing getPizzaPricingById(int id) throws SQLException {
        String sql = "SELECT * FROM PIZZA_PRICING WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PizzaPricing(rs.getInt("ID"), rs.getInt("ITEMID"), rs.getString("SIZES"), rs.getDouble("Price"));
            }
        }
        return null;
    }

    

    public void addToCart(PizzaPricing pricing) {
        cart.add(pricing);
        System.out.println("Added to cart: " + pricing);
    }

    public void showCart() {
        double totalBill = 0;
        System.out.println("Cart Contents:");
        for (PizzaPricing pricing : cart) {
            System.out.println(pricing);
            totalBill += pricing.getPrice();
        }
        System.out.println("Total Bill: " + totalBill);
    }

    public void placeOrder(int customerId) throws SQLException {
        String orderSql = "INSERT INTO PIZZA_ORDERS (CustomerId, OrderTime, STATUS) VALUES (?, NOW(), 'Pending')";
        String orderDetailsSql = "INSERT INTO PIZZA_ORDERDETAILS (OrderId, PRICEID) VALUES (?, ?)";
        try (PreparedStatement orderStmt = connection.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            orderStmt.setInt(1, customerId);
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                try (PreparedStatement detailsStmt = connection.prepareStatement(orderDetailsSql)) {
                    for (PizzaPricing pricing : cart) {
                        detailsStmt.setInt(1, orderId);
                        detailsStmt.setInt(2, pricing.getId());
                        detailsStmt.executeUpdate();
                    }
                }
            }
            cart.clear();
            System.out.println("Order placed successfully!");
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
