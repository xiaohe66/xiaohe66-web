package com.xiaohe66.web.code.resume.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @version 1.0
 * @time 2018-10-09 13:02
 */
@TableName("xiaohe66_web_resume_main")
public class ResumeMain extends BasePoDetailed{
    private String name;
    private String phone;
    private String email;
    /**
     * 学历
     */
    private Integer education;
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

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"name\":\"" + name + "\""
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"phone\":\"" + phone + "\""
                + ",\"email\":\"" + email + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"education\":\"" + education + "\""
                + ",\"graduationSchool\":\"" + graduationSchool + "\""
                + ",\"graduationDate\":\"" + graduationDate + "\""
                + ",\"abilityDesc\":\"" + abilityDesc + "\""
                + ",\"abilityJson\":\"" + abilityJson + "\""
                + "}";
    }
}