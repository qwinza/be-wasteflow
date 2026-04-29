package com.wasteflow.controller;

import com.wasteflow.entity.Feedback;
import com.wasteflow.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        feedback.setTanggal(LocalDate.now());
        return ResponseEntity.ok(feedbackRepository.save(feedback));
    }
}
