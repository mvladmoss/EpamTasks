package com.epam.multithreading.exchange;

import com.epam.multithreading.broker.TypeOfCurrency;
import com.epam.multithreading.currency.courses.TypeOfOperation;

public interface Exchanger {
    double  exchange(double originalMoneyAmount, TypeOfCurrency currency, TypeOfOperation operation);
}
