package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INORGANIC")
public class InorganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Base formula: weight * multiplier + inorganic bonus
        return (getBerat() * multiplier) * 1.5; 
    }

    @Override
    public String getWasteType() {
        return "INORGANIC";
    }
}
