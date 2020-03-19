package com.youpassed.service.impl;

import com.youpassed.domain.Exam;
import com.youpassed.domain.User;
import com.youpassed.entity.ExamEntity;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.ExamRepository;
import com.youpassed.service.ExamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ExamServiceImpl implements ExamService {
	private ExamRepository examRepository;
	private Mapper<ExamEntity, Exam> examMapper;

	@Override
	public List<Exam> findAll() {
		return examRepository.findAll().stream()
				.map(examMapper::mapEntityToDomain)
				.collect(Collectors.toList());
	}

	@Override
	public List<Exam> findAllForStudent(User user) {
		List<Exam> userExams = user.getExams();
		List<Exam> allExams = findAll();

		Set<Integer> studentExamsIds = userExams.stream()
				.map(Exam::getId)
				.collect(Collectors.toSet());
		allExams.stream()
				.filter(exam -> !studentExamsIds.contains(exam.getId()))
				.forEach(userExams::add);

		return userExams;
	}

}
