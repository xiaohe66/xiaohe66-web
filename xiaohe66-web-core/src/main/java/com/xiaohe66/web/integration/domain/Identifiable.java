package com.xiaohe66.web.integration.domain;

/**
 * 可辨认的
 *
 * @author xiaohe
 * @since 2021.08.10 10:33
 */
public interface Identifiable<I extends Id> {

    I getId();

}
