package com.wasteflow.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OutboundRequest {

    @NotNull(message = "Location ID tidak boleh kosong")
    private Long locationId;

    @NotNull(message = "Category ID tidak boleh kosong")
    private Long categoryId;

    @NotNull(message = "Berat tidak boleh kosong")
    @DecimalMin(value = "0.001", message = "Berat minimal 0.001 kg")
    private BigDecimal berat;

    @NotBlank(message = "Tujuan distribusi tidak boleh kosong")
    private String tujuanDistribusi;

    public OutboundRequest() {}

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public String getTujuanDistribusi() { return tujuanDistribusi; }
    public void setTujuanDistribusi(String tujuanDistribusi) { this.tujuanDistribusi = tujuanDistribusi; }
}
