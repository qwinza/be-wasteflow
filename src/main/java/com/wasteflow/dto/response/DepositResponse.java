package com.wasteflow.dto.response;

import com.wasteflow.entity.WasteDeposit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DepositResponse {

    private Long id;
    private Long userId;
    private String namaUser;
    private Long categoryId;
    private String namaKategori;
    private String wasteType;
    private Long locationId;
    private String namaLokasi;
    private BigDecimal berat;
    private Double points;
    private LocalDate tanggal;
    private LocalDateTime createdAt;

    public DepositResponse() {}

    public static DepositResponse from(WasteDeposit entity) {
        DepositResponse dto = new DepositResponse();
        dto.id = entity.getId();
        dto.userId = entity.getUser().getId();
        dto.namaUser = entity.getUser().getNama();
        dto.categoryId = entity.getCategory().getId();
        dto.namaKategori = entity.getCategory().getNamaKategori();
        dto.wasteType = entity.getCategory().getWasteType().name();
        dto.locationId = entity.getLocation().getId();
        dto.namaLokasi = entity.getLocation().getNamaLokasi();
        dto.berat = entity.getBerat();
        dto.points = entity.getPoints();
        dto.tanggal = entity.getTanggal();
        dto.createdAt = entity.getCreatedAt();
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNamaUser() { return namaUser; }
    public void setNamaUser(String namaUser) { this.namaUser = namaUser; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public String getWasteType() { return wasteType; }
    public void setWasteType(String wasteType) { this.wasteType = wasteType; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public Double getPoints() { return points; }
    public void setPoints(Double points) { this.points = points; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
