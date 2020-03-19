package com.youpassed.mapper;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.User;
import com.youpassed.entity.ExamEntity;
import com.youpassed.entity.MajorEntity;
import com.youpassed.entity.StudentMarkEntity;
import com.youpassed.entity.users.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserMapper implements Mapper<UserEntity, User> {
	private Mapper<MajorEntity, Major> majorMapper;
	private Mapper<ExamEntity, Exam> examMapper;

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
//						.majors(user.getMajors().stream()
//								.map(majorMapper::mapDomainToEntity)
//								.collect(Collectors.toList()))
//						.exams(user.getExams().stream()
//								.map(examMapper::mapDomainToEntity)
//								.collect(Collectors.toList()))
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
//						.majors(enity.getMajors().stream()
//								.map(majorMapper::mapEntityToDomain)
//								.collect(Collectors.toList()))
//						.exams(enity.getExams().stream()
//								.map(examMapper::mapEntityToDomain)
//								.collect(Collectors.toList()))
						.build();
	}

	public User mapEntityToDomainFetchLists(UserEntity enity) {
		return enity == null ? null :
				User.builder()
						.id(enity.getId())
						.email(enity.getEmail())
						.password(enity.getPassword())
						.password2(enity.getPassword())
						.role(getUserRoleNullSafe(enity))
						.firstName(enity.getFirstName())
						.lastName(enity.getLastName())
						.majors(enity.getMajors().stream()
								.map(majorMapper::mapEntityToDomain)
								.collect(Collectors.toList()))
						.exams(enity.getMarks().stream()
								.map(mark -> {
									Exam exam = examMapper.mapEntityToDomain(mark.getExam());
									exam.setMark(mark.getMark());
									return exam;
								}).collect(Collectors.toList()))
						.build();
	}

	private User.Role getUserRoleNullSafe(UserEntity enity) {
		Optional<UserEntity.Role> userEntityRole = Optional.ofNullable(enity.getRole());
		return userEntityRole.map(role -> User.Role.valueOf(role.name())).orElse(null);
	}
}
