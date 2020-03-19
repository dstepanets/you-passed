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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public List<Exam> findAllForUser(User user) {
		Map<Integer, Exam> map = findAll().stream().collect(Collectors.toMap(Exam::getId, exam -> exam));
		for (Exam exam : user.getExams()) {
			map.get(exam.getId()).setApplied(true);
			map.get(exam.getId()).setMark(0); // TODO
		}

		return examRepository.findAll().stream()
				.map(examMapper::mapEntityToDomain)
				.collect(Collectors.toList());
	}

}
