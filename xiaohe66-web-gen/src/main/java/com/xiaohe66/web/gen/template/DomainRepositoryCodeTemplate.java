package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.AbstractCodeTemplate;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import com.xiaohe66.web.gen.prop.DomainCodeTemplateProperty;
import org.apache.velocity.VelocityContext;

import java.io.Writer;

/**
 * @author xiaohe
 * @since 2022.03.10 15:59
 */
public class DomainRepositoryCodeTemplate extends AbstractCodeTemplate {

    protected DomainCodeTemplateProperty property;

    public DomainRepositoryCodeTemplate(DomainCodeTemplateProperty property) {
        super(property, "repository.vm");
        this.property = property;
    }

    @Override
    public void gen(JavaDefinition javaDefinition, Writer writer) {

        VelocityContext velocityContext = genDefaultContext(javaDefinition.getClassName());

        velocityContext.put("packageName", property.getPackageName());

        template.merge(velocityContext, writer);
    }

    @Override
    public String genFileName(JavaDefinition javaDefinition) {
        return javaDefinition.getClassName() + "Repository.java";
    }
}
