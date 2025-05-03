package com.ex.model;

import java.util.List;

public class PortfolioResponse {
    private List<RealizedProfit> profit;
    private List<Portfolio> portfolio;


    public List<RealizedProfit> getProfit() {
        return profit;
    }

    public void setProfit(List<RealizedProfit> profit) {
        this.profit = profit;
    }

    public List<Portfolio> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<Portfolio> portfolio) {
        this.portfolio = portfolio;
    }




}
