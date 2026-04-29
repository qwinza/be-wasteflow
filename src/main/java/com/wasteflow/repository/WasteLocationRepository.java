package com.wasteflow.repository;

import com.wasteflow.entity.WasteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteLocationRepository extends JpaRepository<WasteLocation, Long> {
}
