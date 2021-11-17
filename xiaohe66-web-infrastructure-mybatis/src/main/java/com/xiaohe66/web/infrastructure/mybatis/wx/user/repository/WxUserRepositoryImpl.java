package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxTodoUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.convert.WxUserDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTodoUserDo;
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

    private final WxTodoUserRepositoryImpl wxTodoUserRepository;

    @Override
    protected void insertImpl(WxUser agg) {
        super.insertImpl(agg);
        if (agg.getWxTodoUserOpenId() != null) {

            wxTodoUserRepository.insert(agg.getId(), agg.getWxTodoUserOpenId());
        }
    }

    @Override
    protected void removeByIdImpl(WxUserId id) {
        wxTodoUserRepository.removeById(id.getValue());
        super.removeByIdImpl(id);
    }

    @Override
    protected WxUser getByIdImpl(WxUserId id) {

        WxUser wxUser = getById(id);

        if (wxUser == null) {
            return null;
        }

        WxTodoUserDo todoUserDo = wxTodoUserRepository.getById(wxUser.getId().getValue());

        // TODO : 赋值考虑使用转换器
        if(todoUserDo != null){
            wxUser.setWxTodoUserOpenId(new WxTodoUserOpenId(todoUserDo.getOpenId()));
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
