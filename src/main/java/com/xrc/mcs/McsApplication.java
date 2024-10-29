package com.xrc.mcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.xrc.mcs.client")
public class McsApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsApplication.class, args);
	}

}
