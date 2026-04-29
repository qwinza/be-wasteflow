package com.wasteflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "waste_outbounds")
public class WasteOutbound extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private WasteLocation location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private WasteCategory category;

    private BigDecimal berat;
    private LocalDate tanggal;

    @Column(name = "tujuan_distribusi")
    private String tujuanDistribusi;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WasteLocation getLocation() { return location; }
    public void setLocation(WasteLocation location) { this.location = location; }

    public WasteCategory getCategory() { return category; }
    public void setCategory(WasteCategory category) { this.category = category; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getTujuanDistribusi() { return tujuanDistribusi; }
    public void setTujuanDistribusi(String tujuanDistribusi) { this.tujuanDistribusi = tujuanDistribusi; }
}
