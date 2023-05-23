package com.charge.charge.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().requestMatchers("/createCharge","/saveCharge").hasAnyRole("ADMIN","CASHIER");
        httpSecurity.authorizeHttpRequests().requestMatchers("/showCharge","/updateCharge","/deleteCharge").hasAnyRole("ADMIN");
        httpSecurity.authorizeHttpRequests().requestMatchers("/chargesList").hasAnyRole("ADMIN","CASHIER","USER");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
        return httpSecurity.build();

    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
        SimpleGrantedAuthority cashierAuthority = new SimpleGrantedAuthority("ROLE_CASHIER");
        SimpleGrantedAuthority accountantAuthority = new SimpleGrantedAuthority("ROLE_ACCOUNTANT");

        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Arrays.asList(adminAuthority, userAuthority))
                        .build(),
                User.withUsername("cashier")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Collections.singletonList(cashierAuthority))
                        .build(),
                User.withUsername("accountant")
                        .password(passwordEncoder.encode("123"))
                        .authorities(Collections.singletonList(accountantAuthority))
                        .build()
        );
    }
}