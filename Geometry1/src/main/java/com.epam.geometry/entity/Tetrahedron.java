package com.epam.geometry.entity;

import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.generator.IdGenerator;

import java.util.Arrays;
import java.util.List;

public class Tetrahedron {

    private long ID;
    private Point3D pointOne;
    private Point3D pointTwo;
    private Point3D pointThree;
    private Point3D pointFour;
    private double edgeLength;
    public double getEdgeLength() {
        return edgeLength;
    }

    public Tetrahedron(Point3D pointOne, Point3D pointTwo, Point3D pointThree, Point3D pointFour){
        this.pointOne = pointOne;
        this.pointTwo = pointTwo;
        this.pointThree = pointThree;
        this.pointFour = pointFour;
        this.ID = IdGenerator.getId();
        edgeLength = TetrahedronCalculator.calculateEdgeLength(pointOne,pointTwo); }

    public Tetrahedron(List<Point3D> allPoints){
        this.ID = IdGenerator.getId();
        this.pointOne = allPoints.get(0);
        this.pointTwo = allPoints.get(1);
        this.pointThree = allPoints.get(2);
        this.pointFour = allPoints.get(3);
        edgeLength = TetrahedronCalculator.calculateEdgeLength(pointOne,pointTwo); }

    public Point3D getPointOne() {
        return pointOne;
    }

    public Point3D getPointTwo() {
        return pointTwo;
    }

    public Point3D getPointThree() {
        return pointThree;
    }
    public Point3D getPointFour() {
        return pointFour;
    }
    public List<Point3D> getAllPoints(){
        return Arrays.asList(pointOne,pointTwo,pointThree,pointFour);
    }
    public long getID() {
        return ID;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        int PRIME = 31;
        hash = PRIME*hash +pointOne.hashCode();
        hash = PRIME*hash +pointTwo.hashCode();
        hash = PRIME*hash +pointThree.hashCode();
        hash = PRIME*hash +pointFour.hashCode();
        return hash;
        }

    public boolean equals(Object obj) {
        if (this == obj){
            return true;}
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;}
        if (!this.pointOne.equals(((Tetrahedron) obj).pointOne) || !this.pointTwo.equals(((Tetrahedron) obj).pointTwo) || !this.pointThree.equals(((Tetrahedron) obj).pointThree) || !this.pointFour.equals(((Tetrahedron) obj).pointFour))
            return false;
        return true; }

    @Override
    public String toString() {
        return "Tetrahedron" + ID + ":" + "\n" + pointOne + "\n" + pointTwo + "\n" + pointThree + "\n" + pointFour;
    }

    public void setPointOne(Point3D pointOne) {
        this.pointOne = pointOne;
    }

    public void setPointTwo(Point3D pointTwo) {
        this.pointTwo = pointTwo;
    }

    public void setPointThree(Point3D pointThree) {
        this.pointThree = pointThree;
    }

    public void setPointFour(Point3D pointFour) {
        this.pointFour = pointFour;
    }

    public void setEdgeLength(double edgeLength) {
        this.edgeLength = edgeLength;
    }
}
