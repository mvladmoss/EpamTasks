package com.epam.geometry.calculate;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class RatioSurfaceXOYCalculator {

    private final static   TetrahedronCalculator CALCULATOR = new TetrahedronCalculator();


    public Double calculateRatioSurface(Tetrahedron tetrahedron) {
        Point3D topOfTetrahedron = getTetrahedronTop(tetrahedron);
        List<Point3D> listOfInteractionPointXOY = getPointOfIntercationXOY(tetrahedron,topOfTetrahedron);
        double height = Math.abs(topOfTetrahedron.getZ());
        double volumeOfSection = findSquareXOY(listOfInteractionPointXOY) * (double)1 / 3* height;
        double volumeOfTetrahedron = CALCULATOR.calculateVolume(tetrahedron);
        return volumeOfSection / (volumeOfTetrahedron - volumeOfSection);
    }

    private double findSquareXOY(List<Point3D> points) {
            Point3D first = points.get(0);
            Point3D second = points.get(1);
            Point3D third = points.get(2);
            //using the matrix find square.Make separate method for this is not optimally.That's why there some points in line
            double Square = 0.5 * (((first.getX() - third.getX()) *
                                  (second.getY() - third.getY())) - ((first.getY() - third.getY()) *
                                  (second.getX() - third.getX())));
            return Square;
    }
    public boolean isInteractionXOY(Tetrahedron tetrahedron){
        return !(isALllNegativeZ(tetrahedron.getAllPoints()) || isALllPositiveZ(tetrahedron.getAllPoints()));
    }

    private Point3D getTetrahedronTop(Tetrahedron tetrahedron) {
        List<Point3D> positiveZ = new ArrayList<>();
        List<Point3D> negativeZ = new ArrayList<>();
        for (Point3D point3D : tetrahedron.getAllPoints()) {
            if (point3D.getZ() < 0) {
                negativeZ.add(point3D);
            } else {
                positiveZ.add(point3D);
            }
        }
        if(positiveZ.size()==1) {
            return positiveZ.get(0);
        }else{
            return negativeZ.get(0);
        }

    }

    private List<Point3D> getPointOfIntercationXOY(Tetrahedron tetrahedron,Point3D tetrahedronTop) {
        //x = x1+t(x2-x1) y = y1+t(y2-y1) z = z1+t(z2-z1)   z=0     t=-z1/(z2-z1)
        List<Point3D> listOfInteractionPoint = new ArrayList<>();
        for(Point3D point : tetrahedron.getAllPoints()){
            if(!point.equals(tetrahedronTop)){
                double tFirst = getOppositeNumber(tetrahedronTop.getZ()) / (point.getZ() - tetrahedronTop.getZ());
                double x = tetrahedronTop.getX() + tFirst * (point.getX() - tetrahedronTop.getX());
                double y = tetrahedronTop.getY() + tFirst * (point.getY() - tetrahedronTop.getY());
                listOfInteractionPoint.add(new Point3D(x,y,0));
            }
        }
        return listOfInteractionPoint;
    }

    private double getOppositeNumber(double number){
        return number*(-1);
    }

    private boolean isALllPositiveZ(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getZ()<0){
                return false;}
        }
        return  true;
    }
    private boolean isALllNegativeZ(List<Point3D> list){
        for(Point3D point3D : list){
            if(point3D.getZ()>0){
                return false;}
        }
        return  true;
    }
}
