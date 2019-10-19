package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
import org.apache.shiro.codec.Base64;

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
            throw new XhWebException(CodeEnum.NULL_EXCEPTION,"enc is empty");
        }
        if(StrUtils.isEmpty(str)){
            return "";
        }
        try {
            return URLEncoder.encode(str,enc);
        } catch (UnsupportedEncodingException e) {
            throw new XhWebException(CodeEnum.RUNTIME_EXCEPTION);
        }
    }

    public static String urlEncoder(String str){
        return urlEncoder(str,UTF_8);
    }

    public static String base64Encoder(String str){
        return Base64.encodeToString(str.getBytes());
    }

    public static String base64Decode(String str){
        return Base64.decodeToString(str.getBytes());
    }

}
