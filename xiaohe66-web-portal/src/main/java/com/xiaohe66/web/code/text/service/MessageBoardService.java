package com.xiaohe66.web.code.text.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.DtoConverter;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.code.org.helper.UserHelper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.dao.MessageBoardMapper;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.po.MessageBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @time 18-04-01 001
 */
@Service
@Slf4j
public class MessageBoardService extends AbstractService<MessageBoardMapper, MessageBoard>
        implements DtoConverter<MessageBoard, MessageBoardDto> {

    private UserService userService;

    public MessageBoardService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean save(MessageBoard po) {

        if (log.isInfoEnabled()) {
            log.info("增加一条留言：msg={}, 留言者={}",
                    po.getMsg(),
                    po.getAnonymity() == null ? UserHelper.getCurrentUsrIdNotEx() : po.getAnonymity());
        }

        // todo : 目前只能给站长留言
        po.setUsrId(SysCfgHelper.findXhUsrId());

        // 留言信息去掉删除掉标签，前端在传入时是转码后的，若传入带标签的，则会自动被删除掉
        String msg = HtmlUtils.delHtmlTag(po.getMsg());
        po.setMsg(msg);

        return retBool(baseMapper.insert(po));
    }


    public IPage<MessageBoardDto> pageData() {
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setUsrId(SysCfgHelper.findXhUsrId());
        IPage<MessageBoard> page = page(10, new QueryWrapper<>(messageBoard));

        return ClassUtils.convert(MessageBoardDto.class, page, this::convertDto);
    }



    @Override
    public QueryWrapper<MessageBoard> createPageDefaultQueryWrapper(MessageBoard messageBoard) {
        messageBoard.setUsrId(SysCfgHelper.findXhUsrId());
        QueryWrapper<MessageBoard> queryWrapper = new QueryWrapper<>(messageBoard);
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }

    @Override
    public void convertDto(MessageBoardDto dto, MessageBoard po) {
        String anonymity = po.getAnonymity();
        if (anonymity == null || anonymity.length() == 0) {
            User user = userService.getById(po.getCreateId());
            dto.setUserName(user.getUserName());
            dto.setImgFileId(user.getImgFileId());
        } else {
            dto.setUserName(po.getAnonymity());
            dto.setImgFileId(1);
        }
    }
}
