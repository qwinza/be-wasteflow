package com.wasteflow.domain;

import com.wasteflow.entity.WasteType;

/**
 * WasteFactory — Implementasi Factory Pattern untuk membuat objek domain Waste.
 * 
 * Menghindari pengecekan string nama kategori yang brittle.
 * Menggunakan enum WasteType yang tersimpan di WasteCategory sebagai kunci.
 * 
 * Pattern: Factory Method
 * Digunakan oleh: DepositService.createWasteDomainObject()
 */
public class WasteFactory {

    private WasteFactory() {
        // Utility class, tidak boleh di-instantiate
    }

    /**
     * Membuat objek Waste yang sesuai berdasarkan WasteType.
     * Polymorphism terjadi di sini: setiap subclass punya calculatePoints() berbeda.
     *
     * @param wasteType tipe sampah dari WasteCategory
     * @param berat     berat sampah dalam kg
     * @return instance subclass Waste yang sesuai
     */
    public static Waste createWaste(WasteType wasteType, double berat) {
        Waste waste = switch (wasteType) {
            case ORGANIC   -> new OrganicWaste();
            case HAZARDOUS -> new HazardousWaste();
            default        -> new InorganicWaste();  // INORGANIC adalah default
        };
        waste.setBerat(berat);
        return waste;
    }
}
