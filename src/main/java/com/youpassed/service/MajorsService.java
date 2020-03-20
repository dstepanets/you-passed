package com.youpassed.service;

import com.youpassed.domain.Major;
import com.youpassed.domain.User;
import org.springframework.data.domain.Page;

public interface MajorsService {

	Page<Major> findAll(int pageIndex, int pageSize);
	Page<Major> findAllForStudent(User student,int pageIndex, int pageSize);
	Major applyForMajor(Integer majorId, User student);
}
