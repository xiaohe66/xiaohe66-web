package com.xiaohe66.web.common.data;

/**
 * @author xiaohe
 * @time 17-10-29 029
 */
public enum StrEnum {

    //
    SESSION_UER_KEY("usr")
    ,HEADER_UTF_8("application/json; charset=UTF-8")
    ,DEFAULT_ROLE_IDS_KEY("DEFAULT_ROLE_IDS")
    ,PAGING_SIZE_KEY("pageSize")
    ,PAGING_NUM_KEY("pageNum")

    ,SESSION_AUTH_CODE_KEY("authCode")
    ,CONTENT_TYPE_IMAGE_PNG("image/png")
    ,FILE_TYPE_PNG("png")

    ,CFG_KEY_XIAO_HE_USR_ID("XIAO_HE_USR_ID")
    ,DEFAULT_ARTICLE_PID("DEFAULT_ARTICLE_PID")


    ,ARTICLE_LOG_CACHE("ARTICLE_LOG_CACHE")
    ,USR_FILE_LOG_CACHE("USR_FILE_LOG_CACHE")


    ;

    private String data;

    StrEnum(String data){
        this.data = data;
    }

    public String data() {
        return data;
    }
}
