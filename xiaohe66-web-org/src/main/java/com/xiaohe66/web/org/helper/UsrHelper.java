package com.xiaohe66.web.org.helper;

import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.org.dto.UsrDto;

/**
 * @author xh
 * @version 1.0
 * @time 2018-08-20 17:20
 */
public class UsrHelper {

    public static UsrDto getCurrentUsr(){
        UsrDto usrDto = WebUtils.getSessionAttr(Final.Str.SESSION_UER_KEY);
        /*if(usrDto == null){
            throw new XhException(CodeEnum.NOT_LOGGED_IN);
        }*/
        return usrDto;
    }

    public static Long getCurrentUsrId(){
        UsrDto usrDto = getCurrentUsr();
        return usrDto == null ? null : getCurrentUsr().getId();
    }
}
