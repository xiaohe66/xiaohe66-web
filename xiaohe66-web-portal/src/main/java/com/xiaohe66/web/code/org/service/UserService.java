package com.xiaohe66.web.code.org.service;

import com.xiaohe66.web.base.base.DtoConverter;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.code.org.dto.LookAtUserDto;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.mapper.UserMapper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.po.WxUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 用户
 * <p>用户头像用数据库默认值控制
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService extends AbstractService<UserMapper, User> implements DtoConverter<User, UserDto> {

    private final WxUserService wxUserService;

    @Override
    public boolean updateById(User po) {
        String signature = po.getSignature();
        if (signature != null) {
            log.debug("更新签名, {}", signature);
            po.setSignature(HtmlUtils.delHtmlTag(signature));
        }

        return super.updateById(po);
    }

    public void updateImgFile(Integer imgFileId) {
        Integer currentUsrId = UserHelper.getCurrentUsrId();

        User user = new User();
        user.setImgFileId(imgFileId);

        user.setId(currentUsrId);
        user.setUpdateId(currentUsrId);
        updateById(user);

        UserHelper.getCurrentUsr().setImgFileId(imgFileId);
    }

    public User getByUserName(String userName) {
        Check.notEmpty(userName);
        return baseMapper.getByUserName(userName);
    }

    public User getByEmail(String email) {
        Check.notEmpty(email);
        return baseMapper.getByEmail(email);
    }


    public boolean isExistUserName(String userName) {
        return this.getByUserName(userName) != null;
    }

    public boolean isExistEmail(String email) {
        return getByEmail(email) != null;
    }

    /**
     * 获取被查看用户的信息
     *
     * @param userId 被查看的用户id，若传入null，则默认使用站长的
     * @return UsrDto
     */
    public LookAtUserDto lookAtUser(Integer userId) {
        if (userId == null) {
            userId = Final.User.XIAO_HE_USER_ID;
        }
        User user = getById(userId);

        return ClassUtils.convert(LookAtUserDto.class, user);
    }

    @Override
    public void convertDto(UserDto dto, User po) {

        WxUser wxUser = wxUserService.getByUserId(po.getId());
        if(wxUser != null){
            dto.setWxNickname(wxUser.getNickname());
        }

    }
}
