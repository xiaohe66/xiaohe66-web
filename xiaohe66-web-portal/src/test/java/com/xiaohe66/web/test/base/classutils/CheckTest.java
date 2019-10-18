package com.xiaohe66.web.test.base.classutils;

import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh
 * @version 1.0
 * @time 2018-07-05 09:19
 */
public class CheckTest {

    @Test(expected = XhWebException.class)
    public void test1(){
        Check.notEmptyCheck(new ArrayList<>(0));
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>(1);
        list.add("1");
        Check.notEmptyCheck(list);
    }

    @Test(expected = XhWebException.class)
    public void test3(){
        Check.notEmptyCheck("");
    }

    @Test
    public void test4(){
        Check.notEmptyCheck("1");
    }

}
