package com.xiaohe66.web.code.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.CollectionUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.text.dao.ArticleLogDao;
import com.xiaohe66.web.code.text.po.ArticleDownloadCount;
import com.xiaohe66.web.code.text.po.ArticleLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-16 016
 */
@Service
public class ArticleLogService extends AbstractService<ArticleLogDao, ArticleLog> {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleLogService.class);

    /**
     * 方法弃用，请使用 add()
     *
     * @param po           插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Override
    @Deprecated
    public void add(ArticleLog po, Integer currentUsrId) {
        throw new XhException(CodeEnum.NOT_IMPLEMENTED);
    }

    public void addPrepare(Integer articleId) {
        Check.notEmptyCheck(articleId);
        WebUtils.setSessionAttr(Final.Str.ARTICLE_LOG_ADD_PREPARE, articleId);
    }

    /**
     * 定义查看数量的规则
     * 每个session只能增加一次访问量
     */
    public void add() {

        Integer articleId = WebUtils.getSessionAttr(Final.Str.ARTICLE_LOG_ADD_PREPARE);
        Integer currentUsrId = UsrHelper.getCurrentUsrIdNotEx();

        //查看自己的文章不加查看量
        if (Check.eq(articleId, currentUsrId)) {
            LOG.debug("look oneself article");
            return;
        }

        Set<Integer> articleIdSet = WebUtils.getSessionAttr(Final.Str.ARTICLE_LOG_CACHE);
        if (CollectionUtils.isNull(articleIdSet)) {
            articleIdSet = new HashSet<>(4);
            WebUtils.setSessionAttr(Final.Str.ARTICLE_LOG_CACHE, articleIdSet);
        }

        String ip = WebUtils.getRequestIP();
        LOG.debug("ip：" + ip);

        if (!articleIdSet.contains(articleId)) {
            ArticleLog articleLog = new ArticleLog(articleId);
            articleLog.setIp(ip);
            articleLog.setCreateId(currentUsrId);
            articleLog.setCreateTime(new Date());
            super.add(articleLog, currentUsrId);

            articleIdSet.add(articleId);
        }
    }

    /**
     * 月查看数量
     * 传入的用户id不为null时，统计该用户的文章月被查看数量
     * 传入的用户id为null时，统计所有用户的文章月被查看数量
     *
     * @param usrId 用户id
     * @return List<Map < String, Integer>>
     * id:文章id
     * count:被阅读数量
     */
    public List<ArticleDownloadCount> countDownloadOfMonth(Integer usrId) {
        return baseMapper.countDownloadOfMonth(usrId);
    }

    public Integer countByArticleId(Integer articleId) {
        Check.notEmptyCheck(articleId);
        return baseMapper.countByArticleId(articleId);
    }
}
