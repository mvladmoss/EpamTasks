package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RatioSurfaceXOZCalculatorTest {

    RatioSurfaceXOZCalculator calculator = new RatioSurfaceXOZCalculator();


    private final static Tetrahedron TETRAHEDRON_INTERACTION_XOZ = new Tetrahedron(
            new Point3D(1, -2, 2),
            new Point3D(9, -2, 2),
            new Point3D(5, -2,(2 + 4 * Math.sqrt(3))),
            new Point3D(5, -2 + 8 * Math.sqrt((double) 2 / 3),2 + 4 / Math.sqrt(3)));
    private final static Tetrahedron TETRAHEDRON_NOT_INTERACTION_XOZ = new Tetrahedron(
            new Point3D(1,2,2),
            new Point3D(5,2,5),
            new Point3D(3,2,4),
            new Point3D(1,2,7));

    private final static double DELTA = 0.05;

    @Test
    public void shouldProduceAndReturnRatioWhenInteractionXOZ() {
        //given
        double expectedRatio = 0.5;
        //when
        double actualRatio = calculator.calculateRatioSurface(TETRAHEDRON_INTERACTION_XOZ);
        //then
        Assert.assertEquals(expectedRatio,actualRatio,DELTA);
    }

    @Test
    public void shouldCheckIsTetrahedronInteractionXOZ(){
        //given
        boolean expectedInteractionZOX = false;
        //when
        boolean actualInteractionZOX = calculator.isInteractionXOZ(TETRAHEDRON_NOT_INTERACTION_XOZ);
        //then
        Assertions.assertEquals(expectedInteractionZOX,actualInteractionZOX);
    }
}
