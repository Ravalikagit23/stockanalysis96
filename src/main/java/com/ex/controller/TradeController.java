package com.ex.controller;

import com.ex.model.PortfolioResponse;
import com.ex.model.Trade;
import com.ex.service.TradeCsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/trades")
public class TradeController {


    private final TradeCsvService tradeCsvService;

    public TradeController(TradeCsvService tradeCsvService) {

        this.tradeCsvService = tradeCsvService;
    }

    @GetMapping("/read")
    public ResponseEntity<List<Trade>> getTradesFromCsv() {
        List<Trade> trades = tradeCsvService.readTradesFromCsv();
        return ResponseEntity.ok(trades);
    }

    @GetMapping("/qty")
    public ResponseEntity<PortfolioResponse> getNetQuantity() {
        List<Trade>trades=tradeCsvService.readTradesFromCsv();
        PortfolioResponse result = tradeCsvService.calculateNetQuantityOfStocks(trades);
        return ResponseEntity.ok(result);
    }
//    @GetMapping()
//    public ResponseEntity<List<Portfolio>> getNetQuantityAsInList() {
//        List<TradesDTO>trades=tradeCsvService.readTradesFromCsv();
//      WrapperMapPortfolio wrapper=tradeCsvService.calculateNetQuantityOfStocks(trades);
//        List<Portfolio> result = tradeCsvService.mapToListConverter(wrapper);
//        return ResponseEntity.ok(result);
//    }
}

