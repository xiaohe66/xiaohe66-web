package com.xiaohe66.web.code.love.app.po;

import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 小日常
 *
 * @author xiaohe
 * @time 2019.12.06 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmallDaily extends BasePoDetailed {

    private String desc;
    private Integer level;

    @Getter
    public enum  Level{

        L0(0,"幸福"),
        L1(1,"开心"),
        L2(2,"打情骂俏"),
        L3(3,"平淡"),
        L4(4,"小情绪"),
        L5(5,"重要"),
        L6(6,"严重"),
        L7(7,"必须"),

        ;

        private Integer val;
        private String desc;

        Level(Integer val, String desc) {
            this.val = val;
            this.desc = desc;
        }
    }
}
