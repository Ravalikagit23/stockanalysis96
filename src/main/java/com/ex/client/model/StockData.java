package com.ex.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StockData implements Serializable {

    @JsonProperty("s")
    private String symbol;
    @JsonProperty("c")
    private String[] c; ;
    @JsonProperty("p")
    private double Price;
    @JsonProperty("t")
    private String timeInForce;
    @JsonProperty("v")
    private Long  volume;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }



}
