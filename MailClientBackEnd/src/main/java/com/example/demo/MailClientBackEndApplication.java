package com.example.demo;

import org.springframework.boot.SpringApplication;
 //import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.AppController;




@SpringBootApplication
@ComponentScan(basePackageClasses= AppController.class)
public class MailClientBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailClientBackEndApplication.class, args);
	}

}
