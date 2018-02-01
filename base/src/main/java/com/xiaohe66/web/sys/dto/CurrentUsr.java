package com.xiaohe66.web.sys.dto;

import com.xiaohe66.web.org.usr.dto.UsrDto;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class CurrentUsr {
    private UsrDto usr;

    public CurrentUsr(){

    }

    public CurrentUsr(UsrDto usr){
        this.usr = usr;
    }

    public Long getId(){
        return usr != null ? usr.getId() : null;
    }

    public UsrDto getUsr() {
        return usr;
    }
}
