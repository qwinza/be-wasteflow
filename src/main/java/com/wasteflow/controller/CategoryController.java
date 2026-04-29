package com.wasteflow.controller;

import com.wasteflow.entity.WasteCategory;
import com.wasteflow.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private WasteCategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createCategory(@RequestBody WasteCategory category) {
        return ResponseEntity.ok(categoryRepository.save(category));
    }
}
