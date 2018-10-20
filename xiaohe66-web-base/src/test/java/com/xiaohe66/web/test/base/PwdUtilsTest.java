package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.util.PwdUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author xh
 * @time 18-02-25 025
 */

public class PwdUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(PwdUtils.class);

    @Test
    public void test1(){
        String pwd = "1";
        Set<String> set = new HashSet<>();
        int j = 10;
        for (int i = 0; i < j; i++) {
            String hashPwd = PwdUtils.hashPassword(pwd);
            //同一个密码生成的 hash值不能相同
            assertEquals(false,set.contains(hashPwd));
            LOG.debug(hashPwd);
            set.add(hashPwd);

            assertEquals(true,PwdUtils.passwordsMatch(pwd,hashPwd));
        }
    }

}
