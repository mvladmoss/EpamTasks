package com.epam.geometry.reader;

import com.epam.geometry.exception.NotExistFileException;

import java.util.List;

public interface Reader {
    List<String> readLines(String PATH) throws NotExistFileException;
}
