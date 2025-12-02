package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.User;
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
        return "/login";
    }

    @PostMapping("/login")
    public void verifyLogin(@ModelAttribute("user") User user){

    }

    @GetMapping("/register")
    public String registerUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    public void callRegisterUser(@ModelAttribute("user") User user){
        authService.registerUser(user);
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
