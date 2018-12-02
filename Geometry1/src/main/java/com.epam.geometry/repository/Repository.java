package com.epam.geometry.repository;

import com.epam.geometry.entity.Tetrahedron;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public interface Repository<T>{
    void add(T object);
    void remove(T t);
    List<T> query(Specification<T> specification);
    List<T> sortTetrahedron(Comparator<T> comparator);
}

