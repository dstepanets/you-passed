package com.youpassed.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private final Integer id;
	private final String email;
	private final String password;
	private final String salt;
	private final String firstName;
	private final String lastName;
	private final Role role;

	public enum Role {STUDENT, ADMIN}
}