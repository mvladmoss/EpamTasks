package com.epam.geometry.parsers;

import com.epam.geometry.entity.Point3D;
import com.epam.geometry.exception.IncorrectInputDataException;

import java.util.List;

public interface Parser {
    List<Point3D> parse(String s) throws IncorrectInputDataException;
}
