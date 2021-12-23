package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.web.integration.domain.IntId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.23 20:52
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PictureId extends IntId {

    public PictureId(Integer value) {
        super(value);
    }
}
