package com.xiaohe66.web.application.account.convert;

import com.xiaohe66.web.application.account.bo.WxUserUpdateBo;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxUserAvatar;
import com.xiaohe66.web.infrastructure.domain.adapter.wx.WxUserConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author xiaohe
 * @since 2021.12.08 14:09
 */
@Mapper(componentModel = "spring")
public interface WxUserBoConverter extends WxUserConverter {

    Logger log = LoggerFactory.getLogger(WxUserBoConverter.class);

    @Mapping(target = "avatar", expression = "java(newAvatar(bo.getAvatarUrl()))")
    void setUserInfo(@MappingTarget WxUser wxUser, WxUserUpdateBo bo);

    default WxUserAvatar newAvatar(String avatarUrl) {
        if (avatarUrl != null) {
            try {
                URL url = new URL(avatarUrl);
                InputStream inputStream = url.openStream();
                return new WxUserAvatar(inputStream);

            } catch (IOException e) {
                log.error("download wxAvatar error, avatarUrl : {}", avatarUrl, e);
            }
        }
        return null;
    }

}
