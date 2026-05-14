package com.wasteflow.controller;

import com.wasteflow.entity.Reward;
import com.wasteflow.entity.Redemption;
import com.wasteflow.entity.User;
import com.wasteflow.repository.RewardRepository;
import com.wasteflow.repository.RedemptionRepository;
import com.wasteflow.repository.UserRepository;
import com.wasteflow.repository.WasteDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RewardController {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private RedemptionRepository redemptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasteDepositRepository depositRepository;

    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardRepository.findByIsDeletedFalse();
    }

    @PostMapping
    public Reward createReward(@RequestBody Reward reward) {
        return rewardRepository.save(reward);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward rewardDetails) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));

        reward.setName(rewardDetails.getName());
        reward.setPoints(rewardDetails.getPoints());
        reward.setCategory(rewardDetails.getCategory());
        reward.setStock(rewardDetails.getStock());
        reward.setImageUrl(rewardDetails.getImageUrl());
        reward.setDescription(rewardDetails.getDescription());

        return ResponseEntity.ok(rewardRepository.save(reward));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReward(@PathVariable Long id) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));

        reward.setDeleted(true);
        rewardRepository.save(reward);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/redeem")
    public ResponseEntity<?> redeemReward(@PathVariable Long id, @RequestParam Long userId) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 1. Check Stock
        if (reward.getStock() <= 0) {
            return ResponseEntity.badRequest().body("Stok reward habis");
        }

        // 2. Check Points (Sum of deposits - Sum of redemptions)
        double totalEarned = depositRepository.findByUserId(userId).stream()
                .mapToDouble(d -> d.getPoints() != null ? d.getPoints() : 0.0)
                .sum();
        
        double totalSpent = redemptionRepository.findByUserId(userId).stream()
                .mapToDouble(Redemption::getPointsUsed)
                .sum();

        if ((totalEarned - totalSpent) < reward.getPoints()) {
            return ResponseEntity.badRequest().body("Poin tidak cukup");
        }

        // 3. Process Redemption
        reward.setStock(reward.getStock() - 1);
        rewardRepository.save(reward);

        Redemption redemption = new Redemption(user, reward, reward.getPoints());
        redemptionRepository.save(redemption);
        
        return ResponseEntity.ok(redemption);
    }

    @GetMapping("/redemptions/user/{userId}")
    public List<Redemption> getUserRedemptions(@PathVariable Long userId) {
        return redemptionRepository.findByUserId(userId);
    }
}
