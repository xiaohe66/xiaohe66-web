package com.xiaohe66.web.org;

import com.xiaohe66.web.common.base.BasePoDetailed;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class Attention extends BasePoDetailed {

    private Long objUsrId;
    private Boolean isPrivate;

    public Long getObjUsrId() {
        return objUsrId;
    }

    public void setObjUsrId(Long objUsrId) {
        this.objUsrId = objUsrId;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

}
