package com.kodlamaio.demo.config;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpHead;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.context.ReactorContextWebFilter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfiguration {
  private static final String AUTHORIZATION_HEADER = HttpHeaders.AUTHORIZATION;
  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
          String jwtToken = attributes.getRequest().getHeader(AUTHORIZATION_HEADER);
          if (jwtToken != null && jwtToken.startsWith("Bearer")) {
            jwtToken = jwtToken.substring(7);
            template.header(AUTHORIZATION_HEADER, "Bearer " + jwtToken);
          }
        }
      }
    };
  }
}
