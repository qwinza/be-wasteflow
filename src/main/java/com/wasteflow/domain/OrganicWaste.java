package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ORGANIC")
public class OrganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        return getBerat() * 2.0; 
    }

    @Override
    public String getWasteType() {
        return "ORGANIC";
    }
}
