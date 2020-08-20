package com.lan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsConfig() {
    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://192.168.22.87:5500");
        corsConfiguration.addAllowedOrigin("http://localhost:5500");
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:5500");
//        corsConfiguration.addAllowedOrigin("http://127.0.0.1:8080");
//        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource  corsSource=new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",corsConfiguration);
        return  new CorsFilter(corsSource);
    }

}
