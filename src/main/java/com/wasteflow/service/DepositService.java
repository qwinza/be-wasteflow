package com.wasteflow.service;

import com.wasteflow.domain.HazardousWaste;
import com.wasteflow.domain.InorganicWaste;
import com.wasteflow.domain.OrganicWaste;
import com.wasteflow.domain.Waste;
import com.wasteflow.entity.User;
import com.wasteflow.entity.WasteCategory;
import com.wasteflow.entity.WasteDeposit;
import com.wasteflow.entity.WasteLocation;
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
    public WasteDeposit createDeposit(Long userId, Long categoryId, Long locationId, BigDecimal weight) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        WasteCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        WasteLocation location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        // Check Capacity limit
        BigDecimal currentCapacity = reportService.calculateCurrentCapacity(locationId);
        if (currentCapacity.add(weight).compareTo(location.getKapasitasMaksKg()) > 0) {
            throw new RuntimeException("Location capacity exceeded");
        }

        // Use Domain OOP to calculate points
        Waste waste = createWasteDomainObject(category.getNamaKategori(), weight.doubleValue());
        Double multiplier = category.getPointMultiplier() != null ? category.getPointMultiplier() : 1.0;
        double points = waste.calculatePoints(multiplier);

        WasteDeposit deposit = new WasteDeposit();
        deposit.setUser(user);
        deposit.setCategory(category);
        deposit.setLocation(location);
        deposit.setBerat(weight);
        deposit.setTanggal(LocalDate.now());
        deposit.setPoints(points);

        return depositRepository.save(deposit);
    }

    public List<WasteDeposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    public List<WasteDeposit> getDepositsByUser(Long userId) {
        return depositRepository.findByUserId(userId);
    }

    private Waste createWasteDomainObject(String categoryName, double weight) {
        Waste waste;
        if (categoryName.equalsIgnoreCase("Organik")) {
            waste = new OrganicWaste();
        } else if (categoryName.equalsIgnoreCase("Anorganik")) {
            waste = new InorganicWaste();
        } else if (categoryName.equalsIgnoreCase("B3")) {
            waste = new HazardousWaste();
        } else {
            waste = new InorganicWaste();
        }
        waste.setBerat(weight);
        return waste;
    }
}
