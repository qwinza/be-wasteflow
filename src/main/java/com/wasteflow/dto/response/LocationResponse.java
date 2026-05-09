package com.wasteflow.dto.response;

import com.wasteflow.entity.WasteLocation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LocationResponse {

    private Long id;
    private String namaLokasi;
    private String koordinat;
    private BigDecimal kapasitasMaksKg;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LocationResponse() {}

    public static LocationResponse from(WasteLocation entity) {
        LocationResponse dto = new LocationResponse();
        dto.id = entity.getId();
        dto.namaLokasi = entity.getNamaLokasi();
        dto.koordinat = entity.getKoordinat();
        dto.kapasitasMaksKg = entity.getKapasitasMaksKg();
        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public String getKoordinat() { return koordinat; }
    public void setKoordinat(String koordinat) { this.koordinat = koordinat; }

    public BigDecimal getKapasitasMaksKg() { return kapasitasMaksKg; }
    public void setKapasitasMaksKg(BigDecimal kapasitasMaksKg) { this.kapasitasMaksKg = kapasitasMaksKg; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
