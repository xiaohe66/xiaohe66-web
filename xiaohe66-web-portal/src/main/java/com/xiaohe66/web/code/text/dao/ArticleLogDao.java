package com.xiaohe66.web.code.text.dao;

import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.code.text.po.ArticleLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xh
 * @date 18-04-16 016
 */
public interface ArticleLogDao extends BaseDao<ArticleLog>{

    /**
     * 月查看数量
     * 传入的用户id不为null时，统计该用户的文章月被查看数量
     * 传入的用户id为null时，统计所有用户的文章月被查看数量
     * @param usrId 用户id
     * @return  月被查看数量
     */
    List<Map<String,Long>> countDownloadOfMonth(@Param("usrId") Long usrId);

    /**
     * 统计某个文章的被查看数量
     * @param articleId 文章id
     * @return  被查看的数量
     */
    Long countByArticleId(@Param("articleId") Long articleId);
}
