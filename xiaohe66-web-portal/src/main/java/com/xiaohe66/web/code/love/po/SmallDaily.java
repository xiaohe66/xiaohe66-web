package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("xiaohe66_web_love_small_daily")
public class SmallDaily extends LovePoDetailed {

    @TableField("`desc`")
    private String desc;
    private Integer mood;

    @Getter
    public enum Mood {

        MIN(0, "最小值"),

        L0(0, "幸福"),
        L1(1, "开心"),
        L2(2, "打情骂俏"),
        L3(3, "平淡"),
        L4(4, "小情绪"),
        L5(5, "低落"),
        L6(6, "生气"),
        L7(7, "难过"),
        L8(8, "生气+难过"),

        MAX(8, "最大值"),

        ;

        private Integer val;
        private String desc;

        Mood(Integer val, String desc) {
            this.val = val;
            this.desc = desc;
        }
    }
}
