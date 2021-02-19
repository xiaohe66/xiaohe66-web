package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.PrintLog;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.love.dto.NoteDto;
import com.xiaohe66.web.code.love.po.Note;
import com.xiaohe66.web.code.love.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2021.02.19 11:02
 */
@XhController("/love/note")
@Slf4j
@AllArgsConstructor
public class NoteController extends LoveController<NoteService, Note, NoteDto> {

    @PrintLog
    @Put("/update")
    public Result request(Note note) {
        checkUpdate(note);

        return baseService.updateBackup(note);
    }

}
