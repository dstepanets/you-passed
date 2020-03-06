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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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
//	private int parsePageNumber(String strPageNum) {
//		final int firstPage = 1;
//		if (strPageNum == null) {
//			return firstPage;
//		}
//		int usersCount = userDao.count();
//		int maxPage = usersCount / USERS_PER_PAGE + ((usersCount % USERS_PER_PAGE == 0) ? 0 : 1);
//
//		try {
//			final int p = Integer.parseInt(strPageNum);
//			if (p > maxPage) {
//				return maxPage;
//			} else if (p < 1) {
//				return firstPage;
//			}
//			return p;
//		} catch (NumberFormatException e) {
//			LOG.warn("Wrong page number format.");
//			return firstPage;
//		}
//	}
}
