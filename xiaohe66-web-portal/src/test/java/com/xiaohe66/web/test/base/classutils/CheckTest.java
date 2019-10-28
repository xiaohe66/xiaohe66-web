package com.xiaohe66.web.test.base.classutils;

import com.xiaohe66.web.base.exception.param.MissingParamException;
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

    private final String testFieldName = "testField";

    @Test(expected = MissingParamException.class)
    public void test1() {
        Check.notEmpty(new ArrayList<>(0), testFieldName);
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>(1);
        list.add("1");
        Check.notEmpty(list, testFieldName);
    }

    @Test(expected = MissingParamException.class)
    public void test3() {
        Check.notEmpty("", testFieldName);
    }

    @Test
    public void test4() {
        Check.notEmpty("1", testFieldName);
    }

    @Test(expected = MissingParamException.class)
    public void testArray() {
        Integer[] arr = new Integer[0];
        Check.notEmpty(arr, testFieldName);
    }

}
