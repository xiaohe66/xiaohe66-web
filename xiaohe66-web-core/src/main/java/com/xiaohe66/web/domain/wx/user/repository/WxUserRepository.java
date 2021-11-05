package com.xiaohe66.web.domain.wx.user.repository;

import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.value.WxUnionId;
import com.xiaohe66.web.domain.wx.user.value.WxUserId;
import com.xiaohe66.web.integration.domain.Repository;

/**
 * @author xiaohe
 * @since 2021.08.11 17:51
 */
public interface WxUserRepository extends Repository<WxUser, WxUserId> {

    WxUser getByUnionId(WxUnionId unionId);

}
