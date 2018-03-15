package com.xiaohe66.web.text.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.XhData;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.service.SysCfgService;
import com.xiaohe66.web.text.dao.ArticleDao;
import com.xiaohe66.web.text.param.ArticleParam;
import com.xiaohe66.web.text.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@Service
public class ArticleService extends AbstractService<Article>{

    private static final Long ARTICLE_CATEGORY_ROOT_ID = 1L;


    private ArticleDao articleDao;

    @Autowired
    private SysCfgService sysCfgService;

    public ArticleService(){}

    @Autowired
    public ArticleService(ArticleDao articleDao){
        super(articleDao);
        this.articleDao = articleDao;
    }

    /**
     * 查询列表
     * @param article 条件
     * @return 匹配的结果
     */
    public List<Article> list(Article article){
        return this.findByParam(article);
    }

    /**
     * 首页显示数据
     * @return
     */
    public List<Article> indexList(){
        ArticleParam param = new ArticleParam();
        param.setSecretLevel(XhData.PUBLISH_STATE_IS_PUBLISH);
        param.setPublishState(XhData.SECRET_LEVEL_ALL);
        return findByParam(param);
    }

    @Override
    public void updateById(Article entity, Long currentUsrId) {
        if(Check.isNull(entity) || Check.isNull(entity.getId())){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"article or id is null");
        }
        Article dbArticle = findById(entity.getId());
        if(Check.isNull(dbArticle)){
            throw new XhException(CodeEnum.RESOURCE_NOT_FOUND,"object not found");
        }
        if(!currentUsrId.equals(dbArticle.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
        super.updateById(entity, currentUsrId);
    }

    public List<Article> findByUsrId(Long usrId){
        if(Check.isOneNull(usrId)){
            //默认显示站长的列表
            String usrIdStr = sysCfgService.findValByKey(StrEnum.CFG_KEY_XIAO_HE_USR_ID.data());
            usrId = StrUtils.toLong(usrIdStr);
        }
        ArticleParam param = new ArticleParam();
        param.setCreateId(usrId);

        return articleDao.findByParam(param);
    }
}
