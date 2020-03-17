package com.youpassed.mapper;

import com.youpassed.domain.Exam;
import com.youpassed.entity.ExamEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper implements Mapper<ExamEntity, Exam> {
	@Override
	public ExamEntity mapDomainToEntity(Exam exam) {
		return exam == null ? null :
				ExamEntity.builder()
						.id(exam.getId())
						.subject(exam.getSubject())
						.date(exam.getDate())
						.location(exam.getLocation())
						.build();
	}

	@Override
	public Exam mapEntityToDomain(ExamEntity enity) {
		return enity == null ? null :
				Exam.builder()
						.id(enity.getId())
						.subject(enity.getSubject())
						.date(enity.getDate())
						.location(enity.getLocation())
						.build();
	}
}
