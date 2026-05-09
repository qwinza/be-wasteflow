package com.wasteflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * WasteCategory — Master data kategori jenis sampah.
 * Field wasteType digunakan oleh WasteFactory (Factory Pattern) untuk
 * memilih subclass domain OOP yang tepat saat menghitung poin setoran.
 */
@Entity
@Table(name = "waste_categories")
@SQLDelete(sql = "UPDATE waste_categories SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
public class WasteCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kategori", nullable = false, unique = true)
    private String namaKategori;

    private String deskripsi;

    @Column(name = "point_multiplier", nullable = false)
    private Double pointMultiplier = 1.0;

    /**
     * Tipe sampah — digunakan WasteFactory untuk polymorphism.
     * Menentukan subclass Waste yang digunakan (OrganicWaste, InorganicWaste, HazardousWaste).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "waste_type", nullable = false)
    private WasteType wasteType = WasteType.INORGANIC;

    public WasteCategory() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public Double getPointMultiplier() { return pointMultiplier; }
    public void setPointMultiplier(Double pointMultiplier) { this.pointMultiplier = pointMultiplier; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }
}
