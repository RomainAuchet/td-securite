package com.example.banque.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Angular (localhost:4200) appelle l'API sur un autre port : le navigateur demande d'abord
 * à l'API, par un OPTIONS, si elle l'accepte. Ceci répond à Spring MVC ; une fois Spring
 * Security ajouté, sa chaîne de filtres passe avant et doit aussi laisser passer cet OPTIONS.
 */
@Configuration
public class CorsConfig {

    @Bean
    WebMvcConfigurer corsConfigurer(@Value("${app.cors.origines}") String[] origines) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(origines)
                        .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                        .allowedHeaders("Authorization", "Content-Type");
            }
        };
    }
}
