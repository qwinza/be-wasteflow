package com.wasteflow.repository;

import com.wasteflow.entity.WasteOutbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WasteOutboundRepository extends JpaRepository<WasteOutbound, Long> {
    List<WasteOutbound> findByLocationId(Long locationId);

    @Query("SELECT COALESCE(SUM(wo.berat), 0) FROM WasteOutbound wo WHERE wo.location.id = :locationId AND wo.category.id = :categoryId")
    BigDecimal sumBeratByLocationAndCategory(Long locationId, Long categoryId);
}
