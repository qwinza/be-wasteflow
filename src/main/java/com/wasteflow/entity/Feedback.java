package com.wasteflow.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

/**
 * Feedback — Masukan dari Warga terhadap layanan bank sampah.
 * Rating: 1 (buruk) - 5 (sangat baik).
 */
@Entity
@Table(name = "feedbacks")
@SQLDelete(sql = "UPDATE feedbacks SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
public class Feedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String pesan;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private LocalDate tanggal;

    public Feedback() {}

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
