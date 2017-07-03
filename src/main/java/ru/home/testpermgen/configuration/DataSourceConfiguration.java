package ru.home.testpermgen.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


@Configuration
public class DataSourceConfiguration
{

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource()
    {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try
        {
            dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
            dataSource.setJdbcUrl("jdbc:oracle:thin:@***:1521:***");
            dataSource.setUser("***");
            dataSource.setPassword("***");
            dataSource.setContextClassLoaderSource("library");
            dataSource.setPrivilegeSpawnedThreads(true);
        }
        catch (PropertyVetoException e)
        {
            e.printStackTrace();
        }
        return dataSource;
    }


    @Bean(destroyMethod = "destroy")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
    {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

}
