package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.code.org.po.User;
import lombok.Data;

/**
 * @author xiaohe
 * @time 17-11-01 001
 */
@Data
public class CurrentUser {

    private Integer id;
    private String userName;
    private String signature;
    private Integer imgFileId;
    private String email;

    public CurrentUser() {

    }

    public CurrentUser(User user) {
        id = user.getId();
        userName = user.getUserName();
        imgFileId = user.getImgFileId();
    }
}
