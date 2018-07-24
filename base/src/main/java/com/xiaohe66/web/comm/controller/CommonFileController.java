package com.xiaohe66.web.comm.controller;

import com.xiaohe66.web.comm.service.CommonFileService;
import com.xiaohe66.web.common.annotation.Get;
import com.xiaohe66.web.common.annotation.Post;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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
        response.setContentType(ParamFinal.CONTENT_TYPE_IMAGE_PNG);
        commonFileService.outputFile(id,response.getOutputStream());
    }

    @Post("/prepare")
    public Set<Integer> uploadFilePrepare(String md5, Float mb){
        return commonFileService.uploadFilePrepare(md5,mb);
    }

    @Post
    public Boolean uploadFile(MultipartFile file,String md5,Integer chunk) throws IOException {
        return commonFileService.uploadFile(file,md5,chunk);
    }


}
