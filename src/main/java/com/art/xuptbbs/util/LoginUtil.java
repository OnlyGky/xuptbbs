package com.art.xuptbbs.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginUtil {

    /**
     * 密码加密
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getPassword(String password)  {
        String salt  = String.valueOf( password.hashCode());
        password  +=salt;
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code+salt.substring(0,3);
    }
}
