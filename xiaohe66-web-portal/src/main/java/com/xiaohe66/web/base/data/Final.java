package com.xiaohe66.web.base.data;

/**
 * 常量库
 *
 * @author xh
 * @date 18-03-03 003
 */

public final class Final {

    /**
     * 文章模块
     */
    public interface Article {
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
         * <p>
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
    public static final class User {
        private User() {
        }

        public static final int ROOT_USER_ID = 1;
        public static final int YAN_ZI_USER_ID = 2;
        public static final int XIAO_HE_USER_ID = 3;
    }

    public static final class SessionKey {
        private SessionKey() {
        }

        public static final String IMG_AUTH_CODE = "imgAuthCode";
        public static final String EMAIL_AUTH_CODE = "emailAuthCode";
        public static final String REGISTERING_USER = "registeringUser";
        public static final String CURRENT_LOGIN_USER = "user";
        public static final String UPDATE_PWD_USER = "updatePwdUser";
    }

    public static final class ConfigKey {
        private ConfigKey() {
        }

        public static final String EMAIL_HOST = "SYS_EMAIL_HOST";
        public static final String EMAIL_PWD = "SYS_EMAIL_PWD";
        public static final String EMAIL_USER_NAME = "SYS_EMAIL_USR_NAME";
        public static final String EMAIL_SMTP_HOST = "SYS_EMAIL_SMTP_HOST";
    }

    public static final class HeaderKey {
        private HeaderKey() {
        }

        // todo : 这个值是 ContentType ，放在这里好像不合适
        public static final String JSON_UTF_8 = "application/json; charset=UTF-8";
        public static final String PAGE_SIZE = "pageSize";
        public static final String PAGE_NO = "pageNo";

    }

    public static final class CacheKey {
        private CacheKey(){
        }
        public static final String USER_FILE_LOG_CACHE = "USER_FILE_LOG_CACHE";
    }

    public interface Str {
        String CONTENT_TYPE_IMAGE_PNG = "image/png";
        String FILE_TYPE_PNG = "png";

        String DEFAULT_ARTICLE_PID = "DEFAULT_ARTICLE_PID";
        String DEFAULT_USR_IMG_FILE_ID = "DEFAULT_USR_IMG_FILE_ID";

        String ARTICLE_LOG_CACHE = "ARTICLE_LOG_CACHE";
        String ARTICLE_LOG_ADD_PREPARE = "ARTICLE_LOG_ADD_PREPARE";

        String SYS_EMAIL_CONTENT_TYPE = "text/html;charset=UTF-8";

        String DEFAULT_WEB_TITLE = "";
    }

}
