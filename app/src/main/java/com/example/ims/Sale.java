package com.example.ims;

public class Sale {

    private String medicineName;
    private int quantity;
    private double price;

    public Sale(String medicineName, int quantity, double price) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}