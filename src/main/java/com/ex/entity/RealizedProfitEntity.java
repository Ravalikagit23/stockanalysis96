package com.ex.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RealizedProfit_Table")
public class RealizedProfitEntity {
    @Id
    private String symbol;
    private String stockName;
    private Double profit;

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

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {

        this.profit = profit;
    }
}




