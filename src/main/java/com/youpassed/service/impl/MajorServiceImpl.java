package com.youpassed.service.impl;

import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.entity.MajorEntity;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.MajorRepository;
import com.youpassed.service.MajorsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MajorServiceImpl implements MajorsService {
	private MajorRepository majorRepository;
	private Mapper<MajorEntity, Major> majorMapper;

	@Override
	public Page<Major> findAll(int pageIndex, int pageSize) {
		pageIndex = PaginationUtility.limitPageIndex(majorRepository.count(), pageIndex, pageSize);

		return majorRepository.findAll(PageRequest.of(pageIndex, pageSize))
				.map(majorMapper::mapEntityToDomain);
	}
}
