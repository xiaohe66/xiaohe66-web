package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.exception.param.IllegalParamException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtils {

    private static final String HASH_ALGORITHM_NAME = "SHA-1";
    private static final SecureRandomNumberGenerator GENERATOR = new SecureRandomNumberGenerator();

    private static final int HASH_PWD_LENGTH = 64;
    private static final int HASH_SEPARATE_INDEX = 40;

    /**
     * 获取密码的hash值（64位）
     * @param pwd   密码明文
     * @return  密码的64位Hash值
     */
    public static String hashPassword(String pwd){
        String salt = GENERATOR.nextBytes().toBase64();
        String hash = new SimpleHash(HASH_ALGORITHM_NAME,pwd,salt).toHex();
        return  hash + salt;
    }

    /**
     * 验证密码明文和密码Hash值是否匹配
     * @param pwd       密码明文
     * @param hashPwd   密码Hash值
     * @return      返回true为匹配，返回false为不匹配
     */
    public static boolean passwordsMatch(String pwd,String hashPwd){
        if (hashPwd == null || hashPwd.length() != HASH_PWD_LENGTH) {
            throw new IllegalParamException();
        }

        String hash = hashPwd.substring(0,HASH_SEPARATE_INDEX);
        String salt = hashPwd.substring(HASH_SEPARATE_INDEX);

        String hash2 = new SimpleHash(HASH_ALGORITHM_NAME,pwd,salt).toHex();
        return hash.equals(hash2);
    }

    public static String createToken(){
        return UUID.randomUUID().toString();
    }
}
