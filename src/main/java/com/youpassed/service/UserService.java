package com.youpassed.service;import com.youpassed.domain.User;import com.youpassed.exception.ValidationException;import org.springframework.security.core.userdetails.UserDetailsService;import java.util.Optional;public interface UserService extends UserDetailsService {	User register(User user) throws ValidationException;	Optional<User> login(String email, String password);//		PaginalList<User> findAll(String strPageNum);//		int getUsersCount();}