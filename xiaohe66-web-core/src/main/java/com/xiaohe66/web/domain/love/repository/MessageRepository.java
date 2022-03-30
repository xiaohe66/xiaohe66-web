package com.xiaohe66.web.domain.love.repository;

import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.common.util.Paging;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 11:46
 */
public interface MessageRepository extends Repository<Message, MessageId> {

    List<Message> listDesc(LoverId loverId, Paging paging);

}
