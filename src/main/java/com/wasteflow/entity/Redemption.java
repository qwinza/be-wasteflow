package com.wasteflow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "redemptions")
public class Redemption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reward_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Reward reward;

    private Integer pointsUsed;
    private LocalDateTime redemptionDate;

    public Redemption() {}

    public Redemption(User user, Reward reward, Integer pointsUsed) {
        this.user = user;
        this.reward = reward;
        this.pointsUsed = pointsUsed;
        this.redemptionDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Reward getReward() { return reward; }
    public void setReward(Reward reward) { this.reward = reward; }

    public Integer getPointsUsed() { return pointsUsed; }
    public void setPointsUsed(Integer pointsUsed) { this.pointsUsed = pointsUsed; }

    public LocalDateTime getRedemptionDate() { return redemptionDate; }
    public void setRedemptionDate(LocalDateTime redemptionDate) { this.redemptionDate = redemptionDate; }
}
