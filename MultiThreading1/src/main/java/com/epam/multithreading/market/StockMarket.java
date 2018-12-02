package com.epam.multithreading.market;

import com.epam.multithreading.broker.Broker;
import com.epam.multithreading.currency.courses.CurrentCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;
import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;

public class StockMarket extends Thread{

    private final static Logger LOGGER = LogManager.getLogger(StockMarket.class);

    private  static StockMarket instance;
    private static Lock locker = new ReentrantLock();
    private static Condition condition = locker.newCondition();
    private boolean work = true;
    private CourseChanger courseChanger = new CourseChanger();
    private static final CurrentCourse currencyCourses = CurrentCourse.getInstance();
    private static AtomicBoolean initializaed = new AtomicBoolean(false);
    private static AtomicBoolean inProccessOfChhangingCourse  = new AtomicBoolean(true);

    private StockMarket(){}



    public static StockMarket getInstance() {
        if (!initializaed.get()) {
            try {
                locker.lock();
                if (!initializaed.get()) {
                    final StockMarket local = new StockMarket();
                    instance = local;
                    initializaed.set(true);
                }
            }finally {
                locker.unlock();
            }
        }
        return instance;
    }

    @Override
    public void run() {
        LOGGER.info("Stock start to work");
        while(work){
            changeCourse();
            //wait some time for next changing of course
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                LOGGER.error("Thread was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        LOGGER.info("Stock  stop to work");

    }

    public void changeCourse() {
        locker.lock();
        try {
            inProccessOfChhangingCourse.set(true);
            courseChanger.changeCourse();
            LOGGER.info("Stock has changed currency courses");
            System.out.println(this);
            inProccessOfChhangingCourse.set(false);
            condition.signalAll();
        }finally{
            locker.unlock();
        }
    }

    public boolean isInProccessOfChangingCourse(){
        return inProccessOfChhangingCourse.get();
    }

    public boolean isWork(){
        return work;
    }

    public  void closeStock() {
        locker.lock();
        try{
            inProccessOfChhangingCourse.set(false);
            work = false;
            condition.signalAll();
        }finally {
            locker.unlock();
        }
    }

    public Condition getCondition(){
        return condition;
    }

    public Lock getLocker(){
        return locker;
    }

    @Override
    public String toString() {
        return "USD_BUY="+currencyCourses.getCourse(USD,BUY) + "\n"+
                "USD_SELL=" + currencyCourses.getCourse(USD,SELL) + "\n" +
                "EUR_BUY="+currencyCourses.getCourse(EUR,BUY) + "\n" +
                "EUR_SELL="+currencyCourses.getCourse(EUR,SELL) + "\n";
    }
}
