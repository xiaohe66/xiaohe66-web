package com.xiaohe66.web.sys.spring;

import com.xiaohe66.web.base.annotation.XhController;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.util.Map;

/**
 * bean 默认名
 *
 * <p>若bean的类名上使用了{@link XhController}，则使用该注解中的name值作为bean的名称
 * 若未指定name的值，则按spring普通bean的策略生成
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-22 15:17
 * @see XhController
 */
public class XhControllerBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
			Map<String, Object> map =  ((AnnotatedBeanDefinition) definition).getMetadata().getAnnotationAttributes(XhController.class.getName());
			if (map != null) {
                Object obj = map.get("name");
                if (obj != null) {
                    String name = obj.toString();
                    if (name != null && name.length() != 0) {
                        return name;
                    }
                }
            }
		}
		return buildDefaultBeanName(definition, registry);
    }

}
