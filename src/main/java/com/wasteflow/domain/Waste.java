package com.wasteflow.domain;

public abstract class Waste {
    protected double weightKg;

    public Waste(double weightKg) {
        this.weightKg = weightKg;
    }

    public abstract double calculatePoints();
    public abstract String getWasteType();
}
