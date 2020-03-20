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
	public Major mapEntityToDomain(MajorEntity entity) {
		return entity == null ? null :
				Major.builder()
						.id(entity.getId())
						.title(entity.getTitle())
						.capacity(entity.getCapacity())
						.applicants(entity.getApplicants())
						.exams(entity.getExamEntities().stream()
								.map(examMapper::mapEntityToDomain)
								.collect(Collectors.toList()))
						.build();
	}
}
