package com.wasteflow.dto.response;

import com.wasteflow.entity.Feedback;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FeedbackResponse {

    private Long id;
    private Long userId;
    private String namaUser;
    private String pesan;
    private Integer rating;
    private LocalDate tanggal;
    private LocalDateTime createdAt;

    public FeedbackResponse() {}

    public static FeedbackResponse from(Feedback entity) {
        FeedbackResponse dto = new FeedbackResponse();
        dto.id = entity.getId();
        dto.userId = entity.getUser().getId();
        dto.namaUser = entity.getUser().getNama();
        dto.pesan = entity.getPesan();
        dto.rating = entity.getRating();
        dto.tanggal = entity.getTanggal();
        dto.createdAt = entity.getCreatedAt();
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getNamaUser() { return namaUser; }
    public void setNamaUser(String namaUser) { this.namaUser = namaUser; }

    public String getPesan() { return pesan; }
    public void setPesan(String pesan) { this.pesan = pesan; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
