package com.xiaohe66.web.application.file.bo;

import lombok.Data;

import java.io.InputStream;

/**
 * @author xiaohe
 * @since 2021.12.06 09:58
 */
@Data
public class ImageUploadBo {

    private String name;
    private InputStream inputStream;

}
