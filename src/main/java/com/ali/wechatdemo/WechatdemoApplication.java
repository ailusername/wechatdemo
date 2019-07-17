package com.ali.wechatdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ali.wechatdemo.dao")
public class WechatdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatdemoApplication.class, args);
	}

}
