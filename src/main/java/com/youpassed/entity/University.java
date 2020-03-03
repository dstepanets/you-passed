package com.youpassed.entity;

import com.youpassed.entity.users.UserEntity;

import java.util.List;

public class University {
	private final List<UserEntity> applicants;
	private final List<UserEntity> admins;
	private final List<Major> majors;
	private final List<Exam> exams;

	public University(List<UserEntity> applicants, List<UserEntity> admins, List<Major> majors, List<Exam> exams) {
		this.applicants = applicants;
		this.admins = admins;
		this.majors = majors;
		this.exams = exams;
	}

	public List<UserEntity> getApplicants() {
		return applicants;
	}

	public List<UserEntity> getAdmins() {
		return admins;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public List<Exam> getExams() {
		return exams;
	}
}
