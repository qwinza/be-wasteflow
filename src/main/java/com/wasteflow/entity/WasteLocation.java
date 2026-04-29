package com.wasteflow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "waste_locations")
public class WasteLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_lokasi")
    private String namaLokasi;

    private String koordinat;

    @Column(name = "kapasitas_maks_kg")
    private BigDecimal kapasitasMaksKg;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public String getKoordinat() { return koordinat; }
    public void setKoordinat(String koordinat) { this.koordinat = koordinat; }

    public BigDecimal getKapasitasMaksKg() { return kapasitasMaksKg; }
    public void setKapasitasMaksKg(BigDecimal kapasitasMaksKg) { this.kapasitasMaksKg = kapasitasMaksKg; }
}
