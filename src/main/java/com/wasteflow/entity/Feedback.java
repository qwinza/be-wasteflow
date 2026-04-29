package com.wasteflow.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String pesan;
    private Integer rating;
    private LocalDate tanggal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getPesan() { return pesan; }
    public void setPesan(String pesan) { this.pesan = pesan; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }
}
