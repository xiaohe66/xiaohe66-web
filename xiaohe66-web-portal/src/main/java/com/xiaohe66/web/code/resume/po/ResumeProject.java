package com.xiaohe66.web.code.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeProject extends BasePoDetailed{

    private Integer resumeId;
    /**
     * usrFile表id
     */
    private Integer logo;
    private String projectName;
    private String projectDesc;
    private String projectLink;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
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
