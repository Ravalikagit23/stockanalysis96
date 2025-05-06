package com.ex.client;

import com.ex.client.model.LiveStockResponse;
import com.ex.client.model.StockData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.java_websocket.client.WebSocketClient;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.handshake.ServerHandshake;

@Service
public class FinHubWebSocketService {

    private WebSocketClient client;

   private  final Map<String, Double> livePricesMap = new ConcurrentHashMap<>();


    @PostConstruct  // Automatically runs after the bean is initialized
    public void startWebSocketClient() {
        try {
            client = new WebSocketClient(new URI("wss://ws.finnhub.io?token=d0cfcvhr01ql2j3cq1lgd0cfcvhr01ql2j3cq1m0")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("Connected to Finnhub WebSocket");
                    // Subscribe to a symbol
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"AAPL\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"META\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"GOOG\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"NVDA\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"SNOW\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"MSFT\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"ADT\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"GNW\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"RBLX\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"SHIBUSD\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"AMZN\"}");
                    client.send("{\"type\":\"subscribe\",\"symbol\":\"CRM\"}");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("Stock update: " + message);
                    // TODO: broadcast to frontend or save to DB
                    ObjectMapper mapper =new ObjectMapper();
                    try {
                        LiveStockResponse liveStockResponse = mapper.readValue(message, LiveStockResponse.class);
                        String type = liveStockResponse.getType();
                         List<StockData> stockDataList= liveStockResponse.getStockData();
                         for(StockData stockData: stockDataList){
                             livePricesMap.put(stockData.getSymbol(),stockData.getPrice());
                         }

                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket closed: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("WebSocket error: " + ex.getMessage());
                }
            };
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String, Double> getLivePricesMap() {
       return livePricesMap;

    }
}
