package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxLoveUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxLoveUserDo;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.28 14:26
 */
@Repository
public class WxLoveUserRepositoryImpl
        extends ServiceImpl<WxLoveUserMapper, WxLoveUserDo> {

    public void insert(WxUserId wxUserId, WxLoveUserOpenId openId) {

        WxLoveUserDo userDo = new WxLoveUserDo();
        userDo.setOpenId(openId.getValue());
        userDo.setId(wxUserId.getValue());

        save(userDo);
    }

}
