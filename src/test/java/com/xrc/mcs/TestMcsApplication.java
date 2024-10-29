package com.xrc.mcs;

import org.springframework.boot.SpringApplication;

public class TestMcsApplication {

	public static void main(String[] args) {
		SpringApplication.from(McsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
