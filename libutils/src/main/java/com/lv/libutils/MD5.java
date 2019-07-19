package com.lv.libutils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * MD5加密工具类
 */
@SuppressWarnings("all")
public class MD5 {

    // MD5加密
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            return toHexString(messageDigest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            Logger.output("MD5 Exception:" + e.getMessage());
            return "";
        }
    }


    private static String toHexString(byte[] b) { // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte aB : b) {
            sb.append(HEX_DIGITS[(aB & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[aB & 0x0f]);
        }
        return sb.toString();
    }

}