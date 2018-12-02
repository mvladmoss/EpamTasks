package com.epam.geometry.repository;

import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.specification.SearchSpecification;
import com.epam.geometry.specification.SortSpecification;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TetrahedronRepositoryTest {
    private final static Tetrahedron FIRST = new Tetrahedron(
            new Point3D(1, 2, -2),
            new Point3D(9, 2, -2),
            new Point3D(5, (2 + 4 * Math.sqrt(3)), -2),
            new Point3D(5, 2 + 4 / Math.sqrt(3), -2 + 8 * Math.sqrt((double) 2 / 3)));

    private final static Tetrahedron SECOND = new Tetrahedron(
            new Point3D( (Math.sqrt(3)/3),0,(double) 2/3*Math.sqrt(6)),
            new Point3D(0,-1,0),
            new Point3D(Math.sqrt(3),0,0),
            new Point3D(0,1,0));

    private final static Tetrahedron THIRD = new Tetrahedron(
            new Point3D(-2.6,-2.21,0),
            new Point3D(2.04,-2.37,0),
            new Point3D(-0.14,1.73,0),
            new Point3D(-0.23,-0.95, 3.79)
    );

    private final static Tetrahedron FOURTH = new Tetrahedron(
            new Point3D(-2.15,0.41,0),
            new Point3D(1.42,-3.27,0),
            new Point3D(2.82,1.66,0),
            new Point3D(0.7,-0.4, 4.19)
    );

    private final static Tetrahedron FIFTH = new Tetrahedron(
            new Point3D(-2.83,0.96,0),
            new Point3D(1.42,-3.27,0),
            new Point3D(1.3,1.57,0),
            new Point3D(-0.04,-0.89, 3.95)
    );

    private final static Tetrahedron SIXTH = new Tetrahedron(
            new Point3D(0.36,1.25,0),
            new Point3D(2.18,0.83,0),
            new Point3D(1.64,2.62,0),
            new Point3D(1.4,1.57, 1.53)
    );

    private final static List<Tetrahedron> REPOSITORY_SORTED_BY_VOLUME = Arrays.asList(SIXTH,SECOND,THIRD,FOURTH,FIFTH,FIRST);
    private final static List<Tetrahedron> REPOSITORY_SORTED_BY_SQUARE = Arrays.asList(SIXTH,SECOND,THIRD,FOURTH,FIFTH,FIRST);

    private final static Comparator VOLUME_COMPARATOR = Comparator.comparing(SortSpecification.VOLUME_SORT);
    private final static Comparator SQUARE_COMPARATOR = Comparator.comparing(SortSpecification.SQUARE_SORT);

    Repository<Tetrahedron> repository = new TetrahedronRepository();

    @BeforeClass
    public void init() {
        repository.add(FIRST);
        System.out.println(FIRST.getID());
        repository.add(SECOND);
        repository.add(THIRD);
        repository.add(FOURTH);
        repository.add(FIFTH);
        repository.add(SIXTH);


    }

    //start this test class separated from others.Because previous tests can create tetrahedrons and that's why id would be not as expected
    @Test
    public void shouldReturnListOfTetrahedronsWithIdLessThenIdSetBySpecification(){
        //given
        //Doing with Builder.From my point of view, it's good idea for build specifications
        SearchSpecification specificationWithSetId = new SearchSpecification.
                Builder().
                setMaxId(4).
                build();
        List<Tetrahedron> expectedList = Arrays.asList(FIRST,SECOND,THIRD);
        //when
        List<Tetrahedron> actualList = repository.query(specificationWithSetId.ALL_WITH_ID_LESS_THEN_MAX_ID);
        //then
        Assertions.assertEquals(expectedList,actualList);
    }

    @Test
    public void shouldSortRepositoryByVolume(){
        //given
        //when
        List<Tetrahedron> actualRepository = repository.sortTetrahedron(VOLUME_COMPARATOR);
        //then
        Assertions.assertEquals(REPOSITORY_SORTED_BY_VOLUME,actualRepository);
    }

    @Test
    public void shouldSortRepositoryBySquare(){
        //given
        //when
        List<Tetrahedron> actualRepository = repository.sortTetrahedron(SQUARE_COMPARATOR);
        //then
        Assertions.assertEquals(REPOSITORY_SORTED_BY_SQUARE,actualRepository);
    }

    @Test
    public void shouldReturnListOfTetrahedronsWithSquareGreaterThenSquareSetBySpecification(){
        //given
        //Doing with Builder.From my point of view, it's good idea for build specifications
        SearchSpecification specificationWithSetId = new SearchSpecification.
                                                            Builder().
                                                            setMinSquare(7).
                                                            build();
        List<Tetrahedron> expectedList = Arrays.asList(FIRST,THIRD,FOURTH,FIFTH);
        //when
        List<Tetrahedron> actualList = repository.query(specificationWithSetId.ALL_WITH_SQUARE_BIGGER_THEN_MIN_SQUARE);
        //then
        Assertions.assertEquals(expectedList,actualList);
    }



}
