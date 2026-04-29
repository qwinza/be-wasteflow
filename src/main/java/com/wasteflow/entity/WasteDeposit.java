package com.wasteflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "waste_deposits")
public class WasteDeposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private WasteCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private WasteLocation location;

    private BigDecimal berat;
    private LocalDate tanggal;
    private Double points;

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
