package ru.kpfu.itis.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class PasswordHelper {


    private static String getSaltedPass(String password, String salt) {
        return password + salt;
    }

    /**
     * Generate hash for users with own password
     */
    public static String encrypt(String unencrypted, String salt) {
        return DigestUtils.md5Hex(getSaltedPass(unencrypted, salt));
    }

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

}
