package com.pulse.ecommerce.config;

import com.pulse.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; // Import the interface

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler; // Use the interface

    @Autowired
    public SecurityConfig(AuthService authService, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authService = authService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(authService); // "Look up user here"

        provider.setPasswordEncoder(passwordEncoder());     // "Check password using this"
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Okay for internal app, be aware for production
                .authorizeHttpRequests(request -> request

                        // 1. ADMIN ROUTES: Secure all URLs starting with /admin/
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "ORDER_MANAGER", "PRODUCT_MANAGER", "SUPER_ADMIN")

                        // 2. CUSTOMER AUTHENTICATED ROUTES: Require login for cart, orders, etc.
                        .requestMatchers(
                                "/cart/**",
                                "/order/**",
                                "/support/**",
                                "/checkout/**" // Add any other secure paths
                        ).authenticated()

                        // 3. PUBLIC ROUTES: Anyone can access these
                        .requestMatchers(
                                "/",
                                "/home",
                                "/login",
                                "/register",
                                "/search",
                                "/product-details/**", // Allows viewing any product
                                "/css/**",             // Allows CSS files
                                "/js/**",              // Allows JavaScript files
                                "/images/**"           // Allows image files
                        ).permitAll()

                        // 4. Fallback rule: any other URL that wasn't matched needs login
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")

                        // Use our custom handler to redirect based on role
                        .successHandler(authenticationSuccessHandler)

                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}