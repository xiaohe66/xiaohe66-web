package com.xiaohe66.web.test.common.classutils;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xh
 * @date 2018-1-19
 */
public class Obj1 extends BasePoDetailed{

    private int integer;
    private String string;
    private String obj2NotHave;
    private int obj2TypeDifferent;

    public Obj1(){

    }

    public Obj1(int integer, String string, String obj2NotHave, int obj2TypeDifferent) {
        this.integer = integer;
        this.string = string;
        this.obj2NotHave = obj2NotHave;
        this.obj2TypeDifferent = obj2TypeDifferent;
    }

}
