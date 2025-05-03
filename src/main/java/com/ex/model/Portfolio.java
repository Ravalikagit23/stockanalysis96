package com.ex.model;

public class Portfolio {

    private String stockName;
    private String symbol;
    private double qty;
    private Double price;


    public Portfolio(String stockName, String symbol, double qty, Double price) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.qty = qty;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




public Portfolio(){

}
}
