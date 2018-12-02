package com.epam.geometry.calculate;

import com.epam.geometry.entity.Plane;
import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;
import java.util.Arrays;
import java.util.List;

public class SurfaceBelongCalculator {

    //do Plane
    public boolean isBelong(Tetrahedron tetrahedron){
        List<Plane> allPlanesFormTetrahedron = getAllTetrahedronPlanes(tetrahedron);
        for(Plane plane : allPlanesFormTetrahedron){
            if(equalsXOY(plane) || equalsZOY(plane) || equalsXOZ(plane)){
                return true;
            }
        }
        return false;
    }

    private List<Plane> getAllTetrahedronPlanes(Tetrahedron tetrahedron){
        Point3D firstPoint = tetrahedron.getPointOne();
        Point3D secondPoint = tetrahedron.getPointTwo();
        Point3D thirdPoint = tetrahedron.getPointThree();
        Point3D fourthPoint = tetrahedron.getPointFour();
        Plane firstPlane = new Plane(firstPoint,secondPoint,thirdPoint);
        Plane secondPlane = new Plane(firstPoint,secondPoint,fourthPoint);
        Plane thirdPlane = new Plane(firstPoint,thirdPoint,fourthPoint);
        Plane fourthPlane = new Plane(secondPoint,thirdPoint,fourthPoint);
        return Arrays.asList(firstPlane,secondPlane,thirdPlane,fourthPlane);
    }

    private boolean equalsXOY(Plane planeToCheck) {
        Point3D firstPoint = planeToCheck.getFirst();
        Point3D secondPoint = planeToCheck.getSecond();
        Point3D thirdPoint = planeToCheck.getThird();
        double firstPointZ = firstPoint.getZ();
        double secondPointZ = secondPoint.getZ();
        double thirdPointZ = thirdPoint.getZ();
        return firstPointZ==0 && secondPointZ==0 && thirdPointZ==0;
    }

    private boolean equalsZOY(Plane planeToCheck){
        Point3D firstPoint = planeToCheck.getFirst();
        Point3D secondPoint = planeToCheck.getSecond();
        Point3D thirdPoint = planeToCheck.getThird();
        double firstPointX = firstPoint.getX();
        double secondPointX = secondPoint.getX();
        double thirdPointX = thirdPoint.getX();
        return firstPointX==0 && secondPointX==0 && thirdPointX==0;
    }

    private boolean equalsXOZ(Plane planeToCheck){
        Point3D firstPoint = planeToCheck.getFirst();
        Point3D secondPoint = planeToCheck.getSecond();
        Point3D thirdPoint = planeToCheck.getThird();
        double firstPointY = firstPoint.getY();
        double secondPointY = secondPoint.getY();
        double thirdPointY = thirdPoint.getY();
        return firstPointY==0 && secondPointY==0 && thirdPointY==0;
    }
}
