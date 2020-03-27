package com.youpassed.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_majors")
public class StudentMajorEntity implements Serializable {
	@EmbeddedId
	StudentMajorPK pk;

	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(name = "student_id")
	private transient UserEntity student;

	@ManyToOne
	@MapsId("majorId")
	@JoinColumn(name = "major_id")
	private transient MajorEntity major;

	private boolean youPassed;
}
