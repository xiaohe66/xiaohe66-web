package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.mapper.WishLogMapper;
import com.xiaohe66.web.code.love.po.WishLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.30 17:07
 */
@Service
public class WishLogService extends AbstractService<WishLogMapper, WishLog> {

    public List<WishLog> listByWishId(Integer wishId) {
        Check.notEmpty(wishId);

        WishLog wishLog = new WishLog();
        wishLog.setWishId(wishId);

        return list(new QueryWrapper<>(wishLog));
    }

}
