package com.xiaohe66.web.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * 简历-功能开发
 *
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeFunc extends BasePoDetailed{

    private Long projectId;
    private String funcName;
    private String funcDesc;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"projectId\":\"" + projectId + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"funcName\":\"" + funcName + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"funcDesc\":\"" + funcDesc + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + "}";
    }
}
