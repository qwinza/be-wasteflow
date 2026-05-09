package com.wasteflow.service;

import com.wasteflow.dto.request.FeedbackRequest;
import com.wasteflow.dto.response.FeedbackResponse;
import com.wasteflow.entity.Feedback;
import com.wasteflow.entity.User;
import com.wasteflow.exception.ResourceNotFoundException;
import com.wasteflow.repository.FeedbackRepository;
import com.wasteflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAll() {
        return feedbackRepository.findAll()
                .stream()
                .map(FeedbackResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FeedbackResponse getById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback", "id", id));
        return FeedbackResponse.from(feedback);
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getByUser(Long userId) {
        return feedbackRepository.findByUserId(userId)
                .stream()
                .map(FeedbackResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public FeedbackResponse create(FeedbackRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setPesan(request.getPesan());
        feedback.setRating(request.getRating());
        feedback.setTanggal(LocalDate.now());

        return FeedbackResponse.from(feedbackRepository.save(feedback));
    }

    @Transactional
    public void delete(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback", "id", id));
        feedbackRepository.delete(feedback);
    }
}
