package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xavier on 2018/1/21.
 */
@SpringBootApplication
@ComponentScan("com.xavier")
@ImportResource("classpath:spring.xml")
public class SalasApp {

	public static void main(String[] args){
		SpringApplication.run(SalasApp.class, args);
	}
}
