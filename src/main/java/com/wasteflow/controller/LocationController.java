package com.wasteflow.controller;

import com.wasteflow.dto.request.LocationRequest;
import com.wasteflow.dto.response.ApiResponse;
import com.wasteflow.dto.response.LocationResponse;
import com.wasteflow.service.WasteLocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private WasteLocationService locationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LocationResponse>>> getAll() {
        List<LocationResponse> data = locationService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil data lokasi", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationResponse>> getById(@PathVariable Long id) {
        LocationResponse data = locationService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<LocationResponse>> create(@Valid @RequestBody LocationRequest request) {
        LocationResponse data = locationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Lokasi berhasil dibuat", data));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<LocationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody LocationRequest request) {
        LocationResponse data = locationService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("Lokasi berhasil diupdate", data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Lokasi berhasil dihapus", null));
    }
}
