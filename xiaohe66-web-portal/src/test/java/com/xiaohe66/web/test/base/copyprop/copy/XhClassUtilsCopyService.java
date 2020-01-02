package com.xiaohe66.web.test.base.copyprop.copy;

import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.base.base.BasePo;
import com.xiaohe66.web.base.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaohe
 * @time 2020.01.02 10:29
 */
public class XhClassUtilsCopyService extends AbstractCopyService {
    @Override
    public void copy(Object dest, Object orig) throws InvocationTargetException, IllegalAccessException {

        BaseDto dest1 = (BaseDto) dest;
        BasePo orig1 = (BasePo) orig;

        ClassUtils.convert(dest1, orig1);

    }
}
