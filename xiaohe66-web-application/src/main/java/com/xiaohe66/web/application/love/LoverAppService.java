package com.xiaohe66.web.application.love;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.convert.LoverBoConverter;
import com.xiaohe66.web.application.love.result.LoverInfoResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import com.xiaohe66.web.integration.util.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaohe
 * @since 2020.01.06 16:42
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoverAppService {

    public static final String SERIAL_NO_SESSION_KEY = "loverSerialNo";

    private static final Cache<String, Object> serialNoCache = CacheBuilder.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build();

    private final LoverBoConverter boConverter;

    private final LoverRepository loverRepository;
    private final WxUserRepository wxUserRepository;

    private final LoverService loverService;
    private final SecurityService securityService;

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<String> readyBind() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountIdValid(currentAccountId);
        if (lover != null) {
            return R.build(ErrorCodeEnum.ILLEGAL_OPERATE.getCode(), "已存在绑定对象");
        }

        String serialNo = securityService.getAttribute(SERIAL_NO_SESSION_KEY);

        if (serialNo != null && serialNoCache.getIfPresent(serialNo) != null) {
            return R.ok(serialNo);
        }

        serialNo = getOrGenAndCacheSerialNo(currentAccountId);

        securityService.setAttribute(SERIAL_NO_SESSION_KEY, serialNo);

        log.info("ready bind lover, currentAccount : {}, serialNo : {}", currentAccountId, serialNo);

        return R.ok(serialNo);
    }


    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<LoverInfoResult> binding(String serialNo) {

        AccountId loveAccountId = (AccountId) serialNoCache.getIfPresent(serialNo);
        Assert.notNull(loveAccountId, ErrorCodeEnum.NOT_FOUND, "识别码不存在");

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = boConverter.toSaveAgg(loveAccountId);
        loverService.bind(currentAccountId, loveAccountId);

        LoverInfoResult result = boConverter.toResultFull(lover);

        log.info("binding lover,serialNo : {}, lover : {}", serialNo, lover);

        return R.ok(result);
    }

    @NeedRoles(RoleName.LOVE_ROLE_VALUE)
    public R<Void> confirmBind() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        loverService.confirmBind(currentAccountId);

        return R.ok();
    }

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<Void> over() {

        AccountId currentAccountId = securityService.getCurrentAccountId();
        loverService.over(currentAccountId);

        return R.ok();
    }

    @NeedLogin
    public R<LoverInfoResult> info() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountIdValid(currentAccountId);

        Assert.notNull(lover, ErrorCodeEnum.NOT_FOUND);

        LoverInfoResult result = boConverter.toResultFull(lover);

        return R.ok(result);
    }

    protected synchronized String getOrGenAndCacheSerialNo(AccountId accountId) {
        // TODO : 优化可用数量
        String serialNo;
        int qty = 0;
        do {
            int no = ThreadLocalRandom.current().nextInt(100000);
            serialNo = StringUtils.leftPad(String.valueOf(no), 5, '0');
            qty++;
            if (qty > 1000) {
                log.error("while times over 1000");
                throw new BusinessException(ErrorCodeEnum.ERROR);
            }

        } while (serialNoCache.getIfPresent(serialNo) != null);

        serialNoCache.put(serialNo, accountId);

        return serialNo;
    }

}
