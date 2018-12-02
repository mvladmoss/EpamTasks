package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RatioSurfaceZOYCalculatorTest {

    RatioSurfaceZOYCalculator calculator = new RatioSurfaceZOYCalculator();

    private final static Tetrahedron TETRAHEDRON_INTERACTION_ZOY = new Tetrahedron(
            new Point3D(-2, 1, 2),
            new Point3D(-2,9, 2),
            new Point3D(-2,5, (2 + 4 * Math.sqrt(3))),
            new Point3D(-2 + 8 * Math.sqrt((double) 2 / 3),5, 2 + 4 / Math.sqrt(3)));
    private final static Tetrahedron TETRAHEDRON_NOT_INTERACTION_ZOY = new Tetrahedron(
            new Point3D(-2,1,2),
            new Point3D(-2,5,1),
            new Point3D(-2,5,4),
            new Point3D(-2,1,8));

    private final static double DELTA = 0.05;

    @Test
    public void shouldProduceAndReturnRatioWhenInteractionZOY() {
        //given
        double expectedRatio = 0.5;
        //when
        double actualRatio = calculator.calculateRatioSurface(TETRAHEDRON_INTERACTION_ZOY);
        //then
        Assert.assertEquals(expectedRatio,actualRatio,DELTA);
    }

    @Test
    public void shouldCheckIsTetrahedronInteractionZOY(){
        //given
        boolean expectedInteractionZOY = false;
        //when
        boolean actualInteractionZOY = calculator.isInteractionZOY(TETRAHEDRON_NOT_INTERACTION_ZOY);
        //then
        Assertions.assertEquals(expectedInteractionZOY, actualInteractionZOY);
    }
}
