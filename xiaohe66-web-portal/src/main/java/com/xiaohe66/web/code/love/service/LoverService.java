package com.xiaohe66.web.code.love.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.cache.CacheHelper;
import com.xiaohe66.web.code.love.dto.LoverDto;
import com.xiaohe66.web.code.love.dto.LoverLinkDto;
import com.xiaohe66.web.code.love.mapper.LoverMapper;
import com.xiaohe66.web.code.love.po.Lover;
import com.xiaohe66.web.code.love.po.LoverLink;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.po.WxUser;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.org.service.WxUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author xiaohe
 * @time 2020.01.06 16:42
 */
@Service
@Slf4j
@AllArgsConstructor
public class LoverService extends AbstractService<LoverMapper, Lover> {

    private LoverLinkService loverLinkService;
    private UserService userService;
    private WxUserService wxUserService;

    public String ready() {

        String serialNo = WebUtils.getSessionAttr(Final.CacheKey.LOVER_SERIAL_NO_KEY);
        Integer currentUsrId = UserHelper.getCurrentUsrId();

        if (serialNo != null) {
            CacheHelper.put30(serialNo, currentUsrId);
            return serialNo;
        }

        serialNo = UUID.randomUUID().toString().replace("-", "");

        CacheHelper.put30(serialNo, currentUsrId);
        WebUtils.setSessionAttr(Final.CacheKey.LOVER_SERIAL_NO_KEY, serialNo);

        return serialNo;
    }

    public void binding(String serialNo) {

        Integer userId = CacheHelper.get30(serialNo);

        Integer currentUsrId = UserHelper.getCurrentUsrId();

        if (currentUsrId.equals(userId)) {
            throw new MsgException(CodeEnum.B1_ILLEGAL_PARAM, "不可绑定自己");
        }

        if (userId == null) {
            throw new MsgException(CodeEnum.B1_OBJ_NOT_EXIST);
        }

        LoverService loverServiceProxy = (LoverService) AopContext.currentProxy();
        loverServiceProxy.save(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Integer loverUserId) {

        Lover lover = new Lover();
        save(lover);

        List<LoverLink> list = new ArrayList<>(2);

        list.add(new LoverLink(lover.getId(), UserHelper.getCurrentUsrId()));
        list.add(new LoverLink(lover.getId(), loverUserId));

        loverLinkService.saveBatch(list);
    }


    public void confirm() {

        Integer currentUsrId = UserHelper.getCurrentUsrId();

        Lover dbLover = baseMapper.selectByLoverUserId(currentUsrId);

        if (dbLover == null) {
            throw new XhWebException(CodeEnum.B0_ILLEGAL_REQUEST, "不存在 lover");
        }

        if (currentUsrId.equals(dbLover.getCreateId())) {
            throw new XhWebException(CodeEnum.B0_ILLEGAL_REQUEST, "不可以自己同意自己的申请");
        }

        if (dbLover.getStatus() == 0) {

            dbLover.setStatus(1);

            baseMapper.updateById(dbLover);
        }
    }

    public Integer getCurrentUserLoverId() {
        Integer currentUsrId = UserHelper.getCurrentUsrId();

        Integer loverId = baseMapper.selectLoverIdByUserId(currentUsrId);
        if (loverId == null) {
            log.warn("用户{}的loverId为null", currentUsrId);
        }
        return loverId;
    }

    public LoverDto getByUserId(Integer userId) {

        Lover lover = baseMapper.selectByLoverUserId(userId);

        LoverLink loverLink = loverLinkService.getLoverLink(lover.getId(), userId);

        WxUser wxUser = wxUserService.getByUserId(loverLink.getUserId());

        LoverDto loverDto = ClassUtils.convert(LoverDto.class, lover);

        LoverLinkDto loverLinkDto = new LoverLinkDto();
        loverLinkDto.setLoverAvatarUrl(wxUser.getAvatarUrl());
        loverLinkDto.setLoverUserName(wxUser.getNickname());

        loverDto.setLoverInfo(loverLinkDto);

        return loverDto;
    }

}
