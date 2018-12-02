package com.epam.multithreading.utils;

import com.epam.multithreading.currency.courses.TypeOfOperation;

import java.util.Random;

public class RandomGenerator {
    Random random;

    public RandomGenerator(){
        random = new Random();
    }

    public int getRandomNumber(int min, int max) {//
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public TypeOfOperation nextOperation(){
        if(random.nextBoolean()){
            return TypeOfOperation.BUY;
        }
        else{
            return TypeOfOperation.SELL;
        }
    }

}
