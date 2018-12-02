package com.epam.geometry.specification;

import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Tetrahedron;

import java.util.function.Function;

public class SortSpecification {
    public static final Function<Tetrahedron, Double> ID_SORT = (t) -> (double)(t.getID());
    public static final Function<Tetrahedron, Double> Z_FOURTH_POINT_SORT = (t) -> (double)(t.getPointFour().getZ());
    public static final Function<Tetrahedron, Double> EDGE_SORT = (t) -> (double)(t.getEdgeLength());
    public static final Function<Tetrahedron, Double> SQUARE_SORT = (t) -> (double)(new TetrahedronCalculator().calculateSquare(t));
    public static final Function<Tetrahedron, Double> VOLUME_SORT = (t) -> (double)(new TetrahedronCalculator().calculateVolume(t));
}
