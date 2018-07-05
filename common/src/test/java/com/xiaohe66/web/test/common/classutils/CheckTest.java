package com.xiaohe66.web.test.common.classutils;

import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xh
 * @version 1.0
 * @time 2018-07-05 09:19
 */
public class CheckTest {

    @Test(expected = XhException.class)
    public void test1(){
        Check.notEmptyCheck(new ArrayList<>(0));
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>(1);
        list.add("1");
        Check.notEmptyCheck(list);
    }

    @Test(expected = XhException.class)
    public void test3(){
        Check.notEmptyCheck("");
    }

    @Test
    public void test4(){
        Check.notEmptyCheck("1");
    }

}
