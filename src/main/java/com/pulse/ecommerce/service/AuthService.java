package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public AuthService(){

    }

    @Autowired
    public AuthService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public void registerUser(UserRecord user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(LocalDate.now());
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserRecord user = userRepo.findByEmailOrPhoneNumber(username,username)
                .orElseThrow( () -> new UsernameNotFoundException("No user found in the record"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }

    public boolean emailExist(String email){
        return userRepo.existsByEmail(email);
    }

    public boolean phoneNumberExists(String phoneNumber){
        return userRepo.existsByPhoneNumber(phoneNumber);
    }
}

//                                   ------Another Way -------
//        // 1. Get the Box (Optional)
//        Optional<UserRecord> userBox = userRepo.findByEmailorPhoneNumber(userInput, userInput);
//
//        // 2. Check the Box
//        if (userBox.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }

