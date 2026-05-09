package com.wasteflow.controller;

import com.wasteflow.dto.request.DepositRequest;
import com.wasteflow.dto.response.ApiResponse;
import com.wasteflow.dto.response.DepositResponse;
import com.wasteflow.service.DepositService;

import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deposits")
@CrossOrigin(origins = "*")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepositResponse>> createDeposit(
            @Valid @RequestBody DepositRequest request) {
        DepositResponse data = depositService.createDeposit(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                    String.format("Setoran berhasil! Poin didapat: %.2f", data.getPoints()), data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepositResponse>>> getAllDeposits() {
        List<DepositResponse> data = depositService.getAllDeposits();
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil data setoran", data));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<DepositResponse>>> getDepositsByUser(
            @PathVariable Long userId) {
        List<DepositResponse> data = depositService.getDepositsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(
            "Berhasil mengambil riwayat setoran user", data));
    }
}
