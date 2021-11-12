package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.domain.wx.user.value.WxTodoUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.mapper.WxTodoUserMapper;
import com.xiaohe66.web.infrastructure.mybatis.wx.user.model.WxTodoUserDo;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.10.28 11:49
 */
@Repository
public class WxTodoUserRepositoryImpl
        extends ServiceImpl<WxTodoUserMapper, WxTodoUserDo> {

    public void insert(WxUserId wxUserId, WxTodoUserOpenId openId) {

        WxTodoUserDo userDo = new WxTodoUserDo();
        userDo.setOpenId(openId.getValue());
        userDo.setId(wxUserId.getValue());

        save(userDo);
    }
}
