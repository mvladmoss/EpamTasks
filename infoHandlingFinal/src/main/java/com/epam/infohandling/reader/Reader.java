package com.epam.infohandling.reader;

import com.epam.infohandling.exception.NotExistFileException;

import java.util.List;

public interface Reader {
    String readText(String PATH) throws NotExistFileException;
}
