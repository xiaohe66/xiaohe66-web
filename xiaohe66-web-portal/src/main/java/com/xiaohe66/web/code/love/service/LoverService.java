package com.xiaohe66.web.code.love.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.love.dto.LoverDto;
import com.xiaohe66.web.code.love.dto.LoverLinkDto;
import com.xiaohe66.web.code.love.mapper.LoverMapper;
import com.xiaohe66.web.code.love.po.Lover;
import com.xiaohe66.web.code.love.po.LoverLink;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public void save(Integer loverUserId) {

        Lover lover = new Lover();
        save(lover);

        List<LoverLink> list = new ArrayList<>(2);

        list.add(new LoverLink(lover.getId(), UserHelper.getCurrentUsrId()));
        list.add(new LoverLink(lover.getId(), loverUserId));

        loverLinkService.saveBatch(list);
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

        User user = userService.getById(loverLink.getUserId());

        LoverDto loverDto = ClassUtils.convert(LoverDto.class, lover);

        LoverLinkDto loverLinkDto = new LoverLinkDto();
        loverLinkDto.setLoveImgFileId(user.getImgFileId());
        loverLinkDto.setLoverUserName(user.getUserName());

        loverDto.setLoverInfo(loverLinkDto);

        return loverDto;
    }

}
