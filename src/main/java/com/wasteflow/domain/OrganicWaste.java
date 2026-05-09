package com.wasteflow.domain;

/**
 * OrganicWaste — Subclass untuk Sampah Organik.
 * Rumus poin: (berat × multiplier) × 1.1
 * Bonus 10% karena sampah organik mudah dikompos.
 */
public class OrganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Bonus organik: ×1.1 (10% lebih tinggi)
        return (getBerat() * multiplier) * 1.1;
    }

    @Override
    public String getWasteType() {
        return "ORGANIC";
    }
}
