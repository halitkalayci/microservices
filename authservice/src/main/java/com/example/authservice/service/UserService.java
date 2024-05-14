package com.example.authservice.service;


import com.example.authservice.dto.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  void add(RegisterRequest request);
}
