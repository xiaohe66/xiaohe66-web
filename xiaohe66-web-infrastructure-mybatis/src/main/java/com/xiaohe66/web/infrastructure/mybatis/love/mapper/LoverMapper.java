package com.xiaohe66.web.infrastructure.mybatis.love.mapper;

import com.xiaohe66.web.infrastructure.mybatis.love.model.LoverDo;
import com.xiaohe66.web.integration.IBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaohe
 * @since 2021.11.29 12:07
 */
public interface LoverMapper extends IBaseMapper<LoverDo> {

    LoverDo getByAccountId(@Param("accountId") Long accountId);

}
