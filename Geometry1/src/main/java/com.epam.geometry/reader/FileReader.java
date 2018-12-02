package com.epam.geometry.reader;

import com.epam.geometry.exception.NotExistFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileReader implements Reader {

    private final static Logger LOGGER = LogManager.getLogger(FileReader.class);

    public List<String> readLines(String path) throws NotExistFileException {
        ArrayList<String> allLines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while(scanner.hasNextLine()){
                allLines.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new NotExistFileException("File don't exists on the next path: " + path);
        }
        LOGGER.info("File:" + path + " was successfully read");
        return allLines;
    }
}
