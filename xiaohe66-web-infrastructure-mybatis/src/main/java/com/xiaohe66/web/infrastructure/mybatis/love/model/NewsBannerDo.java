package com.xiaohe66.web.infrastructure.mybatis.love.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.12.19 12:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_news_banner")
public class NewsBannerDo extends BaseLongInputDo {

    private Long createId;
    private Long loverId;
    private Long imageId;
    private Integer sort;

}
