package com.xiaohe66.web.code.org.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.org.mapper.UserMapper;
import com.xiaohe66.web.code.org.dto.UsrDto;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.po.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 *
 * 用户
 * <p>用户头像用数据库默认值控制
 *
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class UserService extends AbstractService<UserMapper, User> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public boolean save(User po) {
        return retBool(baseMapper.insert(po));
    }

    @Override
    public boolean updateById(User po) {
        String signature = HtmlUtils.delHtmlTag(po.getSignature());
        if(signature != null ){
            po.setSignature(signature);
        }
        if(signature != null && SecurityUtils.getSubject().isAuthenticated()){
            UsrHelper.getCurrentUsr().setSignature(signature);
        }

        return super.updateById(po);
    }


    public void updateImgFile(Integer imgFileId){
        Integer currentUsrId = UsrHelper.getCurrentUsrId();

        User user = new User();
        user.setImgFileId(imgFileId);

        user.setId(currentUsrId);
        user.setUpdateId(currentUsrId);
        updateById(user);

        UsrHelper.getCurrentUsr().setImgFileId(imgFileId);
    }

    public User findByUsrName(String usrName){
        Check.notEmptyCheck(usrName);
        return baseMapper.findByUsrName(usrName);
    }

    public User findByEmail(String email){
        return baseMapper.findByEmail(email);
    }

    public User findByUsrNameAndPwd(String usrName, String usrPwd){
        if(StrUtils.isAllNotEmpty(usrName,usrPwd)){
            throw new NullPointerException("usrName or usrPwd is null");
        }
        return baseMapper.findByUsrNameAndPwd(usrName,usrPwd);
    }

    /**
     * 获取被查看用户的信息
     * @param usrId     被查看的用户id，若传入null，则默认使用站长的
     * @return  UsrDto
     */
    public UsrDto lookAtUsr(Integer usrId){
        if(Check.isNull(usrId)){
            usrId = Final.Sys.XIAO_HE_USR_ID;
        }
        User user = getById(usrId);
        UsrDto usrDto = ClassUtils.convert(UsrDto.class,user);

        usrDto.setImgFileId(user.getImgFileId());
        return usrDto;
    }

    /**
     * 用户名是否存在
     * @return 存在返回true，不存在返回false
     */
    public boolean usrNameIsExist(String usrName){
        return this.findByUsrName(usrName) != null;
    }

    /**
     * 邮箱是否已被注册
     * @return 已被注册返回true，未被注册返回false
     */
    public boolean emailIsExist(String email){
        Check.notEmptyCheck(email);
        return baseMapper.isExistEmail(email);
    }
}
