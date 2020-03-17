package com.youpassed.entity.users;

import com.youpassed.entity.ExamEntity;
import com.youpassed.entity.MajorEntity;

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
	private List<ExamEntity> examsPassed;
//	@OneToMany
//	@JoinTable(name = "majors", joinColumns = @JoinColumn(name = "id"))
	private List<MajorEntity> majorsApplied;

//	private int schoolScore;	//	TODO add to DB or remove completely
}
