package com.wasteflow.dto.response;

import com.wasteflow.entity.WasteCategory;
import com.wasteflow.entity.WasteType;

import java.time.LocalDateTime;

public class CategoryResponse {

    private Long id;
    private String namaKategori;
    private String deskripsi;
    private Double pointMultiplier;
    private WasteType wasteType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryResponse() {}

    public static CategoryResponse from(WasteCategory entity) {
        CategoryResponse dto = new CategoryResponse();
        dto.id = entity.getId();
        dto.namaKategori = entity.getNamaKategori();
        dto.deskripsi = entity.getDeskripsi();
        dto.pointMultiplier = entity.getPointMultiplier();
        dto.wasteType = entity.getWasteType();
        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();
        return dto;
    }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
