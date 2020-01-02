package com.xiaohe66.web.test.base.copyprop.copy;

import org.springframework.cglib.beans.BeanCopier;

/**
 * @author xiaohe
 * @time 2020.01.02 10:12
 */
public class CglibBeanCopyService extends AbstractCopyService {
    @Override
    public void copy(Object dest, Object orig) {
        BeanCopier beanCopier = BeanCopier.create(orig.getClass(), dest.getClass(), false);
        beanCopier.copy(orig, dest, null);
    }
}