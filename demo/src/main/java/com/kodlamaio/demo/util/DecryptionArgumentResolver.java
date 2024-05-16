package com.kodlamaio.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodlamaio.demo.annotations.Encrypted;
import com.kodlamaio.demo.services.impl.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Type;

@RequiredArgsConstructor
public class DecryptionArgumentResolver implements HandlerMethodArgumentResolver {


  private final EncryptionService encryptionService;
  private final ObjectMapper objectMapper;


  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(Encrypted.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {
    String encryptedData = webRequest.getParameter(parameter.getParameterName());
    if (encryptedData == null) {
      throw new HttpMessageNotReadableException("Missing encrypted data");
    }
    String decryptedData = encryptionService.decrypt(encryptedData);
    Type targetType = parameter.getGenericParameterType();
    return objectMapper.readValue(decryptedData, objectMapper.constructType(targetType));
  }


}
