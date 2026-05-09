package com.wasteflow.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "waste_outbounds")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE waste_outbounds SET is_deleted = true WHERE id=?")
@org.hibernate.annotations.SQLRestriction("is_deleted = false")
public class WasteOutbound extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private WasteLocation location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory category;

    @Column(nullable = false)
    private BigDecimal berat;

    @Column(nullable = false)
    private LocalDate tanggal;

    @Column(name = "tujuan_distribusi", nullable = false)
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
