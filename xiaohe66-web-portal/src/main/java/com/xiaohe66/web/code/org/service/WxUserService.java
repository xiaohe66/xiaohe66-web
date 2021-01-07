package com.xiaohe66.web.code.org.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.org.mapper.WxUserMapper;
import com.xiaohe66.web.code.org.po.WxUser;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @time 2019.12.06 16:22
 */
@Service
public class WxUserService extends AbstractService<WxUserMapper, WxUser> {

    @Override
    public boolean save(WxUser po) {
        return super.save(po);
    }

    public WxUser getByOpenId(String openId){
        WxUser user = new WxUser();
        user.setOpenId(openId);

        return getOne(new QueryWrapper<>(user));
    }

}
