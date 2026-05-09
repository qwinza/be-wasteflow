package com.wasteflow.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waste_categories")
public class WasteCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kategori")
    private String namaKategori;

    private String deskripsi;

    @Column(name = "point_multiplier")
    private Double pointMultiplier = 1.0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public Double getPointMultiplier() { return pointMultiplier; }
    public void setPointMultiplier(Double pointMultiplier) { this.pointMultiplier = pointMultiplier; }
}
