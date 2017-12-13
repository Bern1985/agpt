package com.ancs.agpt.mybatis.plugin.parser;

import org.apache.ibatis.reflection.MetaObject;

/**
 * <p>
 * SQL 解析过滤器
 * </p>
 */
public interface ISqlParserFilter {

    boolean doFilter(MetaObject metaObject);

}
