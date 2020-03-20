package com.youpassed.service.impl;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.User;
import com.youpassed.entity.MajorEntity;
import com.youpassed.exception.MajorNotFoundException;
import com.youpassed.mapper.Mapper;
import com.youpassed.repository.MajorRepository;
import com.youpassed.service.MajorsService;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MajorServiceImpl implements MajorsService {
	private MajorRepository majorRepository;
	private Mapper<MajorEntity, Major> majorMapper;
	private UserService userService;

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
				.forEach(major -> major.setApplied(true));

		majors.forEach(major -> major.getExams().stream()
				.filter(exam -> studentExams.containsKey(exam.getId()))
				.forEach(exam -> {
					exam.setRegistered(true);
					exam.setMark(studentExams.get(exam.getId()).getMark());
				}));

		return majorPage;
	}

	@Override
	@Transactional
	public Major applyForMajor(Integer majorId, User student) {
		MajorEntity majorEntity = majorRepository.findById(majorId)
				.orElseThrow(() -> new MajorNotFoundException("Major with id [" + majorId + "] is not found"));
		majorEntity.setApplicants(majorEntity.getApplicants() + 1);
		majorEntity = majorRepository.save(majorEntity);

		Set<Integer> majorIds = student.getMajors().stream().map(Major::getId).collect(Collectors.toSet());
		if (!majorIds.contains(majorId)) {
			student.getMajors().add(majorMapper.mapEntityToDomain(majorEntity));
			userService.saveStudentWithLists(student);
		}

		return majorMapper.mapEntityToDomain(majorEntity);
	}
}
