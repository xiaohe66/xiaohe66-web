package com.xiaohe66.web.org.dto;

import com.xiaohe66.web.common.base.BaseDto;
import com.xiaohe66.web.org.po.Usr;

/**
 * @author xiaohe
 * @time 17-11-01 001
 */
public class UsrDto extends BaseDto {

    private String usrName;

    public UsrDto(){

    }
    public UsrDto(Usr usr){
        id = usr.getId();
        usrName = usr.getUsrName();
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\""
                + ", \"usrName\":\"" + usrName + "\""
                + "}";
    }
}
