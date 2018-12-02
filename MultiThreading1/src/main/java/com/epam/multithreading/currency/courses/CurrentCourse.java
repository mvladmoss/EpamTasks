package com.epam.multithreading.currency.courses;

import com.epam.multithreading.broker.TypeOfCurrency;
import com.epam.multithreading.market.StockMarket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import com.epam.multithreading.parser.CurrencyParser;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;
import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;

public class CurrentCourse {
    private final static Logger LOGGER = LogManager.getLogger(CurrentCourse.class);

    private static CurrentCourse instance;
    private static double usdBuy;
    private static double usdSell;
    private static double eurBuy;
    private static double eurSell;
    private static InitialOfficialBankCourse initialOfficialBankCourse;
    private static CurrencyParser parser = new CurrencyParser();
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static Lock locker = new ReentrantLock();

    private static void init(){
        initialOfficialBankCourse = new InitialOfficialBankCourse();
        setInitialCourse();
    }

    public static CurrentCourse getInstance() {
        if (!initialized.get()) {
            try {
                locker.lock();
                if (!initialized.get()) {
                    final CurrentCourse local = new CurrentCourse();
                    init();
                    instance = local;
                    initialized.set(true);
                }
            }finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public double getCourse(TypeOfCurrency currency, TypeOfOperation operation){
        double currentCourse = 0;
        switch (currency){
            case EUR:{
                currentCourse = operation==BUY? eurBuy:eurSell;
                break;
            }
            case USD:{
                currentCourse = operation==BUY? usdBuy:usdSell;
                break;
            }
        }
        return currentCourse;
    }

    public void setCourse(double newCourse, TypeOfCurrency currency, TypeOfOperation operation){
        switch (currency){
            case EUR:{
                if(operation==BUY){
                    eurBuy = newCourse;
                }
                else if(operation==SELL){
                    eurSell = newCourse;
                }
                break;
            }
            case USD:{
                if(operation==BUY){
                    usdBuy = newCourse;
                }
                else if(operation==SELL){
                    usdSell = newCourse;
                }
                break;
            }
        }
    }

    private static void setInitialCourse() {
        LOGGER.info("Getting official currency courses from BelarusBank");
        String allCorces = initialOfficialBankCourse.getCourses();
        eurBuy = parser.parse(allCorces, EUR.toString() + "_out");
        eurSell = parser.parse(allCorces, EUR.toString() + "_in");
        usdBuy = parser.parse(allCorces, USD.toString() + "_out");
        usdSell = parser.parse(allCorces, USD.toString() + "_in");
    }


}
