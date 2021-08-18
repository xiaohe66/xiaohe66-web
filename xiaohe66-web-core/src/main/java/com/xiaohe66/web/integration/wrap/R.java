package com.xiaohe66.web.integration.wrap;

import lombok.Getter;

/**
 * @author xiaohe
 * @since 2021.08.13 14:46
 */
@Getter
public class R<T> {

    public static final int SUCCESS = 10000;
    public static final int ERROR = -1;

    private int code;
    private String msg;
    private T data;

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static R<Void> ok() {
        return new R<>(SUCCESS, null, null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(SUCCESS, data, null);
    }

    public static <T> R<T> ok(T data, String msg) {
        return new R<>(SUCCESS, data, msg);
    }

    public static R<Void> err(String msg) {
        return new R<>(-1, null, msg);
    }

    public static R<Void> build(int code, String msg) {
        return new R<>(code, null, msg);
    }

    public static <T> R<T> build(int code, String msg, T data) {
        return new R<>(code, data, msg);
    }
}
