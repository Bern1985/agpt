package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * SQLServer 数据库分页语句组装实现
 * </p>
 */
public class SQLServerDialect implements IDialect {

    public static final SQLServerDialect INSTANCE = new SQLServerDialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ");
        sql.append(limit).append(" ROWS ONLY");
        return sql.toString();
    }
}