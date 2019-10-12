package com.xiaohe66.web.code.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.text.dao.TextCategoryDao;
import com.xiaohe66.web.code.text.param.TextCategoryParam;
import com.xiaohe66.web.code.text.po.TextCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-12 012
 */
@Service
public class TextCategoryService extends AbstractService<TextCategoryDao,TextCategory> {

    public List<TextCategory> findByPid(Integer pid){
        if(Check.isOneNull(pid)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"pid is null");
        }
        return baseMapper.findByPid(pid);
    }

    public List<TextCategory> findByUsrId(Integer usrId){
        if(Check.isOneNull(usrId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrId is null");
        }
        TextCategoryParam param = new TextCategoryParam();
        param.setCreateId(usrId);

        return findByParam(param);
    }

    public String findNamesByArticleId(Integer articleId){
        if(Check.isOneNull(articleId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"articleId is null");
        }
        return baseMapper.findNamesByArticleId(articleId);
    }

    public List<TextCategory> findByArticleId(Integer articleId){
        if(Check.isOneNull(articleId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"articleId is null");
        }
        return baseMapper.findByArticleId(articleId);
    }
}
