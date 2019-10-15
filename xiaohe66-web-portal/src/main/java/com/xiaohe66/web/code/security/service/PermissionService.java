package com.xiaohe66.web.code.security.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.exception.param.EmptyException;
import com.xiaohe66.web.code.security.mapper.PermissionMapper;
import com.xiaohe66.web.code.security.po.Permission;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class PermissionService extends AbstractService<PermissionMapper, Permission> {

    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    private static Cache<List<Integer>, Set<String>> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    public Set<String> listPermissionInRoleId(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            throw new EmptyException();
        }

        Set<String> permissionSet = cache.getIfPresent(roleIdList);
        if (permissionSet == null) {
            logger.debug("缓存已失效,重新获取权限, 角色id : {}", roleIdList);
            permissionSet = baseMapper.listPermissionInRoleId(roleIdList);
            cache.put(roleIdList, permissionSet);
        } else {
            logger.debug("使用缓存的权限, 角色id : {}", roleIdList);
        }
        return permissionSet;
    }
}
