package com.xiaohe66.web.test.base.copyprop.copy;

import org.springframework.beans.BeanUtils;

/**
 * @author xiaohe
 * @time 2020.01.02 10:12
 */
public class SpringBeanUtilsCopyService extends AbstractCopyService {
    @Override
    public void copy(Object dest, Object orig) {
        BeanUtils.copyProperties(orig, dest);
//        BeanUtils.copyProperties(orig, dest,getClass());
    }
}