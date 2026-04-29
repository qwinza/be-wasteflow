package com.wasteflow.service;

import com.wasteflow.entity.WasteCategory;
import com.wasteflow.repository.WasteCategoryRepository;
import com.wasteflow.repository.WasteDepositRepository;
import com.wasteflow.repository.WasteOutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private WasteDepositRepository depositRepository;

    @Autowired
    private WasteOutboundRepository outboundRepository;

    @Autowired
    private WasteCategoryRepository categoryRepository;

    public BigDecimal calculateCurrentStockByCategory(Long locationId, Long categoryId) {
        BigDecimal totalIn = depositRepository.sumBeratByLocationAndCategory(locationId, categoryId);
        BigDecimal totalOut = outboundRepository.sumBeratByLocationAndCategory(locationId, categoryId);

        if(totalIn == null) totalIn = BigDecimal.ZERO;
        if(totalOut == null) totalOut = BigDecimal.ZERO;

        return totalIn.subtract(totalOut);
    }

    public BigDecimal calculateCurrentCapacity(Long locationId) {
        List<WasteCategory> categories = categoryRepository.findAll();
        BigDecimal totalStock = BigDecimal.ZERO;

        for (WasteCategory category : categories) {
            totalStock = totalStock.add(calculateCurrentStockByCategory(locationId, category.getId()));
        }

        return totalStock;
    }

    public Map<String, Object> getCapacityReport(Long locationId) {
        Map<String, Object> report = new HashMap<>();
        BigDecimal currentCapacity = calculateCurrentCapacity(locationId);
        report.put("locationId", locationId);
        report.put("currentTotalStock", currentCapacity);

        List<WasteCategory> categories = categoryRepository.findAll();
        Map<String, BigDecimal> breakdown = new HashMap<>();
        for (WasteCategory category : categories) {
            breakdown.put(category.getNamaKategori(), calculateCurrentStockByCategory(locationId, category.getId()));
        }
        report.put("breakdown", breakdown);

        return report;
    }
}
