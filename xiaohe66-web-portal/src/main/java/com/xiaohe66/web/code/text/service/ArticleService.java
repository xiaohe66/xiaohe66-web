package com.xiaohe66.web.code.text.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.XhPage;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.common.service.CategoryService;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.dao.ArticleMapper;
import com.xiaohe66.web.code.text.dto.ArticleDto;
import com.xiaohe66.web.code.text.param.ArticleParam;
import com.xiaohe66.web.code.text.po.Article;
import com.xiaohe66.web.code.text.po.ArticleCategoryLink;
import com.xiaohe66.web.code.text.po.ArticleDownloadCount;
import com.xiaohe66.web.code.text.po.TextCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @time 17-11-08 008
 */
@Service
public class ArticleService extends AbstractService<ArticleMapper, Article> {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    private ArticleCategoryLinkService articleCategoryLinkService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TextCategoryService textCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleLogService articleLogService;

    /**
     * 查询列表
     *
     * @param article 条件
     * @return 匹配的结果
     */
    public List<Article> list(Article article) {
        return this.list(new QueryWrapper<>(article));
    }

    /**
     * 首页显示数据
     *
     */
    public List<ArticleDto> indexArticle() {
        Article article = new Article();
        article.setSecretLevel(Final.Article.SECRET_LEVEL_PUBLIC);

        IPage<Article> page = new XhPage<>(5);
        page = page(page, new QueryWrapper<>(article));

        return installDto(page.getRecords());
    }

    /**
     * 禁用该方法，调用该方法会抛出异常，请调用add(p1,p2,p3)
     *
     * @param article 禁用
     */
    @Deprecated
    @Override
    public boolean updateById(Article article) {
        throw new XhWebException(CodeEnum.DISABLE_FUNCTION, "pls invoke updateById(p1,p2,p3)");
    }

    /**
     * 更新文章
     *
     * @param article
     * @param perCategoryIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(Article article, Integer[] perCategoryIds) {
        if (Check.isNull(article) || Check.isNull(article.getId())) {
            throw new XhWebException(CodeEnum.NULL_EXCEPTION, "article or id is null");
        }
        Article dbArticle = getById(article.getId());
        if (Check.isNull(dbArticle)) {
            throw new XhWebException(CodeEnum.RESOURCE_NOT_FOUND, "object not found");
        }
        Integer currentUsrId = UserHelper.getCurrentUsrId();
        if (!currentUsrId.equals(dbArticle.getCreateId())) {
            throw new XhWebException(CodeEnum.NOT_PERMISSION, "this article not is current user article");
        }
        article.setUpdateId(currentUsrId);
        super.updateById(article);

        //删除个人分类关联
        articleCategoryLinkService.delByArticleId(dbArticle.getId());
        if (Check.isNotEmpty(perCategoryIds)) {
            List<ArticleCategoryLink> linkList = new ArrayList<>(perCategoryIds.length);
            for (Integer perCategoryId : perCategoryIds) {
                linkList.add(new ArticleCategoryLink(article.getId(), perCategoryId));
            }
            //增加新的个人分类关联
            articleCategoryLinkService.saveBatch(linkList);
        }
    }

    /**
     * 创建新文章
     *
     * @param article        文章
     * @param perCategoryIds 文章个人分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Article article, Integer[] perCategoryIds) {

        Check.notEmptyCheck(article.getText(), article.getSysCategoryId());

        Integer currentUsrId = UserHelper.getCurrentUsrId();

        if (article.getSecretLevel() == null) {
            article.setSecretLevel(Final.Article.SECRET_LEVEL_PUBLIC);
        }

        logger.info("创建一篇文章：title={},usrId={}", article.getTitle(), currentUsrId);
        article.setCreateId(currentUsrId);
        super.save(article);

        if (Check.isNotEmpty(perCategoryIds)) {
            List<ArticleCategoryLink> linkList = new ArrayList<>();
            for (Integer perCategoryId : perCategoryIds) {
                ArticleCategoryLink link = new ArticleCategoryLink(article.getId(), perCategoryId);
                linkList.add(link);
            }
            articleCategoryLinkService.saveBatch(linkList);
        }
    }

    /**
     * 禁用该方法，调用该方法会抛出异常，请调用add(p1,p2,p3)
     *
     * @param article 禁用
     * @deprecated sss
     */
    @Deprecated
    @Override
    public boolean save(Article article) {
        throw new XhWebException(CodeEnum.DISABLE_FUNCTION, "pls invoke add(p1,p2,p3)");
    }

    /**
     * 查询一个用户的文章
     *
     * @param usrId 待查询的用户id，传入null时默认为站长的id
     * @return 对应用户的文章列表
     */
    public List<Article> findByUsrId(Integer usrId, Integer secretLevel) {
        if (Check.isOneNull(usrId)) {
            //默认显示站长的列表
            String usrIdStr = SysCfgHelper.getString(Final.Str.CFG_KEY_XIAO_HE_USR_ID);
            usrId = StrUtils.toInt(usrIdStr);
        }
        ArticleParam param = new ArticleParam();
        param.setCreateId(usrId);
        param.setSecretLevel(secretLevel);

        return baseMapper.selectByParam(param);
    }

