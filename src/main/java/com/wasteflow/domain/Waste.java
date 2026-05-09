package com.wasteflow.domain;

/**
 * Waste — Abstract class sebagai representasi domain "Sampah".
 * Implementasi OOP: Inheritance + Polymorphism.
 * 
 * Subclass: OrganicWaste, InorganicWaste, HazardousWaste
 * Setiap subclass meng-override calculatePoints() dengan rumus berbeda.
 * 
 * CATATAN: Kelas ini adalah POJO murni (bukan JPA Entity).
 * Hanya digunakan untuk business logic di layer Service.
 */
public abstract class Waste {

    private double berat;

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    /**
     * Hitung poin berdasarkan berat dan multiplier kategori.
     * Setiap subclass mengimplementasikan rumus berbeda (Polymorphism).
     *
     * @param multiplier point_multiplier dari WasteCategory
     * @return total poin yang didapat
     */
    public abstract double calculatePoints(double multiplier);

    /**
     * Mengembalikan tipe sampah sebagai String.
     * Berguna untuk logging dan debugging.
     */
    public abstract String getWasteType();
}
