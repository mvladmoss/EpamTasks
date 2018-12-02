package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class RatioSurfaceXOZCalculator {
    private final static  TetrahedronCalculator CALCULATOR = new TetrahedronCalculator();


    public Double calculateRatioSurface(Tetrahedron tetrahedron) {
        Point3D topOfTetrahedron = getTetrahedronTop(tetrahedron);
        List<Point3D> listOfInteractionPointXOZ = getPointOfIntercationXOZ(tetrahedron,topOfTetrahedron);
        double height = Math.abs(topOfTetrahedron.getY());
        double volumeOfSection = findSquareXOZ(listOfInteractionPointXOZ) * (double)1 / 3* height;
        double volumeOfTetrahedron = CALCULATOR.calculateVolume(tetrahedron);
        return volumeOfSection / (volumeOfTetrahedron - volumeOfSection);
    }

    private double findSquareXOZ(List<Point3D> points) {
        Point3D first = points.get(0);
        Point3D second = points.get(1);
        Point3D third = points.get(2);
        double Square = 0.5 * (((first.getX() - third.getX()) *
                (second.getZ() - third.getZ())) - ((first.getZ() - third.getZ()) *
                (second.getX() - third.getX())));
        return Square;
    }

    public boolean isInteractionXOZ(Tetrahedron tetrahedron){
        return !(isALllNegativeY(tetrahedron.getAllPoints()) || isALllPositiveY(tetrahedron.getAllPoints()));
    }

    private Point3D getTetrahedronTop(Tetrahedron tetrahedron) {
        List<Point3D> positiveY = new ArrayList<>();
        List<Point3D> negativeY = new ArrayList<>();
        for (Point3D point3D : tetrahedron.getAllPoints()) {
            if (point3D.getY() < 0) {
                negativeY.add(point3D);
            } else {
                positiveY.add(point3D);
            }
        }
        if(positiveY.size()==1) {
            return positiveY.get(0);
        }else{
            return negativeY.get(0);
        }

    }

    private List<Point3D> getPointOfIntercationXOZ(Tetrahedron tetrahedron,Point3D tetrahedronTop) {
        //x = x1+t(x2-x1) y = y1+t(y2-y1) z = z1+t(z2-z1)   z=0     t=-z1/(z2-z1)
        List<Point3D> listOfInteractionPoint = new ArrayList<>();
        for(Point3D point : tetrahedron.getAllPoints()){
            if(!point.equals(tetrahedronTop)){
                double tFirst = getOppositeNumber(tetrahedronTop.getY()) / (point.getY() - tetrahedronTop.getY());
                double x = tetrahedronTop.getX() + tFirst * (point.getX() - tetrahedronTop.getX());
                double z = tetrahedronTop.getZ() + tFirst * (point.getZ() - tetrahedronTop.getZ());
                listOfInteractionPoint.add(new Point3D(x,0,z));
            }
        }
        return listOfInteractionPoint;
    }

    private double getOppositeNumber(double number){
        return number*(-1);
    }

    private boolean isALllPositiveY(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getX()<0){
                return false;}
        }
        return  true;
    }
    private boolean isALllNegativeY(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getX()>0){
                return false;}
        }
        return  true;
    }
}
