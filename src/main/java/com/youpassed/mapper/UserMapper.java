package com.youpassed.mapper;

import com.youpassed.entity.users.UserEntity;
import com.youpassed.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper implements Mapper<UserEntity, User> {
	@Override
	public UserEntity mapDomainToEntity(User user) {
		return user == null ? null :
				UserEntity.builder()
						.id(user.getId())
						.email(user.getEmail())
						.password(user.getPassword())
						.role(getUserEntityRoleNullSafe(user))
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.build();
	}

	private UserEntity.Role getUserEntityRoleNullSafe(User user) {
		Optional<User.Role> userRole = Optional.ofNullable(user.getRole());
		return userRole.map(role -> UserEntity.Role.valueOf(role.name())).orElse(null);
	}

	@Override
	public User mapEntityToDomain(UserEntity enity) {
		return enity == null ? null :
				User.builder()
						.id(enity.getId())
						.email(enity.getEmail())
						.password(enity.getPassword())
						.password2(enity.getPassword())
						.role(getUserRoleNullSafe(enity))
						.firstName(enity.getFirstName())
						.lastName(enity.getLastName())
						.build();
	}

	private User.Role getUserRoleNullSafe(UserEntity enity) {
		Optional<UserEntity.Role> userEntityRole = Optional.ofNullable(enity.getRole());
		return userEntityRole.map(role -> User.Role.valueOf(role.name())).orElse(null);
	}
}
