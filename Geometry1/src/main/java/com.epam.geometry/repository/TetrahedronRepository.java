package com.epam.geometry.repository;

import com.epam.geometry.entity.Tetrahedron;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TetrahedronRepository implements Repository<Tetrahedron> {
    private List<Tetrahedron> data = new ArrayList<>();

    @Override
    public void add(Tetrahedron object) {
        data.add(object);
    }

    @Override
    public void remove(Tetrahedron tetrahedron) {
        data.remove(tetrahedron);
    }

    @Override
    public List<Tetrahedron> query(Specification<Tetrahedron> specification) {
        return data.stream().
                     filter(specification::specified).
                      collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("TetrahedronRepository:" + "\n");
        for(Tetrahedron tetrahedron : data){
            stringBuilder.append("\t" + tetrahedron + "\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Tetrahedron> sortTetrahedron(Comparator<Tetrahedron> comparator){
        if(comparator != null){
            data.sort(comparator);
        }
        return data;
    }

    public List<Tetrahedron> getRepository(){
        return data;
    }
}
