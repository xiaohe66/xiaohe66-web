package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.11.29 14:02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoverService {

    public static final String CURRENT_LOVER_ID_SESSION_KEY = "currentLoverId";

    private final LoverRepository loverRepository;
    private final SecurityService securityService;

    public Lover bind(AccountId accountId) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        LoverId loverId = new LoverId(IdWorker.genId());

        Lover lover = Lover.builder()
                .id(loverId)
                .createId(currentAccountId)
                .accountId(accountId)
                .status(LoverStatus.READY)
                .build();

        loverRepository.save(lover);

        return lover;
    }

    public void confirmBind(Lover lover) {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        if (currentAccountId.equals(lover.getCreateId())) {

            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION, "不可以自己同意自己的申请");
        }

        if (!currentAccountId.equals(lover.getAccountId())) {

            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION, "不是自己的");
        }

        lover.confirm();

        loverRepository.save(lover);
    }


    public LoverId getCurrentLoverId() {

        LoverId loverId = securityService.getAttribute(CURRENT_LOVER_ID_SESSION_KEY);

        if (loverId != null) {
            return loverId;
        }

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountId(currentAccountId);

        if (lover == null || !LoverStatus.NORMAL.equals(lover.getStatus())) {
            throw new BusinessException(ErrorCodeEnum.ILLEGAL_OPERATE, "当前账户不是伴侣");
        }

        securityService.setAttribute(CURRENT_LOVER_ID_SESSION_KEY, lover.getId());

        return lover.getId();
    }

    public boolean hasLoverPermission(LoverId loverId) {
        // TODO : 管理员权限如何处理
        LoverId currentLoverId = getCurrentLoverId();
        return currentLoverId.equals(loverId);
    }

    public void checkLoverPermission(LoverId loverId) {
        if (!hasLoverPermission(loverId)) {
            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION);
        }
    }

}
