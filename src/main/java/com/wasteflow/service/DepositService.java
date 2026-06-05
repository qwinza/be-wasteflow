package com.wasteflow.service;

import com.wasteflow.domain.Waste;
import com.wasteflow.domain.WasteFactory;
import com.wasteflow.dto.request.DepositRequest;
import com.wasteflow.entity.User;
import com.wasteflow.entity.WasteCategory;
import com.wasteflow.entity.WasteDeposit;
import com.wasteflow.entity.WasteLocation;
import com.wasteflow.exception.ResourceNotFoundException;
import com.wasteflow.repository.UserRepository;
import com.wasteflow.repository.WasteCategoryRepository;
import com.wasteflow.repository.WasteDepositRepository;
import com.wasteflow.repository.WasteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private WasteDepositRepository depositRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasteCategoryRepository categoryRepository;

    @Autowired
    private WasteLocationRepository locationRepository;

    @Autowired
    private ReportService reportService;

    @Transactional
    public WasteDeposit createDeposit(DepositRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        WasteCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteCategory", "id", request.getCategoryId()));

        WasteLocation location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteLocation", "id", request.getLocationId()));

        BigDecimal currentStock = reportService.calculateCurrentCapacity(location.getId());
        BigDecimal maxCapacity = location.getKapasitasMaksKg();
        
        if (maxCapacity != null && currentStock.add(request.getBerat()).compareTo(maxCapacity) > 0) {
            throw new IllegalArgumentException(
                String.format("Kapasitas lokasi '%s' terlampaui. Sisa kapasitas: %.2f kg",
                    location.getNamaLokasi(),
                    maxCapacity.subtract(currentStock).doubleValue()));
        }

        Waste waste = WasteFactory.createWaste(
            category.getWasteType(),
            request.getBerat().doubleValue()
        );
        double multiplier = category.getPointMultiplier() != null ? category.getPointMultiplier() : 1.0;
        double points = Math.round(waste.calculatePoints(multiplier) * 100.0) / 100.0;

        WasteDeposit deposit = new WasteDeposit();
        deposit.setUser(user);
        deposit.setCategory(category);
        deposit.setLocation(location);
        deposit.setNamaSampah(request.getNamaSampah());
        deposit.setBerat(request.getBerat());
        deposit.setTanggal(LocalDate.now());
        deposit.setPoints(points);

        return depositRepository.save(deposit);
    }

    @Transactional(readOnly = true)
    public List<WasteDeposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<WasteDeposit> getDepositsByUser(Long userId) {
        // Pastikan user ada
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return depositRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<WasteDeposit> getDepositsByLocation(Long locationId) {
        // Pastikan lokasi ada
        locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("WasteLocation", "id", locationId));

        return depositRepository.findByLocationId(locationId);
    }
}
