package com.youpassed;

import com.youpassed.domain.User;
import com.youpassed.entity.users.UserEntity;
import com.youpassed.repository.UserRepository;
import com.youpassed.service.UserService;
import com.youpassed.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class YouPassedSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouPassedSpringApplication.class, args);
	}

}
