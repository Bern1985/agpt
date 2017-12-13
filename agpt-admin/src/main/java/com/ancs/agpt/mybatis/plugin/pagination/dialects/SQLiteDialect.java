package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * SQLite 数据库分页语句组装实现
 * </p>
 */
public class SQLiteDialect implements IDialect {

    public static final SQLiteDialect INSTANCE = new SQLiteDialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" limit ").append(limit).append(" offset ").append(offset);
        return sql.toString();
    }
}

