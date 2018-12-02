package com.epam.multithreading.exchange;

import com.epam.multithreading.broker.TypeOfCurrency;
import com.epam.multithreading.currency.courses.CurrentCourse;
import com.epam.multithreading.currency.courses.TypeOfOperation;

import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;
import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;

public class CourseExchanger implements Exchanger{

    private CurrentCourse currentCourse;

    public CourseExchanger(CurrentCourse currentCourse) {
        this.currentCourse = currentCourse;
    }

    public double exchange(double originalMoneyAmount, TypeOfCurrency currency, TypeOfOperation operation) {
        double amountMoneyToReturn = 0;
        switch (currency) {
            case USD: {
                if(operation==BUY) {
                    amountMoneyToReturn = originalMoneyAmount / currentCourse.getCourse(USD,BUY);
                }
                else if(operation==SELL){
                    amountMoneyToReturn = originalMoneyAmount * currentCourse.getCourse(USD,SELL);
                }
                break;
            }
            case EUR: {
                if(operation==BUY) {
                    amountMoneyToReturn = originalMoneyAmount / currentCourse.getCourse(EUR,BUY);
                }
                else if(operation==SELL){
                    amountMoneyToReturn = originalMoneyAmount * currentCourse.getCourse(EUR,SELL);
                }
                break;
            }
        }
        return amountMoneyToReturn;
    }
}
