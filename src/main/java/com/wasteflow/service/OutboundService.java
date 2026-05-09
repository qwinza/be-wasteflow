package com.wasteflow.service;

import com.wasteflow.dto.request.OutboundRequest;
import com.wasteflow.dto.response.OutboundResponse;
import com.wasteflow.entity.WasteCategory;
import com.wasteflow.entity.WasteLocation;
import com.wasteflow.entity.WasteOutbound;
import com.wasteflow.exception.ResourceNotFoundException;
import com.wasteflow.repository.WasteCategoryRepository;
import com.wasteflow.repository.WasteLocationRepository;
import com.wasteflow.repository.WasteOutboundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OutboundService — Business logic untuk pengeluaran/distribusi sampah keluar lokasi.
 * Cek stok per kategori di lokasi sebelum memperbolehkan outbound.
 */
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
    public OutboundResponse createOutbound(OutboundRequest request) {
        WasteLocation location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteLocation", "id", request.getLocationId()));

        WasteCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteCategory", "id", request.getCategoryId()));

        // Cek stok kategori di lokasi ini cukup untuk dikeluarkan
        BigDecimal currentStock = reportService.calculateCurrentStockByCategory(
            location.getId(), category.getId());

        if (currentStock.compareTo(request.getBerat()) < 0) {
            throw new IllegalArgumentException(
                String.format("Stok '%s' di lokasi '%s' tidak cukup. Stok tersedia: %.2f kg, diminta: %.2f kg",
                    category.getNamaKategori(),
                    location.getNamaLokasi(),
                    currentStock.doubleValue(),
                    request.getBerat().doubleValue()));
        }

        WasteOutbound outbound = new WasteOutbound();
        outbound.setLocation(location);
        outbound.setCategory(category);
        outbound.setBerat(request.getBerat());
        outbound.setTujuanDistribusi(request.getTujuanDistribusi());
        outbound.setTanggal(LocalDate.now());

        return OutboundResponse.from(outboundRepository.save(outbound));
    }

    @Transactional(readOnly = true)
    public List<OutboundResponse> getAllOutbounds() {
        return outboundRepository.findAll()
                .stream()
                .map(OutboundResponse::from)
                .collect(Collectors.toList());
    }
}
