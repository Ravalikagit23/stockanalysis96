package com.ex.model;

public class RealizedProfit {

    private String stockName;
    private String symbol;
    private Double profit;

    public String getStockName() {
        return stockName;
    }

    public RealizedProfit(String stockName, String symbol, Double profit) {
        this.stockName = stockName;
        this.symbol = symbol;
        this.profit = profit;
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

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }








}
