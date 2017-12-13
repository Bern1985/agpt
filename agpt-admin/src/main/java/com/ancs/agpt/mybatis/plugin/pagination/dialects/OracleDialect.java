package com.ancs.agpt.mybatis.plugin.pagination.dialects;

import com.ancs.agpt.mybatis.plugin.pagination.IDialect;

/**
 * <p>
 * ORACLE 数据库分页语句组装实现
 * </p>
 */
public class OracleDialect implements IDialect {

    public static final OracleDialect INSTANCE = new OracleDialect();

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ( SELECT TMP.*, ROWNUM ROW_ID FROM ( ");
        sql.append(originalSql).append(" ) TMP WHERE ROWNUM <=").append((offset >= 1) ? (offset + limit) : limit);
        sql.append(") WHERE ROW_ID > ").append(offset);
        return sql.toString();
    }
}
