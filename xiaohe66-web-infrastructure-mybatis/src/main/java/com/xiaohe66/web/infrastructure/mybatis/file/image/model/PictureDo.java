package com.xiaohe66.web.infrastructure.mybatis.file.image.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.integration.IDo;
import lombok.Data;

/**
 * @author xiaohe
 * @since 2021.12.23 21:28
 */
@Data
@TableName("love_picture")
public class PictureDo implements IDo {

    @TableId(type = IdType.INPUT)
    private Long id;

    private Integer category;

    private String path;

}
