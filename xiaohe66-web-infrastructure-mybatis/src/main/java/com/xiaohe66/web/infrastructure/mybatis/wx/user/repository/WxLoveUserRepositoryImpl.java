package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxLoveUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxLoveUserDo;
import com.xiaohe66.web.integration.ServiceSupport;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.28 14:26
 */
@Repository
public class WxLoveUserRepositoryImpl
        extends ServiceSupport<WxLoveUserMapper, WxLoveUserDo> {

    public void insert(AccountId createId, WxLoveUserOpenId openId) {

        WxLoveUserDo userDo = new WxLoveUserDo();
        userDo.setOpenId(openId.getValue());
        userDo.setCreateId(createId.getValue());
        userDo.setId(IdWorker.genId());

        save(userDo);
    }

}
