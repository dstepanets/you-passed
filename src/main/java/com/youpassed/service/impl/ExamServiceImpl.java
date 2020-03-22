package com.youpassed.service.impl;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.User;
import com.youpassed.entity.ExamEntity;
import com.youpassed.entity.MajorEntity;
import com.youpassed.exception.ExamNotFoundException;
import com.youpassed.exception.MajorNotFoundException;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.ExamRepository;
import com.youpassed.service.ExamService;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ExamServiceImpl implements ExamService {
	private ExamRepository examRepository;
	private Mapper<ExamEntity, Exam> examMapper;
	private UserService userService;

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

	@Override
	public Exam findById(Integer examId) throws ExamNotFoundException{
		return examRepository.findById(examId)
				.map(examMapper::mapEntityToDomain)
				.orElseThrow(() -> new ExamNotFoundException("Exam with id [" + examId + "] was not found"));
	}

	@Override
	@Transactional
	public Exam registerStudent(Integer examId, User student) {
		Exam exam = findById(examId);

		Set<Integer> examIds = student.getExams().stream().map(Exam::getId).collect(Collectors.toSet());
		if (!examIds.contains(examId)) {
			student.getExams().add(exam);
			userService.saveStudentWithLists(student);
		}

		return exam;
	}

	@Override
	@Transactional
	public Exam save(Exam exam) {
		ExamEntity examEntity = examRepository.save(examMapper.mapDomainToEntity(exam));
		return examMapper.mapEntityToDomain(examEntity);
	}
}
