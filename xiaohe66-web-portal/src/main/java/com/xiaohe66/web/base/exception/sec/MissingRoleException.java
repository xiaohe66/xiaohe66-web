package com.xiaohe66.web.base.exception.sec;

import com.xiaohe66.web.base.data.CodeEnum;

/**
 * @author xiaohe
 * @time 2019.10.28 12:36
 */
public class MissingRoleException extends IllegalOperationException {

    public MissingRoleException() {
        super(CodeEnum.B2_MISSING_PERMITTED, "缺少角色");
    }

    public MissingRoleException(String roleName) {
        super(CodeEnum.B2_MISSING_PERMITTED, "缺少角色 : " + roleName);
    }
}
