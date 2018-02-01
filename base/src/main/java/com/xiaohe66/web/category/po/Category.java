package com.xiaohe66.web.category.po;


import com.xiaohe66.web.common.base.BasePo;

/**
 * @author xiaohe
 * @time 17-11-11 011
 */
public class Category extends BasePo {

    private String cName;
    private String cDesc;
    private String pId;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getCDesc() {
        return cDesc;
    }

    public void setCDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }


}
