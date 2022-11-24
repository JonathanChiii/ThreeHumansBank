//package com.banking.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import com.banking.security.services.UserDetailsServiceImpl;
//
//
//@Configuration
//@EnableWebSecurity // Allows Spring to find and automatically apply the class to the global web security
//@EnableGlobalMethodSecurity(prePostEnabled = true) // Provides AOP security on methods. It enables @PreAuth, @PostAuth,
//// and supports JSR-250
//public class WebSecurityConfig {
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//}
