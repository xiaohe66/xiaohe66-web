package com.xiaohe66.web.code.org.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.dto.CurrentUser;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-20 17:20
 */
public class UserHelper {

    private UserHelper() {
    }

    public static CurrentUser getCurrentUsr() {
        CurrentUser usrDto = getCurrentUsrNotEx();
        if (usrDto == null) {
            throw new XhWebException(CodeEnum.B2_NOT_LOGGED_IN);
        }
        return usrDto;
    }

    public static Integer getCurrentUsrId() {
        return getCurrentUsr().getId();
    }

    public static CurrentUser getCurrentUsrNotEx() {
        return WebUtils.getSessionAttr(Final.SessionKey.CURRENT_LOGIN_USER);
    }

    public static Integer getCurrentUsrIdNotEx() {
        CurrentUser usrDto = getCurrentUsrNotEx();
        return usrDto == null ? null : usrDto.getId();
    }
}
