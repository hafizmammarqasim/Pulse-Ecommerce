package com.pulse.ecommerce.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

// CRITICAL: @Component tells Spring to manage this class
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Get the list of roles for the user who just logged in
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Loop through their roles
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            // Check if the role is one of the admin types
            if (role.equals("ROLE_ADMIN") ||
                    role.equals("ROLE_ORDER_MANAGER") ||
                    role.equals("ROLE_PRODUCT_MANAGER") ||
                    role.equals("ROLE_SUPER_ADMIN"))
            {
                // If they are any kind of admin, send them to the admin dashboard
                response.sendRedirect("/admin/dashboard");
                return; // Stop processing immediately
            }
        }

        // If the loop finishes and no admin role was found, they must be a customer.
        // Send them to the home page.
        response.sendRedirect("/");
    }
}
