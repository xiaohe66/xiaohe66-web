package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.AbstractCodeTemplate;
import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import org.apache.velocity.VelocityContext;

import java.io.Writer;

/**
 * @author xiaohe
 * @since 2022.03.10 15:44
 */
public class IdCodeTemplate extends AbstractCodeTemplate {

    public IdCodeTemplate(CodeTemplateProperty property) {
        super(property, "id.vm");
    }

    @Override
    public void gen(JavaDefinition javaDefinition, Writer writer) {

        VelocityContext velocityContext = genDefaultContext(javaDefinition.getClassName());

        this.template.merge(velocityContext, writer);
    }

    @Override
    public String genFileName(JavaDefinition javaDefinition) {
        return javaDefinition.getClassName() + "Id.java";
    }
}
