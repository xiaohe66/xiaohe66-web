package com.xiaohe66.web.integration.domain;

import java.io.Serializable;

/**
 * 一个id
 * Id 是一个值对象
 *
 * @author xiaohe
 * @since 2021.08.10 10:31
 */
public interface Id extends ValueObject {

    Serializable getSerializable();
}
