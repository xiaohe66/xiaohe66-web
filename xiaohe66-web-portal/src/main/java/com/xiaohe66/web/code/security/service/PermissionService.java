package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.security.mapper.PermissionMapper;
import com.xiaohe66.web.code.security.po.Permission;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class PermissionService extends AbstractService<PermissionMapper, Permission> {

    public Set<String> listPermissionInRoleId(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.emptySet();
        }

        return baseMapper.listPermissionInRoleId(roleIdList);
    }
}
