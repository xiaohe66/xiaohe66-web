package com.xiaohe66.web.domain.love.repository;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.integration.domain.Repository;

/**
 * @author xiaohe
 * @since 2021.11.29 12:08
 */
public interface LoverRepository extends Repository<Lover, LoverId> {

    Lover getByAccountId(AccountId accountId);

}
