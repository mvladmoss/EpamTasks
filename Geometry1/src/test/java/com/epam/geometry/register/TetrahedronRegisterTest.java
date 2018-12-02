package com.epam.geometry.register;

import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Point3D;
import com.epam.geometry.registar.ObservableTetrahedron;
import com.epam.geometry.registar.TetrahedronRegister;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TetrahedronRegisterTest {
    TetrahedronRegister register;

    private final static ObservableTetrahedron FIRST = new ObservableTetrahedron(
            new Point3D(1, 2, -2),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    @BeforeClass
    public void init(){
        register = TetrahedronRegister.getInstance();
        FIRST.attach(register);
    }


    @Test
    public void ShouldUpdateRegisterSquare(){
        //given
        FIRST.setEdgeLength(7);
        double actualSquare = new TetrahedronCalculator().calculateSquare(FIRST);
        //when
        double expectedSquare = register.getSquare(FIRST.getID());
        //then
        Assertions.assertEquals(expectedSquare,actualSquare);
    }

    @Test
    public void ShouldUpdateRegisterVolume(){
        //given
        FIRST.setEdgeLength(7);
        double actualVolume = new TetrahedronCalculator().calculateVolume(FIRST);
        //when
        double expectedVolume = register.getVolume(FIRST.getID());
        //then
        Assertions.assertEquals(expectedVolume,actualVolume);
    }

}
