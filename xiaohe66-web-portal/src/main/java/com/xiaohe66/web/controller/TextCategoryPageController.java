package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.org.helper.UsrHelper;
import com.xiaohe66.web.text.dto.TextCategoryDto;
import com.xiaohe66.web.text.service.TextCategoryService;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xh
 * @date 18-03-07 007
 */
@XhController("/text/category")
public class TextCategoryPageController {

    private static final String CATEGORY_MANAGEMENT_PAGE_URL = "org/category_management";

    @Resource
    private TextCategoryService textCategoryService;

    @Page("/index")
    public String index(Model model){
        List list = ClassUtils.convertList(TextCategoryDto.class, textCategoryService.findByUsrId(UsrHelper.getCurrentUsrId()));
        model.addAttribute("list",list);
        model.addAttribute("size",list.size());
        model.addAttribute("page",CATEGORY_MANAGEMENT_PAGE_URL);
        model.addAttribute("title","分类管理");

        return OtherPageController.USR_ZONE_PAGE_URL;
    }

}
