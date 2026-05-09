package com.wasteflow.entity;

/**
 * Enum untuk jenis sampah.
 * Digunakan oleh WasteCategory untuk menentukan subclass domain OOP
 * (OrganicWaste, InorganicWaste, HazardousWaste) saat menghitung poin.
 */
public enum WasteType {
    ORGANIC,       // Sampah Organik  (poin x 1.1)
    INORGANIC,     // Sampah Anorganik (poin x 1.5)
    HAZARDOUS      // Sampah B3        (poin x 0.8)
}
