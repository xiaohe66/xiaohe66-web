package com.xiaohe66.web.code.resume.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

import java.util.List;

/**
 * @author xh
 * @date 18-10-12 012
 */
public class ResumeProjectDto extends BaseDtoDetailed{

    /**
     * 项目图片（logo），commonFile 表 id
     */
    private Long imgFileId;
    private String projectName;
    private String projectDesc;
    private String projectLink;

    private List<ResumeFuncDto> resumeFuncDtoList;

    public List<ResumeFuncDto> getResumeFuncDtoList() {
        return resumeFuncDtoList;
    }

    public void setResumeFuncDtoList(List<ResumeFuncDto> resumeFuncDtoList) {
        this.resumeFuncDtoList = resumeFuncDtoList;
    }

    public Long getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(Long imgFileId) {
        this.imgFileId = imgFileId;
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
}
