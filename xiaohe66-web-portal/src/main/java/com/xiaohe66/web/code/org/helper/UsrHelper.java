package com.xiaohe66.web.code.org.helper;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.dto.UsrDto;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-20 17:20
 */
public class UsrHelper {

    public static UsrDto getCurrentUsr(){
        UsrDto usrDto = getCurrentUsrNotEx();
        if(usrDto == null){
            throw new XhException(CodeEnum.NOT_LOGGED_IN);
        }
        return usrDto;
    }

    public static Integer getCurrentUsrId(){
        return getCurrentUsr().getId();
    }

    public static UsrDto getCurrentUsrNotEx(){
        return WebUtils.getSessionAttr(Final.Str.SESSION_UER_KEY);
    }

    public static Integer getCurrentUsrIdNotEx(){
        UsrDto usrDto = getCurrentUsrNotEx();
        return usrDto == null ? null : usrDto.getId();
    }
}
