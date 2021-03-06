package com.ancs.agpt.mybatis.plugin.parser;

/**
 * <p>
 * Sql Info
 * </p>
 */
public class SqlInfo {

    private String sql;// SQL 内容
    private boolean orderBy = true;// 是否排序

    public static SqlInfo newInstance() {
        return new SqlInfo();
    }

    public String getSql() {
        return sql;
    }

    public SqlInfo setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public boolean isOrderBy() {
        return orderBy;
    }

    public SqlInfo setOrderBy(boolean orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
