package com.kodlamaio.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {
  private static final String ALGORITHM = "RSA";

  public static void saveKeyPair(String path, KeyPair keyPair) throws IOException {
    File directory = new File(path);
    if (!directory.exists()) {
      directory.mkdirs(); // Create the directory and any necessary but nonexistent parent directories
    }

    File privateKeyFile = new File(path + "/private.key");
    File publicKeyFile = new File(path + "/public.key");

    // Create files if they don't exist
    if (!privateKeyFile.exists()) {
      privateKeyFile.createNewFile();
    }
    if (!publicKeyFile.exists()) {
      publicKeyFile.createNewFile();
    }

    // Write private key
    try (FileOutputStream fos = new FileOutputStream(privateKeyFile)) {
      fos.write(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
    }

    // Write public key
    try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
      fos.write(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
    }
  }

  public static KeyPair loadKeyPair(String path) throws Exception {
    File privateKeyFile = new File(path + "/private.key");
    File publicKeyFile = new File(path + "/public.key");

    if (!privateKeyFile.exists() || !publicKeyFile.exists()) {
      KeyPair keyPair = generateKeyPair();
      saveKeyPair(path, keyPair);
      return keyPair;
    }

    byte[] encodedPrivateKey = Base64.getDecoder().decode(new String(readFile(privateKeyFile)));
    byte[] encodedPublicKey = Base64.getDecoder().decode(new String(readFile(publicKeyFile)));

    KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

    PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
    PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

    return new KeyPair(publicKey, privateKey);
  }

  private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }

  private static byte[] readFile(File file) throws IOException {
    try (FileInputStream fis = new FileInputStream(file)) {
      return fis.readAllBytes();
    }
  }
}
