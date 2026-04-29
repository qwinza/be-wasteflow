package com.wasteflow.domain;

public class InorganicWaste extends Waste {

    private static final double POINTS_PER_KG = 20.0;

    public InorganicWaste(double weightKg) {
        super(weightKg);
    }

    @Override
    public double calculatePoints() {
        return weightKg * POINTS_PER_KG;
    }

    @Override
    public String getWasteType() {
        return "Anorganik";
    }
}
