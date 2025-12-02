package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.User;
import com.pulse.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AuthService {
    private UserRepo userRepo;

    public AuthService(){

    }

    @Autowired
    public AuthService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void registerUser(User user){

    }


}

