package com.xiaohe66.web.gen.prop;

import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2022.03.10 15:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DomainCodeTemplateProperty extends CodeTemplateProperty {

    private String packageName;

}
