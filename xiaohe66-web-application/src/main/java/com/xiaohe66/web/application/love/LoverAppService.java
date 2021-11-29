package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.love.convert.LoverBoConverter;
import com.xiaohe66.web.application.love.result.LoverInfoResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.wx.user.aggregate.WxUser;
import com.xiaohe66.web.domain.wx.user.repository.WxUserRepository;
import com.xiaohe66.web.integration.cache.CacheHelper;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author xiaohe
 * @since 2020.01.06 16:42
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoverAppService {

    public static final String SERIAL_NO_SESSION_KEY = "loverSerialNo";

    private final LoverBoConverter boConverter;

    private final LoverRepository loverRepository;
    private final WxUserRepository wxUserRepository;

    private final LoverService loverService;
    private final SecurityService securityService;

    @NeedLogin
    public R<String> readyBind() {

        String serialNo = securityService.getAttribute(SERIAL_NO_SESSION_KEY);

        AccountId currentAccountId = securityService.getCurrentAccountId();

        if (serialNo != null) {

            CacheHelper.put30(serialNo, currentAccountId);
            return R.ok(serialNo);
        }

        serialNo = genSerialNo();

        CacheHelper.put30(serialNo, currentAccountId);

        securityService.setAttribute(SERIAL_NO_SESSION_KEY, serialNo);

        log.info("ready bind lover, currentAccount : {}, serialNo : {}", currentAccountId, serialNo);

        return R.ok(serialNo);
    }

    @NeedLogin
    public void binding(String serialNo) {

        AccountId accountId = CacheHelper.get30(serialNo);

        AccountId currentAccountId = securityService.getCurrentAccountId();

        if (currentAccountId.equals(accountId)) {
            throw new BusinessException(ErrorCodeEnum.PARAM_ERROR, "不可绑定自己");
        }

        if (accountId == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        loverService.bind(accountId);
    }

    @NeedLogin
    public void confirmBind() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountId(currentAccountId);

        if (lover == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        loverService.confirmBind(lover);
    }

    @NeedLogin
    public R<LoverInfoResult> info() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountId(currentAccountId);
        if (lover == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        AccountId loverAccountId = currentAccountId.equals(lover.getCreateId()) ? lover.getAccountId() : lover.getCreateId();
        WxUser wxUser = wxUserRepository.getByAccountId(loverAccountId);

        LoverInfoResult result = boConverter.toResult(lover, wxUser);

        return R.ok(result);
    }

    /*public Integer getCurrentUserLoverId() {
        Integer currentUsrId = UserHelper.getCurrentUsrId();

        Integer loverId = baseMapper.selectLoverIdByUserId(currentUsrId);
        if (loverId == null) {
            log.warn("用户{}的loverId为null", currentUsrId);
        }
        return loverId;
    }*/

    protected String genSerialNo() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
