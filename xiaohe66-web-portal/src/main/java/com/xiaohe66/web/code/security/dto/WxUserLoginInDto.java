package com.xiaohe66.web.code.security.dto;

import com.xiaohe66.web.base.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author xiaohe
 * @time 2019.12.06 16:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxUserLoginInDto extends BaseDto {

    private String openId;
    private BigDecimal lat;
    private BigDecimal lon;
    private Long timestamp;
    private String sign;

}
