package com.wasteflow.service;

import com.wasteflow.entity.*;
import com.wasteflow.repository.UserRepository;
import com.wasteflow.repository.WasteCategoryRepository;
import com.wasteflow.repository.WasteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * DataSeeder — Mengisi data awal ke database saat aplikasi pertama kali berjalan.
 * Data yang di-seed:
 *   - Akun default (Admin, Super Admin, Warga demo)
 *   - Master data WasteCategory (Organik, Anorganik, B3)
 *   - Master data WasteLocation (3 lokasi bank sampah)
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasteCategoryRepository categoryRepository;

    @Autowired
    private WasteLocationRepository locationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedCategories();
        seedLocations();
    }

    // ─────────────────────────────────────────────────────────────
    // SEED USERS
    // ─────────────────────────────────────────────────────────────

    private void seedUsers() {
        createUserIfNotExists("Super Admin", "superadmin@wasteflow.com", "superadmin123", Role.ADMIN,
            "Kantor Pusat WasteFlow");
        createUserIfNotExists("Admin Bank Sampah", "admin@wasteflow.com", "admin123", Role.ADMIN,
            "Kantor Pusat WasteFlow");
        createUserIfNotExists("Budi Santoso (Demo Warga)", "rt01@wasteflow.com", "rt01123", Role.WARGA,
            "Jl. Melati No. 12, Kawasan Perumahan Indah");
        createUserIfNotExists("Siti Rahayu (Demo Warga)", "warga@wasteflow.com", "warga123", Role.WARGA,
            "Jl. Mawar No. 5, Blok B");
    }

    private void createUserIfNotExists(String nama, String email, String rawPassword, Role role, String alamat) {
        if (!userRepository.existsByEmail(email)) {
            User user = new User();
            user.setNama(nama);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            user.setAlamat(alamat);
            userRepository.save(user);
            System.out.printf("✅ [Seeder] User: %-40s | Email: %-35s | Password: %s%n", nama, email, rawPassword);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // SEED WASTE CATEGORIES
    // ─────────────────────────────────────────────────────────────

    private void seedCategories() {
        // Organik: rumus poin × 1.1 (OrganicWaste.calculatePoints)
        createCategoryIfNotExists(
            "Organik",
            "Sisa makanan, daun, ranting, dan limbah dapur yang dapat dikompos",
            1.0,
            WasteType.ORGANIC
        );

        // Anorganik: rumus poin × 1.5 (InorganicWaste.calculatePoints)
        createCategoryIfNotExists(
            "Anorganik",
            "Plastik, kertas, kardus, logam, dan bahan daur ulang lainnya",
            1.5,
            WasteType.INORGANIC
        );

        // B3: rumus poin × 0.8 (HazardousWaste.calculatePoints)
        createCategoryIfNotExists(
            "B3",
            "Bahan Berbahaya dan Beracun: baterai, elektronik bekas, cat, pestisida",
            2.0,
            WasteType.HAZARDOUS
        );
    }

    private void createCategoryIfNotExists(String nama, String deskripsi,
                                            Double multiplier, WasteType wasteType) {
        if (categoryRepository.findByNamaKategoriIgnoreCase(nama).isEmpty()) {
            WasteCategory category = new WasteCategory();
            category.setNamaKategori(nama);
            category.setDeskripsi(deskripsi);
            category.setPointMultiplier(multiplier);
            category.setWasteType(wasteType);
            categoryRepository.save(category);
            System.out.printf("✅ [Seeder] Kategori: %-12s | WasteType: %-10s | Multiplier: %.1f%n",
                nama, wasteType, multiplier);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // SEED WASTE LOCATIONS
    // ─────────────────────────────────────────────────────────────

    private void seedLocations() {
        createLocationIfNotExists("Bank Sampah Induk RT 01", "-6.9175, 107.6191", new BigDecimal("500.00"));
        createLocationIfNotExists("Bank Sampah Blok B",      "-6.9200, 107.6220", new BigDecimal("300.00"));
        createLocationIfNotExists("Depo Pengumpulan Pusat",  "-6.9150, 107.6150", new BigDecimal("2000.00"));
    }

    private void createLocationIfNotExists(String nama, String koordinat, BigDecimal kapasitas) {
        if (!locationRepository.existsByNamaLokasiIgnoreCase(nama)) {
            WasteLocation location = new WasteLocation();
            location.setNamaLokasi(nama);
            location.setKoordinat(koordinat);
            location.setKapasitasMaksKg(kapasitas);
            locationRepository.save(location);
            System.out.printf("✅ [Seeder] Lokasi: %-35s | Kapasitas: %.0f kg%n", nama, kapasitas);
        }

        // Enforce Core Category Multipliers
        seedOrUpdateCategory("Organik", 2.0);
        seedOrUpdateCategory("Anorganik", 3.0);
        seedOrUpdateCategory("B3", 1.0);
        System.out.println("Core category multipliers enforced.");

        // Seed Default Location if empty
        if (locationRepository.count() == 0) {
            WasteLocation location = new WasteLocation();
            location.setNamaLokasi("TPS Pusat WasteFlow");
            location.setKapasitasMaksKg(new BigDecimal("1000.0"));
            locationRepository.save(location);
            System.out.println("Default location seeded.");
        }
    }

    private void seedOrUpdateCategory(String name, double multiplier) {
        WasteCategory category = categoryRepository.findByNamaKategoriIgnoreCase(name)
                .orElse(new WasteCategory());
        category.setNamaKategori(name);
        category.setPointMultiplier(multiplier);
        categoryRepository.save(category);
    }
}
