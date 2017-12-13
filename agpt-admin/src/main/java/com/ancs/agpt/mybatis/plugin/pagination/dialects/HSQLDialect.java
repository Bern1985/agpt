package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * HSQL 数据库分页语句组装实现
 * </p>
 */
public class HSQLDialect implements IDialect {

    public static final HSQLDialect INSTANCE = new HSQLDialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" limit ").append(offset).append(",").append(limit);
        return sql.toString();
    }
}
