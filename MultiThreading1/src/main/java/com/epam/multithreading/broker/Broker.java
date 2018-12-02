package com.epam.multithreading.broker;
import com.epam.multithreading.currency.courses.CurrentCourse;
import com.epam.multithreading.currency.courses.TypeOfOperation;
import com.epam.multithreading.exchange.CourseExchanger;
import com.epam.multithreading.exchange.Exchanger;
import com.epam.multithreading.market.StockMarket;
import com.epam.multithreading.utils.RandomGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;

public class Broker extends Thread{
    private final static Logger LOGGER = LogManager.getLogger(Broker.class);

    private static int counter = 1;
    private final int id = counter++;
    private StockMarket stockMarket;
    private Condition marketCondition;
    private Lock marketLocker;
    private double amountOfMoneyBel;
    private double amountOfMoneyExt;
    private TypeOfCurrency typeOfCurrency;
    private String currencySign;
    private CurrentCourse currentCourse;
    private RandomGenerator randomGenerator;
    private Exchanger courseExchanger;

    public Broker(double amountOfMoneyBel, double amountOfMoneyExt,TypeOfCurrency typeOfCurrency){
        this.amountOfMoneyBel = amountOfMoneyBel;
        this.amountOfMoneyExt = amountOfMoneyExt;
        this.typeOfCurrency = typeOfCurrency;
        this.currencySign = typeOfCurrency.equals(TypeOfCurrency.USD)? "$" : "€";
        this.randomGenerator = new RandomGenerator();
        this.currentCourse = CurrentCourse.getInstance();
        this.courseExchanger = new CourseExchanger(currentCourse);
    }

    @Override
    public void run() {
        while (stockMarket.isWork()) {
            marketLocker.lock();
            try {
                LOGGER.info("Broker" + id + " wait while stock stop changing course");
                if (stockMarket.isInProccessOfChangingCourse()) {//wait until stock stop change the course
                    marketCondition.await();
                }
                LOGGER.info("Broker" + id + "start to make deal");
                makeDeal();
                System.out.println(this);//print new state of current broker
                LOGGER.info("new count of Broker" + id + "\n" + this);
            } catch (InterruptedException e) {
                LOGGER.error("Thread was interrupted", e);
                Thread.currentThread().interrupt();
            } finally {
                marketLocker.unlock();
            }

            try {
                long timeToSleep = randomGenerator.getRandomNumber(3,7);
                TimeUnit.SECONDS.sleep(timeToSleep);//get some rest
            } catch (InterruptedException e) {
                LOGGER.error("Thread was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void makeDeal(){
        TypeOfOperation nextOperation = randomGenerator.nextOperation();
        switch(nextOperation) {
            case SELL: {//define we will buy courses or sell our money
                double sumToDeal = amountOfMoneyExt * getPartOfSum();//Selling courses
                amountOfMoneyBel += courseExchanger.exchange(sumToDeal, typeOfCurrency, SELL);
                amountOfMoneyExt = amountOfMoneyExt - sumToDeal;
            }
            case BUY:{
                double sumToDeal = amountOfMoneyBel * getPartOfSum();//Buy courses
                amountOfMoneyExt += courseExchanger.exchange(sumToDeal, typeOfCurrency, BUY);
                amountOfMoneyBel -= sumToDeal;
            }
        }
    }


    private double getPartOfSum(){//help us to define what part of out money we would like to exchange
        return (double)randomGenerator.getRandomNumber(100,200)/1000;
    }

    public void setStockMarket(StockMarket stockMarket){
        this.stockMarket = stockMarket;
        this.marketCondition = stockMarket.getCondition();
        this.marketLocker = stockMarket.getLocker();
    }

    @Override
    public String toString() {
        return "Broker" + id + " Balance:" + "\n" + "BEL:" + amountOfMoneyBel  + "rub" + "\n" +  typeOfCurrency.toString() + ":" + amountOfMoneyExt + currencySign + "\n";
    }
}

