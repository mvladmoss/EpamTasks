package com.epam.geometry.validators;

import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.validator.TetrahedronExistValidator;
import com.epam.geometry.entity.Point3D;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TetrahedronExistValidatorTest {
    TetrahedronExistValidator tetrahedronExistValidator;

    private final static List<Point3D> FIRST = Arrays.asList(
            new Point3D(1, 2, -2),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    private final static List<Point3D> SECOND = Arrays.asList(
            new Point3D( (Math.sqrt(3)/3),0,(double) 2/3*Math.sqrt(6)),
            new Point3D(0,-1,0),
            new Point3D(Math.sqrt(3),0,0),
            new Point3D(0,1,0));

    private final static List<Point3D> THIRD = Arrays.asList(
            new Point3D(1, 2, -3),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    private final static List<Point3D> FOURTH = Arrays.asList(
            new Point3D(11, 24, -2),
            new Point3D(7, -2, -10),
            new Point3D(5, 0, -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 6)));

    private final static List<Point3D> FIFTH = Arrays.asList(
            new Point3D(-2.6,-2.21,0),
            new Point3D(2.04,-2.37,0),
            new Point3D(-0.14,1.73,0),
            new Point3D(-0.23,-0.95, 3.79)
    );

    @BeforeClass
    public void init(){
        tetrahedronExistValidator = new TetrahedronExistValidator();
    }

    @DataProvider(name = "dataForValidationTetrahedron")
    public Object[][] dataForValidation() {
        return new Object[][]{
                {true, FIRST},
                {true, SECOND},
                {false, THIRD},
                {false, FOURTH},
                {true, FIFTH}
        };
    }

    @Test(dataProvider = "dataForValidationTetrahedron")
    public void shouldValidateTetrahedron(boolean expectedValid, List<Point3D> tetrahedronToCheckPoints){
        //given
        TetrahedronExistValidator validator = new TetrahedronExistValidator();
        //when
        boolean actualValid = validator.validateExistence(tetrahedronToCheckPoints);
        //then
        Assertions.assertEquals(expectedValid,actualValid);
    }


}
