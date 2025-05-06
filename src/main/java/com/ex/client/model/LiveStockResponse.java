package com.ex.client.model;

import com.ex.client.model.StockData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class LiveStockResponse  implements Serializable {
    @JsonProperty("type")
    private String type;
    @JsonProperty("data")
    private List<StockData> stockData;

    public LiveStockResponse(String type, List<StockData> stockData) {
        this.type = type;
        this.stockData = stockData;
    }

    public LiveStockResponse() {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StockData> getStockData() {
        return stockData;
    }

    public void setStockData(List<StockData> stockData) {
        this.stockData = stockData;
    }
}
