package com.kodlamaio.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.demo.services.impl.EncryptionService;
import com.kodlamaio.demo.util.DecryptionArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final EncryptionService encryptionService;
  private final ObjectMapper objectMapper;
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new DecryptionArgumentResolver(encryptionService, objectMapper));
  }
}
