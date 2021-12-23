package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.web.integration.domain.IntValue;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import com.xiaohe66.web.integration.util.Assert;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.23 21:09
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PictureCategory extends IntValue {

    public static final PictureCategory ANCIENT = new PictureCategory(0);
    public static final PictureCategory BIG = new PictureCategory(1);
    public static final PictureCategory RED = new PictureCategory(2);
    public static final PictureCategory SMALL = new PictureCategory(3);
    public static final PictureCategory STUDENT = new PictureCategory(4);

    protected PictureCategory(int value) {
        super(value);
    }

    public static PictureCategory valueOf(Integer value) {
        Assert.notNull(value);
        switch (value) {
            case 0:
                return ANCIENT;
            case 1:
                return BIG;
            case 2:
                return RED;
            case 3:
                return SMALL;
            case 4:
                return STUDENT;
            default:
                throw new BusinessException(ErrorCodeEnum.PARAM_ILLEGAL);
        }
    }
}