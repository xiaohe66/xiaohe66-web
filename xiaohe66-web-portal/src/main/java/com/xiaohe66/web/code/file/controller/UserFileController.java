package com.xiaohe66.web.code.file.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.XhIoException;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.file.dto.UserFileDto;
import com.xiaohe66.web.code.file.po.UserFile;
import com.xiaohe66.web.code.file.service.UserFileService;
import com.xiaohe66.web.code.org.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @time 18-03-12 012
 */
@XhController("/org/user/file")
public class UserFileController extends BaseController<UserFileService, UserFile, UserFileDto> {

    private UserService userService;

    public UserFileController(UserService userService) {
        this.userService = userService;
    }

    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable Integer id) throws IOException {
        response.setContentType(Final.Str.CONTENT_TYPE_IMAGE_PNG);
        baseService.showImg(response.getOutputStream(), id);
    }

    @Page("/down/{id}")
    public void download(HttpServletResponse response, @PathVariable Integer id) throws XhIoException {
        baseService.downloadFile(response, id);
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

//    @Page("/index")
    public String index(Model model){
        model.addAttribute("usrDto",userService.lookAtUser(null));
        model.addAttribute("title","资源列表");
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("hasEdit", WebUtils.isPermitted(moduleName+":update"));
        model.addAttribute("hasDelete", WebUtils.isPermitted(moduleName+":delete"));
        return "/org/userFile";
    }

    @Override
    protected void checkSelect(UserFile userFile) {
        // 不检查下载权限
    }

    @Override
    protected void checkUpdate(UserFile po) {
        super.checkUpdate(po);
        // 下面3个属性不可以被更新
        po.setFileType(null);
        po.setFileId(null);
        po.setFileDesc(null);
    }
}
