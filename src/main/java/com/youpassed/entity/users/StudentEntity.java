package com.youpassed.entity.users;

import com.youpassed.entity.Exam;
import com.youpassed.entity.Major;

import java.util.List;

//@Data
//@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@Entity(name = "student")
//@Table(name = "users")
//@DiscriminatorValue("STUDENT")
public class StudentEntity extends UserEntity {
//	@ManyToMany
//	@JoinTable(name = "marks", joinColumns = @JoinColumn(name = "student_id"),
//			inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Exam> examsPassed;
//	@OneToMany
//	@JoinTable(name = "majors", joinColumns = @JoinColumn(name = "id"))
	private List<Major> majorsApplied;

//	private int schoolScore;	//	TODO add to DB or remove completely
}
