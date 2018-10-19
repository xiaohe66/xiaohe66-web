package com.xiaohe66.web.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.CollectionUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.text.dao.ArticleLogDao;
import com.xiaohe66.web.text.po.ArticleLog;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xh
 * @date 18-04-16 016
 */
@Service
public class ArticleLogService extends AbstractService<ArticleLog>{

    private static final Logger LOG = LoggerFactory.getLogger(ArticleLogService.class);

    private ArticleLogDao articleLogDao;

    public ArticleLogService() {
    }

    @Autowired
    public ArticleLogService(ArticleLogDao articleLogDao) {
        super(articleLogDao);
        this.articleLogDao = articleLogDao;
    }


    /**
     * 重写插入方法
     * 定义查看数量的规则
     * 每个session只能增加一次访问量
     *
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Override
    public void add(ArticleLog po, Long currentUsrId) {
        String ip = WebUtils.getRequestIP();
        LOG.debug("ip："+ip);

        Long articleId = po.getArticleId();

        if(Check.eq(articleId,currentUsrId)){
            LOG.debug("look oneself article");
            return;
        }

        Session session = WebUtils.getSession();
        Set<Long> articleIdSet = (Set<Long>) session.getAttribute(Final.Str.ARTICLE_LOG_CACHE);
        if(CollectionUtils.isNull(articleIdSet)){
            articleIdSet = new HashSet<>(4);
            session.setAttribute(Final.Str.ARTICLE_LOG_CACHE,articleIdSet);
        }

        if(!articleIdSet.contains(articleId)){
            po.setIp(ip);
            po.setCreateId(currentUsrId);
            po.setCreateTime(new Date());
            super.add(po,currentUsrId);

            articleIdSet.add(articleId);
        }
    }

    /**
     * 月查看数量
     * 传入的用户id不为null时，统计该用户的文章月被查看数量
     * 传入的用户id为null时，统计所有用户的文章月被查看数量
     * @param usrId 用户id
     * @return List<Map<String,Long>>
     *          id:文章id
     *          count:被阅读数量
     */
    public List<Map<String,Long>> countDownloadOfMonth(Long usrId){
        return articleLogDao.countDownloadOfMonth(usrId);
    }

    public Long countByArticleId(Long articleId){
        Check.notEmptyCheck(articleId);
        return articleLogDao.countByArticleId(articleId);
    }
}
