package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HAZARDOUS")
public class HazardousWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Base formula: weight * multiplier + hazardous penalty or flat rate depending on policy
        // Usually hazardous waste gives lower points to discourage mixing, or flat rate for safe disposal.
        return (getBerat() * multiplier) * 0.8; 
    }

    @Override
    public String getWasteType() {
        return "HAZARDOUS";
    }
}
