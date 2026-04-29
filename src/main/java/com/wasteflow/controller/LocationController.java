package com.wasteflow.controller;

import com.wasteflow.entity.WasteLocation;
import com.wasteflow.repository.WasteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private WasteLocationRepository locationRepository;

    @GetMapping
    public ResponseEntity<List<WasteLocation>> getAllLocations() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<WasteLocation> createLocation(@RequestBody WasteLocation location) {
        return ResponseEntity.ok(locationRepository.save(location));
    }
}
