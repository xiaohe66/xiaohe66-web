package com.xiaohe66.web.base.data;

/**
 *
 * 常量库
 *
 * @author xh
 * @date 18-03-03 003
 */

public interface Final {

    /**
     * 文章模块
     */
    interface Article{
        /**
         * 私密等级，全部可见
         */
        int SECRET_LEVEL_PUBLIC = 0;

        /**
         * 私密等级，仅自己可见
         */
        int SECRET_LEVEL_ONLY_ONESELF = 1;

        /**
         * 简历文章
         *
         * 列表不显示，但是可以被任意人查看详情
         */
        int SECRET_LEVEL_RESUME_ARTICLE = 2;

        /**
         * 发表状态，未发表
         */
        int PUBLISH_STATE_NOT_PUBLISH = 0;
        /**
         * 发表状态，已发表
         */
        int PUBLISH_STATE_PUBLISH = 1;
    }

    /**
     * 系统相关
     */
    interface Sys{
        /**
         * xiaohe usrId
         */
        long XIAO_HE_USR_ID = 3;
    }

    /**
     * 常量字符串
     */
    interface Str{
        String UTF_8 = "UTF-8";
        String SESSION_UER_KEY = "usr" ;
        String HEADER_JSON_UTF_8 = "application/json; charset=UTF-8" ;
        String DEFAULT_ROLE_IDS_KEY = "DEFAULT_ROLE_IDS" ;
        String PAGING_SIZE_KEY = "pageSize" ;
        String PAGING_NUM_KEY = "pageNum" ;
    
        String SESSION_IMG_AUTH_CODE_KEY = "imgAuthCode" ;
        String SESSION_EMAIL_AUTH_CODE_KEY = "emailAuthCode" ;
        String SESSION_REGISTERING_USR_KEY = "registeringUsr" ;

        String SESSION_UPDATE_PWD_USR_KEY = "updatePwdUsr";
    
        String CONTENT_TYPE_IMAGE_PNG = "image/png" ;
        String FILE_TYPE_PNG = "png" ;
    
        String CFG_KEY_XIAO_HE_USR_ID = "XIAO_HE_USR_ID" ;
        String DEFAULT_ARTICLE_PID = "DEFAULT_ARTICLE_PID" ;
        String DEFAULT_USR_IMG_FILE_ID = "DEFAULT_USR_IMG_FILE_ID" ;
    
    
        String ARTICLE_LOG_CACHE = "ARTICLE_LOG_CACHE" ;
        String ARTICLE_LOG_ADD_PREPARE = "ARTICLE_LOG_ADD_PREPARE" ;
        String USR_FILE_LOG_CACHE = "USR_FILE_LOG_CACHE" ;
    
        String SYS_EMAIL_HOST_KEY = "SYS_EMAIL_HOST";
        String SYS_EMAIL_PWD_KEY = "SYS_EMAIL_PWD";
        String SYS_EMAIL_USR_NAME_KEY = "SYS_EMAIL_USR_NAME";
        String SYS_EMAIL_SMTP_HOST_KEY = "SYS_EMAIL_SMTP_HOST";
    
        String SYS_EMAIL_CONTENT_TYPE = "text/html;charset=UTF-8";
    
    
        String DEFAULT_WEB_TITLE = "";
    }

}
