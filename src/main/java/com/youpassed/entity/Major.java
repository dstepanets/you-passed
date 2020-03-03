package com.youpassed.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "major")
@Table(name = "majors")
public class Major {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "title")
	@NotEmpty
	private String title;
	@ManyToMany
	@JoinTable(name = "major_exams", joinColumns = @JoinColumn(name = "major_id"),
			inverseJoinColumns = @JoinColumn(name = "exam_id"))
	private List<Exam> exams;
	@Column(name = "capacity")
	private int capacity;
//	private List<User> applicants;
}
