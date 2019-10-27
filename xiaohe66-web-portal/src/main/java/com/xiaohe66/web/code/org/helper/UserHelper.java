package com.xiaohe66.web.code.org.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.dto.UserDto;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-20 17:20
 */
public class UserHelper {

    private UserHelper() {
    }

    public static UserDto getCurrentUsr() {
        UserDto usrDto = getCurrentUsrNotEx();
        if (usrDto == null) {
            throw new XhWebException(CodeEnum.B2_NOT_LOGGED_IN);
        }
        return usrDto;
    }

    public static Integer getCurrentUsrId() {
        return getCurrentUsr().getId();
    }

    public static UserDto getCurrentUsrNotEx() {
        return WebUtils.getSessionAttr(Final.Str.SESSION_UER_KEY);
    }

    public static Integer getCurrentUsrIdNotEx() {
        UserDto usrDto = getCurrentUsrNotEx();
        return usrDto == null ? null : usrDto.getId();
    }
}
