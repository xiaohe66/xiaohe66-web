package com.xiaohe66.web.text.service;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.xiaohe66.web.comm.service.CategoryService;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.XhData;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.HtmlUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.sys.service.SysCfgService;
import com.xiaohe66.web.text.dao.ArticleDao;
import com.xiaohe66.web.text.dto.DtoArticle;
import com.xiaohe66.web.text.param.ArticleParam;
import com.xiaohe66.web.text.param.TextCategoryParam;
import com.xiaohe66.web.text.po.Article;
import com.xiaohe66.web.text.po.ArticleCategoryLink;
import com.xiaohe66.web.text.po.TextCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@Service
public class ArticleService extends AbstractService<Article>{

    private ArticleDao articleDao;

    @Autowired
    private SysCfgService sysCfgService;

    @Autowired
    private ArticleCategoryLinkService articleCategoryLinkService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TextCategoryService textCategoryService;

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
    public List<DtoArticle> indexArticle(){
        PageHelper.startPage(1,5);
        return toDto(findByParam(null));
    }

    /**
     * 禁用该方法，调用该方法会抛出异常，请调用add(p1,p2,p3)
     * @param article 禁用
     * @param currentUsrId 当前操作者id
     */
    @Deprecated
    @Override
    public void updateById(Article article, Long currentUsrId) {
        throw new XhException(CodeEnum.DISABLE_FUNCTION,"pls invoke updateById(p1,p2,p3)");
    }

    /**
     * 更新文章
     * @param article
     * @param currentUsrId
     * @param perCategoryIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Article article, Long currentUsrId,Long[] perCategoryIds) {
        if(Check.isNull(article) || Check.isNull(article.getId())){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"article or id is null");
        }
        Article dbArticle = findById(article.getId());
        if(Check.isNull(dbArticle)){
            throw new XhException(CodeEnum.RESOURCE_NOT_FOUND,"object not found");
        }
        if(!currentUsrId.equals(dbArticle.getCreateId())){
            throw new XhException(CodeEnum.NOT_PERMISSION,"this article not is current usr article");
        }
        super.updateById(article, currentUsrId);

        //删除个人分类关联
        articleCategoryLinkService.delByArticleId(dbArticle.getId());
        if(Check.isNotEmpty(perCategoryIds)){
            List<ArticleCategoryLink> linkList = new ArrayList<>(perCategoryIds.length);
            for (Long perCategoryId : perCategoryIds) {
                linkList.add(new ArticleCategoryLink(article.getId(),perCategoryId));
            }
            //增加新的个人分类关联
            articleCategoryLinkService.addAll(linkList,currentUsrId);
        }
    }

    /**
     * 禁用该方法，调用该方法会抛出异常，请调用add(p1,p2,p3)
     * @param article 禁用
     * @param currentUsrId 当前操作者id
     */
    @Deprecated
    @Override
    public void add(Article article, Long currentUsrId) {
        throw new XhException(CodeEnum.DISABLE_FUNCTION,"pls invoke add(p1,p2,p3)");
    }

    /**
     * 创建新文章
     * @param article   文章
     * @param currentUsrId  当前用户id
     * @param perCategoryIds    文章个人分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Article article,Long currentUsrId,Long[] perCategoryIds){
        if(StrUtils.isOneEmpty(article.getTitle(),article.getText())){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"title or text is null");
        }
        if(Check.isOneNull(article.getSysCategoryId())){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"sysCategoryId is null");
        }
        super.add(article, currentUsrId);

        if(Check.isNotEmpty(perCategoryIds)){
            List<ArticleCategoryLink> linkList = new ArrayList<>();
            for (Long perCategoryId : perCategoryIds) {
                ArticleCategoryLink link = new ArticleCategoryLink(article.getId(),perCategoryId);
                linkList.add(link);
            }
            articleCategoryLinkService.addAll(linkList,currentUsrId);
        }
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

    public List<DtoArticle> findDtoByUsrId(Long usrId){
        return toDto(this.findByUsrId(usrId));
    }

    public DtoArticle findDtoById(Long id){
        return toDto(super.findById(id));
    }

    public DtoArticle toDto(Article article){
        DtoArticle dtoArticle = ClassUtils.convert(DtoArticle.class,article);
        dtoArticle.setSysCategoryName(categoryService.findById(article.getSysCategoryId()).getCategoryName());

        List<TextCategory> textCategoryList = textCategoryService.findByArticleId(article.getId());

        final int textCategoryListSize = textCategoryList.size();
        if(textCategoryListSize > 0){
            TextCategory textCategory = textCategoryList.get(0);
            StringBuilder perCategoryIds = new StringBuilder(String.valueOf(textCategory.getId()));
            StringBuilder perCategoryNames = new StringBuilder(String.valueOf(textCategory.getCategoryName()));
            for (int i=1;i<textCategoryListSize;i++) {
                textCategory = textCategoryList.get(i);
                perCategoryIds.append(",").append(textCategory.getId());
                perCategoryNames.append("、").append(textCategory.getCategoryName());
            }
            dtoArticle.setPerCategoryIds(perCategoryIds.toString());
            dtoArticle.setPerCategoryNames(perCategoryNames.toString());
        }

        return dtoArticle;
    }

    /**
     * 返回文章列表，文章的内容作摘要处理
     * @param articleList
     * @return
     */
    public List<DtoArticle> toDto(List<Article> articleList){
        List<DtoArticle> dtoArticleList = new ArrayList<>(articleList.size());
        for (Article article : articleList) {
            DtoArticle dtoArticle = toDto(article);
            dtoArticleList.add(dtoArticle);

            dtoArticle.setText(HtmlUtils.digest(dtoArticle.getText(),130));
        }
        return dtoArticleList;
    }
}
