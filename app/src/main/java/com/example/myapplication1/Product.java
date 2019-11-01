package com.example.myapplication1;

public class Product {
    private String name;
    private int price;
    private int count;
    private boolean bought;

    public Product(String name, int price, int count, boolean bought) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.bought = bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
