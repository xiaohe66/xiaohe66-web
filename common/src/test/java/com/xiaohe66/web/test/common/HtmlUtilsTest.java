package com.xiaohe66.web.test.common;

import com.xiaohe66.web.common.util.HtmlUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author xh
 * @version 1.0
 * @time 2018-07-16 09:16
 */
public class HtmlUtilsTest {

    @Test
    public void test1(){
        String str1 = "123<script>456</script>789";
        String str2 = "123<SCRIPT>456</SCRIPT>789";
        String str3 = "123<Script>456</Script>789";
        String targetStr = "123789";
        assertEquals(targetStr,HtmlUtils.delScriptTag(str1));
        assertEquals(targetStr,HtmlUtils.delScriptTag(str2));
        assertEquals(targetStr,HtmlUtils.delScriptTag(str3));
    }

}
