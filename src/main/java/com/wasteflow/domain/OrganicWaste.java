package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ORGANIC")
public class OrganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Base formula: weight * multiplier + organic bonus
        return (getBerat() * multiplier) * 1.1; 
    }

    @Override
    public String getWasteType() {
        return "ORGANIC";
    }
}
