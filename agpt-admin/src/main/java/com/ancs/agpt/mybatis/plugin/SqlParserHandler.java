package com.ancs.agpt.mybatis.plugin;

import java.util.List;

import org.apache.ibatis.reflection.MetaObject;

import com.ancs.agpt.mybatis.plugin.parser.ISqlParser;
import com.ancs.agpt.mybatis.plugin.parser.ISqlParserFilter;
import com.ancs.agpt.mybatis.plugin.parser.SqlInfo;
import com.ancs.agpt.system.toolkit.CollectionUtils;
import com.ancs.agpt.system.toolkit.PluginUtils;

/**
 * <p>
 * SQL 解析处理器
 * </p>
 *
 */
public abstract class SqlParserHandler {
	
	 private List<ISqlParser> sqlParserList;
	    private ISqlParserFilter sqlParserFilter;

	    /**
	     * 拦截 SQL 解析执行
	     */
	    protected void sqlParser(MetaObject metaObject) {
	        if (null != metaObject) {
	            if (null != this.sqlParserFilter && this.sqlParserFilter.doFilter(metaObject)) {
	                return;
	            }
	            // SQL 解析
	            if (CollectionUtils.isNotEmpty(this.sqlParserList)) {
	                int flag = 0;// 标记是否修改过 SQL
	                String originalSql = (String) metaObject.getValue(PluginUtils.DELEGATE_BOUNDSQL_SQL);
	                for (ISqlParser sqlParser : this.sqlParserList) {
	                    SqlInfo sqlInfo = sqlParser.optimizeSql(metaObject, originalSql);
	                    if (null != sqlInfo) {
	                        originalSql = sqlInfo.getSql();
	                        ++flag;
	                    }
	                }
	                if (flag >= 1) {
	                    metaObject.setValue(PluginUtils.DELEGATE_BOUNDSQL_SQL, originalSql);
	                }
	            }
	        }
	    }

	    public List<ISqlParser> getSqlParserList() {
	        return sqlParserList;
	    }

	    public SqlParserHandler setSqlParserList(List<ISqlParser> sqlParserList) {
	        this.sqlParserList = sqlParserList;
	        return this;
	    }

	    public ISqlParserFilter getSqlParserFilter() {
	        return sqlParserFilter;
	    }

	    public void setSqlParserFilter(ISqlParserFilter sqlParserFilter) {
	        this.sqlParserFilter = sqlParserFilter;
	    }
	    
}
