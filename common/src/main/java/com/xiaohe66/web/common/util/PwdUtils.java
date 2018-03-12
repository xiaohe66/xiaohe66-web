package com.xiaohe66.web.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtils {

    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 1024;

    public static String getHashStr(String str){
        SimpleHash obj = new SimpleHash(HASH_ALGORITHM_NAME, str, null, HASH_ITERATIONS);
        return obj.toBase64();
    }
}
