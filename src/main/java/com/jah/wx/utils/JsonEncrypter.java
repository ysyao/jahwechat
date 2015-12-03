package com.jah.wx.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

public class JsonEncrypter {
    public static final String PASSWORD = "jdurw9jk[fak8lfpp980a";

    public <T> String encryptObject(T t) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(t);
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(PASSWORD);
        return textEncryptor.encrypt(json);
    }

    public String decryptObject(String cryptedString) throws Exception {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(PASSWORD);
        return textEncryptor.decrypt(cryptedString);
    }
}
