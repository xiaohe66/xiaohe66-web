package com.xiaohe66.web.gateway.http.file;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.web.application.file.ImageAppService;
import com.xiaohe66.web.application.file.bo.ImageUploadBo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaohe
 * @since 2021.12.06 10:10
 */
@RestController
@RequestMapping("/file/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageAppService imageAppService;

    @PostMapping
    public R<Long> upload(@RequestParam("file") MultipartFile file) throws IOException {

        ImageUploadBo bo = new ImageUploadBo();
        bo.setInputStream(file.getInputStream());

        return imageAppService.upload(bo);
    }

    @GetMapping("/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/png");
        imageAppService.download(id, response.getOutputStream());
    }

}
