package com.xiaohe66.web.code.love.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
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
@TableName("xiaohe66_web_love_daily")
public class Daily extends LovePoDetailed {

    @TableField("`desc`")
    private String desc;
    private Integer mood;

    @Getter
    @AllArgsConstructor
    public enum Mood {

        /**
         * 心情
         */
        HAPPY_A(10, "幸福"),
        HAPPY(20, "开心"),
        SWEET(30, "甜蜜"),
        PLAY(40, "打情骂俏"),
        GENERAL(50, "平淡"),
        EMOTION(60, "小情绪"),
        STORY(70, "睡前故事"),
        LOW(80, "低落"),
        ANGER(90, "生气"),
        SORROWFUL(100, "难过"),
        ANGER_SORROWFUL(110, "生气难过"),
        BACK(120, "备用"),

        ;

        private Integer val;
        private String desc;
    }
}
