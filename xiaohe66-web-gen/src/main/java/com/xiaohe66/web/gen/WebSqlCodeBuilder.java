package com.xiaohe66.web.gen;

import com.xiaohe66.gen.convert.DefinitionConverter;
import com.xiaohe66.gen.convert.impl.SimpleDefinitionConverter;
import com.xiaohe66.gen.reader.TableDefinitionReader;
import com.xiaohe66.gen.reader.bo.TableDefinition;
import com.xiaohe66.gen.reader.impl.SqlTableDefinitionReader;
import com.xiaohe66.gen.template.CodeTemplate;
import com.xiaohe66.gen.template.bo.JavaDefinition;
import com.xiaohe66.gen.write.CodeWriterFactory;
import com.xiaohe66.gen.write.CodeWriterWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohe
 * @since 2022.03.10 14:48
 */
public class WebSqlCodeBuilder {

    private final TableDefinitionReader reader = new SqlTableDefinitionReader();

    @Getter
    protected DefinitionConverter definitionConverter = new SimpleDefinitionConverter();

    private final List<Item> list = new ArrayList<>();

    public void add(CodeWriterFactory codeWriterFactory, List<CodeTemplate> codeTemplates) {

        Item item = new Item(codeWriterFactory, codeTemplates);
        list.add(item);
    }

    public void build(String sql, String name) {

        TableDefinition tableDefinition = this.reader.read(sql);
        JavaDefinition definition = this.definitionConverter.convert(tableDefinition);
        definition.setClassName(name);

        for (Item item : list) {

            for (CodeTemplate builder : item.codeTemplates) {

                try (CodeWriterWrapper write = item.codeWriterFactory.create(builder, definition)) {

                    builder.gen(definition, write);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @AllArgsConstructor
    private static class Item {

        CodeWriterFactory codeWriterFactory;
        List<CodeTemplate> codeTemplates;
    }
}
