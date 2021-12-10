package com.xiaohe66.web.integration.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.10 22:40
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Md5 extends StringValue {

    public Md5(@NonNull String value) {
        super(value);
    }
}
