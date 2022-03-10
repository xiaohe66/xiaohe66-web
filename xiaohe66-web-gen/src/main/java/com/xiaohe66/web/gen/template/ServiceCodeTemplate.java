package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.AbstractCodeTemplate;
import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import org.apache.velocity.VelocityContext;

import java.io.Writer;

/**
 * @author xiaohe
 * @since 2022.03.10 16:04
 */
public class ServiceCodeTemplate extends AbstractCodeTemplate {

    public ServiceCodeTemplate(CodeTemplateProperty property) {
        super(property, "service.vm");
    }

    @Override
    public void gen(JavaDefinition javaDefinition, Writer writer) {

        VelocityContext velocityContext = genDefaultContext(javaDefinition.getClassName());

        velocityContext.put("entityName2", lowerFirst(javaDefinition.getClassName()));

        template.merge(velocityContext, writer);
    }

    private String lowerFirst(String className) {
        char c = className.charAt(0);
        return Character.toLowerCase(c) + className.substring(1);
    }

    @Override
    public String genFileName(JavaDefinition javaDefinition) {
        return javaDefinition.getClassName() + "Service.java";
    }
}
