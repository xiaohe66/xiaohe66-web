package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtils {

    private static final String HASH_ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 512;
    private static final String DEFAULT_MD5_MIN = "xh";


    public static String getHashStr(String str){
        return getHashStr(str,DEFAULT_MD5_MIN);
    }

    /**
     * 对给定的字符串加盐后进行哈希运算
     * @param str   准备哈希运算的字符串
     * @param mix   盐
     * @return  结果
     * @throws XhException 参数str为空时，会抛出空该异常
     */
    public static String getHashStr(String str,String mix){
        if(str==null||str.length()==0){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"str is empty");
        }
        return new SimpleHash(HASH_ALGORITHM_NAME, str+mix, null, HASH_ITERATIONS).toString();
    }
}
