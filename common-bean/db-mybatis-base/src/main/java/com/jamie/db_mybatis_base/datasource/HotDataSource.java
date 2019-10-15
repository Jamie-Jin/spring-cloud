package com.jamie.db_mybatis_base.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 支持热部署的数据源与MyBatis配置
 */
@Configuration
public class HotDataSource {

    @Autowired
    private DruidProperties druid;

    @Autowired
    private MyBatisProperties mybatis;

    @Bean
    @Primary
    @RefreshScope
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(druid.getUrl());
        dataSource.setUsername(druid.getUserName());
        dataSource.setPassword(druid.getPassword());
        dataSource.setDriverClassName(druid.getDriverClassName());

        dataSource.setInitialSize(druid.getInitialSize());
        dataSource.setMaxActive(druid.getMaxActive());
        dataSource.setMaxWait(druid.getMaxWait());
        dataSource.setPoolPreparedStatements(druid.getPoolPreparedStatements());
        dataSource.setValidationQuery(druid.getValidationQuery());
        dataSource.setValidationQueryTimeout(druid.getValidationQueryTimeout());
        dataSource.setTestOnBorrow(druid.getTestOnBorrow());
        dataSource.setTestOnReturn(druid.getTestOnReturn());
        dataSource.setTestWhileIdle(druid.getTestWhileIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(druid.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druid.getMinEvictableIdleTimeMillis());

        return dataSource;
    }

    @Bean
    @RefreshScope
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        // MyBatis设置数据源
        bean.setDataSource(dataSource);
        // 设置Mapper.xml路径（注意要用getResources!!）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatis.getMapperLocations()));
        // MyBatis配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(mybatis.getUseGeneratedKeys());
        configuration.setMapUnderscoreToCamelCase(mybatis.getMapUnderscoreToCamelCase());
        configuration.setLogImpl(StdOutImpl.class);

        bean.setConfiguration(configuration);

        return bean.getObject();
    }

}
