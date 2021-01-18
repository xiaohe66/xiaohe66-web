package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.exception.sec.IllegalOperationException;
import com.xiaohe66.web.code.love.po.LovePo;
import com.xiaohe66.web.code.love.po.LovePoDetailed;
import com.xiaohe66.web.code.org.helper.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @time 2020.01.07 12:24
 */
public class LoveService<M extends IBaseMapper<P>, P extends LovePo> extends AbstractService<M, P> {

    @Autowired
    protected LoverService loverService;

    @Override
    public boolean save(P po) {
        po.setLoverId(loverService.getCurrentUserLoverId());
        if (po instanceof LovePoDetailed) {
            Integer currentUserId = UserHelper.getCurrentUsrId();
            if (currentUserId != null) {
                LovePoDetailed detailed = (LovePoDetailed) po;
                detailed.setCreateId(currentUserId);
                detailed.setUpdateId(currentUserId);
            }
        }
        return super.save(po);
    }

    @Override
    public QueryWrapper<P> createDefaultQueryWrapper(P po) {

        Integer currentUserLoverId = loverService.getCurrentUserLoverId();
        if(currentUserLoverId == null){
            throw new IllegalOperationException("未绑定Lover");
        }
        po.setLoverId(currentUserLoverId);

        return new QueryWrapper<>(po);
    }
}
