package com.xiaohe66.web.code.org.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.dto.CurrentUser;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/user")
public class UserController extends BaseController<UserService, User, UserDto> {

    @Get("name/{userName}")
    public Boolean isExistUserName(@PathVariable String userName) {
        return baseService.isExistUserName(userName);
    }

    // 这里不把参数放在url后面，是因为这样做会返回406错误，可能是后缀 .com造成的
    @Get("email")
    public Boolean isExistEmail(String email) {
        return baseService.isExistEmail(email);
    }

    @Get("/info")
    public Result current() {
        CurrentUser userDto = WebUtils.getSessionAttr(Final.SessionKey.CURRENT_LOGIN_USER);
        return Result.ok(userDto);
    }

    @Override
    protected void checkSavePermitted() {
        super.checkSavePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }

    @Override
    protected void checkDeletePermitted() {
        super.checkDeletePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }

    @Override
    protected void checkUpdatePermitted() {
        super.checkUpdatePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }

    @Override
    protected void checkSelectPermitted() {
        super.checkSelectPermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }
}
