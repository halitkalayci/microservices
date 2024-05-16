package com.kodlamaio.demo.controller;

import com.kodlamaio.demo.annotations.Decrypted;
import com.kodlamaio.demo.annotations.Encrypted;
import com.kodlamaio.demo.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

  @PostMapping("encrypt")
  @Encrypted
  public ResponseEntity<Category> encrypt(@RequestBody Category category)
  {
    return ResponseEntity.ok(category);
  }

  @PostMapping("decrypt")
  public ResponseEntity<Category> decrypt(@Decrypted @RequestBody Category category)
  {
    return ResponseEntity.ok(category);
  }
}
