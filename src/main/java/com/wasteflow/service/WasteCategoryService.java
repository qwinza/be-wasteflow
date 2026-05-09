package com.wasteflow.service;

import com.wasteflow.dto.request.CategoryRequest;
import com.wasteflow.dto.response.CategoryResponse;
import com.wasteflow.entity.WasteCategory;
import com.wasteflow.exception.ResourceNotFoundException;
import com.wasteflow.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteCategoryService {

    @Autowired
    private WasteCategoryRepository categoryRepository;

    /** Ambil semua kategori */
    @Transactional(readOnly = true)
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    /** Ambil satu kategori by ID */
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id) {
        WasteCategory category = findOrThrow(id);
        return CategoryResponse.from(category);
    }

    /** Buat kategori baru */
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        // Cek duplikasi nama
        if (categoryRepository.findByNamaKategoriIgnoreCase(request.getNamaKategori()).isPresent()) {
            throw new IllegalArgumentException(
                "Kategori dengan nama '" + request.getNamaKategori() + "' sudah ada");
        }

        WasteCategory category = new WasteCategory();
        mapRequestToEntity(request, category);
        return CategoryResponse.from(categoryRepository.save(category));
    }

    /** Update kategori yang sudah ada */
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        WasteCategory category = findOrThrow(id);

        // Cek duplikasi nama, tapi izinkan jika nama sama dan milik kategori ini sendiri
        categoryRepository.findByNamaKategoriIgnoreCase(request.getNamaKategori())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException(
                            "Kategori dengan nama '" + request.getNamaKategori() + "' sudah ada");
                    }
                });

        mapRequestToEntity(request, category);
        return CategoryResponse.from(categoryRepository.save(category));
    }

    /** Soft delete kategori */
    @Transactional
    public void delete(Long id) {
        WasteCategory category = findOrThrow(id);
        categoryRepository.delete(category);  // Trigger @SQLDelete → UPDATE is_deleted = true
    }

    // ─── Helper ─────────────────────────────────────────────────────────────

    private WasteCategory findOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WasteCategory", "id", id));
    }

    private void mapRequestToEntity(CategoryRequest request, WasteCategory entity) {
        entity.setNamaKategori(request.getNamaKategori());
        entity.setDeskripsi(request.getDeskripsi());
        entity.setPointMultiplier(request.getPointMultiplier());
        entity.setWasteType(request.getWasteType());
    }
}
