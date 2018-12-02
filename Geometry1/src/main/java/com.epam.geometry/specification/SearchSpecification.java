package com.epam.geometry.specification;

import com.epam.geometry.calculate.SurfaceBelongCalculator;
import com.epam.geometry.calculate.TetrahedronCalculator;
import com.epam.geometry.entity.Tetrahedron;
import com.epam.geometry.repository.Specification;

public class SearchSpecification{

    private final static TetrahedronCalculator CALCULATOR = new TetrahedronCalculator();


    private long maxId;
    private double minSquare;
    private double maxVolume;
    private double maxEdgeLength;

    public SearchSpecification(Builder builder) {
        this.maxId = builder.maxId;
        this.minSquare = builder.minSquare;
        this.maxVolume = builder.maxVolume;
        this.maxEdgeLength = builder.maxEdgeLength;
    }

    public final Specification<Tetrahedron> ALL_WITH_ID_LESS_THEN_MAX_ID = t -> t.getID()<maxId;

    public final Specification<Tetrahedron> ALL_LIE_IN_FIRST_QUADRANT = t -> {
        return
                        t.getPointOne().getX() >= 0 &&
                        t.getPointTwo().getX() >= 0 &&
                        t.getPointThree().getX() >= 0 &&
                        t.getPointFour().getX() >= 0 &&

                        t.getPointOne().getY() >= 0 &&
                        t.getPointTwo().getY() >= 0 &&
                        t.getPointThree().getY() >= 0 &&
                        t.getPointFour().getY() >= 0 &&

                        t.getPointOne().getZ() >= 0 &&
                        t.getPointTwo().getZ() >= 0 &&
                        t.getPointThree().getZ() >= 0 &&
                        t.getPointFour().getZ() >= 0;
    };

    public final Specification<Tetrahedron>  ALL_WITH_SQUARE_BIGGER_THEN_MIN_SQUARE = t -> CALCULATOR.calculateSquare(t) > minSquare;

    public final Specification<Tetrahedron> ALL_WITH_VOLUME_LESS_THEN_MAX_VOLUME = t -> CALCULATOR.calculateVolume(t) < maxVolume;

    public final Specification<Tetrahedron> ALL_WITH_EDGE_LENGTH_LESS_THEN_MAX_EDGE_LENGTH = t -> t.getEdgeLength() < maxEdgeLength;

    public static class Builder {

        private long maxId = 1000;
        private double minSquare = 1;
        private double maxVolume = 100_000;
        private double maxEdgeLength = 5;

        public SearchSpecification build() {
            return new SearchSpecification(this);
        }

        public Builder setMaxId(long maxId) {
            this.maxId = maxId;
            return this;
        }

        public Builder setMinSquare(double minSquare) {
            this.minSquare = minSquare;
            return this;
        }

        public Builder setMaxVolume(double maxVolume) {
            this.maxVolume = maxVolume;
            return this;
        }


        public Builder setEdgeLength(double edgeLength) {
            this.maxEdgeLength = edgeLength;
            return this;
        }
    }
}
