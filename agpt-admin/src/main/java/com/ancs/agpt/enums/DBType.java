package com.ancs.agpt.enums;

import com.ancs.agpt.exception.AncsDataBaseException;

/**
 * <p>
 * MybatisPlus 数据库类型
 * </p>
 */
public enum DBType {
    /**
     * MYSQL
     */
    MYSQL("mysql", "`%s`", "MySql数据库"),
    /**
     * ORACLE
     */
    ORACLE("oracle", null, "Oracle数据库"),
    /**
     * DB2
     */
    DB2("db2", null, "DB2数据库"),
    /**
     * H2
     */
    H2("h2", null, "H2数据库"),
    /**
     * HSQL
     */
    HSQL("hsql", null, "HSQL数据库"),
    /**
     * SQLITE
     */
    SQLITE("sqlite", "`%s`", "SQLite数据库"),
    /**
     * POSTGRE
     */
    POSTGRE("postgresql", "\"%s\"", "Postgre数据库"),
    /**
     * SQLSERVER2005
     */
    SQLSERVER2005("sqlserver2005", null, "SQLServer2005数据库"),
    /**
     * SQLSERVER
     */
    SQLSERVER("sqlserver", null, "SQLServer数据库"),
    /**
     * UNKONWN DB
     */
    OTHER("other", null, "其他数据库");

    private final String db;

    private final String quote;

    private final String desc;

    DBType(final String db, final String quote, final String desc) {
        this.db = db;
        this.quote = quote;
        this.desc = desc;
    }

    /**
     * <p>
     * 获取数据库类型（默认 MySql）
     * </p>
     *
     * @param dbType 数据库类型字符串
     * @return
     */
    public static DBType getDBType(String dbType) {
        DBType[] dts = DBType.values();
        for (DBType dt : dts) {
            if (dt.getDb().equalsIgnoreCase(dbType)) {
                return dt;
            }
        }
        throw new AncsDataBaseException("Error: Unknown database type, or do not support changing database!\n");
    }

    public String getDb() {
        return this.db;
    }

    public String getQuote() {
        return this.quote;
    }

    public String getDesc() {
        return this.desc;
    }

}
