package com.lab409.crowdingsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * application启动类
 * @author yinghogncan
 */
@SpringBootApplication
public class CrowdingSourcingApplication {


	public static void main(String[] args) {
		SpringApplication.run(CrowdingSourcingApplication.class, args);
	}
}
