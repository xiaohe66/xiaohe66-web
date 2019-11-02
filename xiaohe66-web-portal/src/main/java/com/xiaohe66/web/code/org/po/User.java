package com.xiaohe66.web.code.org.po;


import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaohe66.web.base.base.BasePoDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@EqualsAndHashCode(callSuper = true)
@TableName("xiaohe66_web_org_user")
@Data
public class User extends BasePoDetailed {

    private String userName;
    private String userPwd;
    private String signature;
    private Integer imgFileId;
    private String email;

}
