package com.expenseTracker.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig {

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception
            {
                   httpSecurity.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                           .authorizeHttpRequests(authorize->authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                           .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                           .csrf(csrf->csrf.disable());
                   return httpSecurity.build();
            }



}
