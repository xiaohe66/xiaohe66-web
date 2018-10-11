package com.xiaohe66.web.resume.po;

import com.xiaohe66.web.base.base.BasePoDetailed;

/**
 * @author xh
 * @version 1.0
 * @time 2018-10-09 13:02
 */
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

    @Override
    public String toString() {
        return "{" + "\"createId\":\"" + createId + "\""
                + ",\"createTime\":" + createTime
                + ",\"id\":\"" + id + "\""
                + ",\"updateId\":\"" + updateId + "\""
                + ",\"name\":\"" + name + "\""
                + ",\"phone\":\"" + phone + "\""
                + ",\"updateTime\":" + updateTime
                + ",\"email\":\"" + email + "\""
                + ",\"isDelete\":\"" + isDelete + "\""
                + ",\"education\":\"" + education + "\""
                + ",\"graduationSchool\":\"" + graduationSchool + "\""
                + ",\"graduationDate\":\"" + graduationDate + "\""
                + "}";
    }
}