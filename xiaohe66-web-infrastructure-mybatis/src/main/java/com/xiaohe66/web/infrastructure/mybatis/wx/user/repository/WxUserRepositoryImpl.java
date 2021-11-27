package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.convert.WxUserDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTaskUserDo;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxUserDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.29 17:28
 */
@Repository
@AllArgsConstructor
@Slf4j
public class WxUserRepositoryImpl
        extends AbstractMybatisService<WxUserDoConverter, WxUserMapper, WxUserDo, WxUser, WxUserId>
        implements WxUserRepository {

    private final WxTaskUserRepositoryImpl wxTaskUserRepository;

    @Override
    protected void insertImpl(WxUser agg) {
        super.insertImpl(agg);
        if (agg.getWxTaskUserOpenId() != null) {

            wxTaskUserRepository.insert(agg.getId(), agg.getWxTaskUserOpenId());
        }
    }

    @Override
    protected void removeByIdImpl(WxUserId id) {
        wxTaskUserRepository.removeById(id.getValue());
        super.removeByIdImpl(id);
    }

    @Override
    protected WxUser getByIdImpl(WxUserId id) {

        WxUser wxUser = getById(id);

        if (wxUser == null) {
            return null;
        }

        WxTaskUserDo taskUserDo = wxTaskUserRepository.getById(wxUser.getId().getValue());

        // TODO : 赋值考虑使用转换器
        if(taskUserDo != null){
            wxUser.setWxTaskUserOpenId(new WxTaskUserOpenId(taskUserDo.getOpenId()));
        }

        // TODO : love user

        return wxUser;
    }

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
