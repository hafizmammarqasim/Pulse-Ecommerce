package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired private UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    public List<UserRecord> getAllUsers() {
        return userRepo.findAll();
    }

    public void createNewAdmin(String fname, String lname, String email, String phone, String password, String role) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        UserRecord admin = new UserRecord();
        admin.setfName(fname);
        admin.setlName(lname);
        admin.setEmail(email);
        admin.setPhoneNumber(phone);
        admin.setPassword(passwordEncoder.encode(password));
        admin.setRole(role); // "ORDER_MANAGER", "PRODUCT_MANAGER"
        admin.setRegistrationDate(LocalDate.now());
        admin.setLast_loginDate(LocalDate.now());

        userRepo.save(admin);
    }

    public void deleteUser(Long userId) {
        // Prevent deleting yourself (Optional safety check)
        userRepo.deleteById(userId);
    }
}