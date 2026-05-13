package com.wasteflow.repository;

import com.wasteflow.entity.WasteDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WasteDepositRepository extends JpaRepository<WasteDeposit, Long> {
    List<WasteDeposit> findByUserId(Long userId);

    @Query("SELECT wd FROM WasteDeposit wd JOIN wd.location l WHERE l.id = :locationId ORDER BY wd.tanggal DESC")
    List<WasteDeposit> findByLocationId(@Param("locationId") Long locationId);

    @Query("SELECT COALESCE(SUM(wd.berat), 0) FROM WasteDeposit wd WHERE wd.location.id = :locationId AND wd.category.id = :categoryId")
    BigDecimal sumBeratByLocationAndCategory(@Param("locationId") Long locationId, @Param("categoryId") Long categoryId);
}
