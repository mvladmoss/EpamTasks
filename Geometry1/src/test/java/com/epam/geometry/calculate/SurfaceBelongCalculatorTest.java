package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SurfaceBelongCalculatorTest {

    private final static Tetrahedron FIRST = new Tetrahedron(
            new Point3D(0,3,5),
            new Point3D(0,6,3),
            new Point3D(0,8,4),
            new Point3D(2,1,0));
    private final static Tetrahedron SECOND = new Tetrahedron(
            new Point3D(1,3,5),
            new Point3D(10,6,3),
            new Point3D(0,8,4),
            new Point3D(2,1,0));
    private final static Tetrahedron THIRD = new Tetrahedron(
            new Point3D(0,3,5),
            new Point3D(0,6,3),
            new Point3D(0,8,4),
            new Point3D(2,1,0));
    private final static Tetrahedron FOURTH = new Tetrahedron(
            new Point3D(1,3,0),
            new Point3D(10,6,0),
            new Point3D(0,8,4),
            new Point3D(2,1,0));
    private final static Tetrahedron FIFTH = new Tetrahedron(
            new Point3D(12,3,5),
            new Point3D(-2,6,3),
            new Point3D(0,-4,4),
            new Point3D(2,0,0));

    SurfaceBelongCalculator validator;
    @BeforeClass
    public void init(){
        validator = new SurfaceBelongCalculator();
    }


    @DataProvider(name = "dataForCheckBelongOneSurface")
    public Object[][] dataForVolume() {
        return new Object[][]{
                {true, FIRST},
                {false, SECOND},
                {true, THIRD},
                {true, FOURTH},
                {false, FIFTH}
        };
    }

    @Test(dataProvider = "dataForCheckBelongOneSurface")
    public void shouldReturnIfTetrahedronLieOnCoordinatePlan(boolean expectedBelong, Tetrahedron tetrahedron){
        boolean actualBelong = validator.isBelong(tetrahedron);
        Assertions.assertEquals(expectedBelong, actualBelong);
    }

}
