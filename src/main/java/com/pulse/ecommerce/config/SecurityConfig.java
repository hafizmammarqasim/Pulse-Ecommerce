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

@Configuration
@EnableWebSecurity

public class SecurityConfig {

        //  "Translator" service
        private AuthService authService;

        @Autowired
        public SecurityConfig(AuthService authService){
            this.authService = authService;
        }

        // 2. Define the Encryption Tool (BCrypt)
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.
                authorizeHttpRequests(request ->
                        request.requestMatchers("/login","/register","/home").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(
                        form -> form.loginPage("/login")
                        .loginProcessingUrl("/login").permitAll())
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }
}
