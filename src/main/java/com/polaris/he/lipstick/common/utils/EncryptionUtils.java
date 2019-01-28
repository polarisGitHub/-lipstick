package com.polaris.he.lipstick.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * User: hexie
 * Date: 2019-01-28 21:06
 * Description:
 */
@Slf4j
public class EncryptionUtils {

    public static String AESEncode(String content, String password) {
        try {
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, password);
            return new String(Base64.getUrlEncoder().encode(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            log.info("AES加密错误", e);
        }
        return null;
    }

    public static String AESDecode(String content, String password) {
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE, password);
            return new String(cipher.doFinal(Base64.getUrlDecoder().decode(content)));
        } catch (Exception e) {
            log.info("AES解密错误", e);
        }
        return null;
    }

    private static Cipher initCipher(int opmode, String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
        keyGenerator.init(128, random);
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(opmode, new SecretKeySpec(secretKey.getEncoded(), "AES"));
        return cipher;
    }
}
