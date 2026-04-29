package com.wasteflow.service;

import com.wasteflow.entity.WasteCategory;
import com.wasteflow.entity.WasteLocation;
import com.wasteflow.entity.WasteOutbound;
import com.wasteflow.repository.WasteCategoryRepository;
import com.wasteflow.repository.WasteLocationRepository;
import com.wasteflow.repository.WasteOutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OutboundService {

    @Autowired
    private WasteOutboundRepository outboundRepository;

    @Autowired
    private WasteLocationRepository locationRepository;

    @Autowired
    private WasteCategoryRepository categoryRepository;

    @Autowired
    private ReportService reportService;

    @Transactional
    public WasteOutbound createOutbound(Long locationId, Long categoryId, BigDecimal weight, String destination) {
        WasteLocation location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        WasteCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Check if there's enough capacity to take out
        BigDecimal currentCategoryStock = reportService.calculateCurrentStockByCategory(locationId, categoryId);
        if (currentCategoryStock.compareTo(weight) < 0) {
            throw new RuntimeException("Not enough stock for this category in the location to outbound.");
        }

        WasteOutbound outbound = new WasteOutbound();
        outbound.setLocation(location);
        outbound.setCategory(category);
        outbound.setBerat(weight);
        outbound.setTujuanDistribusi(destination);
        outbound.setTanggal(LocalDate.now());

        return outboundRepository.save(outbound);
    }

    public List<WasteOutbound> getAllOutbounds() {
        return outboundRepository.findAll();
    }
}
