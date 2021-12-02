package com.xiaohe66.web.integration.util;

import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiaohe
 * @since 2021.12.02 17:13
 */
public class Assert {

    protected Assert() {

    }

    public static void isTrue(boolean bool) {
        if (!bool) {
            throw new BusinessException(ErrorCodeEnum.PARAM_ILLEGAL);
        }
    }

    public static void isFalse(boolean bool) {
        if (bool) {
            throw new BusinessException(ErrorCodeEnum.PARAM_ILLEGAL);
        }
    }

    public static void notNull(Object object) {
        if (object == null) {
            throw new BusinessException(ErrorCodeEnum.PARAM_EMPTY);
        }
    }

    public static void notEmpty(String object) {
        if (object == null || object.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.PARAM_EMPTY);
        }
    }

    public static void notEmpty(Collection<?> object) {
        if (object == null || object.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.PARAM_EMPTY);
        }
    }

    public static void notEmpty(Map<?, ?> object) {
        if (object == null || object.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.PARAM_EMPTY);
        }
    }

    public static void lt(long value, long num) {
        if (value >= num) {
            throw new BusinessException(ErrorCodeEnum.PARAM_ILLEGAL);
        }
    }

    public static void gt(long value, long num) {
        if (value <= num) {
            throw new BusinessException(ErrorCodeEnum.PARAM_ILLEGAL);
        }
    }
}
