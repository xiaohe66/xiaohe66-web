package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.common.util.Paging;
import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.domain.love.repository.WishRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    private final LoverService loverService;

    public void update(Wish wish) {

        Wish dbWish = wishRepository.getById(wish.getId());

        loverService.checkLoverPermission(dbWish.getLoverId());

        wishRepository.save(wish);
    }

    public List<Wish> list(LoverId loverId, WishFinished finished, Paging paging) {

        loverService.checkLoverPermission(loverId);

        return wishRepository.listByLoverIdDesc(loverId, finished, paging);
    }

    public Wish detail(WishId id) {

        Wish wish = wishRepository.getById(id);
        if (wish == null) {
            return null;
        }

        loverService.checkLoverPermission(wish.getLoverId());

        return wish;
    }
}
