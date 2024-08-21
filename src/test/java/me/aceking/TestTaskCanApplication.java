package me.aceking;

import org.springframework.boot.SpringApplication;

public class TestTaskCanApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskCanApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
