package com.xiaohe66.web.application.sys.sec.convert;

import com.xiaohe66.web.application.sys.sec.bo.WxLoginBo;
import com.xiaohe66.web.application.sys.sec.result.WxLoginResult;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxLoveUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxTaskUserOpenId;
import com.xiaohe66.web.domain.wx.user.value.WxUserNickname;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @since 2021.10.28 16:40
 */
@Mapper
@RequiredArgsConstructor
@Slf4j
public abstract class WxLoginBoConverter implements DataConverter {

    @Autowired
    private LoverService loverService;

    @Autowired
    private SecurityService securityService;

    public WxLoginResult toWxLoginResult(WxUser wxUser) {

        WxLoginResult result = new WxLoginResult();

        result.setSessionId(securityService.getSessionId());
        result.setId(wxUser.getCreateId().getValue());

        WxUserNickname nickname = wxUser.getNickname();
        result.setNeedUserInfo(nickname == null || StringUtils.isEmpty(nickname.getValue()));

        /*Lover lover = loverService.getCurrentLover();

        if (lover != null) {
            AccountId currentAccountId = securityService.getCurrentAccountId();

            AccountId loveAccountId = currentAccountId.equals(lover.getCreateId()) ?
                    lover.getAccountId() :
                    lover.getCreateId();

            WxLoginResult.Love love = new WxLoginResult.Love();
            love.setId(loveAccountId.getValue());

            result.setLove(love);
        }*/

        return result;
    }

    public void setOpenId(WxUser wxUser, WxLoginBo.Type type, String openId) {

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

    public void setRoleId(Account account, WxLoginBo.Type type) {
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
