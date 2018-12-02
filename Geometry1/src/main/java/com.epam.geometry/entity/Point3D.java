package com.epam.geometry.entity;

import java.util.List;

public class Point3D {
    private double x;
    private double y;
    private double z;

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(List<Double> pointList) {
        this.x = pointList.get(0);
        this.y = pointList.get(1);
        this.z = pointList.get(2);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point3D check = (Point3D) obj;
        double EPSILON = 0.05;
        if (Math.abs(this.x-check.x)>EPSILON || Math.abs(this.y-check.y)>EPSILON || Math.abs(this.z-check.z)>EPSILON)
            return false;
        return true; }

    @Override
    public String toString() {
        return "Point" + "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public int hashCode() {
        final int PRIME_NUMBER = 37;
        int hash = 1;
        hash = PRIME_NUMBER*hash + Double.valueOf(x).hashCode();
        hash = PRIME_NUMBER*hash + Double.valueOf(y).hashCode();
        hash = PRIME_NUMBER*hash + Double.valueOf(z).hashCode();
        return hash;
    }
}

