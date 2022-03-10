package com.xiaohe66.web.gen.template;

import com.xiaohe66.gen.template.AbstractCodeTemplate;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import com.xiaohe66.web.gen.prop.DomainCodeTemplateProperty;
import org.apache.velocity.VelocityContext;

import java.io.Writer;

/**
 * @author xiaohe
 * @since 2022.03.10 15:50
 */
public class AggCodeTemplate extends AbstractCodeTemplate {

    protected DomainCodeTemplateProperty property;

    public AggCodeTemplate(DomainCodeTemplateProperty property) {
        super(property, "agg.vm");
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
        return javaDefinition.getClassName() + ".java";
    }
}
