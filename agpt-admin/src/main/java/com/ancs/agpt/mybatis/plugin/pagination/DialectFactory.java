package com.ancs.agpt.mybatis.plugin.pagination;

import org.apache.ibatis.session.RowBounds;

import com.ancs.agpt.enums.DBType;
import com.ancs.agpt.exception.AncsException;
import com.ancs.agpt.exception.AncsPageException;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.DB2Dialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.H2Dialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.HSQLDialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.MySqlDialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.OracleDialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.PostgreDialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.SQLServer2005Dialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.SQLServerDialect;
import com.ancs.agpt.mybatis.plugin.pagination.dialects.SQLiteDialect;
import com.ancs.agpt.system.toolkit.StringUtils;

/**
 * <p>
 * 分页方言工厂类
 * </p>
 */
public class DialectFactory {

    /**
     * <p>
     * 生成翻页执行 SQL
     * </p>
     *
     * @param page         翻页对象
     * @param buildSql     执行 SQL
     * @param dbType       数据库类型
     * @param dialectClazz 自定义方言实现类
     * @return
     * @throws Exception
     */
    public static String buildPaginationSql(Pagination page, String buildSql, DBType dbType, String dialectClazz)
            throws Exception {
        // fix #172, 196
        return getDialect(dbType, dialectClazz).buildPaginationSql(buildSql, page.getOffsetCurrent(), page.getSize());
    }

    /**
     * Physical Pagination Interceptor for all the queries with parameter
     * {@link org.apache.ibatis.session.RowBounds}
     *
     * @param rowBounds
     * @param buildSql
     * @param dbType
     * @param dialectClazz
     * @return
     * @throws Exception
     */
    public static String buildPaginationSql(RowBounds rowBounds, String buildSql, DBType dbType, String dialectClazz)
            throws Exception {
        // fix #196
        return getDialect(dbType, dialectClazz).buildPaginationSql(buildSql, rowBounds.getOffset(), rowBounds.getLimit());
    }

    /**
     * <p>
     * 获取数据库方言
     * </p>
     *
     * @param dbType       数据库类型
     * @param dialectClazz 自定义方言实现类
     * @return
     * @throws Exception
     */
    private static IDialect getDialect(DBType dbType, String dialectClazz) throws Exception {
        IDialect dialect = null;
        if (StringUtils.isNotEmpty(dialectClazz)) {
            try {
                Class<?> clazz = Class.forName(dialectClazz);
                if (IDialect.class.isAssignableFrom(clazz)) {
                    dialect = (IDialect) clazz.newInstance();
                }
            } catch (ClassNotFoundException e) {
                throw new AncsException("Class :" + dialectClazz + " is not found");
            }
        } else if (null != dbType) {
            dialect = getDialectByDbtype(dbType);
        }
        /* 未配置方言则抛出异常 */
        if (dialect == null) {
            throw new AncsPageException("The value of the dialect property in mybatis configuration.xml is not defined.");
        }
        return dialect;
    }

    /**
     * <p>
     * 根据数据库类型选择不同分页方言
     * </p>
     *
     * @param dbType 数据库类型
     * @return
     * @throws Exception
     */
    private static IDialect getDialectByDbtype(DBType dbType) {
        IDialect dialect;
        switch (dbType) {
            case MYSQL:
                dialect = MySqlDialect.INSTANCE;
                break;
            case ORACLE:
                dialect = OracleDialect.INSTANCE;
                break;
            case DB2:
                dialect = DB2Dialect.INSTANCE;
                break;
            case H2:
                dialect = H2Dialect.INSTANCE;
                break;
            case SQLSERVER:
                dialect = SQLServerDialect.INSTANCE;
                break;
            case SQLSERVER2005:
                dialect = SQLServer2005Dialect.INSTANCE;
                break;
            case POSTGRE:
                dialect = PostgreDialect.INSTANCE;
                break;
            case HSQL:
                dialect = HSQLDialect.INSTANCE;
                break;
            case SQLITE:
                dialect = SQLiteDialect.INSTANCE;
                break;
            default:
                throw new AncsPageException("The Database's Not Supported! DBType:" + dbType);
        }
        return dialect;
    }

}
