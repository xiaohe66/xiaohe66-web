package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.love.mapper.NoteMapper;
import com.xiaohe66.web.code.love.po.Note;
import com.xiaohe66.web.code.love.po.NoteBak;
import com.xiaohe66.web.code.org.helper.UserHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author xiaohe
 * @time 2021.02.19 10:57
 */
@AllArgsConstructor
@Service
@Slf4j
public class NoteService extends AbstractService<NoteMapper, Note> {

    private final NoteBakService noteBakService;

    @Transactional(rollbackFor = Exception.class)
    public Result updateBackup(Note note) {

        Note dbNote = getById(note.getId());

        Integer currentUsrId = UserHelper.getCurrentUsrId();

        log.info("updateBackup note, currentUserId : {}, dbNote : {}", currentUsrId, dbNote);

        if (!currentUsrId.equals(dbNote.getCreateId())) {
            return Result.err(CodeEnum.B2_ILLEGAL_OPERATE);
        }

        NoteService currentProxy = (NoteService) AopContext.currentProxy();
        currentProxy.updateById(note);

        boolean needBak = !Objects.equals(dbNote.getTitle(), note.getTitle()) ||
                !Objects.equals(dbNote.getDesc(), note.getDesc());

        if (needBak) {
            NoteBak noteBak = new NoteBak();
            noteBak.setCreateId(currentUsrId);
            noteBak.setNoteId(dbNote.getId());
            noteBak.setTitle(dbNote.getTitle());
            noteBak.setDesc(dbNote.getDesc());

            noteBakService.save(noteBak);
        }

        return Result.ok();
    }

    @Override
    public QueryWrapper<Note> createDefaultQueryWrapper(Note po) {
        QueryWrapper<Note> queryWrapper = super.createDefaultQueryWrapper(po);

        queryWrapper.orderByDesc("is_top","update_time");

        return queryWrapper;
    }

}
