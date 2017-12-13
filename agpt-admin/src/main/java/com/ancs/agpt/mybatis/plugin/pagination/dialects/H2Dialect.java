package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * H2 数据库分页方言
 * </p>
 */
public class H2Dialect implements IDialect {

    public static final H2Dialect INSTANCE = new H2Dialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" limit ").append(limit);
        if (offset > 0) {
            sql.append(" offset ").append(offset);
        }
        return sql.toString();
    }
}
