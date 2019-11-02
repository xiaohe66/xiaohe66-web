package com.xiaohe66.web.code.security.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.security.mapper.RoleMapper;
import com.xiaohe66.web.code.security.po.Role;
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
public class RoleService extends AbstractService<RoleMapper, Role> {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private static Cache<String, List<Role>> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    public Set<Integer> listDefaultRoleId() {
        return baseMapper.listDefaultRoleId();
    }

    public Set<String> listRoleNameByUsrId(Integer usrId) {
        return baseMapper.listRoleNameByUsrId(usrId);
    }

    /**
     * 获取指定用户名的所有角色（缓存30分钟）
     */
    public List<Role> listRoleByUserName(String userName) {
        List<Role> roleList = cache.getIfPresent(userName);
        if (roleList == null) {
            logger.debug("缓存已失效,重新获取角色, 用户名 : {}", userName);
            roleList = baseMapper.listRoleByUserName(userName);
            cache.put(userName, roleList);
        } else {
            logger.debug("使用缓存的角色, 用户名 : {}", userName);
        }
        return roleList;
    }

}
