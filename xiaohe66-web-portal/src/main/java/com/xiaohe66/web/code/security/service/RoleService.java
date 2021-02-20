package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.security.mapper.RoleMapper;
import com.xiaohe66.web.code.security.po.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class RoleService extends AbstractService<RoleMapper, Role> {

    public Set<Integer> listDefaultRoleId() {
        return baseMapper.listDefaultRoleId();
    }

    public Set<String> listRoleNameByUserId(Integer userId) {
        return baseMapper.listRoleNameByUsrId(userId);
    }

    public List<Role> listRoleByUserId(Integer userId) {
        return baseMapper.listRoleByUserId(userId);
    }

    public List<Role> listRoleByUserName(String userName) {
        return baseMapper.listRoleByUserName(userName);
    }

}
