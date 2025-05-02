package com.training.ottcataloge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OttcatalogeApplication {

	public static void main(String[] args) {
		ApplicationContext ioc = SpringApplication.run(OttcatalogeApplication.class, args);
		PlayService playservice = ioc.getBean(PlayService.class);
		System.out.println(playservice.playMovies());
	}

}
