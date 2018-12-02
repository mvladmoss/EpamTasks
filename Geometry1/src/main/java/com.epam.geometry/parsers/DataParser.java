package com.epam.geometry.parsers;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.exception.IncorrectInputDataException;
import com.epam.geometry.reader.FileReader;
import com.epam.geometry.validator.DataValidator;
import com.epam.geometry.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataParser implements Parser {

    private final static Logger LOGGER = LogManager.getLogger(FileReader.class);
    private static final String REGEX_POINT_DELIMETER = "\\,";
    private static final String REGEX_COORDINATES_DELIMETER = "\\s";

    private Validator validator;

    public DataParser(Validator validator){
        this.validator = validator;
    }

    public List<Point3D> parse(String toParse) throws IncorrectInputDataException {
        DataValidator validator = new DataValidator();
        try {
            if (validator.validate(toParse)) {
                LOGGER.info("Parse was successful complete");
                return parseToPointArray(toParse);
            } else {
                throw new IncorrectInputDataException("Incorrect input data:" + toParse);
            }
        } catch (IncorrectInputDataException e) {
            throw e;
        }
    }

    private List<Point3D> parseToPointArray(String toParse){
        List<Point3D> pointList = new ArrayList<>();
        List<String> pointCoordinates = new ArrayList<>();
        List<String> values = Arrays.asList(toParse.split(REGEX_POINT_DELIMETER));
        pointCoordinates.addAll(values);
        for(String coordinates : pointCoordinates){
            List<Double> pointListToAdd = new ArrayList<>();
            for(String num : coordinates.split(REGEX_COORDINATES_DELIMETER)){
                pointListToAdd.add(Double.parseDouble(num));
            }
            pointList.add(new Point3D(pointListToAdd));
        }
        return pointList;
    }
}
