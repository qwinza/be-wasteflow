package com.wasteflow.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DepositRequest {

    @NotNull(message = "User ID tidak boleh kosong")
    private Long userId;

    @NotNull(message = "Category ID tidak boleh kosong")
    private Long categoryId;

    @NotNull(message = "Location ID tidak boleh kosong")
    private Long locationId;

    @NotNull(message = "Berat tidak boleh kosong")
    @DecimalMin(value = "0.001", message = "Berat minimal 0.001 kg")
    private BigDecimal berat;

    private String namaSampah;

    public DepositRequest() {}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public BigDecimal getBerat() { return berat; }
    public void setBerat(BigDecimal berat) { this.berat = berat; }

    public String getNamaSampah() { return namaSampah; }
    public void setNamaSampah(String namaSampah) { this.namaSampah = namaSampah; }
}
