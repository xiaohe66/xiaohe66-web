package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.love.result.LoverInfoResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.infrastructure.domain.adapter.love.LoverConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @since 2021.11.29 15:26
 */
@Mapper
public abstract class LoverBoConverter implements LoverConverter {

    protected static final LoverStatus DEFAULT_LOVER_STATUS = LoverStatus.READY;

    @Autowired
    protected SecurityService securityService;

    @Autowired
    protected WxUserRepository wxUserRepository;

    @Mapping(target = "id", expression = "java(genId())")
    @Mapping(target = "createId", expression = "java(securityService.getCurrentAccountId())")
    @Mapping(target = "status", expression = "java(DEFAULT_LOVER_STATUS)")
    public abstract Lover toSaveAgg(AccountId accountId);

    public LoverInfoResult toResultFull(Lover lover) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        AccountId loveId = lover.getLoveAccountId(currentAccountId);

        WxUser loverWxUser = wxUserRepository.getByAccountId(loveId);

        LoverInfoResult result = toResult(lover);
        result.setLoverNickname(loverWxUser.getNickname().getValue());
        result.setLoveId(loveId.getValue());

        return result;
    }

    @Mapping(source = "id", target = "createTime")
    abstract LoverInfoResult toResult(Lover lover);

    protected LoverId genId() {
        return new LoverId(IdWorker.genId());
    }

}
