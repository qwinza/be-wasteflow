package com.wasteflow.service;

import com.wasteflow.dto.request.LocationRequest;
import com.wasteflow.dto.response.LocationResponse;
import com.wasteflow.entity.WasteLocation;
import com.wasteflow.exception.ResourceNotFoundException;
import com.wasteflow.repository.WasteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteLocationService {

    @Autowired
    private WasteLocationRepository locationRepository;

    @Transactional(readOnly = true)
    public List<LocationResponse> getAll() {
        return locationRepository.findAll()
                .stream()
                .map(LocationResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LocationResponse getById(Long id) {
        return LocationResponse.from(findOrThrow(id));
    }

    @Transactional
    public LocationResponse create(LocationRequest request) {
        if (locationRepository.existsByNamaLokasiIgnoreCase(request.getNamaLokasi())) {
            throw new IllegalArgumentException(
                "Lokasi dengan nama '" + request.getNamaLokasi() + "' sudah ada");
        }

        WasteLocation location = new WasteLocation();
        mapRequestToEntity(request, location);
        return LocationResponse.from(locationRepository.save(location));
    }

    @Transactional
    public LocationResponse update(Long id, LocationRequest request) {
        WasteLocation location = findOrThrow(id);

        locationRepository.findByNamaLokasiIgnoreCase(request.getNamaLokasi())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException(
                            "Lokasi dengan nama '" + request.getNamaLokasi() + "' sudah ada");
                    }
                });

        mapRequestToEntity(request, location);
        return LocationResponse.from(locationRepository.save(location));
    }

    @Transactional
    public void delete(Long id) {
        WasteLocation location = findOrThrow(id);
        locationRepository.delete(location);
    }

    // ─── Helper ─────────────────────────────────────────────────────────────

    private WasteLocation findOrThrow(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WasteLocation", "id", id));
    }

    private void mapRequestToEntity(LocationRequest request, WasteLocation entity) {
        entity.setNamaLokasi(request.getNamaLokasi());
        entity.setKoordinat(request.getKoordinat());
        entity.setKapasitasMaksKg(request.getKapasitasMaksKg());
    }
}
