package com.example.pushnotificationwithwebsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/ws/**")
                .permitAll().anyRequest().authenticated())
                .formLogin(withDefaults()).logout((logout) ->
        logout.deleteCookies("remove")
                .invalidateHttpSession(false)
                .logoutUrl("/custom-logout")
                .logoutSuccessUrl("/logout-success")
  			);
        return http.build();
    }
}
