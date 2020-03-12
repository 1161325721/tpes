package com.cy.tpes;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TpesApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(TpesApplication.class, args);
	}

}
