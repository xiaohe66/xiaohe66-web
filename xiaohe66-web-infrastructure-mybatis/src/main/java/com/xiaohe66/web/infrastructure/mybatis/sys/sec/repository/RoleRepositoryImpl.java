package com.xiaohe66.web.infrastructure.mybatis.sys.sec.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.domain.sys.sec.repository.RoleRepository;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.convert.RoleDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.mapper.RoleMapper;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.RoleDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.01 16:59
 */
@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl
        extends AbstractMybatisService<RoleDoConverter, RoleMapper, RoleDo, Role, RoleId>
        implements RoleRepository {

    @Override
    protected void insertImpl(Role agg) {

        throw new UnsupportedOperationException();
    }

    @Override
    protected void updateImpl(Role agg, Role snapshot) {

        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeByIdImpl(RoleId id) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Role getByIdImpl(RoleId id) {

        RoleDo roleDo = getById(id.getValue());
        return dataConverter.toAgg(roleDo);
    }

    public List<Role> listByAccountId(AccountId accountId) {

        List<RoleDo> roleDoList = baseMapper.listByAccountId(accountId.getValue());

        return dataConverter.toEntity(roleDoList);
    }

    @Override
    public List<Role> listDefaultRole() {

        LambdaQueryWrapper<RoleDo> queryWrapper = new LambdaQueryWrapper<RoleDo>()
                .eq(RoleDo::getDefaultRole, true);

        List<RoleDo> roleDoList = list(queryWrapper);

        return dataConverter.toEntity(roleDoList);
    }
}