    public List<ArticleDto> findDtoByUsrId(Integer usrId, Integer secretLevel) {
        return installDto(findByUsrId(usrId, secretLevel));
    }

    public ArticleDto findDtoById(Integer id) {
        Check.notEmptyCheck(id);
        Article article = this.getById(id);

        //如果该文章是简历文章，则任何人都可以查看
        if (Final.Article.SECRET_LEVEL_RESUME_ARTICLE != article.getSecretLevel()) {

            //只有文章作者是自己 或者 文章的私密等级是公开时，才能查看
            if (Final.Article.SECRET_LEVEL_PUBLIC != article.getSecretLevel()) {
                Integer currentUsrId = UserHelper.getCurrentUsrId();
                if (!currentUsrId.equals(article.getCreateId())) {
                    throw new XhWebException(CodeEnum.NOT_PERMISSION);
                }
            }
        }

        ArticleDto articleDto = ClassUtils.convert(ArticleDto.class, article);

        //保存日志准备
        articleLogService.addPrepare(id);

        installDto(articleDto, article);
        return articleDto;
    }

    public List<ArticleDto> findDtoAll(String search, boolean onlyWebmaster) {
        ArticleParam param = new ArticleParam();
        if (onlyWebmaster) {
            String usrIdStr = SysCfgHelper.getString(Final.Str.CFG_KEY_XIAO_HE_USR_ID);
            param.setCreateId(StrUtils.toInt(usrIdStr));
        }
        if (StrUtils.isNotEmpty(search)) {
            param.setTitle("%" + search + "%");
        }
        param.setSecretLevel(Final.Article.SECRET_LEVEL_PUBLIC);
        return installDto(listByParam(param));
    }

    public void installDto(ArticleDto articleDto, Article article) {

        articleDto.setSysCategoryName(categoryService.getById(article.getSysCategoryId()).getCategoryName());
        articleDto.setCount(articleLogService.countByArticleId(article.getId()));

        List<TextCategory> textCategoryList = textCategoryService.findByArticleId(article.getId());

        final int textCategoryListSize = textCategoryList.size();
        if (textCategoryListSize > 0) {
            TextCategory textCategory = textCategoryList.get(0);
            StringBuilder perCategoryIds = new StringBuilder(String.valueOf(textCategory.getId()));
            StringBuilder perCategoryNames = new StringBuilder(String.valueOf(textCategory.getCategoryName()));
            for (int i = 1; i < textCategoryListSize; i++) {
                textCategory = textCategoryList.get(i);
                perCategoryIds.append(",").append(textCategory.getId());
                perCategoryNames.append("、").append(textCategory.getCategoryName());
            }
            articleDto.setPerCategoryIds(perCategoryIds.toString());
            articleDto.setPerCategoryNames(perCategoryNames.toString());
        } else {
            articleDto.setPerCategoryIds("");
            articleDto.setPerCategoryNames("");
        }
    }

    /**
     * 返回文章列表，文章的内容作摘要处理
     *
     * @param articleList
     * @return
     */
    public List<ArticleDto> installDto(List<Article> articleList) {
        return ClassUtils.convert(ArticleDto.class, articleList, (articleDto, article) -> {
            installDto(articleDto, article);

            articleDto.setText(HtmlUtils.digest(articleDto.getText(), 110));

            User user = userService.getById(article.getCreateId());
            articleDto.setUsrName(user.getUsrName());
            articleDto.setImgFileId(user.getImgFileId());
            articleDto.setCount(articleLogService.countByArticleId(article.getId()));
        });
    }

    /**
     * 热门文章top5
     *
     * @param usrId 传入用户id，则查该用户的，否则查全部用户的
     * @return 热门文章top5
     */
    public List<ArticleDto> findDtoHotTop5(Integer usrId) {
        final int hotSize = 5;
        int i = 0;
        List<ArticleDto> articleDtoList = new ArrayList<>(hotSize);
        List<ArticleDownloadCount> downloadCountList = articleLogService.countDownloadOfMonth(usrId);
        for (ArticleDownloadCount downloadCount : downloadCountList) {

            ArticleParam param = new ArticleParam();
            param.setSecretLevel(Final.Article.PUBLISH_STATE_NOT_PUBLISH);
            param.setId(downloadCount.getId());

            List<Article> articleList = listByParam(param);
            if (articleList == null || articleList.size() == 0) {
                continue;
            }

            ArticleDto articleDto = ClassUtils.convert(ArticleDto.class, articleList.get(0));
            articleDtoList.add(articleDto);

            articleDto.setCount(downloadCount.getCount());

            if (++i >= hotSize) {
                break;
            }
        }
        return articleDtoList;
    }
}
