package com.pizzabite.entity;

public class PizzaPricing {

    private int id;
    private int itemId;
    private String size;
    private double price;

    public PizzaPricing() {
        
    }

    public PizzaPricing(int id, int itemId, String size, double price) {
        this.id = id;
        this.itemId = itemId;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PizzaPricing [id=" + id + ", itemId=" + itemId + ", size=" + size + ", price=" + price + "]";
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    
    
}
