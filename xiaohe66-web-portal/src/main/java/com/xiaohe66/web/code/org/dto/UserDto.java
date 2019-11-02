package com.xiaohe66.web.code.org.dto;

import com.xiaohe66.web.base.base.BaseDto;
import com.xiaohe66.web.code.org.po.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * todo : 该类当前是用于session保存和返回客户端的，需要拆分成两个类
 *
 * @author xiaohe
 * @time 17-11-01 001
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {

    private String userName;
    private String signature;
    private Integer imgFileId;
    private String email;

    public UserDto(){

    }
    public UserDto(User user){
        id = user.getId();
        userName = user.getUserName();
        imgFileId = user.getImgFileId();
    }
}
