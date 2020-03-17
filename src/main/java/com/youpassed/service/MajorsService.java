package com.youpassed.service;

import com.youpassed.domain.Major;
import org.springframework.data.domain.Page;

public interface MajorsService {

	Page<Major> findAll(int pageIndex, int pageSize);
}
