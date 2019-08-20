package com.xiaohe66.web.test.base.classutils;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 2018-1-19
 */
public class Obj2 extends BaseDtoDetailed{

    private Long id;
    private int integer;
    private String string;

    private String obj1NotHave;

    private long typeDifferent;


    public Long getId() {
        return id;
    }

    public int getInteger() {
        return integer;
    }

    public String getString() {
        return string;
    }

    public String getObj1NotHave() {
        return obj1NotHave;
    }

    public long getTypeDifferent() {
        return typeDifferent;
    }

    @Override
    public String toString() {
        return "Obj2{" +
                "createdId=" + createId +
                ", id=" + id +
                ", id=" + id +
                ", createdTime=" + createTime +
                ", integer=" + integer +
                ", string='" + string + '\'' +
                ", updatedId=" + updateId +
                ", updatedTime=" + updateTime +
                ", obj1NotHave='" + obj1NotHave + '\'' +
                ", isDelete=" + isDelete +
                ", typeDifferent=" + typeDifferent +
                '}';
    }
}
