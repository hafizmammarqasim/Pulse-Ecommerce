package com.pulse.ecommerce;

import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

   @Autowired
    public DataSeeder(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Check if the database is empty
        if (userRepo.count() == 0) {

            // --- CREATE ADMIN ---
            UserRecord admin = new UserRecord();
            admin.setfName("Admin");
            admin.setlName("User");
            admin.setEmail("admin@pulse.com");
            admin.setPhoneNumber("03001234567");

            // CRITICAL: Encrypt the password!
            admin.setPassword(passwordEncoder.encode("1234"));

            admin.setRole("ADMIN");
            admin.setRegistrationDate(LocalDate.now());
            admin.setLast_loginDate(LocalDate.now());

            // --- CREATE NORMAL USER (Optional, for testing) ---
            UserRecord user = new UserRecord();
            user.setfName("John");
            user.setlName("Doe");
            user.setEmail("john@pulse.com");
            user.setPhoneNumber("03007654321");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole("USER");
            user.setRegistrationDate(LocalDate.now());
            user.setLast_loginDate(LocalDate.now());

            // Save both
            userRepo.save(admin);
            userRepo.save(user);

            System.out.println("✅ Database seeded with Admin and User accounts!");
        }
    }
}
