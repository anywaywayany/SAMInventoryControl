package com.samic.samic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
//@EnableTransactionManagement
public class ORCLConfig{

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//           LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//           return (LocalContainerEntityManagerFactoryBean) emf.getNativeEntityManagerFactory();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
}
