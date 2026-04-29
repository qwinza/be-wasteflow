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

            WasteDeposit deposit = depositService.createDeposit(userId, categoryId, locationId, weight);
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
    public ResponseEntity<List<WasteDeposit>> getDepositsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(depositService.getDepositsByUser(userId));
    }
}
