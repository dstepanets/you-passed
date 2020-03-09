package com.youpassed.entity.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
//@DiscriminatorColumn(name="role_id")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "* Please provide a valid Email")
	@NotEmpty(message = "* Please provide an email")
	private String email;
	@Column(name = "password", nullable = false)
	@Length(min = 5, message = "* Your password must have at least 5 characters")
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=]).{5,})",
			message = "* Your password must have at least 1 char of each of these types: " +
					"uppercase latin letter, lowercase, digit, special symbol (!@#$%^&*+=)")
	@NotEmpty(message = "* Please provide your password")
	private String password;
	@Column(name = "first_name", nullable = false)
	@NotEmpty(message = "* Please provide your first name")
	private String firstName;
	@Column(name = "last_name", nullable = false)
	@NotEmpty(message = "* Please provide your last name")
	private String lastName;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserEntity.Role role;

	public enum Role {STUDENT, ADMIN }

}