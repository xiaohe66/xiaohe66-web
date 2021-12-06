package com.xiaohe66.web.application.file.result;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.06 11:23
 */
@Data
public class PictureListResult {

    private String name;
    private List<String> images;

}
