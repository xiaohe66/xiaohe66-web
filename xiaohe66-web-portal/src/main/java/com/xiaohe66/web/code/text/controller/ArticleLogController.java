package com.xiaohe66.web.code.text.controller;

import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.text.service.ArticleLogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xh
 * @date 18-10-26 026
 */
@XhController("/text/article/log")
public class ArticleLogController {

    @Autowired
    private ArticleLogService articleLogService;

    @Post
    public void add(){
        articleLogService.add();
    }

}
