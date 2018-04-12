package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xh
 * @date 18-04-10 010
 */
public class EncoderUtils {

    public static final String UTF_8 = "UTF-8";

    public static String urlEncoder(String str,String enc){
        if(StrUtils.isEmpty(enc)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"enc is empty");
        }
        if(StrUtils.isEmpty(str)){
            return "";
        }
        try {
            return URLEncoder.encode(str,enc);
        } catch (UnsupportedEncodingException e) {
            throw new XhException(CodeEnum.RUNTIME_EXCEPTION);
        }
    }

    public static String urlEncoder(String str){
        return urlEncoder(str,UTF_8);
    }

}
