package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class RatioSurfaceZOYCalculator {
    private final static TetrahedronCalculator CALCULATOR = new TetrahedronCalculator();


    public Double calculateRatioSurface(Tetrahedron tetrahedron) {
        Point3D topOfTetrahedron = getTetrahedronTop(tetrahedron);
        List<Point3D> listOfInteractionPointZOY = getPointOfIntercationZOY(tetrahedron,topOfTetrahedron);
        double height = Math.abs(topOfTetrahedron.getX());
        double volumeOfSection = findSquareZOY(listOfInteractionPointZOY) * (double)1 / 3* height;
        double volumeOfTetrahedron = CALCULATOR.calculateVolume(tetrahedron);
        return volumeOfSection / (volumeOfTetrahedron - volumeOfSection);
    }

    private double findSquareZOY(List<Point3D> points) {
        Point3D first = points.get(0);
        Point3D second = points.get(1);
        Point3D third = points.get(2);
        double Square = 0.5 * (((first.getY() - third.getY()) *
                                (second.getZ() - third.getZ())) - ((first.getZ() - third.getZ()) *
                                (second.getY() - third.getY())));
        return Square;
    }
    public boolean isInteractionZOY(Tetrahedron tetrahedron){
        return !(isALllNegativeX(tetrahedron.getAllPoints()) || isALllPositiveX(tetrahedron.getAllPoints()));
    }

    private Point3D getTetrahedronTop(Tetrahedron tetrahedron) {
        List<Point3D> positiveX = new ArrayList<>();
        List<Point3D> negativeX = new ArrayList<>();
        for (Point3D point3D : tetrahedron.getAllPoints()) {
            if (point3D.getX() < 0) {
                negativeX.add(point3D);
            } else {
                positiveX.add(point3D);
            }
        }
        if(positiveX.size()==1) {
            return positiveX.get(0);
        }else{
            return negativeX.get(0);
        }

    }

    private List<Point3D> getPointOfIntercationZOY(Tetrahedron tetrahedron,Point3D tetrahedronTop) {
        //x = x1+t(x2-x1) y = y1+t(y2-y1) z = z1+t(z2-z1)   z=0     t=-z1/(z2-z1)
        List<Point3D> listOfInteractionPoint = new ArrayList<>();
        for(Point3D point : tetrahedron.getAllPoints()){
            if(!point.equals(tetrahedronTop)){
                double tFirst = getOppositeNumber(tetrahedronTop.getX()) / (point.getX() - tetrahedronTop.getX());
                double z = tetrahedronTop.getZ() + tFirst * (point.getZ() - tetrahedronTop.getZ());
                double y = tetrahedronTop.getY() + tFirst * (point.getY() - tetrahedronTop.getY());
                listOfInteractionPoint.add(new Point3D(0,y,z));
            }
        }
        return listOfInteractionPoint;
    }

    private double getOppositeNumber(double number){
        return number*(-1);
    }

    private boolean isALllPositiveX(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getX()<0){
                return false;}
        }
        return  true;
    }
    private boolean isALllNegativeX(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getX()>0){
                return false;}
        }
        return  true;
    }
}
