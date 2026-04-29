package com.wasteflow.domain;

public class HazardousWaste extends Waste {

    private static final double POINTS_PER_KG = 5.0;

    public HazardousWaste(double weightKg) {
        super(weightKg);
    }

    @Override
    public double calculatePoints() {
        return weightKg * POINTS_PER_KG;
    }

    @Override
    public String getWasteType() {
        return "B3";
    }
}
