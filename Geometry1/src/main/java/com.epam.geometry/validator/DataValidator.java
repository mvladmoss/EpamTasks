package com.epam.geometry.validator;

public class DataValidator implements Validator{

    private static final String REGEX_COORDINATES_DELIMETER = "((((-)?\\d+(.(\\d+))?[\\s]){2}((-)?\\d+([.](\\d)+)?))[,]){3}((((-)?\\d+(.(\\d+))?[\\s]){2}((-)?\\d+([.](\\d)+)?)))";

    public boolean validate(String toParse) {
       return toParse.matches(REGEX_COORDINATES_DELIMETER);
    }
}
