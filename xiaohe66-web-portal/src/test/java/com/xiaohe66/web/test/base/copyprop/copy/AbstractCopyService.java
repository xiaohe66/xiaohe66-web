package com.xiaohe66.web.test.base.copyprop.copy;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaohe
 * @time 2020.01.02 10:11
 */
public abstract class AbstractCopyService {

    /**
     * 计算多次执行的时间
     *
     * @param dest
     * @param orig
     * @param loopTimes
     */
    public void calcExecTime(final Object dest, final Object orig, int loopTimes) {
        long begin = System.currentTimeMillis();
        try {
            for (int i = 0; i < loopTimes; i++) {
                copy(dest, orig);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - begin;
        System.out.println("执行时间(ms):" + time + ", " + this.getClass().getSimpleName());
    }

    /**
     * 抽象对象属性拷贝方法
     *
     * @param dest
     * @param orig
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public abstract void copy(final Object dest, final Object orig) throws InvocationTargetException, IllegalAccessException;

}
