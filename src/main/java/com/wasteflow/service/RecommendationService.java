package com.wasteflow.service;

import com.wasteflow.entity.WasteDeposit;
import com.wasteflow.repository.WasteDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private WasteDepositRepository depositRepository;

    public Map<String, String> getRecommendationsForUser(Long userId) {
        List<WasteDeposit> deposits = depositRepository.findByUserId(userId);

        if (deposits.isEmpty()) {
            return Map.of("recommendation", "Belum ada riwayat setoran. Mulai setor sampahmu untuk mendapatkan poin dan rekomendasi menarik!");
        }

        // Group by category name and sum weights
        Map<String, Double> categoryWeights = deposits.stream()
                .collect(Collectors.groupingBy(
                        d -> d.getCategory().getNamaKategori(),
                        Collectors.summingDouble(d -> d.getBerat().doubleValue())
                ));

        // Find the category with the most weight
        String maxCategory = "";
        double maxWeight = 0;
        for (Map.Entry<String, Double> entry : categoryWeights.entrySet()) {
            if (entry.getValue() > maxWeight) {
                maxCategory = entry.getKey();
                maxWeight = entry.getValue();
            }
        }

        String recommendationText = "";
        if (maxCategory.equalsIgnoreCase("Organik")) {
            recommendationText = "Kamu sudah luar biasa dalam mengelola sampah organik! Pertimbangkan untuk membuat kompos sendiri di rumah.";
        } else if (maxCategory.equalsIgnoreCase("Anorganik")) {
            recommendationText = "Anda banyak menyetor sampah anorganik (plastik, kertas). Cobalah untuk mengurangi penggunaan plastik sekali pakai di kehidupan sehari-hari.";
        } else if (maxCategory.equalsIgnoreCase("B3")) {
            recommendationText = "Terima kasih telah menyetor sampah B3 dengan aman. Pastikan baterai dan elektronik bekas selalu ditangani dengan hati-hati.";
        } else {
            recommendationText = "Terus tingkatkan partisipasi Anda dalam menjaga lingkungan!";
        }

        return Map.of("recommendation", recommendationText);
    }
}
