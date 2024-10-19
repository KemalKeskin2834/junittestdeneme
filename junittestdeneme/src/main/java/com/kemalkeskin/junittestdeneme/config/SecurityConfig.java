package com.kemalkeskin.junittestdeneme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;


import javax.sql.DataSource;
/*
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.GET ,"product/products/*").hasRole("ADMİN")
        );

        http.csrf(crsf->crsf.disable());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }




}
*/