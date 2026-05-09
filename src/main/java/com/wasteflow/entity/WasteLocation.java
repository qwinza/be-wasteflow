package com.wasteflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

/**
 * WasteLocation — Master data lokasi/titik pengumpulan bank sampah.
 */
@Entity
@Table(name = "waste_locations")
@SQLDelete(sql = "UPDATE waste_locations SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
public class WasteLocation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_lokasi", nullable = false)
    private String namaLokasi;

    private String koordinat;

    @Column(name = "kapasitas_maks_kg", nullable = false)
    private BigDecimal kapasitasMaksKg;

    public WasteLocation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNamaLokasi() { return namaLokasi; }
    public void setNamaLokasi(String namaLokasi) { this.namaLokasi = namaLokasi; }

    public String getKoordinat() { return koordinat; }
    public void setKoordinat(String koordinat) { this.koordinat = koordinat; }

    public BigDecimal getKapasitasMaksKg() { return kapasitasMaksKg; }
    public void setKapasitasMaksKg(BigDecimal kapasitasMaksKg) { this.kapasitasMaksKg = kapasitasMaksKg; }
}
