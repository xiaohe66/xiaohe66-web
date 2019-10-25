package com.xiaohe66.web.code.file.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.file.dto.UsrFileDto;
import com.xiaohe66.web.code.file.po.UserFile;
import com.xiaohe66.web.code.file.service.UserFileService;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @time 18-03-12 012
 */
@XhController("/org/user/file")
public class UserFileController extends BaseController<UserFileService, UserFile, UsrFileDto> {

    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        // todo : impl
    }

    @Post("/head")
    public Result updateHeadImg(Integer fileId, String md5) {
//        userService.updateImgFile(fileId);
        return Result.ok();
    }

    @Post("/article")
    public Result uploadArticleImg(Integer fileId, String md5) {
//        return baseService.uploadImg(file, md5, UserHelper.getCurrentUsrId(), baseService.USR_ARTICLE_IMG_FILE_TYPE);
        return Result.ok();
    }

}
