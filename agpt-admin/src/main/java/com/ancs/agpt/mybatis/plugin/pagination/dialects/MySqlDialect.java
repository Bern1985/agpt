package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * MYSQL 数据库分页语句组装实现
 * </p>
 */
public class MySqlDialect implements IDialect {

    public static final MySqlDialect INSTANCE = new MySqlDialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder(originalSql);
        sql.append(" LIMIT ").append(offset).append(",").append(limit);
        return sql.toString();
    }
}
