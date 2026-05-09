package com.wasteflow.domain;

/**
 * InorganicWaste — Subclass untuk Sampah Anorganik (plastik, logam, kertas, dll).
 * Rumus poin: (berat × multiplier) × 1.5
 * Bonus lebih tinggi karena bernilai jual dan dapat didaur ulang.
 */
public class InorganicWaste extends Waste {

    @Override
    public double calculatePoints(double multiplier) {
        // Bonus anorganik: ×1.5 (50% lebih tinggi, bernilai daur ulang)
        return (getBerat() * multiplier) * 1.5;
    }

    @Override
    public String getWasteType() {
        return "INORGANIC";
    }
}
