package com.zhao.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描包
@SpringBootApplication(scanBasePackages = {"com.zhao.controller","com.zhao.service","com.zhao.dao"})
//启动springboot框架的自动配置
@EnableAutoConfiguration
//使用注解形式的mybatis
//@MapperScan(basePackages={"com.zhao.mapper"})
public class StartApplication {
	public static void main(String[] args) {
		//SpringApplication是springboot的核心类.SpringBoot中使用大量注解，所以简单
		//SpringMVC核心类DispatcherServlet
		//Spring的核心类：ClassPathXmlApplicationContext
		//mybatis的核心类：SqlSession
		SpringApplication.run(StartApplication.class, args);
	}

}
