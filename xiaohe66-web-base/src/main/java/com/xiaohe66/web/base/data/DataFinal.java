package com.xiaohe66.web.base.data;

/**
 *
 * 常量库
 *
 * @author xh
 * @date 18-03-03 003
 */

public interface DataFinal {

    /**
     * 私密等级，仅自己可见
     */
    int SECRET_LEVEL_ONLY_ONESELF = 0;

    /**
     * 私密等级，我的关注
     */
    int SECRET_LEVEL_MY_ATTENTION = 1;

    /**
     * 私密等级，全部可见
     */
    int SECRET_LEVEL_ALL = 2;

    /**
     * 发表状态，未发表
     */
    int PUBLISH_STATE_NOT_PUBLISH = 0;
    /**
     * 发表状态，已发表
     */
    int PUBLISH_STATE_IS_PUBLISH = 1;

    long XIAO_HE_USR_ID = 3;

}
