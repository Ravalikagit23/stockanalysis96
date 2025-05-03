package com.ex.service;

import com.ex.entity.PortfolioEntity;
import com.ex.entity.RealizedProfitEntity;
import com.ex.entity.TradeEntity;
import com.ex.model.Portfolio;
import com.ex.model.RealizedProfit;
import com.ex.model.Trade;
import com.ex.model.PortfolioResponse;
import com.ex.repo.PortfolioRepo;
import com.ex.repo.RealizedProfitRepo;
import com.ex.repo.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TradeScheduler {

    @Autowired
    private TradeCsvService tradeService;
    @Autowired
    private TradeRepository tradeRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private RealizedProfitRepo profitRepo;

    // Run every 5 minutes (for example)
    @Scheduled(fixedRate = 300000)
    public void importTradesFromCsv() {
        List<Trade> tradeList = tradeService.readTradesFromCsv();
        List<TradeEntity >tradeEntityList=convertToTradeEntityList(tradeList);
        tradeRepo.saveAll(tradeEntityList);
       savePortfolioAndProfit(tradeList);
        System.out.println("CSV import triggered by scheduler.");

    }
    public void savePortfolioAndProfit(List<Trade> tradeList) {
         PortfolioResponse response = tradeService.calculateNetQuantityOfStocks(tradeList);
        List<PortfolioEntity> portfolioEntityList = converToPortfolioEntityList(response.getPortfolio());
        List<RealizedProfitEntity>realizedProfitEntityList=converToRealizedEntityList(response.getProfit());
        profitRepo.saveAll(realizedProfitEntityList);
        portfolioRepo.saveAll(portfolioEntityList);

    }
    private  List<RealizedProfitEntity> converToRealizedEntityList(List<RealizedProfit> profitList){
        List<RealizedProfitEntity> listOfRealizedProfit = new ArrayList<>();
        for(RealizedProfit profit:profitList){
            RealizedProfitEntity listOfProfit=new RealizedProfitEntity();
           listOfProfit.setStockName(profit.getStockName());
           listOfProfit.setSymbol(profit.getSymbol());
           listOfProfit.setProfit(profit.getProfit());
           listOfRealizedProfit.add(listOfProfit);
        }

        return listOfRealizedProfit;

    }

    private  List<PortfolioEntity> converToPortfolioEntityList(List<Portfolio> portfolioList){
        List<PortfolioEntity> listOfPortfolio = new ArrayList<>();
        for(Portfolio portfolio1:portfolioList){
            PortfolioEntity newPortfolio=new PortfolioEntity();
            newPortfolio.setStockName(portfolio1.getStockName());
            newPortfolio.setSymbol(portfolio1.getSymbol());
            newPortfolio.setQty(portfolio1.getQty());
            newPortfolio.setPrice(portfolio1.getPrice());
            listOfPortfolio.add(newPortfolio);
        }

        return listOfPortfolio;

    }
    private static List<TradeEntity> convertToTradeEntityList(List<Trade> tradeList) {
        List<TradeEntity> listOfTrade = new ArrayList<>();

        for (Trade trade : tradeList) {
            TradeEntity newTrade  = new TradeEntity();
            newTrade.setName(trade.getName());
            newTrade.setSymbol(trade.getSymbol());
            newTrade.setSide(trade.getSide());
            newTrade.setStatus(trade.getStatus());
            newTrade.setFilled(trade.getFilled());
            newTrade.setTotalQty(trade.getTotalQty());
            newTrade.setPrice(trade.getPrice());
            newTrade.setAvgPrice(trade.getAvgPrice());
            newTrade.setTimeInForce(trade.getTimeInForce());
            newTrade.setPlacedTime(trade.getPlacedTime());
            newTrade.setFilledTime(trade.getFilledTime());

            listOfTrade.add(newTrade);
        }
        return  listOfTrade;
    }
}
