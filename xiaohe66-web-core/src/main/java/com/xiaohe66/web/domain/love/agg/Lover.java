package com.xiaohe66.web.domain.love.agg;

import com.xiaohe66.common.util.ex.BusinessException;
import com.xiaohe66.common.util.ex.ErrorCodeEnum;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.LoverStatus;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.11.29 11:50
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Lover implements Aggregate<Lover, LoverId> {

    @NonNull
    private final LoverId id;

    @NonNull
    private final AccountId createId;

    @NonNull
    private final AccountId accountId;

    private LoverStatus status;

    public void confirm() {
        if (!LoverStatus.READY.equals(status)) {
            throw new BusinessException(ErrorCodeEnum.ILLEGAL_OPERATE);
        }
        status = LoverStatus.NORMAL;
    }

    public void over() {
        if (!LoverStatus.NORMAL.equals(status)) {
            throw new BusinessException(ErrorCodeEnum.ILLEGAL_OPERATE);
        }
        status = LoverStatus.OVER;
    }

    public void recover() {
        if (!LoverStatus.OVER.equals(status)) {
            throw new BusinessException(ErrorCodeEnum.ILLEGAL_OPERATE);
        }
        status = LoverStatus.NORMAL;
    }

    public AccountId getLoveAccountId(@NonNull AccountId currentAccountId) {
        return currentAccountId.equals(createId) ? accountId : createId;
    }

    @Override
    public boolean hasSameRootAttribute(Lover other) {
        return other != null &&
                Objects.equals(status, other.status);
    }
}
