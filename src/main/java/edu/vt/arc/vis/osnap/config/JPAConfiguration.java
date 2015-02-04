package edu.vt.arc.vis.osnap.config;


// @formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */
// @formatter:on


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


/**
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.1.0
 *
 */
@Configuration
@PropertySource("application.properties")
// @EnableJpaRepositories(basePackages =
// "edu.vt.arc.vis.osnap.persistence.repository")
@EnableTransactionManagement
@EnableLoadTimeWeaving
// @ComponentScan(basePackages = "edu.vt.arc.vis.osnap.persistence.services")
public class JPAConfiguration
        implements TransactionManagementConfigurer {

    @Autowired
    private Environment env;



    /**
     * Returns the data source of the application.
     * 
     * @return the data source of the application.
     */
    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        String url = env.getProperty("jdbc.url");
        String username = env.getProperty("jdbc.username");
        String password = env.getProperty("jdbc.password");

        String driverClassName = env.getProperty("jdbc.driverClassName");


        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);


        dataSource.setInitialSize(20);
        dataSource.setMaxTotal(30);
        dataSource.setRemoveAbandonedOnMaintenance(true);

        return dataSource;
    }

    /**
     * Returns the JPA entity manager factory for the application.
     * 
     * @return the JPA entity manager factory for the application.
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {

        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("eclipselink.weaving", "true");
        jpaProperties.put("jpaDialect",
                "org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect");
        jpaProperties
                .put("loadTimeWeaver",
                        "org.springframework.instrument.classloading.tomcat.TomcatLoadTimeWeaver");
        factory.setJpaPropertyMap(jpaProperties);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        factory.setPackagesToScan("raziel.pawn.persistence.domain");
        factory.setDataSource(this.dataSource());
        factory.setLoadTimeWeaver(new TomcatLoadTimeWeaver());
        factory.setJpaDialect(this.eclipseLinkJpaDialect());


        factory.afterPropertiesSet();
        return factory.getObject();
    }

    /**
     * Returns the EclipseLink JPA Dialect.
     * 
     * @return the EclipseLink JPA Dialect.
     */
    @Bean
    public EclipseLinkJpaDialect eclipseLinkJpaDialect() {

        return new EclipseLinkJpaDialect();
    }

    /**
     * Returns the JPA entity manager for the application.
     * 
     * @param entityManagerFactory
     *            the entity manager factory that allows creating the entity
     *            manager.
     * 
     * @return the JPA entity manager for the application.
     */
    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {

        return entityManagerFactory.createEntityManager();
    }

    /**
     * Returns the shared JPA transaction manager for the application.
     * 
     * @return the shared JPA transaction manager for the application.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    /**
     * Returns the persistence exception post-processor.
     * 
     * @return the persistence exception post-processor.
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {

        return new PersistenceExceptionTranslationPostProcessor();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.transaction.annotation.TransactionManagementConfigurer
     * #annotationDrivenTransactionManager()
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {

        return transactionManager();
    }

}