package com.banking.security;

import com.banking.security.jwt.AuthEntryPointJwt;
import com.banking.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.banking.security.services.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Allows Spring to find and automatically apply the class to the global web security
@EnableGlobalMethodSecurity(prePostEnabled = true) // Provides AOP security on methods. It enables @PreAuth, @PostAuth, and supports JSR-250

// if the Authentication process is successful, we can get User's information from an Authentication Object
// Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        userDetails.getUsername();
//        userDetails.getPassword();
//        userDetails.getAuthorities();

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    // Spring Security will load UserDetails to perform authentication and authorization. So it has userDetailsService interface
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    // We need a PasswordEncoder for the DaoAuthenticationProvider. If we don't specify, it will use plain text;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Overrider configure method from the WebSecurityConfigurerAdapter interface.
    // It tells Spring Security how we configure CORS and CSRF, when we have to require all users to be authenticated or not,
    // which filter(AuthTokenFilter) and when we want it to work(filter before UsernamePasswordAuthenticationFilter),
    // which Exception Handler is chosen (AuthEntryPointJwt).
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/customer/**").permitAll()
                .antMatchers("/staff/**").permitAll()
                .antMatchers("/manager/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
