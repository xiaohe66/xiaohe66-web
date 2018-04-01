package com.xiaohe66.web.comm.controller;

import com.xiaohe66.web.comm.service.CommonFileService;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @date 18-03-25 025
 */
@XhController("/comm/file")
public class CommonFileController {

    @Autowired
    private CommonFileService commonFileService;

    @Get("/img/{id}")
    public void showImg(HttpServletResponse response,@PathVariable("id")Long id) throws IOException {
        if(id == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"id is null");
        }
        response.setContentType(StrEnum.CONTENT_TYPE_IMAGE_PNG.data());
        commonFileService.outputFile(id,response.getOutputStream());
    }

}
