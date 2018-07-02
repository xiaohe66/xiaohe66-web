package com.xiaohe66.web.test.common;

import com.xiaohe66.web.common.util.PwdUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtilsTest {

    @Test
    public void test1(){
        String pwd = "1";
        String hash = "55f3dab202749cc5cf049f0a80727f90";
        assertEquals(PwdUtils.getHashStr(pwd),hash);
    }
}
