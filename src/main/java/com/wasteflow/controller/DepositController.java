package com.wasteflow.controller;

import com.wasteflow.entity.WasteDeposit;
import com.wasteflow.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/deposits")
@CrossOrigin(origins = "*")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping
    public ResponseEntity<?> createDeposit(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            Long categoryId = Long.valueOf(payload.get("categoryId").toString());
            Long locationId = Long.valueOf(payload.get("locationId").toString());
            BigDecimal weight = new BigDecimal(payload.get("berat").toString());
            String namaSampah = payload.containsKey("namaSampah") ? payload.get("namaSampah").toString() : null;

            WasteDeposit deposit = depositService.createDeposit(userId, categoryId, locationId, weight, namaSampah);
            return ResponseEntity.ok(deposit);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<WasteDeposit>> getAllDeposits() {
        return ResponseEntity.ok(depositService.getAllDeposits());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WasteDeposit>> getDepositsByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(depositService.getDepositsByUser(userId));
    }

    @GetMapping("/filter/tps/{locationId}")
    public ResponseEntity<?> getDepositsByLocation(@PathVariable("locationId") Long locationId) {
        try {
            List<WasteDeposit> deposits = depositService.getDepositsByLocation(locationId);
            return ResponseEntity.ok(deposits);
        } catch (Exception e) {
            System.err.println("Error fetching deposits for location " + locationId + ": " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Gagal mengambil data: " + e.getMessage()));
        }
    }
}
