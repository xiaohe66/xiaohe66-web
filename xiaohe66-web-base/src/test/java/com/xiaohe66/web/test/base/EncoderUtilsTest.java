package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.util.EncoderUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * @author xh
 * @date 18-10-26 026
 */
public class EncoderUtilsTest {

    @Test
    public void test1(){
        String str = "testStr";
        String encoderStr = EncoderUtils.base64Encoder(str);
        System.out.println(encoderStr);

        String decodeStr = EncoderUtils.base64Decode(encoderStr);

        System.out.println(decodeStr);

        assertEquals(decodeStr,str);
    }
}
