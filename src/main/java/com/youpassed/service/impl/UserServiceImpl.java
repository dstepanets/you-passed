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

import java.util.Arrays;
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
	public Page<User> findAll(String pageNumStr, String pageSizeStr) {
		final int pageSize = parsePageSize(pageSizeStr);
		final int pageNum = parsePageNumber(pageNumStr, pageSize);

		return userRepository.findAll(PageRequest.of(pageNum, pageSize))
				.map(userMapper::mapEntityToDomain);
	}


	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = {5, 10};

	private int parsePageSize(String pageSizeStr) {
		int pageSize;
		try {
			if (pageSizeStr == null) {
				throw new NumberFormatException("");
			}
			pageSize = Integer.parseInt(pageSizeStr);
			if (!Arrays.asList(PAGE_SIZES).contains(pageSize)) {
				throw new NumberFormatException("Illegal value for pageSize parameter");
			}
		} catch (NumberFormatException e) {
			log.warn(e.getMessage());
			pageSize = INITIAL_PAGE_SIZE;
		}
		return pageSize;
	}

	private static final int INITIAL_PAGE_NUM = 0;
//		final int firstPage = 1;

	private int parsePageNumber(String pageNumStr, int pageSize) {
		int pageNum;
		try {
			if (pageNumStr == null) {
				throw new NumberFormatException("");
			}
			pageNum = Integer.parseInt(pageNumStr);
			if (pageNum < 0) {
				throw new NumberFormatException("Illegal value for pageNum parameter");
			}
		} catch (NumberFormatException e) {
			log.warn(e.getMessage());
			pageNum = INITIAL_PAGE_NUM;
		}
		return pageNum;
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
