package com.wasteflow.dto.request;

import com.wasteflow.entity.WasteType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryRequest {

    @NotBlank(message = "Nama kategori tidak boleh kosong")
    private String namaKategori;

    private String deskripsi;

    @NotNull(message = "Point multiplier tidak boleh kosong")
    @DecimalMin(value = "0.1", message = "Point multiplier minimal 0.1")
    private Double pointMultiplier = 1.0;

    @NotNull(message = "Waste type tidak boleh kosong (ORGANIC / INORGANIC / HAZARDOUS)")
    private WasteType wasteType;

    public CategoryRequest() {}

    public String getNamaKategori() { return namaKategori; }
    public void setNamaKategori(String namaKategori) { this.namaKategori = namaKategori; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public Double getPointMultiplier() { return pointMultiplier; }
    public void setPointMultiplier(Double pointMultiplier) { this.pointMultiplier = pointMultiplier; }

    public WasteType getWasteType() { return wasteType; }
    public void setWasteType(WasteType wasteType) { this.wasteType = wasteType; }
}
