package com.employee.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser = User.withUsername("employee")
                .password(passwordEncoder().encode("password"))
                .roles("EMPLOYEE")
                .build();

        UserDetails adminUser = User.withUsername("employer")
                .password(passwordEncoder().encode("password"))
                .roles("EMPLOYER")
                .build();
        return  new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                            .regexMatchers("/employer/all_employees")
                            .hasRole("EMPLOYER")
                            .regexMatchers("/employee/employee")
                            .hasAnyRole("EMPLOYER","EMPLOYEE")
                .regexMatchers("/employer/all_employees")
                .permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();

        return httpSecurity.build();
    }
}