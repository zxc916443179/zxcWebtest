package com.test.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.sqlite.SQLiteDataSource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Bean(destroyMethod = "", name = "EmbeddeddataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite:" + "test.db");
        dataSourceBuilder.type(SQLiteDataSource.class);
        return dataSourceBuilder.build();
    }

    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.test.app",
            transactionManagerRef = "jpaTransactionManager",
            entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
    )
    @EnableTransactionManagement
    public class JpaConfiguration {

        @Autowired
        @Bean
        public JpaTransactionManager jpaTransactionManager(@Qualifier(value = "EmbeddeddataSource") DataSource dataSource, EntityManagerFactory entityManagerFactory) {
            JpaTransactionManager jpaTransactionManager
                    = new JpaTransactionManager();
            jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
            jpaTransactionManager.setDataSource(dataSource);

            return jpaTransactionManager;
        }

        @Autowired
        @Bean
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(@Qualifier(value = "EmbeddeddataSource") DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
            LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
                    = new LocalContainerEntityManagerFactoryBean();
            localContainerEntityManagerFactoryBean.setDataSource(dataSource);
            localContainerEntityManagerFactoryBean.setPackagesToScan("com.test.app");
            localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
            return localContainerEntityManagerFactoryBean;
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
            hibernateJpaVendorAdapter.setGenerateDdl(true);
            hibernateJpaVendorAdapter.setShowSql(true);
            hibernateJpaVendorAdapter.setDatabasePlatform("com.enigmabridge.hibernate.dialect.SQLiteDialect");
            return hibernateJpaVendorAdapter;
        }
    }
}
