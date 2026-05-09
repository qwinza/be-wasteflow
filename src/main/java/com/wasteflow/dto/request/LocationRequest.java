package com.wasteflow.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class LocationRequest {

    @NotBlank(message = "Nama lokasi tidak boleh kosong")
    private String namaLokasi;

    private String koordinat;

    @NotNull(message = "Kapasitas maksimum tidak boleh kosong")
    @DecimalMin(value = "1.0", message = "Kapasitas minimal 1 kg")
    private BigDecimal kapasitasMaksKg;

    public LocationRequest() {}

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public String getKoordinat() { return koordinat; }
    public void setKoordinat(String koordinat) { this.koordinat = koordinat; }

    public BigDecimal getKapasitasMaksKg() { return kapasitasMaksKg; }
    public void setKapasitasMaksKg(BigDecimal kapasitasMaksKg) { this.kapasitasMaksKg = kapasitasMaksKg; }
}
