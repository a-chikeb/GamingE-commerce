package com.programming.models;


//Class for view
public class Item {

    public int id;

    public String productName;

    public String color;

    public String productImage;

    public String productPrice;

    public String subTotal;

    public int quantity;

    public Item(String productName, String color, String productImage, String productPrice, String subTotal, int quantity) {
        this.productName = productName;
        this.color = color;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }
}
