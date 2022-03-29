package com.xiaohe66.web.gen;

import com.xiaohe66.gen.DefaultCodeBuildProperty;
import com.xiaohe66.gen.template.CodeTemplate;
import com.xiaohe66.gen.template.bo.CodeTemplateProperty;
import com.xiaohe66.gen.template.impl.MapperCodeTemplate;
import com.xiaohe66.gen.write.file.FileCodeWriteFactory;
import com.xiaohe66.web.gen.prop.DomainCodeTemplateProperty;
import com.xiaohe66.web.gen.template.AggCodeTemplate;
import com.xiaohe66.web.gen.template.DoCodeTemplate;
import com.xiaohe66.web.gen.template.DoConverterCodeTemplate;
import com.xiaohe66.web.gen.template.DomainRepositoryCodeTemplate;
import com.xiaohe66.web.gen.template.DomainRepositoryImplCodeTemplate;
import com.xiaohe66.web.gen.template.IdCodeTemplate;
import com.xiaohe66.web.gen.template.RepositoryImplCodeTemplate;
import com.xiaohe66.web.gen.template.ServiceCodeTemplate;
import com.xiaohe66.web.integration.BaseLongInputDo;
import com.xiaohe66.web.integration.MapperSupport;
import com.xiaohe66.web.integration.ServiceSupport;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaohe
 * @since 2022.03.10 11:15
 */
public class GenMain {

    public static void main(String[] args) throws IOException {

        String name = "Wish";
        String packageName = "love";
        String sql = "CREATE TABLE `love_wish` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `create_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建者id',\n" +
                "  `update_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',\n" +
                "  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0:未删除,1:删除）',\n" +
                "  `lover_id` bigint(20) NOT NULL,\n" +
                "  `title` varchar(255) NOT NULL COMMENT '愿望标题',\n" +
                "  `desc` varchar(3200) NOT NULL COMMENT '愿望详情',\n" +
                "  `finished` tinyint(1) NOT NULL COMMENT '是否完成',\n" +
                "  `finish_date` date DEFAULT NULL,\n" +
                "  `remark` varchar(3200) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `nk_lover_id` (`lover_id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

        gen(name, packageName, sql, true);

    }

    public static void gen(String name, String packageName, String sql, boolean needDomain) throws IOException {

        WebSqlCodeBuilder builder = new WebSqlCodeBuilder();

        addMyBatisConfig(builder, packageName, needDomain);
        if (needDomain) {
            addDomainConfig(builder, packageName);
        }

        builder.build(sql, name);
    }

    private static void addDomainConfig(WebSqlCodeBuilder builder, String packageName) throws IOException {

        CodeTemplateProperty idProperty = new CodeTemplateProperty();
        idProperty.setClassPackage("com.xiaohe66.web.domain." + packageName + ".value");

        DomainCodeTemplateProperty aggProperty = new DomainCodeTemplateProperty();
        aggProperty.setClassPackage("com.xiaohe66.web.domain." + packageName + ".agg");
        aggProperty.setPackageName(packageName);

        DomainCodeTemplateProperty repositoryProperty = new DomainCodeTemplateProperty();
        repositoryProperty.setClassPackage("com.xiaohe66.web.domain." + packageName + ".repository");
        repositoryProperty.setPackageName(packageName);

        CodeTemplateProperty serviceProperty = new CodeTemplateProperty();
        serviceProperty.setClassPackage("com.xiaohe66.web.domain." + packageName + ".service");

        List<CodeTemplate> templates = List.of(
                new IdCodeTemplate(idProperty),
                new AggCodeTemplate(aggProperty),
                new DomainRepositoryCodeTemplate(repositoryProperty),
                new ServiceCodeTemplate(serviceProperty)
        );

        String path = new File("").getCanonicalPath() + "\\xiaohe66-web-core\\src\\main\\java";

        builder.add(new FileCodeWriteFactory(path), templates);
    }

    private static void addMyBatisConfig(WebSqlCodeBuilder builder, String packageName, boolean needDomain) throws IOException {

        builder.getDefinitionConverter().setIgnoreFieldName(List.of("id", "create_id", "update_time", "deleted"));

        DefaultCodeBuildProperty property = DefaultCodeBuildProperty.builder()
                .entityPackage("com.xiaohe66.web.infrastructure.mybatis." + packageName + ".model")
                .entityBaseClass(BaseLongInputDo.class)
                .mapperPackage("com.xiaohe66.web.infrastructure.mybatis." + packageName + ".mapper")
                .mapperBaseClass(MapperSupport.class)
                .repositoryPackage("com.xiaohe66.web.infrastructure.mybatis." + packageName + ".repository")
                .repositoryBaseClass(ServiceSupport.class)
                .build();

        List<CodeTemplate> templates;
        if (needDomain) {

            DomainCodeTemplateProperty domainRepositoryProperty = new DomainCodeTemplateProperty();
            domainRepositoryProperty.setPackageName(packageName);
            domainRepositoryProperty.setClassPackage("com.xiaohe66.web.infrastructure.mybatis." + packageName + ".repository");

            DomainCodeTemplateProperty doConverterProperty = new DomainCodeTemplateProperty();
            doConverterProperty.setPackageName(packageName);
            doConverterProperty.setClassPackage("com.xiaohe66.web.infrastructure.mybatis." + packageName + ".convert");

            templates = List.of(new DoCodeTemplate(property.toEntityCodeBuildProperty()),
                    new MapperCodeTemplate(property.toMapperCodeBuildProperty(), "mapper.vm"),
                    new DomainRepositoryImplCodeTemplate(domainRepositoryProperty),
                    new DoConverterCodeTemplate(doConverterProperty));
        } else {

            templates = List.of(new DoCodeTemplate(property.toEntityCodeBuildProperty()),
                    new MapperCodeTemplate(property.toMapperCodeBuildProperty(), "mapper.vm"),
                    new RepositoryImplCodeTemplate(property.toRepositoryCodeBuildProperty()));
        }

        String path = new File("").getCanonicalPath() + "\\xiaohe66-web-infrastructure-mybatis\\src\\main\\java";

        builder.add(new FileCodeWriteFactory(path), templates);
    }

}
