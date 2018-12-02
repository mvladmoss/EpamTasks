package com.epam.geometry.reader;

import com.epam.geometry.exception.NotExistFileException;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class FileReaderTest {
    private final static String DATA_PATH  = "src/main/resources/data_files/";
    private FileReader reader;

    @BeforeClass
    public void init(){
        reader = new FileReader();
    }

    @Test
    public void testReadAllLinesSuccess() throws NotExistFileException {
        //give
        List<String> expectedList = Arrays.asList("1.0 2.0 -2,9.0 2.0 -2.0,5.0 8.93 -2.0,5.0 4.31 4.532",
                                                  "0.577 0.0 1.634,0.0 -1.0 0.0,1.732 0.0 0.0,0.0 1.0 0.0");
        //when
        List<String> actualList = reader.readLines(DATA_PATH + "correctData1.txt");
        //then
        Assertions.assertEquals(expectedList, actualList);
    }
    @Test(expectedExceptions = NotExistFileException.class)
    public void testReadAllLinesFailFileNotExist() throws NotExistFileException {
        reader.readLines(DATA_PATH + "notExistFile.txt");
    }
}
