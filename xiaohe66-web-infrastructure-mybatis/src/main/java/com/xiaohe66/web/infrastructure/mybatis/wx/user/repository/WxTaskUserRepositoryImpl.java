package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxTaskUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTaskUserDo;
import com.xiaohe66.web.integration.ServiceSupport;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.28 11:49
 */
@Repository
public class WxTaskUserRepositoryImpl
        extends ServiceSupport<WxTaskUserMapper, WxTaskUserDo> {

    public void insert(AccountId createId, WxTaskUserOpenId openId) {

        WxTaskUserDo userDo = new WxTaskUserDo();
        userDo.setOpenId(openId.getValue());
        userDo.setCreateId(createId.getValue());
        userDo.setId(IdWorker.genId());

        save(userDo);
    }
}
