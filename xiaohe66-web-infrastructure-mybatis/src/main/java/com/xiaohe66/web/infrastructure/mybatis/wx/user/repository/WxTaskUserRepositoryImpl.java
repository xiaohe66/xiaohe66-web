package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxTaskUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTaskUserDo;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.28 11:49
 */
@Repository
public class WxTaskUserRepositoryImpl
        extends ServiceImpl<WxTaskUserMapper, WxTaskUserDo> {

    public void insert(WxUserId wxUserId, WxTaskUserOpenId openId) {

        WxTaskUserDo userDo = new WxTaskUserDo();
        userDo.setOpenId(openId.getValue());
        userDo.setId(wxUserId.getValue());

        save(userDo);
    }
}
