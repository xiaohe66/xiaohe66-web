package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import com.xiaohe66.gen.template.impl.EntityCodeTemplate;

/**
 * @author xiaohe
 * @since 2022.03.10 11:07
 */
public class DoCodeTemplate extends EntityCodeTemplate {

    public DoCodeTemplate(CodeTemplateProperty property) {
        super(property, "do.vm");
    }

    @Override
    public String genFileName(JavaDefinition definition) {
        return definition.getClassName() + "Do.java";
    }

}
