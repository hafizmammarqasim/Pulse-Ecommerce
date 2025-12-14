package com.pulse.ecommerce.config;

import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

 private final CategoryRepository categoryRepo;
 private final UserRepo userRepo;
 private final PasswordEncoder passwordEncoder;

 public DataSeeder(CategoryRepository categoryRepo,
                       UserRepo userRepo,
                       PasswordEncoder passwordEncoder) {
  this.categoryRepo = categoryRepo;
  this.userRepo = userRepo;
  this.passwordEncoder = passwordEncoder;
 }

 @Override
 public void run(String... args) {

  // Seed default admin + user only if they don't exist
  createUserIfNotExists(
          "Admin", "User",
          "admin@pulse.com",
          "03001234567",
          "1234",
          "ADMIN"
  );

  createUserIfNotExists(
          "John", "Doe",
          "john@pulse.com",
          "03007654321",
          "1234",
          "USER"
  );

  // Seed categories if not present
  if (categoryRepo.count() == 0) {
   Category earbudsCat = new Category();
   earbudsCat.setName("Earbuds");
   earbudsCat.setDescription("True wireless earbuds for music, calls, and workouts.");
   categoryRepo.save(earbudsCat);

   Category headphonesCat = new Category();
   headphonesCat.setName("Headphones");
   headphonesCat.setDescription("On-ear and over-ear headphones for immersive sound.");
   categoryRepo.save(headphonesCat);

   Category speakersCat = new Category();
   speakersCat.setName("Speakers");
   speakersCat.setDescription("Portable and home speakers for music and parties.");
   categoryRepo.save(speakersCat);

   Category watchesCat = new Category();
   watchesCat.setName("Watches");
   watchesCat.setDescription("Smartwatches for fitness tracking and notifications.");
   categoryRepo.save(watchesCat);

   Category powerbanksCat = new Category();
   powerbanksCat.setName("Powerbanks");
   powerbanksCat.setDescription("Portable chargers to keep your devices powered.");
   categoryRepo.save(powerbanksCat);

   System.out.println("✅ Categories seeded!");
  }
 }

 private void createUserIfNotExists(String fName,
                                    String lName,
                                    String email,
                                    String phone,
                                    String rawPassword,
                                    String role) {
  if (!userRepo.existsByEmail(email)) {
   UserRecord user = new UserRecord();
   user.setfName(fName);
   user.setlName(lName);
   user.setEmail(email);
   user.setPhoneNumber(phone);
   user.setPassword(passwordEncoder.encode(rawPassword));
   user.setRole(role);
   user.setRegistrationDate(LocalDate.now());
   user.setLast_loginDate(LocalDate.now());
   userRepo.save(user);
   System.out.println("✅ Created user: " + email + " (" + role + ")");
  }
 }
}
