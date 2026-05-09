package com.wasteflow.domain;

/**
 * HazardousWaste — Subclass untuk Sampah B3 (Bahan Berbahaya dan Beracun).
 * Rumus poin: (berat × multiplier) × 0.8
 * Poin lebih rendah sebagai disinsentif membuang B3 sembarangan,
 * namun tetap diberi reward agar warga tidak membuangnya ke lingkungan.
 */
public class HazardousWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Penalty B3: ×0.8 (20% lebih rendah, disinsentif tapi tetap reward)
        return (getBerat() * multiplier) * 0.8;
    }

    @Override
    public String getWasteType() {
        return "HAZARDOUS";
    }
}
