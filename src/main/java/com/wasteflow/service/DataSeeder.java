package com.wasteflow.service;

import com.wasteflow.entity.Role;
import com.wasteflow.entity.User;
import com.wasteflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByEmail("admin@wasteflow.com")) {
            User admin = new User();
            admin.setNama("Super Admin");
            admin.setEmail("admin@wasteflow.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setAlamat("Kantor Pusat WasteFlow");
            userRepository.save(admin);
            System.out.println("Default Admin Account Created: admin@wasteflow.com / admin123");
        }

        if (!userRepository.existsByEmail("superadmin@wasteflow.com")) {
            User admin = new User();
            admin.setNama("Super Admin");
            admin.setEmail("superadmin@wasteflow.com");
            admin.setPassword(passwordEncoder.encode("superadmin123"));
            admin.setRole(Role.ADMIN);
            admin.setAlamat("Kantor Pusat WasteFlow");
            userRepository.save(admin);
            System.out.println("Default Super Admin Account Created: superadmin@wasteflow.com / superadmin123");
        }

        if (!userRepository.existsByEmail("rt01@wasteflow.com")) {
            User warga = new User();
            warga.setNama("Pengurus RT 01 / RW 05");
            warga.setEmail("rt01@wasteflow.com");
            warga.setPassword(passwordEncoder.encode("rt01123"));
            warga.setRole(Role.WARGA);
            warga.setAlamat("Kawasan Perumahan Indah Blok A");
            userRepository.save(warga);
            System.out.println("Default Kawasan Account Created: rt01@wasteflow.com / rt01123");
        }
    }
}
