package com.epam.multithreading.parser;

import com.epam.multithreading.broker.Broker;
import com.epam.multithreading.broker.TypeOfCurrency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.List;
import static com.epam.multithreading.broker.TypeOfCurrency.EUR;
import static com.epam.multithreading.broker.TypeOfCurrency.USD;

public class DataParser {
    private final static Logger LOGGER = LogManager.getLogger(DataParser.class);
    public List<Broker> parse(String jsonString){
        List<Broker> brokerList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject newJsonObject = (JSONObject) jsonArray.get(i);
                String strToParse = String.valueOf(newJsonObject);
                JSONObject newBroker = (JSONObject) parser.parse(strToParse);
                brokerList.add(makeBroker(newBroker));
            }
        }catch (ParseException exception){
            LOGGER.error("Exception was meet during parsing JSON file with initial data",exception);
        }
        return brokerList;
    }

    private Broker makeBroker(JSONObject object){
        TypeOfCurrency currency = object.get("currency").equals("USD")? USD : EUR;
        double amountMoneyBel = (double) object.get("amountBel");
        double amountMoneyExt = (double) object.get("amountExt");
        return new Broker(amountMoneyBel, amountMoneyExt, currency);
    }
}
