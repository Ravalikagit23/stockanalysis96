package com.ex.model;

import lombok.Data;

@Data
public class Trade {
    private String name;
    private String symbol;
    private String side;
    private String status;
    private double filled;
    private double totalQty;
    private Double price;
    private double avgPrice;
    private String timeInForce;
    private String placedTime;
    private String filledTime;

    public Trade(String name, String side, double price , double totalQty) {
        this.name = name;
        this.side = side;
        this.price = price;
        this.totalQty = totalQty;
    }

    public Trade() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFilled() {
        return filled;
    }

    public void setFilled(double filled) {
        this.filled = filled;
    }

    public double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(double totalQty) {
        this.totalQty = totalQty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(String placedTime) {
        this.placedTime = placedTime;
    }

    public String getFilledTime() {
        return filledTime;
    }

    public void setFilledTime(String filledTime) {
        this.filledTime = filledTime;
    }


}