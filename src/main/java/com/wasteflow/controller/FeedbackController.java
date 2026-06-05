package com.wasteflow.controller;

import com.wasteflow.dto.request.FeedbackRequest;
import com.wasteflow.dto.response.ApiResponse;
import com.wasteflow.dto.response.FeedbackResponse;
import com.wasteflow.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getAll() {
        List<FeedbackResponse> data = feedbackService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil semua feedback", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FeedbackResponse>> getById(@PathVariable Long id) {
        FeedbackResponse data = feedbackService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getByUser(@PathVariable Long userId) {
        List<FeedbackResponse> data = feedbackService.getByUser(userId);
        return ResponseEntity.ok(ApiResponse.success("Berhasil mengambil feedback user", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FeedbackResponse>> create(
            @Valid @RequestBody FeedbackRequest request) {
        FeedbackResponse data = feedbackService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Feedback berhasil dikirim, terima kasih!", data));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        feedbackService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Feedback berhasil dihapus", null));
    }
}
