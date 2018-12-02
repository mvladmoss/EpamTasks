package com.epam.infohandling.service;


import com.epam.infohandling.interpreter.calculator.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionConverter{
    // search all number expressions
    private static final String NUMBER_EXPRESSION_REGEX = "\\[([\\w\\s+*-/&])*\\]";
    private static final Logger LOGGER = LogManager.getLogger(ExpressionConverter.class);

    public String replaceExpressionsByNumber(String text){
        List<String> mustReplace = getAllExpressions(text);
        List<String> willReplace = convertToNumbers(mustReplace);

        String result = text;
        for (int i = 0; i < mustReplace.size(); i++) {
            result = result.replace(mustReplace.get(i), willReplace.get(i));
        }

        LOGGER.info("All expressions in the text were replaced by numbers");

        return result;
    }

    private List<String> getAllExpressions(String text){
        List<String> expressions = new ArrayList<>();
        Pattern p = Pattern.compile(NUMBER_EXPRESSION_REGEX);
        Matcher matcher = p.matcher(text);

        while (matcher.find()){
            expressions.add(matcher.group());
        }

        return expressions;
    }
    private List<String> convertToNumbers(List<String> input){
        List<String> result = new ArrayList<>();


        for(String value : input){
            value = value.substring(1,value.length()-1);
            Calculator calculator = new Calculator();
            String toAdd = String.valueOf(calculator.calculate(value));
            result.add(String.valueOf(toAdd));
        }

        return result;
    }

}

