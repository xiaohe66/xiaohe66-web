package com.xiaohe66.web.test.common;

import com.xiaohe66.web.common.util.PwdUtils;
import org.junit.Test;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtilsTest {

    @Test
    public void test1(){
        String pwd = "1";
        String hash = "AMGKltzVRXZSTZ0oSnCx/w==";
        System.out.println(PwdUtils.getHashStr(pwd));
    }
}
