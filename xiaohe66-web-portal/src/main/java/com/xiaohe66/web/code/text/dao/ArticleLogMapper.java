package com.xiaohe66.web.code.text.dao;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.text.po.ArticleDownloadCount;
import com.xiaohe66.web.code.text.po.ArticleLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xh
 * @date 18-04-16 016
 */
public interface ArticleLogMapper extends IBaseMapper<ArticleLog> {

    /**
     * 月查看数量
     * 传入的用户id不为null时，统计该用户的文章月被查看数量
     * 传入的用户id为null时，统计所有用户的文章月被查看数量
     *
     * @param usrId 用户id
     * @return 月被查看数量
     */
    List<ArticleDownloadCount> countDownloadOfMonth(@Param("usrId") Integer usrId);

    /**
     * 统计某个文章的被查看数量
     *
     * @param articleId 文章id
     * @return 被查看的数量
     */
    Integer countByArticleId(@Param("articleId") Integer articleId);
}
