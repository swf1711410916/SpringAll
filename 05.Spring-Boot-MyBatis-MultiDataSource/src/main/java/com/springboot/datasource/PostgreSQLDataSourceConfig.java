package com.springboot.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = PostgreSQLDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "pgsqlSqlSessionFactory")
public class PostgreSQLDataSourceConfig {
    // pgsqldao扫描路径
    static final String PACKAGE = "com.springboot.pgsqldao";
    // mybatis mapper扫描路径
    static final String MAPPER_LOCATION = "classpath:mapper/pgsql/*.xml";
    @Primary
    @Bean(name = "pgsqlDataSource")
    @ConfigurationProperties("spring.datasource.druid.postgresql")
    public DataSource pgsqldatasource() {
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "pgsqlTransactionManager")
    public DataSourceTransactionManager pgsqlTransactionManager() {
        return new DataSourceTransactionManager(pgsqldatasource());
    }
    @Primary
    @Bean(name = "pgsqlSqlSessionFactory")
    public SqlSessionFactory pgsqlSqlSessionFactory(@Qualifier("pgsqlDataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PostgreSQLDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
