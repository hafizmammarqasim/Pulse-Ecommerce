package com.pulse.ecommerce.config;

import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    UserRepo userRepo;

    @Autowired
    public LoginSuccessListener(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) throws UsernameNotFoundException {
        Object principal = event.getAuthentication().getPrincipal();

        if(principal instanceof UserDetails userDetails){
            String email = userDetails.getUsername();

            UserRecord user = userRepo.findByEmailOrPhoneNumber(email,email)
                    .orElseThrow(() -> new UsernameNotFoundException("No data found in our Record for this user"));

            user.setLast_loginDate(LocalDate.now());
            userRepo.save(user);
        }
    }
}
