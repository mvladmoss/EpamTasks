package com.epam.multithreading.exchange;

import com.epam.multithreading.currency.courses.CurrentCourse;
import com.epam.multithreading.market.CourseChanger;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;
import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CourseExchangeTest {
    private static CourseExchanger courseExchanger;



    @Test
    public void shouldReturnNewSumInSpecifiedCurrencyUsdBuy() {
        //given
        double originalMoneyAmount = 100;
        CurrentCourse currentCourse = mock(CurrentCourse.class);
        when(currentCourse.getCourse(USD, BUY)).thenReturn(2.1);
        courseExchanger = new CourseExchanger(currentCourse);
        double actualSumInSpecifiedCurrency = originalMoneyAmount/currentCourse.getCourse(USD,BUY);;
        //when
        double expectedSumInSpecifiedCurrency = courseExchanger.exchange(originalMoneyAmount,USD,BUY);
        //then
        Assertions.assertEquals(expectedSumInSpecifiedCurrency,actualSumInSpecifiedCurrency);
    }

    @Test
    public void shouldReturnNewSumInSpecifiedCurrencyEurSell() {
        //given
        double originalMoneyAmount = 100;
        CurrentCourse currentCourse = mock(CurrentCourse.class);
        when(currentCourse.getCourse(EUR,SELL)).thenReturn(2.15);
        courseExchanger = new CourseExchanger(currentCourse);
        double actualSumInSpecifiedCurrency = originalMoneyAmount*currentCourse.getCourse(EUR,SELL);;
        //when
        double expectedSumInSpecifiedCurrency = courseExchanger.exchange(originalMoneyAmount,EUR,SELL);
        //then
        Assertions.assertEquals(expectedSumInSpecifiedCurrency,actualSumInSpecifiedCurrency);
    }
}
