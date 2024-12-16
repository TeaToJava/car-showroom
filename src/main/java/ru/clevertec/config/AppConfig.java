package ru.clevertec.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ru.clevertec")
@PropertySource(value = "classpath:application.yml", factory = YamlPropertiesFactory.class)
@EnableJpaRepositories("ru.clevertec.repository")
public class AppConfig {
    @Value("${datasource.driver}")
    private String dataSourceClassName;
    @Value("${datasource.user}")
    private String user;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.url}")
    private String url;

    @Value("${liquibase.changelog}")
    private String changeLog;

    @Value("${hibernate.format_sql}")
    private String formatSQL;
    @Value("${hibernate.show_sql}")
    private String showSQL;
    @Value("${hibernate.use_sql_comments}")
    private String useSqlComments;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.clevertec.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public TransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.format_sql", formatSQL);
        hibernateProperties.put("hibernate.use_sql_comments", useSqlComments);
        hibernateProperties.put("hibernate.show_sql", showSQL);
        return hibernateProperties;
    }


    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource());
        springLiquibase.setDropFirst(false);
        springLiquibase.setChangeLog(changeLog);
        return springLiquibase;
    }
}

