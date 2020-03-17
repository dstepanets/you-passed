package com.youpassed.entity;

import com.youpassed.entity.users.UserEntity;

import java.util.List;

public class University {
	private final List<UserEntity> applicants;
	private final List<UserEntity> admins;
	private final List<MajorEntity> majorEntities;
	private final List<ExamEntity> examEntities;

	public University(List<UserEntity> applicants, List<UserEntity> admins, List<MajorEntity> majorEntities, List<ExamEntity> examEntities) {
		this.applicants = applicants;
		this.admins = admins;
		this.majorEntities = majorEntities;
		this.examEntities = examEntities;
	}

	public List<UserEntity> getApplicants() {
		return applicants;
	}

	public List<UserEntity> getAdmins() {
		return admins;
	}

	public List<MajorEntity> getMajorEntities() {
		return majorEntities;
	}

	public List<ExamEntity> getExamEntities() {
		return examEntities;
	}
}
