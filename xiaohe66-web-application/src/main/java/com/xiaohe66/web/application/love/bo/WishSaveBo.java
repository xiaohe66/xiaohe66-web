package com.xiaohe66.web.application.love.bo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author xiaohe
 * @since 2022.03.29 18:23
 */
@Data
public class WishSaveBo {

    private Long id;
    private String title;
    private String desc;
    private Boolean finished;
    private LocalDate finishDate;
    private String remark;

}
