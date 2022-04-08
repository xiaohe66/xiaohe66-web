package com.xiaohe66.web.infrastructure.mybatis.love.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.BaseLongInputDo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author xiaohe66
 * @since 2022-03-28 18:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("love_wish")
public class WishDo extends BaseLongInputDo {

    /*
    CREATE TABLE `love_wish` (
      `id` bigint(20) NOT NULL,
      `create_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者id',
      `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
      `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0:未删除,1:删除）',
      `lover_id` bigint(20) NOT NULL,
      `title` varchar(255) NOT NULL COMMENT '愿望标题',
      `desc` varchar(3200) NOT NULL DEFAULT '' COMMENT '愿望详情',
      `finished` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成',
      `finish_date` date DEFAULT NULL,
      `remark` varchar(3200) NOT NULL DEFAULT '',
      PRIMARY KEY (`id`),
      KEY `nk_lover_id` (`lover_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
     */

    private Long loverId;
    private String title;

    @TableField("`desc`")
    private String desc;
    private Boolean finished;
    private LocalDate finishDate;
    private String remark;

}