package com.xiaohe66.web.code.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @date 18-10-11 011
 */
public class ResumeJob extends BasePoDetailed{
    private Integer resumeId;
    private Integer logo;
    private String orgName;
    private String orgDesc;
    private String startDate;
    private String endDate;
    /**
     * 工作职责
     */
    private String obligation;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getObligation() {
        return obligation;
    }

    public void setObligation(String obligation) {
        this.obligation = obligation;
    }

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"resumeId\":\"" + resumeId + "\""
                + ",\"createTime\":" + createTime
                + ",\"logo\":\"" + logo + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"orgName\":\"" + orgName + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"orgDesc\":\"" + orgDesc + "\""
                + ",\"startDate\":\"" + startDate + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"endDate\":\"" + endDate + "\""
                + ",\"obligation\":\"" + obligation + "\""
                + "}";
    }
}
