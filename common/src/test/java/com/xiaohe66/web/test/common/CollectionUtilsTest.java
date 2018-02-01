package com.xiaohe66.web.test.common;

import com.xiaohe66.web.common.util.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author xiaohe
 * @time 18-01-21 021
 */
public class CollectionUtilsTest {

    @Test
    public void isNullTest(){
        assertEquals(true,CollectionUtils.isNull(null));
        assertEquals(false,CollectionUtils.isNull(new ArrayList()));
    }

    @Test
    public void isNotNullTest(){
        assertEquals(false,CollectionUtils.isNotNull(null));
        assertEquals(true,CollectionUtils.isNotNull(new ArrayList()));
    }


    @Test
    public void isEmptyTest(){
        List<String> list = null;
        assertEquals(true,CollectionUtils.isEmpty(list));
        list = new ArrayList<String>();
        assertEquals(true,CollectionUtils.isEmpty(list));
        list.add("");
        assertEquals(false,CollectionUtils.isEmpty(list));
    }

    @Test
    public void isNotEmptyTest(){
        List<String> list = null;
        assertEquals(false,CollectionUtils.isNotEmpty(list));
        list = new ArrayList<String>();
        assertEquals(false,CollectionUtils.isNotEmpty(list));
        list.add("");
        assertEquals(true,CollectionUtils.isNotEmpty(list));
    }

}
