package com.xiaohe66.web.code.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import com.xiaohe66.web.code.text.dao.MessageBoardMapper;
import com.xiaohe66.web.code.text.dto.MessageBoardDto;
import com.xiaohe66.web.code.text.param.MessageBoardParam;
import com.xiaohe66.web.code.text.po.MessageBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xh
 * @date 18-04-01 001
 */
@Service
public class MessageBoardService extends AbstractService<MessageBoardMapper, MessageBoard> {

    private static final Logger logger = LoggerFactory.getLogger(MessageBoardService.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean save(MessageBoard po) {
        logger.info("增加一条留言：msg={},留言者={}",
                po.getMsg(),
                po.getAnonymity() == null ? UsrHelper.getCurrentUsrIdNotEx() : po.getAnonymity());

        return super.save(po);
    }

    /**
     * 增加一条留言方法
     *
     * @param msg       留言
     * @param usrId     被留言的用户
     * @param anonymity 匿名留言名称
     */
    public MessageBoard save(String msg, Integer usrId, String anonymity) {
        msg = HtmlUtils.delHtmlTag(msg);
        Check.notEmptyCheck(msg, usrId);

        MessageBoard messageBoard = new MessageBoard(usrId, msg);
        messageBoard.setAnonymity(anonymity);

        save(messageBoard);
        return messageBoard;
    }

    /**
     * 根据被留言的用户id查询
     *
     * @param usrId 被留言的用户id，默认为站长
     * @return List<MessageBoardDto>
     */
    public List<MessageBoardDto> findByUsrId(Integer usrId) {
        if (usrId == null) {
            String usrIdStr = SysCfgHelper.getString(Final.Str.CFG_KEY_XIAO_HE_USR_ID);
            usrId = StrUtils.toInt(usrIdStr);
        }

        List<MessageBoard> messageBoardList = super.listByParam(new MessageBoardParam(usrId));

        return ClassUtils.convertList(MessageBoardDto.class, messageBoardList, (messageBoardDto, messageBoard) -> {
            String anonymity = messageBoard.getAnonymity();
            if (anonymity == null || anonymity.length() == 0) {
                User user = userService.getById(messageBoard.getCreateId());
                messageBoardDto.setUsrName(user.getUsrName());
                messageBoardDto.setImgFileId(user.getImgFileId());
            } else {
                messageBoardDto.setUsrName(messageBoard.getAnonymity());
                messageBoardDto.setImgFileId(1);
            }
        });
    }

}
