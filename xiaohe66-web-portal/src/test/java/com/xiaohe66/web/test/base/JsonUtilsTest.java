package com.xiaohe66.web.test.base;

import com.xiaohe66.web.base.util.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * @author xiaohe
 * @time 18-01-22 022
 */
public class JsonUtilsTest {

    @Test
    public void test1(){
        String jsonObj = "{\"name\":\"xiaohe\",\"age\":23}";
        Map<String,Object> map = new HashMap<String,Object>(2);
        map.put("name","xiaohe");
        map.put("age",23);
        assertEquals(jsonObj, JsonUtils.toString(map));
    }

    @Test
    public void test2(){
        String jsonArr = "[1,2,3,4]";
        int[] arr = new int[]{1,2,3,4};
        assertEquals(jsonArr, JsonUtils.toString(arr));
    }
}
