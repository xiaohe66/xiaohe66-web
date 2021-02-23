package com.xiaohe66.web.code.sys.mapper;

import com.xiaohe66.web.base.base.CreateTableMapper;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.sys.po.Menu;

import java.util.List;

/**
 * @author xiaohe
 * @time 2021.02.22 17:47
 */
public interface MenuMapper extends IBaseMapper<Menu>, CreateTableMapper {

    List<Menu> selectByPid(Integer pid);

}
