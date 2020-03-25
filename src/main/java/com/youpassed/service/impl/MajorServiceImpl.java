package com.youpassed.service.impl;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.User;
import com.youpassed.entity.MajorEntity;
import com.youpassed.exception.MajorNotFoundException;
import com.youpassed.mapper.MajorMapper;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.MajorRepository;
import com.youpassed.service.ExamService;
import com.youpassed.service.MajorsService;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MajorServiceImpl implements MajorsService {
	private MajorRepository majorRepository;
	private MajorMapper majorMapper;
	private UserService userService;
	private ExamService examService;

	@Override
	public Page<Major> findAll(int pageIndex, int pageSize) {
		pageIndex = PaginationUtility.limitPageIndex(majorRepository.count(), pageIndex, pageSize);

		return majorRepository.findAll(PageRequest.of(pageIndex, pageSize))
				.map(majorMapper::mapEntityToDomain);
	}

	@Override
	public Page<Major> findAllForStudent(User student, int pageIndex, int pageSize) {
		Page<Major> majorPage = findAll(pageIndex, pageSize);
		List<Major> majors = majorPage.toList();

		Map<Integer, Major> studentMajors = student.getMajors().stream()
				.collect(Collectors.toMap(Major::getId, major -> major));
		Map<Integer, Exam> studentExams = student.getExams().stream()
				.collect(Collectors.toMap(Exam::getId, exam -> exam));

		majors.stream()
				.filter(major -> studentMajors.containsKey(major.getId()))
				.forEach(major -> major.setYouPassed(true));

		majors.forEach(major -> major.getExams().stream()
				.filter(exam -> studentExams.containsKey(exam.getId()))
				.forEach(exam -> {
					exam.setRegistered(true);
					exam.setMark(studentExams.get(exam.getId()).getMark());
				}));

		return majorPage;
	}

	@Override
	public Major findById(Integer majorId) {
		return majorRepository.findById(majorId)
				.map(majorMapper::mapEntityToDomainWithApplicants)
				.orElseThrow(() -> new MajorNotFoundException("Major with ID [" + majorId + "] was not found"));
	}

	@Override
	public Major findByIdWithUserRanking(Integer majorId) {
		Major major = findById(majorId);
		Set<Integer> majorExamsIds = major.getExams().stream()
				.map(Exam::getId)
				.collect(Collectors.toSet());

		List<User> applicants = major.getApplicants().stream()
				.map(student -> student = userService.findById(student.getId()))
				.collect(Collectors.toList());

		applicants.forEach(student -> student.getExams().forEach(exam -> {
			if (majorExamsIds.contains(exam.getId())) {
				student.setMajorScore(student.getMajorScore() + exam.getMark());
			}
		}));

		applicants.sort(Comparator.comparing(User::getMajorScore).reversed());

		major.setApplicants(applicants);
		return major;
	}

	@Override
	@Transactional
	public Major save(Major major) {
		MajorEntity majorEntity = majorRepository.save(majorMapper.mapDomainToEntity(major));
		return majorMapper.mapEntityToDomain(majorEntity);
	}

	@Override
	@Transactional
	public void delete(Major major) {
		majorRepository.delete(majorMapper.mapDomainToEntity(major));
	}

	@Override
	@Transactional
	public Major applyForMajor(Integer majorId, User student) {
		MajorEntity majorEntity = majorRepository.findById(majorId)
				.orElseThrow(() -> new MajorNotFoundException("Major with id [" + majorId + "] is not found"));
		majorEntity.setApplicantsNum(majorEntity.getApplicantsNum() + 1);
		majorEntity = majorRepository.save(majorEntity);

		Set<Integer> majorIds = student.getMajors().stream().map(Major::getId).collect(Collectors.toSet());
		if (!majorIds.contains(majorId)) {
			student.getMajors().add(majorMapper.mapEntityToDomain(majorEntity));
			userService.saveStudentWithLists(student);
		}

		return majorMapper.mapEntityToDomain(majorEntity);
	}
}
