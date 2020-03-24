package com.youpassed.mapper;

import com.youpassed.domain.Major;
import com.youpassed.entity.MajorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MajorMapper implements Mapper<MajorEntity, Major> {
	private ExamMapper examMapper;
	private UserMapper userMapper;

	@Autowired
	public MajorMapper(ExamMapper examMapper, @Lazy UserMapper userMapper) {
		this.examMapper = examMapper;
		this.userMapper = userMapper;
	}

	@Override
	public MajorEntity mapDomainToEntity(Major major) {
		return major == null ? null :
				MajorEntity.builder()
						.id(major.getId())
						.title(major.getTitle())
						.capacity(major.getCapacity())
						.applicantsNum(major.getApplicantsNum())
						.examEntities(major.getExams().stream()
								.map(examMapper::mapDomainToEntity)
								.collect(Collectors.toList()))
						.applicants(major.getApplicants().stream()
								.map(userMapper::mapDomainToEntity)
								.collect(Collectors.toList()))
						.build();
	}

	@Override
	public Major mapEntityToDomain(MajorEntity entity) {
		return entity == null ? null :
				Major.builder()
						.id(entity.getId())
						.title(entity.getTitle())
						.capacity(entity.getCapacity())
						.applicantsNum(entity.getApplicantsNum())
						.exams(entity.getExamEntities().stream()
								.map(examMapper::mapEntityToDomain)
								.collect(Collectors.toList()))
						.applicants(entity.getApplicants().stream()
								.map(userMapper::mapEntityToDomain)
								.collect(Collectors.toList()))
						.build();
	}
}
