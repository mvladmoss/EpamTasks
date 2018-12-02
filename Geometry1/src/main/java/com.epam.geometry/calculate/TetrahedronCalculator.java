package com.epam.geometry.calculate;
import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

public class TetrahedronCalculator {
    public double calculateSquare(Tetrahedron tetrahedron){
        double edgeLength = tetrahedron.getEdgeLength();
        return Math.pow(edgeLength,2)*Math.sqrt(3);
    }

    public double calculateVolume(Tetrahedron tetrahedron){
        double edgeLength = tetrahedron.getEdgeLength();
        return Math.pow(edgeLength,3)*Math.sqrt(2)/12;
    }

    public static double calculateEdgeLength(Point3D first, Point3D second){
        return Math.sqrt(Math.pow(first.getX()-second.getX(),2) +
                Math.pow(first.getY()-second.getY(),2) +
                Math.pow(first.getZ()-second.getZ(),2));

    }


}
