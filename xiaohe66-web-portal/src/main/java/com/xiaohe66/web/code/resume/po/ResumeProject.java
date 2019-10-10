package com.xiaohe66.web.code.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeProject extends BasePoDetailed{

    private Long resumeId;
    /**
     * usrFile表id
     */
    private Long logo;
    private String projectName;
    private String projectDesc;
    private String projectLink;

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"resumeId\":\"" + resumeId + "\""
                + ",\"createTime\":" + createTime
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"logo\":\"" + logo + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"projectName\":\"" + projectName + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"projectDesc\":\"" + projectDesc + "\""
                + ",\"projectLink\":\"" + projectLink + "\""
                + "}";
    }
}
