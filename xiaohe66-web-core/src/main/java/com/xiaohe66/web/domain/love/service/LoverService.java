package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.common.util.Assert;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AccountRepository accountRepository;
    private final SecurityService securityService;

    public Lover bind(AccountId currentAccountId, AccountId loveAccountId) {

        /*
            NOTE : 不支持取消绑定，因为用的人肯定很少。
         */
        Assert.requireFalse(currentAccountId.equals(loveAccountId), ErrorCodeEnum.PARAM_ERROR, "不可绑定自己");

        // List<Lover> loverList = loverRepository.getByAccountId(currentAccountId);

        // 1. 若绑定的是曾经的爱人，则恢复
        /*for (Lover dbLover : loverList) {
            if (dbLover.getLoveAccountId(currentAccountId).equals(loveAccountId)) {
                // 恢复
                dbLover.recover();
                loverRepository.save(dbLover);
                return dbLover;
            }
        }

        // 2. 若自己存在有效的情侣，则无法绑定
        for (Lover dbLover : loverList) {

            Assert.isFalse(LoverStatus.NORMAL.equals(dbLover.getStatus()), ErrorCodeEnum.ILLEGAL_OPERATE);
        }*/

        // 保证绑定的双方都不存在第三者关系，正常会在前端控制，因此这里抛出非法操作
        Lover dbLover = loverRepository.getByAccountIdValid(currentAccountId);
        Assert.requireTrue(dbLover == null, ErrorCodeEnum.ILLEGAL_OPERATE);

        dbLover = loverRepository.getByAccountIdValid(loveAccountId);
        Assert.requireTrue(dbLover == null, ErrorCodeEnum.ILLEGAL_OPERATE);

        // 3. 不存在，新增绑定
        Lover lover = Lover.builder()
                .id(new LoverId(IdWorker.genId()))
                .createId(currentAccountId)
                .accountId(loveAccountId)
                .status(LoverStatus.READY)
                .build();

        loverRepository.save(lover);

        return lover;
    }

    @Transactional(rollbackFor = Exception.class)
    public void confirmBind(AccountId currentAccountId) {

        Lover lover = loverRepository.getByAccountIdValid(currentAccountId);

        Assert.requireNotNull(lover, ErrorCodeEnum.NOT_FOUND, "当前账号不是情侣");

        Assert.requireFalse(currentAccountId.equals(lover.getCreateId()), ErrorCodeEnum.NOT_DATA_PERMISSION, "不可以自己同意自己的申请");

        lover.confirm();

        loverRepository.save(lover);

        // 保存情侣权限
        Account currentAccount = accountRepository.getById(lover.getAccountId());
        currentAccount.addRole(RoleId.LOVER_ROLE_ID);
        accountRepository.save(currentAccount);

        Account loveAccount = accountRepository.getById(lover.getCreateId());
        loveAccount.addRole(RoleId.LOVER_ROLE_ID);
        accountRepository.save(loveAccount);
    }

    @Transactional(rollbackFor = Exception.class)
    public void over(AccountId currentAccountId) {

        Lover lover = loverRepository.getByAccountIdValid(currentAccountId);

        lover.over();

        Account account = accountRepository.getById(lover.getAccountId());
        account.removeRole(RoleId.LOVER_ROLE_ID);
        accountRepository.save(account);

        Account createAccount = accountRepository.getById(lover.getCreateId());
        createAccount.removeRole(RoleId.LOVER_ROLE_ID);
        accountRepository.save(createAccount);

        loverRepository.save(lover);
    }


    public Lover getCurrentLover() {

        AccountId currentAccountId = securityService.getCurrentAccountId();

        return loverRepository.getByAccountIdValid(currentAccountId);
    }

    public LoverId getCurrentLoverId() {

        LoverId loverId = securityService.getAttribute(CURRENT_LOVER_ID_SESSION_KEY);

        if (loverId != null) {
            return loverId;
        }

        AccountId currentAccountId = securityService.getCurrentAccountId();

        Lover lover = loverRepository.getByAccountIdValid(currentAccountId);

        if (lover == null || !LoverStatus.NORMAL.equals(lover.getStatus())) {
            throw new BusinessException(ErrorCodeEnum.ILLEGAL_OPERATE, "当前账户不是伴侣");
        }

        // cache
        securityService.setAttribute(CURRENT_LOVER_ID_SESSION_KEY, lover.getId());

        return lover.getId();
    }

    public boolean hasLoverPermission(LoverId loverId) {
        // note : 管理员默认没有 lover 权限
        LoverId currentLoverId = getCurrentLoverId();
        return currentLoverId.equals(loverId);
    }

    public void checkLoverPermission(LoverId loverId) {
        if (!hasLoverPermission(loverId)) {
            throw new BusinessException(ErrorCodeEnum.NOT_DATA_PERMISSION);
        }
    }

}
