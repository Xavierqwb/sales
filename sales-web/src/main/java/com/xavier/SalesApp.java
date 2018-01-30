package com.xavier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xavier on 2018/1/21.
 */
@SpringBootApplication(scanBasePackages = "com.xavier")
@EnableAspectJAutoProxy
@ImportResource("classpath:spring.xml")
public class SalesApp {

	public static void main(String[] args){
		SpringApplication.run(SalesApp.class, args);
	}
}
