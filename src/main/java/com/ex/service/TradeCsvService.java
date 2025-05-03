package com.ex.service;

import com.ex.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeCsvService {

    @Value("${csv.file.path}")
    private String csvFilePath;

    public List<Trade> readTradesFromCsv() {
        List<Trade> trades = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            List<String> lines = new ArrayList<>();
            String line; //csvfile row
//            skip the Header br.lreadline ();
            String headerRow = br.readLine();
            String[] headerColumns = headerRow.split(",");
            if (headerColumns.length < 11) {
                throw new RuntimeException("Header should have 11 columns");
            }
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            for (int i = lines.size() - 1; i >= 0; i--) {
                String[] parts = lines.get(i).split(",");

                if (parts.length < 11) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                Trade trade = new Trade();
                trade.setName(parts[0]);
                trade.setSymbol(parts[1]);
                trade.setSide(parts[2]);
                trade.setStatus(parts[3]);
                trade.setFilled(Double.parseDouble(parts[4]));
                trade.setTotalQty(Double.parseDouble(parts[5]));
                trade.setPrice(Double.parseDouble(parts[6].replace("@", "")));
                trade.setAvgPrice(Double.parseDouble(parts[7]));
                trade.setTimeInForce(parts[8]);
                trade.setPlacedTime(parts[9]);
                trade.setFilledTime(parts[10]);

                trades.add(trade);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error reading CSV file", ex);
        }

        return trades;
    }

    public PortfolioResponse calculateNetQuantityOfStocks(List<Trade> trades) {
        Map<String, Portfolio> portfolioMap = new HashMap<>();//AAPL, prof
        Map<String, RealizedProfit> realizedProfitMap = new HashMap<>();
        for (Trade trade : trades) {
            String stockSymbol = trade.getSymbol();
            String type = trade.getSide();//buy
            Double price = trade.getPrice();//140
            double newPurchasedQuantity = trade.getTotalQty();//10
            //AAPL- (10, 1200)
            //AMZN- (5, 2000)

            Portfolio existingPortfolioValue = portfolioMap.getOrDefault(stockSymbol, new Portfolio(trade.getName(), trade.getSymbol(), 0, 0.0));
            if ("BUY".equalsIgnoreCase(type)) {
                existingPortfolioValue.setQty(existingPortfolioValue.getQty() + newPurchasedQuantity);//60 qty

//               newPurchasedQuantity*price
                double newPrice = newPurchasedQuantity * price;//500
                existingPortfolioValue.setPrice(existingPortfolioValue.getPrice() - newPrice);

            } else if ("SELL".equalsIgnoreCase(type)) {
                existingPortfolioValue.setQty(existingPortfolioValue.getQty() - newPurchasedQuantity);//0

                double newPrice = newPurchasedQuantity * price; //140*10=1400
                existingPortfolioValue.setPrice(existingPortfolioValue.getPrice() + newPrice);

            }
            if (existingPortfolioValue.getQty() == 0) {

                if (realizedProfitMap.containsKey(stockSymbol)) {
                    RealizedProfit exsitingProfit = realizedProfitMap.get(stockSymbol);
                    double newProfit = exsitingProfit.getProfit() + existingPortfolioValue.getPrice();
                    exsitingProfit.setProfit(newProfit);
                    realizedProfitMap.put(stockSymbol, exsitingProfit);
                    portfolioMap.remove(stockSymbol);
                } else {
                    RealizedProfit profit = realizedProfitMap.getOrDefault(stockSymbol, new RealizedProfit(trade.getName(), trade.getSymbol(), existingPortfolioValue.getPrice()));
                    realizedProfitMap.put(stockSymbol, profit);
                    portfolioMap.remove(stockSymbol);
                }

            } else {
                portfolioMap.put(stockSymbol, existingPortfolioValue);
            }

        }

        PortfolioResponse portfolio = convertMapToList(portfolioMap, realizedProfitMap);

        return portfolio;
    }

    public PortfolioResponse convertMapToList(Map<String, Portfolio> portfolioMap, Map<String, RealizedProfit> realizedProfitMap) {
        List<Portfolio> portfolios = new ArrayList<>();
        List<RealizedProfit> realizedProfitList = new ArrayList<>();
        for (Portfolio portfolio : portfolioMap.values()) {
            portfolios.add(portfolio);
        }
        for (RealizedProfit profit : realizedProfitMap.values()) {
            realizedProfitList.add(profit);
        }
        PortfolioResponse response = new PortfolioResponse();
        response.setPortfolio(portfolios);
        response.setProfit(realizedProfitList);
        return response;
    }
}

