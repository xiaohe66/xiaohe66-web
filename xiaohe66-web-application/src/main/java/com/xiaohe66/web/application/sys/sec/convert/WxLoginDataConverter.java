package com.xiaohe66.web.application.sys.sec.convert;

import com.xiaohe66.web.application.sys.sec.bo.WxLoginBo;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author xiaohe
 * @since 2021.10.28 16:40
 */
@Mapper(componentModel = "spring")
public interface WxLoginDataConverter {

    void copyValueToWxUser(@MappingTarget WxUser wxUser, WxLoginBo wxLoginBo);

    default void setOpenId(WxUser wxUser, WxLoginBo.Type type, String openId) {

        switch (type) {
            case TASK:
                wxUser.setWxTaskUserOpenId(new WxTaskUserOpenId(openId));
                break;

            case LOVE:
                wxUser.setWxLoveUserOpenId(new WxLoveUserOpenId(openId));
                break;

            default:
        }
    }

    default void setRoleId(Account account, WxLoginBo.Type type) {
        switch (type) {
            case TASK:
                account.addRole(RoleId.TASK_ROLE_ID);
                break;

            case LOVE:
                account.addRole(RoleId.LOVE_ROLE_ID);
                break;
            default:

        }
    }
}
