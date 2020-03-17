package com.youpassed.service;

import com.youpassed.entity.MajorEntity;
import org.springframework.data.domain.Page;

public interface MajorsService {

	Page<MajorEntity> findAll(int pageIndex, int pageSize);
}
