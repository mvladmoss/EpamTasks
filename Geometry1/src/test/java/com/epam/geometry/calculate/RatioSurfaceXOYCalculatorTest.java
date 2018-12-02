package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RatioSurfaceXOYCalculatorTest {

    private final static  RatioSurfaceXOYCalculator calculator = new RatioSurfaceXOYCalculator();


    private final static Tetrahedron TETRAHEDRON_INTERACTION_XOY = new Tetrahedron(
            new Point3D(1, 2, -2),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    private final static Tetrahedron TETRAHEDRON_NOT_INTERACTION_XOY = new Tetrahedron(
            new Point3D(1,2,2),
            new Point3D(5,5,2),
            new Point3D(3,4,2),
            new Point3D(1,7,2));

    private final static double DELTA = 0.05;

    @Test
    public void shouldProduceAndReturnRatioWhenInteractionXOY() {
        //given
        double expectedRatio = 0.5;
        //when
        double actualRatio = calculator.calculateRatioSurface(TETRAHEDRON_INTERACTION_XOY);
        //then
        Assert.assertEquals(expectedRatio,actualRatio,DELTA);
    }

    @Test
    public void shouldCheckIsTetrahedronInteractionXOY(){
        //given
        boolean expectedInteractionXOY = false;
        //when
        boolean actualInteractionXOY = calculator.isInteractionXOY(TETRAHEDRON_NOT_INTERACTION_XOY);
        //then
        Assertions.assertEquals(expectedInteractionXOY,actualInteractionXOY);
    }
}
