package com.kodlamaio.clientservice.config;

import com.halitkalayci.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final BaseSecurityService baseSecurityService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
  {
    baseSecurityService.configureCoreSecurity(http);

    http.authorizeHttpRequests(
            (req)->req.requestMatchers("/api/v1/client").authenticated()
    );

    return http.build();
  }
}
