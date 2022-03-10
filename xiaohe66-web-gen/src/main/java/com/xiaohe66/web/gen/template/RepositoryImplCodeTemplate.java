package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.bo.JavaDefinition;
import com.xiaohe66.gen.template.impl.RepositoryCodeTemplate;
import com.xiaohe66.gen.template.impl.prop.RepositoryCodeBuildProperty;

/**
 * @author xiaohe
 * @since 2022.03.10 16:50
 */
public class RepositoryImplCodeTemplate extends RepositoryCodeTemplate {

    public RepositoryImplCodeTemplate(RepositoryCodeBuildProperty property) {
        super(property, "repositoryImpl.vm");
    }

    @Override
    public String genFileName(JavaDefinition definition) {
        return definition.getClassName() + "RepositoryImpl.java";
    }
}
