package com.wasteflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * WasteDeposit — Transaksi setoran sampah dari Warga ke lokasi bank sampah.
 * Field 'points' dihitung otomatis oleh DepositService menggunakan OOP polymorphism.
 */
@Entity
@Table(name = "waste_deposits")
@SQLDelete(sql = "UPDATE waste_deposits SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
public class WasteDeposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private WasteCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private WasteLocation location;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal berat;

    @Column(nullable = false)
    private LocalDate tanggal;

    @Column(name = "poin_didapat")
    private Double points;

    public WasteDeposit() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public WasteCategory getCategory() { return category; }
    public void setCategory(WasteCategory category) { this.category = category; }

    public WasteLocation getLocation() { return location; }
    public void setLocation(WasteLocation location) { this.location = location; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public Double getPoints() { return points; }
    public void setPoints(Double points) { this.points = points; }
}
