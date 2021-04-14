package com.hhgs.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.hhgs.app.mapper.sys"},sqlSessionTemplateRef = "sysTemplate")
public class DataSourceSys {

    @Bean("sysdatasource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource createDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sysSqlFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("sysdatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/sys/*.xml"));
        return bean.getObject();
    }


    @Bean(name="sysTransManager")
    public DataSourceTransactionManager createDataSourceTransactionManager(@Qualifier("sysdatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sysTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(@Qualifier("sysSqlFactory") SqlSessionFactory factory){
        return new SqlSessionTemplate(factory);
    }

}
