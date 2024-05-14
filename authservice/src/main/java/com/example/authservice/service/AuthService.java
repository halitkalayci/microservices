package com.example.authservice.service;


import com.example.authservice.dto.LoginRequest;
import com.example.authservice.dto.RegisterRequest;

public interface AuthService {
  void register(RegisterRequest request);
  String login(LoginRequest request);
}
