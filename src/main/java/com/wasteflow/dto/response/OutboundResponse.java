package com.wasteflow.dto.response;

import com.wasteflow.entity.WasteOutbound;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OutboundResponse {

    private Long id;
    private Long locationId;
    private String namaLokasi;
    private Long categoryId;
    private String namaKategori;
    private BigDecimal berat;
    private String tujuanDistribusi;
    private LocalDate tanggal;
    private LocalDateTime createdAt;

    public OutboundResponse() {}

    public static OutboundResponse from(WasteOutbound entity) {
        OutboundResponse dto = new OutboundResponse();
        dto.id = entity.getId();
        dto.locationId = entity.getLocation().getId();
        dto.namaLokasi = entity.getLocation().getNamaLokasi();
        dto.categoryId = entity.getCategory().getId();
        dto.namaKategori = entity.getCategory().getNamaKategori();
        dto.berat = entity.getBerat();
        dto.tujuanDistribusi = entity.getTujuanDistribusi();
        dto.tanggal = entity.getTanggal();
        dto.createdAt = entity.getCreatedAt();
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public String getTujuanDistribusi() { return tujuanDistribusi; }
    public void setTujuanDistribusi(String tujuanDistribusi) { this.tujuanDistribusi = tujuanDistribusi; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
