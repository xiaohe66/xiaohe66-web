package com.xiaohe66.web.text.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.HtmlUtils;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
import com.xiaohe66.web.text.dao.MessageBoardDao;
import com.xiaohe66.web.text.dto.MessageBoardDto;
import com.xiaohe66.web.text.param.MessageBoardParam;
import com.xiaohe66.web.text.po.MessageBoard;
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
public class MessageBoardService extends AbstractService<MessageBoard>{

    private static final Logger LOG = LoggerFactory.getLogger(MessageBoardService.class);

    @Autowired
    private UsrService usrService;

    public MessageBoardService() {
    }

    @Autowired
    public MessageBoardService(MessageBoardDao messageBoardDao) {
        super(messageBoardDao);
    }

    /**
     * 方法弃用，请使用add(?,?,?);
     * @param po 插入的实体
     * @param currentUsrId 当前操作者id
     */
    @Deprecated
    @Override
    public void add(MessageBoard po, Long currentUsrId) {
        throw new XhException(CodeEnum.DISABLE_FUNCTION,"invoke add(?,?,?) pls");
    }

    /**
     * 增加一条留言方法
     * @param msg       留言
     * @param usrId     被留言的用户
     * @param anonymity  匿名留言名称
     */
    public MessageBoard add(String msg,Long usrId,String anonymity){
        msg = HtmlUtils.delHtmlTag(msg);
        Check.notEmptyCheck(msg,usrId);

        MessageBoard messageBoard = new MessageBoard(usrId,msg);
        messageBoard.setAnonymity(anonymity);

        Long currentUsrId = UsrHelper.getCurrentUsrIdNotEx();

        LOG.info("增加一条留言：msg="+msg+",留言者="+(anonymity == null ? currentUsrId : anonymity));
        super.add(messageBoard,currentUsrId);
        return messageBoard;
    }

    /**
     * 根据被留言的用户id查询
     * @param usrId 被留言的用户id，默认为站长
     * @return  List<MessageBoardDto>
     */
    public List<MessageBoardDto> findByUsrId(Long usrId){
        if(usrId == null){
            String usrIdStr = SysCfgHelper.getString(Final.Str.CFG_KEY_XIAO_HE_USR_ID);
            usrId = StrUtils.toLong(usrIdStr);
        }

        List<MessageBoard> messageBoardList = super.findByParam(new MessageBoardParam(usrId));

        return ClassUtils.convertList(MessageBoardDto.class,messageBoardList,(messageBoardDto,messageBoard)->{
            String anonymity = messageBoard.getAnonymity();
            if(anonymity == null || anonymity.length() == 0){
                Usr usr = usrService.findById(messageBoard.getCreateId());
                messageBoardDto.setUsrName(usr.getUsrName());
                messageBoardDto.setImgFileId(usr.getImgFileId());
            }else{
                messageBoardDto.setUsrName(messageBoard.getAnonymity());
                messageBoardDto.setImgFileId(1L);
            }
        });
    }

}
