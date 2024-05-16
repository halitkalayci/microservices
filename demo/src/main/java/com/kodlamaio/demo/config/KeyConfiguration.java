package com.kodlamaio.demo.config;

import com.kodlamaio.demo.util.KeyUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.*;

@Configuration
public class KeyConfiguration {

  private static final String KEY_DIRECTORY = "keys"; // Anahtar dosyalarının saklanacağı dizin

  @Bean
  public KeyPair keyPair() throws Exception {
    return KeyUtils.loadKeyPair(KEY_DIRECTORY);
  }

  @Bean
  public PublicKey publicKey(KeyPair keyPair) {
    return keyPair.getPublic();
  }

  @Bean
  public PrivateKey privateKey(KeyPair keyPair) {
    return keyPair.getPrivate();
  }
}