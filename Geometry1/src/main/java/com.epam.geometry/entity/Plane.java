package com.epam.geometry.entity;

public class Plane {
    private Point3D first;
    private Point3D second;
    private Point3D third;

    public Point3D getFirst() {
        return first;
    }

    public void setFirst(Point3D first) {
        this.first = first;
    }

    public Point3D getSecond() {
        return second;
    }

    public void setSecond(Point3D second) {
        this.second = second;
    }

    public Point3D getThird() {
        return third;
    }

    public void setThird(Point3D third) {
        this.third = third;
    }

    public Plane(Point3D first, Point3D second, Point3D third){
        this.first = first;
        this.second = second;
        this.third = third;

    }
}
