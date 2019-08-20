package com.xiaohe66.web.org.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.org.dao.UsrDao;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.org.po.Usr;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsrService extends AbstractService<Usr> {

    private static final Logger LOG = LoggerFactory.getLogger(UsrService.class);

    private UsrDao usrDao;

    public UsrService(){

    }

    @Autowired
    public UsrService(UsrDao usrDao) {
        super(usrDao);
        this.usrDao = usrDao;
    }

    @Override
    public void updateById(Usr po, Long currentUsrId) {
        String signature = HtmlUtils.delHtmlTag(po.getSignature());
        if(signature != null ){
            po.setSignature(signature);
        }
        super.updateById(po, currentUsrId);

        if(signature != null && SecurityUtils.getSubject().isAuthenticated()){
            UsrHelper.getCurrentUsr().setSignature(signature);
        }
    }

    public void updateImgFile(Long imgFileId){
        Long currentUsrId = UsrHelper.getCurrentUsrId();

        Usr usr = new Usr();
        usr.setImgFileId(imgFileId);

        usr.setId(currentUsrId);
        updateById(usr,currentUsrId);

        UsrHelper.getCurrentUsr().setImgFileId(imgFileId);
    }

    public Usr findByUsrName(String usrName){
        Check.notEmptyCheck(usrName);
        return usrDao.findByUsrName(usrName);
    }

    public Usr findByEmail(String email){
        return usrDao.findByEmail(email);
    }

    public Usr findByUsrNameAndPwd(String usrName,String usrPwd){
        if(StrUtils.isAllNotEmpty(usrName,usrPwd)){
            throw new NullPointerException("usrName or usrPwd is null");
        }
        return usrDao.findByUsrNameAndPwd(usrName,usrPwd);
    }

    /**
     * 获取被查看用户的信息
     * @param usrId     被查看的用户id，若传入null，则默认使用站长的
     * @return  UsrDto
     */
    public UsrDto lookAtUsr(Long usrId){
        if(Check.isNull(usrId)){
            usrId = Final.Sys.XIAO_HE_USR_ID;
        }
        Usr usr = findById(usrId);
        UsrDto usrDto = ClassUtils.convert(UsrDto.class,usr);

        usrDto.setImgFileId(usr.getImgFileId());
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
        return usrDao.isExistEmail(email);
    }
}
