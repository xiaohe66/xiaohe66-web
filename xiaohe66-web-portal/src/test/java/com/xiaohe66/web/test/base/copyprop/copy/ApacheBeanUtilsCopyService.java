package com.xiaohe66.web.test.base.copyprop.copy;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaohe
 * @time 2020.01.02 10:11
 */
public class ApacheBeanUtilsCopyService extends AbstractCopyService {

    @Override
    public void copy(Object dest, Object orig) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.copyProperties(dest, orig);
    }
}
