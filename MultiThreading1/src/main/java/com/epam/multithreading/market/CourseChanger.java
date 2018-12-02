package com.epam.multithreading.market;

import com.epam.multithreading.currency.courses.CurrentCourse;
import com.epam.multithreading.utils.RandomGenerator;

import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;
import static com.epam.multithreading.currency.courses.TypeOfOperation.BUY;
import static com.epam.multithreading.currency.courses.TypeOfOperation.SELL;


public class CourseChanger {
    private static final CurrentCourse currencyCourses = new CurrentCourse();
    private RandomGenerator randomGenerator = new RandomGenerator();
    public void changeCourse() {
        double partToChangeUsd = getPartChange();
        double partToChangeEur = getPartChange();

        double originalCourseUsdBuy = currencyCourses.getCourse(USD, BUY);
        double originalCourseUsdSell = currencyCourses.getCourse(USD, SELL);
        double originalCourseEurBuy = currencyCourses.getCourse(EUR, BUY);
        double originalCourseEurSell = currencyCourses.getCourse(EUR, SELL);

        double newCourseUsdBuy = originalCourseUsdBuy * (1 + partToChangeUsd);
        currencyCourses.setCourse(newCourseUsdBuy, USD, BUY);

        double newCourseUsdSell = originalCourseUsdSell * (1 + partToChangeUsd);
        currencyCourses.setCourse(newCourseUsdSell, USD, SELL);

        double newCourseEurBuy = originalCourseEurBuy * (1 + partToChangeEur);
        currencyCourses.setCourse(newCourseEurBuy, EUR, BUY);

        double newCourseEurSell = originalCourseEurSell * (1 + partToChangeEur);
        currencyCourses.setCourse(newCourseEurSell, EUR, SELL);
    }

    private double getPartChange(){//help us to define on what percentage courses will be changed
        return (double)randomGenerator.getRandomNumber(-100,100)/1000;
    }
}
