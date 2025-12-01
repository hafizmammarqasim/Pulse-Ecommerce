package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    //What is the purpose of this login when we have specified it in the requestMatchers?
    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }

    @PostMapping("/login")
    public void verifyLogin(@ModelAttribute("user") User user){

    }
}
