package com.training.springwithhibernatedemo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HibernateConfig {

	@Bean
	public SessionFactory initSession() {
		StandardServiceRegistry ssr  = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		 Metadata metaData= new MetadataSources(ssr).getMetadataBuilder().build();
		  SessionFactory sessionFactory= metaData.getSessionFactoryBuilder().build();
		  return sessionFactory;
	}
	
	
	
}
