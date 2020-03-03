package com.youpassed.mapper;

import com.youpassed.entity.users.UserEntity;
import com.youpassed.domain.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper implements Mapper<UserEntity, User> {
	@Override
	public UserEntity mapDomainToEntity(User item) {
		return item == null ? null :
				UserEntity.builder()
						.id(item.getId())
						.email(item.getEmail())
						.password(item.getPassword())
						.role(getUserRoleNullSafe(item))
						.firstName(item.getFirstName())
						.lastName(item.getLastName())
						.build();
	}

	private UserEntity.Role getUserRoleNullSafe(User item) {
		Optional<User.Role> userRole = Optional.ofNullable(item.getRole());
		return userRole.map(role -> UserEntity.Role.valueOf(role.name())).orElse(null);
	}

	@Override
	public User mapEntityToDomain(UserEntity enity) {
		return enity == null ? null :
				User.builder()
						.id(enity.getId())
						.email(enity.getEmail())
						.password(enity.getPassword())
						.role(getUserEntityRoleNullSafe(enity))
						.firstName(enity.getFirstName())
						.lastName(enity.getLastName())
						.build();
	}

	private User.Role getUserEntityRoleNullSafe(UserEntity enity) {
		Optional<UserEntity.Role> userEntityRole = Optional.ofNullable(enity.getRole());
		return userEntityRole.map(role -> User.Role.valueOf(role.name())).orElse(null);
	}
}
