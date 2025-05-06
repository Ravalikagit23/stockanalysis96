package com.ex.service;

import com.ex.model.Portfolio;
import com.ex.model.RealizedProfit;
import com.ex.model.PortfolioResponse;
import com.ex.model.Trade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
 public class TradeCsvServiceTest {

    @InjectMocks
    TradeCsvService service;

//    @Mock
//    private SomeDependency mockDependency;

    @Test
    void calculateNetQuantityStocks() {

        Trade trade1 = new Trade("AAPL", "BUY", 100.0, 15);  // Buy 10 @ 100 1000but//600 400 5
        Trade trade2 = new Trade("AAPL", "SELL", 120.0, 5);   // Sell 5 @ 120
        Trade trade3 = new Trade("AAPL", "SELL", 100.0, 5);

        List<Trade> tradeList = List.of(trade1, trade2,trade3);
        PortfolioResponse response=service.calculateNetQuantityOfStocks(tradeList);

        Portfolio applePortfolio =response.getPortfolio().get(0);
        assertNotNull(applePortfolio);
        assertEquals(5, applePortfolio.getQty());
        assertEquals(-400.0, applePortfolio.getTotalCost()); // -1000 + 600


        List<RealizedProfit >profitPortfolio=response.getProfit();
        assertTrue(profitPortfolio.isEmpty());
        // -200 * 3

    }

    @Test
    void testNoTrades() {
        List<Trade> trades = new ArrayList<>();
        PortfolioResponse result = service.calculateNetQuantityOfStocks(trades);
        assertNotNull(result);
        assertTrue(result.getPortfolio().isEmpty());
    }

    @Test
    void testOnlyBuyTrades() {
        Trade trade1 = new Trade("AAPL", "BUY", 100.0, 10);  //1000-
        Trade trade2 = new Trade("AAPL", "BUY", 120.0, 5);  //600-

        List<Trade> tradeList = List.of(trade1, trade2);

        PortfolioResponse netQuantity = service.calculateNetQuantityOfStocks(tradeList);

        Portfolio applePortfolio = netQuantity.getPortfolio().get(0);
//        assertNotNull(applePortfolio);
        assertEquals(15, applePortfolio.getQty());//15
        assertEquals(-1600.0, applePortfolio.getTotalCost());//-1600

    }

    @Test
    void testOnlySellTrades() {
        Trade trade1 = new Trade("AAPL", "SELL", 100.0, 10);  //1000+
        Trade trade2 = new Trade("AAPL", "SELL", 120.0, 5);  //600+

        List<Trade> tradeList = List.of(trade1, trade2);
         PortfolioResponse netQuantity = service.calculateNetQuantityOfStocks(tradeList);

        Portfolio applePortfolio = netQuantity.getPortfolio().get(0);
//        assertNotNull(applePortfolio);
        assertEquals(-15, applePortfolio.getQty());//15
        assertEquals(+1600.0, applePortfolio.getTotalCost());//+1600

    }

//    @Test
//    void testReadTradesFromCSV() {
//        ReflectionTestUtils.setField(service, "csvFilePath", "C:\\Users\\rajug\\Downloads\\stocktrade\\stocktrade\\src\\test\\files\\trades.csv");
//        List<Trade> tradeList = service.readTradesFromCsv();
//        assertNotNull(tradeList);
//        assertEquals(3, tradeList.size());
//
//    }

    @Test
    void testReadNotFoundCSVFile() {
        ReflectionTestUtils.setField(service, "csvFilePath", "C:\\Users\\rajug\\Downloads\\stocktrade\\stocktrade\\src\\test\\files\\traders.csv");

        assertThrows(RuntimeException.class,()-> service.readTradesFromCsv());
    }

    @Test
    void testReadCSVFileRowCount() {
        ReflectionTestUtils.setField(service, "csvFilePath", "C:\\Users\\rajug\\Downloads\\stocktrade\\stocktrade\\src\\test\\files\\trading.csv");
//        SomeExecutable someExecutable= new SomeExecutable();
        assertThrows(RuntimeException.class, () -> service.readTradesFromCsv());

    }

}
