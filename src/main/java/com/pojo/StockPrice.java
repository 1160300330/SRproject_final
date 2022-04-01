package com.pojo;

public class StockPrice {
    private String id;
    private String name;
    private double price;
    private String ups_and_downs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUps_and_downs() {
        return ups_and_downs;
    }

    public void setUps_and_downs(String ups_and_downs) {
        this.ups_and_downs = ups_and_downs;
    }
}
