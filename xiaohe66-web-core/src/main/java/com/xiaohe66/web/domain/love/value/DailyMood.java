package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.IntValue;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.30 11:58
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DailyMood extends IntValue {


    /**
     * 心情
     * HAPPY_A(10, "幸福"),
     * HAPPY(20, "开心"),
     * SWEET(30, "甜蜜"),
     * PLAY(40, "打情骂俏"),
     * GENERAL(50, "平淡"),
     * EMOTION(60, "小情绪"),
     * STORY(70, "睡前故事"),
     * LOW(80, "低落"),
     * ANGER(90, "生气"),
     * SORROWFUL(100, "难过"),
     * ANGER_SORROWFUL(110, "生气难过"),
     * BACK(120, "备用"),
     */

    public static final DailyMood HAPPY_A = new DailyMood(10);
    public static final DailyMood HAPPY = new DailyMood(20);
    public static final DailyMood SWEET = new DailyMood(30);
    public static final DailyMood PLAY = new DailyMood(40);
    public static final DailyMood GENERAL = new DailyMood(50);
    public static final DailyMood EMOTION = new DailyMood(60);
    public static final DailyMood STORY = new DailyMood(70);
    public static final DailyMood LOW = new DailyMood(80);
    public static final DailyMood ANGER = new DailyMood(90);
    public static final DailyMood SORROWFUL = new DailyMood(100);
    public static final DailyMood ANGER_SORROWFUL = new DailyMood(110);
    public static final DailyMood BACK_UP = new DailyMood(120);

    private DailyMood(int value) {
        super(value);
    }

    public static DailyMood valueOf(int value) {
        switch (value) {
            case 10:
                return HAPPY_A;
            case 20:
                return HAPPY;
            case 30:
                return SWEET;
            case 40:
                return PLAY;
            case 50:
                return GENERAL;
            case 60:
                return EMOTION;
            case 70:
                return STORY;
            case 80:
                return LOW;
            case 90:
                return ANGER;
            case 100:
                return SORROWFUL;
            case 110:
                return ANGER_SORROWFUL;
            case 120:
                return BACK_UP;
            default:
                throw new BusinessException(ErrorCodeEnum.PARAM_ERROR, "unknown dailyMood value");
        }
    }
}
