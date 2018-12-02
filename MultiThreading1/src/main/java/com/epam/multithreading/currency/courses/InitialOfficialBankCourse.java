package com.epam.multithreading.currency.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class InitialOfficialBankCourse {
    private final static Logger LOGGER = LogManager.getLogger(InitialOfficialBankCourse.class);

    public String getCourses(){
        URLConnection connection;
        Scanner in = null;
        try {
            URL jsonString = new URL("https://belarusbank.by/api/kursExchange?city=Минск");
            connection = jsonString.openConnection();
            in = new Scanner(new InputStreamReader(connection.getInputStream()));
        }catch (IOException exception){
            LOGGER.warn("Can't get official courses from bank, no internet connection",exception);
        }
        String result = "";

        while (in.hasNextLine()) {
            result += in.nextLine();
        }
        in.close();
        return result;
    }
}
