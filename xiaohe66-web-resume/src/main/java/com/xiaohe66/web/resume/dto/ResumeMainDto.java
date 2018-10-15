package com.xiaohe66.web.resume.dto;

import com.xiaohe66.web.base.base.BaseDtoDetailed;

import java.util.List;

/**
 * @author xh
 * @date 18-10-10 010
 */
public class ResumeMainDto extends BaseDtoDetailed{

    private String name;
    private String phone;
    private String email;
    /**
     * 学历
     */
    private String education;
    /**
     * 毕业院校
     */
    private String graduationSchool;
    /**
     * 毕业日期
     */
    private String graduationDate;

    private String abilityDesc;

    private String abilityJson;

    /**
     * 工作经历
     */
    private List<ResumeJobDto> resumeJobDtoList;

    /**
     * 项目经历
     */
    private List<ResumeProjectDto> resumeProjectDtoList;

    public List<ResumeJobDto> getResumeJobDtoList() {
        return resumeJobDtoList;
    }

    public void setResumeJobDtoList(List<ResumeJobDto> resumeJobDtoList) {
        this.resumeJobDtoList = resumeJobDtoList;
    }

    public List<ResumeProjectDto> getResumeProjectDtoList() {
        return resumeProjectDtoList;
    }

    public void setResumeProjectDtoList(List<ResumeProjectDto> resumeProjectDtoList) {
        this.resumeProjectDtoList = resumeProjectDtoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getAbilityDesc() {
        return abilityDesc;
    }

    public void setAbilityDesc(String abilityDesc) {
        this.abilityDesc = abilityDesc;
    }

    public String getAbilityJson() {
        return abilityJson;
    }

    public void setAbilityJson(String abilityJson) {
        this.abilityJson = abilityJson;
    }
}
