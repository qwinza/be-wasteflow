package com.wasteflow.service;

import com.wasteflow.domain.Waste;
import com.wasteflow.domain.WasteFactory;
import com.wasteflow.dto.request.DepositRequest;
import com.wasteflow.dto.response.DepositResponse;
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
import java.util.stream.Collectors;

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
    public DepositResponse createDeposit(DepositRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        WasteCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteCategory", "id", request.getCategoryId()));

        WasteLocation location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("WasteLocation", "id", request.getLocationId()));

        BigDecimal currentStock = reportService.calculateCurrentCapacity(location.getId());
        if (currentStock.add(request.getBerat()).compareTo(location.getKapasitasMaksKg()) > 0) {
            throw new IllegalArgumentException(
                String.format("Kapasitas lokasi '%s' terlampaui. Sisa kapasitas: %.2f kg",
                    location.getNamaLokasi(),
                    location.getKapasitasMaksKg().subtract(currentStock).doubleValue()));
        }
        Waste waste = WasteFactory.createWaste(
            category.getWasteType(),
            request.getBerat().doubleValue()
        );
        double multiplier = category.getPointMultiplier() != null ? category.getPointMultiplier() : 1.0;
        double points = waste.calculatePoints(multiplier);

        WasteDeposit deposit = new WasteDeposit();
        deposit.setUser(user);
        deposit.setCategory(category);
        deposit.setLocation(location);
        deposit.setBerat(request.getBerat());
        deposit.setTanggal(LocalDate.now());
        deposit.setPoints(points);

        WasteDeposit saved = depositRepository.save(deposit);
        return DepositResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<DepositResponse> getAllDeposits() {
        return depositRepository.findAll()
                .stream()
                .map(DepositResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DepositResponse> getDepositsByUser(Long userId) {
        // Pastikan user ada
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return depositRepository.findByUserId(userId)
                .stream()
                .map(DepositResponse::from)
                .collect(Collectors.toList());
    }
}
