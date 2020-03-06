package com.youpassed;

import com.youpassed.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class YouPassedSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouPassedSpringApplication.class, args);
	}

}
