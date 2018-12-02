package com.epam.multithreading.director;

import com.epam.multithreading.broker.Broker;
import com.epam.multithreading.market.StockMarket;
import com.epam.multithreading.parser.DataParser;
import com.epam.multithreading.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Director {

    private final static Logger LOGGER = LogManager.getLogger(Director.class);

    private static final String PATH = "src/main/resources/info.json";
    private static DataReader reader = new DataReader();
    private static DataParser parser = new DataParser();
    private static StockMarket stockMarket = StockMarket.getInstance();
    private static ExecutorService service;

    public static void main(String[] args) {
        //!!!ATTENTION
        //For successfully work you should provide internet connection.DataReader get official current courses from BelarusBank fromBelarusBank API through JSON
        String jsonString = reader.read(PATH);
        List<Broker> brokerList =  parser.parse(jsonString);
        initializateBrokers(brokerList);
        proccessStock(brokerList);
        timeToSleep(24);
        //provide stock to work for 24 hour(1 second of real time = 1hour)
        closeStock();

    }

    private static void initializateBrokers(List<Broker> brokerList){
        for(int i = 0; i < brokerList.size(); i++){
            Broker broker = brokerList.get(i);
            broker.setStockMarket(stockMarket);
        }
    }

    private static void proccessStock(List<Broker> brokerList){
        int numberOfBrokers = brokerList.size();
        service = Executors.newFixedThreadPool(numberOfBrokers+1);
        for(int i = 0; i < brokerList.size(); i++){
            service.execute(brokerList.get(i));
        }
        service.execute(stockMarket);
    }

    private static void timeToSleep(long time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    private static void closeStock(){
        stockMarket.closeStock();
        service.shutdown();
    }

}
