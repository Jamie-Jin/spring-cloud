package com.jamie.servicea.config;

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

@Configuration
public class JamieDataSourceConfig {
    @Autowired
    private DruidProperties druid;

    @Autowired
    private MySqlProperties mysql;

    @Autowired
    private MyBatisProperties mybatis;

    /**
     * 热部署数据源
     * @return
     */
    @Bean
    @Primary
    @RefreshScope
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(mysql.getUrl());
        dataSource.setUsername(mysql.getUsername());
        dataSource.setPassword(mysql.getPassword());
        dataSource.setDriverClassName(mysql.getDriverClassName());

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

    /**
     * 热部署mybatis
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
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
