package com.xiaohe66.web.common.data;

/**
 * @author xiaohe
 * @time 17-10-29 029
 */
public enum StrEnum {

    SESSION_UER_KEY("usr")
    ,HEADER_UTF_8("application/json; charset=UTF-8")
    ,DEFAULT_ROLE_IDS_KEY("DEFAULT_ROLE_IDS")
    ,PAGING_SIZE_KEY("pageSize")
    ,PAGING_NUM_KEY("pageNum")
    ,VALIDATE_CODE_STR_KEY("validateCodeStr")


    ;

    private String data;

    StrEnum(String data){
        this.data = data;
    }

    public String data() {
        return data;
    }
}
