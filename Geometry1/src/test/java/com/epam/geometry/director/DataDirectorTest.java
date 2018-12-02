package com.epam.geometry.director;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.parsers.DataParser;
import com.epam.geometry.reader.FileReader;
import com.epam.geometry.validator.DataValidator;
import com.epam.geometry.validator.TetrahedronExistValidator;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class DataDirectorTest {
    private static final List<Tetrahedron> TETRAHEDRON_LIST_FIRST = Arrays.asList(
            new Tetrahedron(
                    new Point3D(1, 2, -2),
                    new Point3D(9, 2, -2),
                    new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
                    new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3))),
            new Tetrahedron(
                    new Point3D( (Math.sqrt(3)/3),0,(double) 2/3*Math.sqrt(6)),
                    new Point3D(0,-1,0),
                    new Point3D(Math.sqrt(3),0,0),
                    new Point3D(0,1,0)));

    private static final List<Tetrahedron> TETRAHEDRON_LIST_SECOND = Arrays.asList(
            new Tetrahedron(
                    new Point3D( (Math.sqrt(3)/3),0,(double) 2/3*Math.sqrt(6)),
                    new Point3D(0,-1,0),
                    new Point3D(Math.sqrt(3),0,0),
                    new Point3D(0,1,0)));

    private static final List<Tetrahedron> TETRAHEDRON_LIST_THIRD = Arrays.asList(
            new Tetrahedron(
                    new Point3D(1, 2, -2),
                    new Point3D(9, 2, -2),
                    new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
                    new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3))));

    @DataProvider(name = "dataForTetrahedronCreation")
    public Object[][] dataForTetrahedrons() {
        return new Object[][]{
                {TETRAHEDRON_LIST_FIRST, "src/main/resources/data_files/correctData1.txt"},
                {TETRAHEDRON_LIST_SECOND, "src/main/resources/data_files/correctData2.txt"},
                {TETRAHEDRON_LIST_THIRD, "src/main/resources/data_files/incorrectData1.txt"},
        };
    }

    @Test(dataProvider ="dataForTetrahedronCreation" )
    public void shouldCreateTetradedronFromFile(List<Tetrahedron> expectedTetrahedronList, String PATH){
        //given
        DataDirector creator = new DataDirector(new FileReader(), new DataParser(new DataValidator()), new TetrahedronExistValidator());
        //when
        List<Tetrahedron> actualTetrahedronList = creator.getTetrahedronFromFile(PATH);
        //then
        Assertions.assertEquals(expectedTetrahedronList,actualTetrahedronList);
    }
}
