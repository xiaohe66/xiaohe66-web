package com.xiaohe66.web.gateway.http.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.file.PictureAppService;
import com.xiaohe66.web.application.file.result.PictureListResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.06 11:38
 */
@RestController
@RequestMapping("/file/picture")
@RequiredArgsConstructor
public class PictureController {

    private final PictureAppService pictureAppService;

    @GetMapping
    public R<List<PictureListResult>> list() {

        return pictureAppService.list();
    }


    // 这种方式不支持中文
    /*@GetMapping("/{dir}/{name}")
    public void download_bak(@PathVariable("dir") String dir,
                             @PathVariable("name") String name,
                             HttpServletResponse response) throws IOException {

        String path = File.separator + dir + File.separator + name;

        response.setContentType("image/jpg");
        pictureAppService.download(path, response.getOutputStream());
    }*/

    @GetMapping("/download")
    public void download(@RequestParam String dir,
                         @RequestParam String name,
                         @RequestParam(required = false, defaultValue = "true") boolean isSmall,
                         HttpServletResponse response) throws IOException {

        String path = isSmall ?
                File.separator + dir + File.separator + "small" + File.separator + name :
                File.separator + dir + File.separator + name;

        response.setContentType("image/jpg");
        pictureAppService.download(path, response.getOutputStream());
    }


}
