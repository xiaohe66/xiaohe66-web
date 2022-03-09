package com.xiaohe66.web.infrastructure.mybatis.sys.sec.mapper;

import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.RoleDo;
import com.xiaohe66.web.integration.MapperSupport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.01 16:59
 */
public interface RoleMapper extends MapperSupport<RoleDo> {

    List<RoleDo> listByAccountId(@Param("accountId") Long accountId);

}
