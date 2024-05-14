package com.kodlamaio.clientservice.controller;

import com.kodlamaio.clientservice.entity.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
  @GetMapping
  public String hello(){
    return "hello";
  }

  @GetMapping("entity")
  public List<Example> exampleList()
  {
    List<Example> exampleList = new ArrayList<>();
    exampleList.add(new Example(1,"Deneme"));
    exampleList.add(new Example(2,"Deneme 2"));
    exampleList.add(new Example(3, "Deneme 3"));

    return  exampleList;
  }
}
