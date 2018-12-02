package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TetrahedronCalculatorTest {

    private final static double DELTA =0.01;

    Tetrahedron FIRST = new Tetrahedron(
            new Point3D(1, 2, -2),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    Tetrahedron SECOND = new Tetrahedron(
            new Point3D( (Math.sqrt(3)/3),0,(double) 2/3*Math.sqrt(6)),
            new Point3D(0,-1,0),
            new Point3D(Math.sqrt(3),0,0),
            new Point3D(0,1,0));

    @DataProvider(name = "dataForTetrahedronVolume")
    public Object[][] dataForSquare() {
        return new Object[][]{
                {FIRST, 110.85},
                {SECOND, 6.92},
        };
    }

    @Test(dataProvider = "dataForTetrahedronVolume")
    public void shouldCalculateandReturnSquare(Tetrahedron tetrahedron, double expectedSquare) {
        TetrahedronCalculator calculate = new TetrahedronCalculator();
        double actualSquare = calculate.calculateSquare(tetrahedron);
        //then
        Assert.assertEquals(expectedSquare, actualSquare,DELTA);
    }

    @DataProvider(name = "dataForTetrahedronSquare")
    public Object[][] dataForVolume() {
        return new Object[][]{
                {FIRST, 60.34},
                {SECOND, 0.942},
        };
    }

    @Test(dataProvider = "dataForTetrahedronSquare")
    public void shouldCalculateandReturnVolume(Tetrahedron tetrahedron,double expectedVolume) {
        TetrahedronCalculator calculate = new TetrahedronCalculator();
        double actualVolume = calculate.calculateVolume(tetrahedron);
        //then
        Assert.assertEquals(expectedVolume, actualVolume,DELTA);
    }

}