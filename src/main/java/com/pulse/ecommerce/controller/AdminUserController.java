package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // ⭐️ IMPORT THIS

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService userService;

    // Best Practice: Use constructor injection instead of @Autowired on fields
    @Autowired
    public AdminUserController(AdminUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/admin-users-list";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "admin/admin-user-create";
    }

    // ⭐️ UPGRADED METHOD TO HANDLE ERRORS ⭐️
    @PostMapping("/create")
    public String createAdmin(@RequestParam String fname,
                              @RequestParam String lname,
                              @RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String password,
                              @RequestParam String role,
                              RedirectAttributes redirectAttributes) { // Add this parameter
        try {
            userService.createNewAdmin(fname, lname, email, phone, password, role);
            // On success, add a success message to show on the user list page
            redirectAttributes.addFlashAttribute("success", "Admin user '" + email + "' created successfully!");
            return "redirect:/admin/users";
        } catch (IllegalArgumentException e) {
            // On failure, add the error message from the service
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            // Send back the user's input so they don't have to re-type it
            redirectAttributes.addFlashAttribute("fname", fname);
            redirectAttributes.addFlashAttribute("lname", lname);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("phone", phone);
            // Redirect BACK to the create form page
            return "redirect:/admin/users/create";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long userId, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("success", "User with ID " + userId + " has been deleted.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Could not delete user. It may be linked to existing orders.");
        }
        return "redirect:/admin/users";
    }
}