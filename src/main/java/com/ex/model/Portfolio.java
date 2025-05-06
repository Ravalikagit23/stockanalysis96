package com.ex.model;

public class Portfolio {

    private String stockName;
    private String symbol;
    private double qty;
    private Double totalCost;

    private  Double currentPrice;



    public Portfolio(String stockName, String symbol, double qty, Double price,Double currentPrice) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.qty = qty;
        this.totalCost = price;
        this.currentPrice=currentPrice;
    }



    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Double getTotalCost() {

        return totalCost;
    }
    public Double getCurrentPrice() {

        return currentPrice;
    }

    public void setTotalCost(Double totalCost) {

        this.totalCost = totalCost;
    }

    public void setCurrentPrice(Double  currentPrice) {

        this.currentPrice = currentPrice;
    }


public Portfolio(){

}
}
