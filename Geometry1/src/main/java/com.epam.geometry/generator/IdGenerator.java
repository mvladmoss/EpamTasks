package com.epam.geometry.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdGenerator {

    private static long counter = 1;

    public static long getId() {
        return counter++;
    }
}
