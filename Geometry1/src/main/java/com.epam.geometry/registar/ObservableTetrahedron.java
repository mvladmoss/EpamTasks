package com.epam.geometry.registar;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.entity.Tetrahedron;

import java.util.ArrayList;
import java.util.List;

public class ObservableTetrahedron extends Tetrahedron implements Observable<Observer<Tetrahedron>> {

    private List<Observer<Tetrahedron>> observers = new ArrayList<>();

    public ObservableTetrahedron(Point3D pointOne, Point3D pointTwo, Point3D pointThree, Point3D pointFour){
        super(pointOne,pointTwo,pointThree,pointFour);
    }

    ObservableTetrahedron(List<Point3D> list){
        super(list);
    }


    @Override
    public void attach(Observer<Tetrahedron> observer) {
        observers.add(observer);
        observer.update(this);
    }

    @Override
    public void detach(Observer<Tetrahedron> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(this);
        }
    }

    @Override
    public void setEdgeLength(double edgeLength) {
        super.setEdgeLength(edgeLength);
        notifyObservers();
    }
}
