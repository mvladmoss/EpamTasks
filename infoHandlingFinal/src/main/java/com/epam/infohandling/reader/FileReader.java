package com.epam.infohandling.reader;

import com.epam.infohandling.exception.NotExistFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class FileReader implements Reader {

    private final static Logger LOGGER = LogManager.getLogger(FileReader.class);

    public String readText(String path) throws NotExistFileException {
        String text = new String();
        try {
            Scanner scanner = new Scanner(new File(path));
            while(scanner.hasNextLine()){
                text+=scanner.nextLine();
            }
        } catch (IOException e) {
            throw new NotExistFileException("File don't exists on the next path: " + path);
        }
        LOGGER.info("File:" + path + " was successfully read");
        return text;
    }
}
