package com.wasteflow.repository;

import com.wasteflow.entity.WasteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteLocationRepository extends JpaRepository<WasteLocation, Long> {
    Optional<WasteLocation> findByNamaLokasiIgnoreCase(String namaLokasi);
    boolean existsByNamaLokasiIgnoreCase(String namaLokasi);
}
