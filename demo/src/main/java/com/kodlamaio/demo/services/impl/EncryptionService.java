package com.kodlamaio.demo.services.impl;

import com.kodlamaio.demo.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;

@Service
@RequiredArgsConstructor
public class EncryptionService {
  private final PublicKey publicKey;
  private final PrivateKey privateKey;



  public String encrypt(String data) throws Exception {
    return EncryptionUtil.encrypt(data, publicKey);
  }

  public String decrypt(String encryptedData) throws Exception {
    return EncryptionUtil.decrypt(encryptedData, privateKey);
  }
}
