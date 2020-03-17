package com.youpassed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "major")
@Table(name = "majors")
public class MajorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "title")
	@NotEmpty
	private String title;
	@ManyToMany
	@JoinTable(name = "major_exams", joinColumns = @JoinColumn(name = "major_id"),
			inverseJoinColumns = @JoinColumn(name = "exam_id"))
	private List<ExamEntity> examEntities;
	@Column(name = "capacity")
	private int capacity;
	private int applied;
//	private List<User> applicants;
}
