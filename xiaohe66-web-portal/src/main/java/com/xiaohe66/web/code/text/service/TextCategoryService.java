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
public class TextCategoryService extends AbstractService<TextCategory> {
    private TextCategoryDao categoryDao;

    public TextCategoryService(){}

    @Autowired
    public TextCategoryService(TextCategoryDao textCategoryDao){
        super(textCategoryDao);
        this.categoryDao = textCategoryDao;
    }

    public List<TextCategory> findByPid(Long pid){
        if(Check.isOneNull(pid)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"pid is null");
        }
        return categoryDao.findByPid(pid);
    }

    public List<TextCategory> findByUsrId(Long usrId){
        if(Check.isOneNull(usrId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrId is null");
        }
        TextCategoryParam param = new TextCategoryParam();
        param.setCreateId(usrId);

        return findByParam(param);
    }

    public String findNamesByArticleId(Long articleId){
        if(Check.isOneNull(articleId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"articleId is null");
        }
        return categoryDao.findNamesByArticleId(articleId);
    }

    public List<TextCategory> findByArticleId(Long articleId){
        if(Check.isOneNull(articleId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"articleId is null");
        }
        return categoryDao.findByArticleId(articleId);
    }
}
