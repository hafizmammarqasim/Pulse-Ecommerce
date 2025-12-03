package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Role;
import com.pulse.ecommerce.model.UserRecord;
import com.pulse.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(){

    }

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //What is the purpose of this login when we have specified it in the requestMatchers?
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    public void verifyLogin(@ModelAttribute("user") UserRecord userRecord){

    }

    @GetMapping("/register")
    public String registerUser(Model model){
        UserRecord userRecord = new UserRecord();
        model.addAttribute("user", userRecord);
        return "register";
    }

    @PostMapping("/register")
    public String callRegisterUser(@ModelAttribute("user") UserRecord userRecord, Model model){
        String email = userRecord.getEmail();
        String phoneNumber = userRecord.getPhoneNumber();

        if(authService.emailExist(email)){
            model.addAttribute("message","Error: Email is already Registered");
            return "register";
        }
        if(authService.phoneNumberExists(phoneNumber)){
            model.addAttribute("message","Error: Phone Number is already Registered");
            return "register";
        }
        userRecord.setRole(String.valueOf(Role.CUSTOMER));
        authService.registerUser(userRecord);
        return "redirect:/login?registrationSuccess";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
