package com.wasteflow.controller;

import com.wasteflow.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/capacity/{locationId}")
    public ResponseEntity<Map<String, Object>> getCapacityReport(@PathVariable Long locationId) {
        return ResponseEntity.ok(reportService.getCapacityReport(locationId));
    }
}
