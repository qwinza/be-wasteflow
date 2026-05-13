package com.wasteflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "waste_deposits")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WasteDeposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WasteCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WasteLocation location;

    @Column(name = "nama_sampah")
    private String namaSampah;

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

    public String getNamaSampah() { return namaSampah; }
    public void setNamaSampah(String namaSampah) { this.namaSampah = namaSampah; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public Double getPoints() { return points; }
    public void setPoints(Double points) { this.points = points; }
}
