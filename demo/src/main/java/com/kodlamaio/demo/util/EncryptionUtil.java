package com.kodlamaio.demo.util;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class EncryptionUtil {
  private static final String ALGORITHM = "RSA";
  private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding"; // PKCS1 Padding

  public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptedBytes = Base64.getDecoder().decode(encryptedText);
    return new String(cipher.doFinal(decryptedBytes));
  }
}
