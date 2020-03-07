package com.mi.haircut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mi.haircut.mapper")
public class OrderHaircutApplication  {
	public static void main(String[] args) {
		SpringApplication.run(OrderHaircutApplication.class, args);
	}

}

