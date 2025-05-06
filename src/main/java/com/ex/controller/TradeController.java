package com.ex.controller;

import com.ex.client.model.StockData;
import com.ex.model.Portfolio;
import com.ex.model.PortfolioResponse;
import com.ex.model.Trade;
import com.ex.client.FinHubWebSocketService;
import com.ex.service.TradeCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/trades")
public class TradeController {



    @Autowired
    private FinHubWebSocketService socketService;

    private final TradeCsvService tradeCsvService;

    public TradeController(TradeCsvService tradeCsvService) {

        this.tradeCsvService = tradeCsvService;
    }

    @Autowired
    private FinHubWebSocketService finnhubService;
    @GetMapping("/read")
    public ResponseEntity<List<Trade>> getTradesFromCsv() {
        List<Trade> trades = tradeCsvService.readTradesFromCsv();
        return ResponseEntity.ok(trades);
    }


    @GetMapping("/qty")
    public ResponseEntity<PortfolioResponse> getNetQuantity() {
        List<Trade> trades = tradeCsvService.readTradesFromCsv();
        PortfolioResponse result = tradeCsvService.calculateNetQuantityOfStocks(trades);
        Map<String, Double> livePricesMap = socketService.getLivePricesMap();
        List<Portfolio> portfolioList = result.getPortfolio();
        for (Portfolio portfolio : portfolioList) {
            if (livePricesMap.containsKey(portfolio.getSymbol())) {
                portfolio.setCurrentPrice(livePricesMap.get(portfolio.getSymbol()));
            }


    }
        return ResponseEntity.ok(result);
    }
}

