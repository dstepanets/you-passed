package com.youpassed.service.impl;

import com.youpassed.domain.User;
import com.youpassed.entity.users.UserEntity;
import com.youpassed.exception.ValidationException;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.UserRepository;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {
	private static final int USERS_PER_PAGE = 5;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private Mapper<UserEntity, User> userMapper;

/*	@Override
	public void defaultAllUsersPasswords() {
		List<UserEntity> userList = userRepository.findAll();
		final String encodedPass = passwordEncoder.encode("pass");
		for (UserEntity ue : userList) {
			System.out.println(ue);
			ue.setPassword(encodedPass);
			System.out.println(encodedPass);
			userRepository.save(ue);
		}
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		defaultAllUsersPasswords();

		final Optional<UserEntity> userEntity = userRepository.findByEmail(username);
		return userEntity
				.map(userMapper::mapEntityToDomain)
				.orElseThrow(() -> new UsernameNotFoundException("User with email '" + username + "' is not found."));
	}

	@Override
	public Optional<User> login(String email, String password) {
		final Optional<UserEntity> userEntity = userRepository.findByEmail(email);
		if (userEntity.isPresent()) {
			String encryptedPassword = passwordEncoder.encode(password);
			if (encryptedPassword.equals(userEntity.get().getPassword()))
				return Optional.of(userMapper.mapEntityToDomain(userEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public User register(User user) throws ValidationException {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new ValidationException("User with this email was registered already");
		}
		final String encryptedPass = passwordEncoder.encode(user.getPassword());

		UserEntity newUserEntity = UserEntity.builder()
				.email(user.getEmail())
				.password(encryptedPass)
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.role(UserEntity.Role.valueOf(user.getRole().name()))
				.build();

		userRepository.save(newUserEntity);
//		як варіант, id повертати?
		return user;
	}


	@Override
	public Page<User> findAll(int pageIndex, int pageSize) {
		long count = userRepository.count();
		int maxPageIndex = (int) (count / pageSize);
		maxPageIndex -= (count % pageSize == 0) ? 1 : 0;
		pageIndex = Math.min(pageIndex, maxPageIndex);

		return userRepository.findAll(PageRequest.of(pageIndex, pageSize))
				.map(userMapper::mapEntityToDomain);
	}


	//	@Override
//	public PaginalList<User> findAll(String strPageNum) {
//		int pageNum = parsePageNumber(strPageNum);
//
//		PaginalList<UserEntity> userEntityPaginalList = userDao.findAll(new Page(pageNum, USERS_PER_PAGE));
//		List<User> userList = userEntityPaginalList.getItems().stream()
//				.map(userMapper::mapEntityToDomain)
//				.collect(Collectors.toList());
//		return new PaginalList<User>(userList, userEntityPaginalList.getPage(), userEntityPaginalList.getMaxPageNumber());
//	}

//	@Override
//	public int getUsersCount() {
//		return userDao.count();
//	}
//

}
