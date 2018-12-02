package com.epam.geometry.parsers;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.exception.IncorrectInputDataException;
import com.epam.geometry.validator.DataValidator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseStringTest {
    DataParser parser;

    private final static Tetrahedron FIRST = new Tetrahedron(
            new Point3D(1.0,2.3,3.6),
            new Point3D(3.2,4.2,1.2),
            new Point3D(3.2,5.2,3.2),
            new Point3D(5.6,2.1,7.6));

    private final static Tetrahedron SECOND = new Tetrahedron(
            new Point3D(5.2,2.3,3.6),
            new Point3D(4.2,4.2,1.2),
            new Point3D(3.2,5.2,1.4),
            new Point3D(5.7,2.1,7.6));

    private final static Tetrahedron THIRD = new Tetrahedron(
            new Point3D(1.0, 2.3, 3.6),
            new Point3D(3.2, 4.2, 1.2),
            new Point3D(3.2, 5.2, 10.2),
            new Point3D(5.6, 2.1, 7.6));

    @BeforeClass
    public void init() {
        parser = new DataParser(new DataValidator());
    }

    @DataProvider(name = "dataForParsingTetrahedronSuccess")
    public Object[][] dataForTetrahedronSuccess() {
        return new Object[][]{
                {FIRST,"1.0 2.3 3.6,3.2 4.2 1.2,3.2 5.2 3.2,5.6 2.1 7.6"},
                {SECOND,"5.2 2.3 3.6,4.2 4.2 1.2,3.2 5.2 1.4,5.7 2.1 7.6"},
                {THIRD,"1.0 2.3 3.6,3.2 4.2 1.2,3.2 5.2 10.2,5.6 2.1 7.6"},
        };
    }

    @Test(dataProvider = "dataForParsingTetrahedronSuccess")
    public void shouldParseAndReturnPoint3DWhenGetCorrectString(Tetrahedron expectedTetrahedron, String toParse) throws IncorrectInputDataException {
        Tetrahedron actualTetrahedron = new Tetrahedron(parser.parse(toParse));
        Assertions.assertEquals(expectedTetrahedron, actualTetrahedron);
    }

    @DataProvider(name = "dataForParsingTetrahedronFail")
    public Object[][] dataForTetrahedronFail() {
        return new Object[][]{
                {"1.0 2.3 3.6,3.2 4.2 1.2,3.2 5.2 d,5.6 2.1 7.6"},
                {"1.0 2.3 3.6),3.2 4.2 1.2,3.2 5.2 4.5 4.3,5.6 2.1 7.6"},
                {"1.0 2.3 3.6,3.2 4.2 1.2,3.2 5.2,5.6 2.1 7.6"},
                {"1.0 4.2 5,4,0"}
        };
    }

    @Test(expectedExceptions = IncorrectInputDataException.class, dataProvider = "dataForParsingTetrahedronFail")
    public void shouldParseandReturnPoint3DFailIncorrectData(String failData) throws IncorrectInputDataException {
        parser.parse(failData);
    }
}
