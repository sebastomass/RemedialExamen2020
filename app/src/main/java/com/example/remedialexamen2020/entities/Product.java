package com.example.remedialexamen2020.entities;

public class Product {

    public Product(String name, String quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public Product(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    private String name;
    private String quantity;


}
