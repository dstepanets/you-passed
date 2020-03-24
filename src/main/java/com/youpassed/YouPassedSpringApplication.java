package com.youpassed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class YouPassedSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouPassedSpringApplication.class, args);
	}

}
