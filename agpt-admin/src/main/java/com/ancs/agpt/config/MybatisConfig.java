package com.ancs.agpt.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ancs.agpt.mybatis.plugin.PaginationInterceptor;


@Configuration
@EnableTransactionManagement
@MapperScan("com.ancs.agpt.system.mapper")
public class MybatisConfig {
	

	    @Bean(name = "sqlSessionFactory")
	    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
	        SqlSessionFactoryBean sqlsession = new SqlSessionFactoryBean();
	        sqlsession.setDataSource(dataSource);
	        //扫描entity包 使用别名
	        sqlsession.setTypeAliasesPackage("com.ancs.agpt.system.entity");
	        	
	        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
	        //使用jdbc的getGeneratedKeys获取数据库自增主键值
	        configuration.setUseGeneratedKeys(true);
	        //使用列别名替换列名 select user as User
	        configuration.setUseColumnLabel(true);
	        //-自动使用驼峰命名属性映射字段   userId    user_id
	        configuration.setMapUnderscoreToCamelCase(true);
	        sqlsession.setConfiguration(configuration);
	        sqlsession.setFailFast(true);
	        PaginationInterceptor interceptor = new PaginationInterceptor();
	        Properties p = new Properties();
	        p.setProperty("dialectType", "mysql");
	        interceptor.setProperties(p);
	        sqlsession.setPlugins(new Interceptor[] {interceptor});
	        
	        //sqlsession.setTypeHandlers(new TypeHandler[]{new EnumTypeHandler(ValidState.class)});
	        //自定义数据库配置的时候，需要将pageHelper的bean注入到Plugins中，如果采用系统默认的数据库配置，则只需要定义pageHelper的bean，会自动注入。
	        //sqlsession.setPlugins(new Interceptor[] { pageHelper });
	        //添加XML目录
	        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        SqlSessionFactory sqlSessionFactory = null;
	        try {
	            sqlsession.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
	            sqlSessionFactory = sqlsession.getObject();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	        
	        // -----------  动态加载实现BaseCodeEnum接口的枚举，使用CodeEnumTypeHandler转换器

	        // 取得类型转换注册器
	        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
				typeHandlerRegistry.register(com.ancs.agpt.system.entity.enums.Status.class, com.ancs.agpt.system.entity.enums.IEnumTypeHandler.class);
				
	        return sqlSessionFactory;
	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }

	    @Bean
	    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
	        return new DataSourceTransactionManager(dataSource);
	    }
	    
	  
}
