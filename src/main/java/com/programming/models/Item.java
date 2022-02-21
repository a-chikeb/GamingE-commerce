package com.programming.models;


//Class for view
public class Item {

    public int id;

    public String productName;

    public String color;

    public String productImage;

    public int productPrice;

    public int subTotal;

    public int quantity;
    public int productId;

    public Item(int id, String productName, String color, String productImage, int productPrice, int subTotal, int quantity, int productId) {
        this.id = id;
        this.productName = productName;
        this.color = color;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.subTotal = subTotal;
        this.quantity = quantity;
        this.productId = productId;
    }
}
