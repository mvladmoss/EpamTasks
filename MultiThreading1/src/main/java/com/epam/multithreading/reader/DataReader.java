package com.epam.multithreading.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataReader {
    private final static Logger LOGGER = LogManager.getLogger(DataReader.class);
    public String read(String Path) {
        String resultStringJson = "";
        File file = new File(Path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            LOGGER.fatal("There is no such file on path:" + Path);
            throw  new RuntimeException("There is no such file on path:" + Path);
        }
        while(scanner.hasNextLine()){
            resultStringJson += (scanner.nextLine() + "\n");
        }
        return resultStringJson;
    }
}
