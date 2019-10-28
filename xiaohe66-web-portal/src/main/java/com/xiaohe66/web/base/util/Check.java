package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.exception.param.MissingParamException;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2019-10-28 11:07
 */
public class Check {

    private Check() {
    }

    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    public static boolean isNotEmpty(Object[] obj) {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(String obj) {
        return obj != null && obj.length() != 0;
    }

    public static boolean isNotEmpty(Collection obj) {
        return obj != null && !obj.isEmpty();
    }

    public static boolean isNotEmpty(Map obj) {
        return obj != null && !obj.isEmpty();
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(String obj) {
        return obj == null || obj.length() == 0;
    }

    public static boolean isEmpty(Collection obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(Map obj) {
        return obj == null || obj.isEmpty();
    }

    public static void notEmpty(Object obj) {
        if (obj == null) {
            throw new MissingParamException();
        }
    }

    public static void notEmpty(String obj) {
        if (obj == null || obj.length() == 0) {
            throw new MissingParamException();
        }
    }

    public static void notEmpty(Collection obj) {
        if (obj == null || obj.isEmpty()) {
            throw new MissingParamException();
        }
    }

    public static void notEmpty(Map obj) {
        if (obj == null || obj.isEmpty()) {
            throw new MissingParamException();
        }
    }

    public static void notEmpty(Object obj, String paramName) {
        if (obj == null) {
            throw new MissingParamException(paramName);
        }
    }

    public static void notEmpty(Object[] obj, String paramName) {
        if (obj == null || obj.length == 0) {
            throw new MissingParamException(paramName);
        }
    }

    public static void notEmpty(String obj, String paramName) {
        if (obj == null || obj.length() == 0) {
            throw new MissingParamException(paramName);
        }
    }

    public static void notEmpty(Collection obj, String paramName) {
        if (obj == null || obj.isEmpty()) {
            throw new MissingParamException(paramName);
        }
    }

    public static void notEmpty(Map obj, String paramName) {
        if (obj == null || obj.isEmpty()) {
            throw new MissingParamException(paramName);
        }
    }

}
