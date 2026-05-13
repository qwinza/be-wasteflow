package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HAZARDOUS")
public class HazardousWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        return getBerat() * 1.0; 
    }

    @Override
    public String getWasteType() {
        return "HAZARDOUS";
    }
}
