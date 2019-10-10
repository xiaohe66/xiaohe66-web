package com.xiaohe66.web.code.resume.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

/**
 * @author xh
 * @date 18-10-11 011
 */
public class ResumeJobDto extends BaseDtoDetailed{

    /**
     * 对应 commonFile 表 Id
     */
    private Long imgFileId;

    private String orgName;
    private String orgDesc;
    private String startDate;
    private String endDate;
    /**
     * 工作职责
     */
    private String obligation;

    public Long getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Long imgFileId) {
        this.imgFileId = imgFileId;
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
}
