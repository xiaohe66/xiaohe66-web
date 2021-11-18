package com.xiaohe66.web.integration.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author xiaohe
 * @since 2021.11.18 17:51
 */
public class IsExistId extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {

        String sql = String.format("select ifNull((select 1 from %s where id = #{id} limit 1),0)", tableInfo.getTableName());

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForOther(mapperClass, "isExistId", sqlSource, Boolean.TYPE);
    }
}
