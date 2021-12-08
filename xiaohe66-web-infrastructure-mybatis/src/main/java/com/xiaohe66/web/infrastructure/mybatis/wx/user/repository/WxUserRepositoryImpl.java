package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.convert.WxUserDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxLoveUserDo;
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

    private final WxTaskUserRepositoryImpl taskUserRepository;
    private final WxLoveUserRepositoryImpl loveUserRepository;

    @Override
    protected void insertImpl(WxUser agg) {
        
        super.insertImpl(agg);

        if (agg.getWxTaskUserOpenId() != null) {

            taskUserRepository.insert(agg.getCreateId(), agg.getWxTaskUserOpenId());
        }
        if (agg.getWxLoveUserOpenId() != null) {

            loveUserRepository.insert(agg.getCreateId(), agg.getWxLoveUserOpenId());
        }
    }

    @Override
    protected void removeByIdImpl(WxUserId id) {

        WxUserDo wxUserDo = getById(id.getValue());
        Long createId = wxUserDo.getCreateId();

        taskUserRepository.removeByCreateId(createId);
        loveUserRepository.removeByCreateId(createId);

        super.removeByIdImpl(id);

        removeSnapshot();
    }

    @Override
    protected WxUser getByIdImpl(WxUserId id) {

        WxUser wxUser = getById(id);

        fillOpenId(wxUser);

        return wxUser;
    }

    @Override
    public WxUser getByUnionId(@NonNull WxUnionId unionId) {

        LambdaQueryWrapper<WxUserDo> queryWrapper = new LambdaQueryWrapper<WxUserDo>()
                .eq(WxUserDo::getUnionId, unionId.getValue());

        WxUserDo wxUserDo = getOne(queryWrapper);

        WxUser wxUser = dataConverter.toAgg(wxUserDo);

        fillOpenId(wxUser);

        saveSnapshot(dataConverter.copyAgg(wxUser));

        return wxUser;
    }

    @Override
    public WxUser getByAccountId(AccountId accountId) {

        LambdaQueryWrapper<WxUserDo> queryWrapper = new LambdaQueryWrapper<WxUserDo>()
                .eq(WxUserDo::getAccountId, accountId.getValue());

        WxUserDo wxUserDo = getOne(queryWrapper);

        WxUser wxUser = dataConverter.toAgg(wxUserDo);

        fillOpenId(wxUser);

        saveSnapshot(dataConverter.copyAgg(wxUser));

        return wxUser;
    }

    protected void fillOpenId(WxUser wxUser) {

        if (wxUser == null) {
            return;
        }

        WxTaskUserDo taskUserDo = taskUserRepository.getByCreateId(wxUser.getId().getValue());
        if (taskUserDo != null) {
            wxUser.setWxTaskUserOpenId(new WxTaskUserOpenId(taskUserDo.getOpenId()));
        }

        WxLoveUserDo loveUserDo = loveUserRepository.getByCreateId(wxUser.getId().getValue());
        if (loveUserDo != null) {
            wxUser.setWxLoveUserOpenId(new WxLoveUserOpenId(loveUserDo.getOpenId()));
        }
    }
}
