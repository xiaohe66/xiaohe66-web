package com.xiaohe66.web.test.base.copyprop.model;

import com.xiaohe66.web.base.base.BasePo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiaohe
 * @time 2020.01.02 12:20
 */
@ToString
public class GetSetMethodObj extends BasePo {

    @Getter
    @Setter
    private String name;
}
