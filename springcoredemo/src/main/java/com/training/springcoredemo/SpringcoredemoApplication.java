package com.training.springcoredemo;

import com.learning.springcoredemo.ExternalClass;
import com.training.springcoredemo.util.MyUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.training.springcoredemo,com.learning.springcoredemo")
@SpringBootApplication
public class SpringcoredemoApplication {


	public static void main(String[] args) {
		
		ApplicationContext ioc = SpringApplication.run(SpringcoredemoApplication.class, args);
		
		Demoservice demoservice = ioc.getBean(Demoservice.class);
		demoservice.execute();
		
	}

}
