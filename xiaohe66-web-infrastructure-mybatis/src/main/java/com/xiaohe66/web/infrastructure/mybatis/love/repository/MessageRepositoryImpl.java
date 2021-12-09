package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.love.agg.Message;
import com.xiaohe66.web.domain.love.repository.MessageRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.MessageId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.MessageDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.MessageMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.MessageDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.web.integration.domain.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.07 11:49
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class MessageRepositoryImpl
        extends AbstractMybatisService<MessageDoConverter, MessageMapper, MessageDo, Message, MessageId>
        implements MessageRepository {


    @Override
    public List<Message> listDesc(LoverId loverId, Paging paging) {

        LambdaQueryWrapper<MessageDo> queryWrapper = new LambdaQueryWrapper<MessageDo>()
                .eq(MessageDo::getLoverId, loverId.getValue())
                .orderByDesc(MessageDo::getId)
                .last(paging.toLimit());

        List<MessageDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
