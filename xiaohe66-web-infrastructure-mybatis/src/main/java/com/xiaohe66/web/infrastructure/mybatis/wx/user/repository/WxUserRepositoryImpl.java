package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.convert.WxUserDataConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxUserDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
@Repository
public class WxUserRepositoryImpl
        extends AbstractMybatisService<WxUserDataConverter, WxUserMapper, WxUserDo, WxUser, WxUserId>
        implements WxUserRepository {

    @Override
    public WxUser getByUnionId(@NonNull WxUnionId unionId) {

        LambdaQueryWrapper<WxUserDo> queryWrapper = new LambdaQueryWrapper<WxUserDo>()
                .eq(WxUserDo::getUnionId, unionId.getValue());

        WxUserDo wxUserDo = getOne(queryWrapper);

        WxUser wxUser = dataConverter.toAgg(wxUserDo);

        saveSnapshot(wxUser);

        return wxUser;
    }
}
