package com.epam.multithreading.parser;

import com.epam.multithreading.broker.Broker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CurrencyParser {

    private final static Logger LOGGER = LogManager.getLogger(Broker.class);

    public double parse(String jsonString, String toFind) {
        JSONObject JsonObjectToReturn = null;
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            JSONObject temporyJsonObject = (JSONObject) jsonArray.get(0);
            String strToParse = String.valueOf(temporyJsonObject);
            Object objOfParse = parser.parse(strToParse);
            JsonObjectToReturn = (JSONObject) objOfParse;
        } catch (ParseException exception) {
            LOGGER.error("Exception was meet during parsing of official courses from BelarusBank", exception);
        }
        return Double.valueOf((String) JsonObjectToReturn.get(toFind));
    }
}
