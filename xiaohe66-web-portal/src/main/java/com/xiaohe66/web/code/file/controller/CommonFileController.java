package com.xiaohe66.web.code.file.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.XhIoException;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.file.dto.UploadFilePrepareDto;
import com.xiaohe66.web.code.file.service.CommonFileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 所有文件都需要经过该类的上传，然后再关联，其它类不可以上传文件
 * <p>
 * todo : 下载文件和显示文件，放到关联类里面
 *
 * @author xiaohe
 * @time 18-03-25 025
 */
@XhController("/comm/file")
public class CommonFileController {

    private CommonFileService commonFileService;

    public CommonFileController(CommonFileService commonFileService) {
        this.commonFileService = commonFileService;
    }

    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        Check.notEmpty(id,"id");
        Set<Integer> cache = WebUtils.getSessionAttr(CommonFileService.CACHE_FILE_MD5_SESSION_KEY);
        if (cache != null && cache.contains(id)) {
            response.setContentType(Final.Str.CONTENT_TYPE_IMAGE_PNG);
            commonFileService.outputFile(response.getOutputStream(), id);
        }
    }

    @Post("/prepare")
    public Result uploadFilePrepare(String md5, Float mb) {
        UploadFilePrepareDto uploadFilePrepareDto = commonFileService.uploadFilePrepare(md5, mb);
        return Result.ok(uploadFilePrepareDto);
    }

    @Post
    public Boolean uploadFile(MultipartFile file, String md5, Integer chunk) throws XhIoException {
        return commonFileService.uploadFile(file, md5, chunk);
    }

}
