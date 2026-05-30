package com.wasteflow.controller;

import com.wasteflow.dto.request.DepositRequest;
import com.wasteflow.dto.response.ApiResponse;
import com.wasteflow.dto.response.DepositResponse;
import com.wasteflow.service.DepositService;

import com.wasteflow.entity.WasteDeposit;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/deposits")
@CrossOrigin(origins = "*")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping
    public ResponseEntity<WasteDeposit> createDeposit(
            @Valid @RequestBody DepositRequest request) {
        WasteDeposit data = depositService.createDeposit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
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
