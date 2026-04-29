package com.wasteflow.repository;

import com.wasteflow.entity.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
    Optional<WasteCategory> findByNamaKategoriIgnoreCase(String namaKategori);
}
