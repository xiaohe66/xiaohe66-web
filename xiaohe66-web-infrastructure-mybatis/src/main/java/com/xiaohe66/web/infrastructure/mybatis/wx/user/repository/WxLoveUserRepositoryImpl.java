package com.xiaohe66.web.infrastructure.mybatis.wx.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
