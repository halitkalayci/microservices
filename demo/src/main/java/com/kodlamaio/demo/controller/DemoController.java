package com.kodlamaio.demo.controller;

import com.kodlamaio.demo.client.ExampleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
  private final ExampleClient exampleClient;


  @GetMapping()
  public String get(){
    String response = exampleClient.get();

    return "Response from other service was: " + response;
  }


}
