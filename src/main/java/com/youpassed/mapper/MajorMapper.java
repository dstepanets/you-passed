package com.youpassed.mapper;

import com.youpassed.domain.Major;
import com.youpassed.entity.MajorEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MajorMapper implements Mapper<MajorEntity, Major> {
	private ExamMapper examMapper;

	@Override
	public MajorEntity mapDomainToEntity(Major major) {
		return major == null ? null :
				MajorEntity.builder()
						.id(major.getId())
						.title(major.getTitle())
						.capacity(major.getCapacity())
						.applicants(major.getApplicants())
						.examEntities(major.getExams().stream()
								.map(examMapper::mapDomainToEntity)
								.collect(Collectors.toList()))
						.build();
	}

	@Override
	public Major mapEntityToDomain(MajorEntity enity) {
		return enity == null ? null :
				Major.builder()
						.id(enity.getId())
						.title(enity.getTitle())
						.capacity(enity.getCapacity())
						.applicants(enity.getApplicants())
						.exams(enity.getExamEntities().stream()
								.map(examMapper::mapEntityToDomain)
								.collect(Collectors.toList()))
						.build();
	}
}
