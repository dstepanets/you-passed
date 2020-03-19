package com.youpassed.controller;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.User;
import com.youpassed.service.ExamService;
import com.youpassed.service.MajorsService;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {
	private MajorsService majorsService;
	private ExamService examService;
	private UserService userService;
	private AuthenticationFacade authFacade;

	@GetMapping(value = {"/majors"})
	public ModelAndView listMajors(@RequestParam(value = "pageSize", required = false) String pageSizeStr,
								   @RequestParam(value = "page", required = false) String pageNumStr) {

		final int pageSize = PaginationUtility.parsePageSize(pageSizeStr);
		final int pageIndex = PaginationUtility.parsePageNumber(pageNumStr) - 1;

		User student = userService.findById(authFacade.getPrincipalUser().getId());
		Page<Major> majorsPage = majorsService.findAllForStudent(student, pageIndex, pageSize);

		PaginationUtility pager = new PaginationUtility(majorsPage.getTotalPages(), majorsPage.getNumber());

		ModelAndView modelAndView = new ModelAndView("student/majors");
		modelAndView.addObject("majorsPage", majorsPage)
					.addObject("selectedPageSize", pageSize)
					.addObject("pageSizes", PaginationUtility.PAGE_SIZES)
					.addObject("pager", pager);

		return modelAndView;
	}

	@GetMapping(value = {"/exams"})
	public String listExams(Model model) {
		User student = userService.findById(authFacade.getPrincipalUser().getId());
		List<Exam> examList = examService.findAllForStudent(student);
		model.addAttribute(examList);
		return "student/exams";
	}

}
