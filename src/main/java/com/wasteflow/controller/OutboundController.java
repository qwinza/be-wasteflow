package com.wasteflow.controller;

import com.wasteflow.dto.request.OutboundRequest;
import com.wasteflow.dto.response.ApiResponse;
import com.wasteflow.dto.response.OutboundResponse;
import com.wasteflow.service.OutboundService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/outbounds")
@CrossOrigin(origins = "*")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<OutboundResponse>> createOutbound(
            @Valid @RequestBody OutboundRequest request) {
        OutboundResponse data = outboundService.createOutbound(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Pengeluaran sampah berhasil dicatat", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OutboundResponse>>> getAllOutbounds() {
        List<OutboundResponse> data = outboundService.getAllOutbounds();
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil data pengeluaran", data));
    }
}
