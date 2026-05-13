package com.wasteflow.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INORGANIC")
public class InorganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        return getBerat() * 3.0; 
    }

    @Override
    public String getWasteType() {
        return "INORGANIC";
    }
}
