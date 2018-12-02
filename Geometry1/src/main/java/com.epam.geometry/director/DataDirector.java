package com.epam.geometry.director;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.exception.IncorrectInputDataException;
import com.epam.geometry.exception.NotExistFileException;
import com.epam.geometry.parsers.Parser;
import com.epam.geometry.reader.Reader;
import com.epam.geometry.validator.TetrahedronExistValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataDirector {

    private static final Logger LOGGER = LogManager.getLogger();

    private Reader reader;
    private Parser parser;
    private TetrahedronExistValidator validator;

    public DataDirector(Reader reader, Parser parser, TetrahedronExistValidator validator){
        this.reader = reader;
        this.parser = parser;
        this.validator = validator;
    }

    public List<Tetrahedron> getTetrahedronFromFile(String Path){
        List<Tetrahedron> validTetrahedrons = new ArrayList<>();
        List<String> allLines = getAllLinesFromFile(Path);
        for(String pointString : allLines){
            try {
                List<Point3D> pointList = parser.parse(pointString);
                if(validator.validateExistence(pointList)){
                    Tetrahedron tetrahedron =  new Tetrahedron(pointList);
                    validTetrahedrons.add(tetrahedron);
                }  else{
                    LOGGER.warn("Tetrahedron with such points don't exist:" + pointList);
                }
            } catch (IncorrectInputDataException e) {
                LOGGER.warn("Was met incorrect data:" + pointString);
            }
        }
        LOGGER.info("Tetrahedrons was created");
        return validTetrahedrons;
    }

    private List<String> getAllLinesFromFile(String Path){
        List<String> allLines;
        try {
            allLines = reader.readLines(Path);
        } catch (NotExistFileException e) {
            LOGGER.fatal(e);
            throw new RuntimeException(e);
        }
        return allLines;
    }
}
