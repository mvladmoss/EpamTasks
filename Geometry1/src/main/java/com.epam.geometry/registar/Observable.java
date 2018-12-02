package com.epam.geometry.registar;

public interface Observable<T extends Observer> {
    void attach(T observer);
    void detach(T observer);
    void notifyObservers();
}

