package com.kodlamaio.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.demo.dto.EncryptedData;
import com.kodlamaio.demo.entity.Product;
import com.kodlamaio.demo.services.impl.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final EncryptionService encryptionService;
  private final ObjectMapper objectMapper;


  @PostMapping("/encrypt")
  public ResponseEntity<String> encryptProduct(@RequestBody Product product) throws Exception {
    String jsonData = objectMapper.writeValueAsString(product);
    String encryptedData = encryptionService.encrypt(jsonData);
    return ResponseEntity.ok(encryptedData);
  }

  @PostMapping("/decrypt")
  public ResponseEntity<Product> decryptProduct(@RequestBody EncryptedData encryptedData) throws Exception {
    String decryptedData = encryptionService.decrypt(encryptedData.getEncryptedData());
    Product product = objectMapper.readValue(decryptedData, Product.class);
    return ResponseEntity.ok(product);
  }
}
