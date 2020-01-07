package com.xiaohe66.web.code.love.mapper;

import com.xiaohe66.web.base.base.CreateTableMapper;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.love.po.LoverLink;

/**
 * @author xiaohe
 * @time 2020.01.06 16:49
 */
public interface LoverLinkMapper extends IBaseMapper<LoverLink>, CreateTableMapper {

    Integer selectLoverUserId(Integer userId);

}
