package com.pulse.ecommerce.config;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.SupportTicketRepo;
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
 private final SupportTicketRepo supportTicketRepo;

 public DataSeeder(CategoryRepository categoryRepo,
                   UserRepo userRepo,
                   PasswordEncoder passwordEncoder,
                   SupportTicketRepo supportTicketRepo) {
  this.categoryRepo = categoryRepo;
  this.userRepo = userRepo;
  this.passwordEncoder = passwordEncoder;
  this.supportTicketRepo = supportTicketRepo;
 }

 @Override
 public void run(String... args) {

  // 1) Seed admins and managers (same logic you had)
  seedUsers();

  // 2) Seed categories once (only if table is empty)
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

  // 3) Seed one demo support ticket for John (only if none exist)
  var johnOpt = userRepo.findByEmail("john@pulse.com");
  if (johnOpt.isPresent() && supportTicketRepo.count() == 0) {
   SupportTicket sp1 = new SupportTicket();
   sp1.setOrder(null);
   sp1.setCustomer(johnOpt.get());
   sp1.setSubject("How to");
   sp1.setDescription("How to order a Product");
   sp1.setCreatedAt(LocalDate.now());
   sp1.setType(TicketType.COMPLAINT);
   sp1.setStatus(TicketStatus.OPEN);
   supportTicketRepo.save(sp1);
   System.out.println("✅ Demo support ticket seeded for john@pulse.com");
  }

  System.out.println("✅ Database seeded with Admin and User accounts!");
 }

 // === Idempotent user seeding (your original logic) ===
 private void seedUsers() {
  createUserIfNotExists("Super", "Admin", "superadmin@pulse.com", "03000000001", "super123", "ROLE_SUPER_ADMIN");
  createUserIfNotExists("Alice", "ProductMgr", "product@pulse.com", "03000000002", "prod123", "ROLE_PRODUCT_MANAGER");
  createUserIfNotExists("Bob", "OrderMgr", "orders@pulse.com", "03000000003", "order123", "ROLE_ORDER_MANAGER");
  createUserIfNotExists("Super", "Admin", "superadmin@pulse.com", "03000000001", "super123", "SUPER_ADMIN");
  createUserIfNotExists("Alice", "ProductMgr", "product@pulse.com", "03000000002", "prod123", "PRODUCT_MANAGER");
  createUserIfNotExists("Bob", "OrderMgr", "orders@pulse.com", "03000000003", "order123", "ORDER_MANAGER");
 }

 private void createUserIfNotExists(String fName, String lName, String email, String phone, String rawPassword, String role) {
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
   System.out.println("Created admin/user: " + email + " → " + role);
  }
 }
}
