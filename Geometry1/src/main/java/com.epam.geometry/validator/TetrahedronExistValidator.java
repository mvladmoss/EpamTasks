package com.epam.geometry.validator;
import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class TetrahedronExistValidator {

    private final static double EPSILON = 0.01;

    public static boolean validateExistence(List<Point3D> tetrahedronPoints) {
        return new TetrahedronExistValidator().isAllEdgesEquals(tetrahedronPoints);
    }

    private boolean isAllEdgesEquals(List<Point3D> tetrahedronPoints){
        List<Double> allEdgesLength = new ArrayList<>();
        for(int i = 0; i < tetrahedronPoints.size(); i++){
            for(int j = 1; j < tetrahedronPoints.size(); j++){
                if(i>=j){
                    continue;
                }
                Point3D firstPointForEdge = tetrahedronPoints.get(j);
                Point3D secondPointForEdge = tetrahedronPoints.get(i);
                double edgeLength = TetrahedronCalculator.calculateEdgeLength(firstPointForEdge, secondPointForEdge);
                allEdgesLength.add(edgeLength);
            }
        }

        Double toCheck = allEdgesLength.get(0);
        for(int i = 1; i < allEdgesLength.size(); i++){
            if(toCheck-allEdgesLength.get(i)>=EPSILON){
                return false;
            }
        }
        return true;
    }
}
