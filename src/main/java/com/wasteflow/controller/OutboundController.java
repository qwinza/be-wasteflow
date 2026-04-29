package com.wasteflow.controller;

import com.wasteflow.entity.WasteOutbound;
import com.wasteflow.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/outbounds")
@CrossOrigin(origins = "*")
public class OutboundController {

    @Autowired
    private OutboundService outboundService;

    @PostMapping
    public ResponseEntity<?> createOutbound(@RequestBody Map<String, Object> payload) {
        try {
            Long locationId = Long.valueOf(payload.get("locationId").toString());
            Long categoryId = Long.valueOf(payload.get("categoryId").toString());
            BigDecimal weight = new BigDecimal(payload.get("berat").toString());
            String destination = payload.get("tujuanDistribusi").toString();

            WasteOutbound outbound = outboundService.createOutbound(locationId, categoryId, weight, destination);
            return ResponseEntity.ok(outbound);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<WasteOutbound>> getAllOutbounds() {
        return ResponseEntity.ok(outboundService.getAllOutbounds());
    }
}
