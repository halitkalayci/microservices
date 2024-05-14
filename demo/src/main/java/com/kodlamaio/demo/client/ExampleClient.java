package com.kodlamaio.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="clientservice")
public interface ExampleClient {
  @GetMapping("/api/v1/client")
  String get();
}
