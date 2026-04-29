package com.wasteflow.domain;

public class OrganicWaste extends Waste {

    private static final double POINTS_PER_KG = 10.0;

    public OrganicWaste(double weightKg) {
        super(weightKg);
    }

    @Override
    public double calculatePoints() {
        return weightKg * POINTS_PER_KG;
    }

    @Override
    public String getWasteType() {
        return "Organik";
    }
}
