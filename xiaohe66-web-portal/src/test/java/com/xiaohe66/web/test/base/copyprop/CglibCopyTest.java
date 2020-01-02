package com.xiaohe66.web.test.base.copyprop;

import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.test.base.copyprop.model.GetMethodObj;
import com.xiaohe66.web.test.base.copyprop.model.GetSetMethodObj;

/**
 * @author xiaohe
 * @time 2020.01.02 12:22
 */
public class CglibCopyTest {

    public static void main(String[] args) {

        GetSetMethodObj obj1 = new GetSetMethodObj();
        obj1.setName("测试名称");

        GetMethodObj convert = ClassUtils.convert(GetMethodObj.class, obj1);

        System.out.println("原始数据:" + obj1);
        System.out.println("复制结果:" + convert);

    }
}
