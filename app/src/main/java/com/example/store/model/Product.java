package com.example.store.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    // product info
    // tag for each variable (use @SerializedName("valueOfTag")), valueOfTag its from API.php
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("img")
    String img;

    // constructor (using generate"insert code")
    public Product(String name, String price, String img) {
        this.name = name;
        this.price = price;
        this.img = img;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }
}
