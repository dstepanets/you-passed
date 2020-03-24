package com.youpassed.controller;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.User;
import com.youpassed.service.ExamService;
import com.youpassed.service.MajorsService;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StudentController {
	private MajorsService majorsService;
	private ExamService examService;
	private UserService userService;
	private AuthenticationFacade authFacade;

	@GetMapping(value = {"/majors"})
	public ModelAndView listMajors(@RequestParam(value = "pageSize", required = false) String pageSizeStr,
								   @RequestParam(value = "page", required = false) String pageNumStr,
								   @RequestParam(required = false) Integer selected) {



		final int pageSize = PaginationUtility.parsePageSize(pageSizeStr);
		final int pageIndex = PaginationUtility.parsePageNumber(pageNumStr) - 1;

		User student = userService.findById(authFacade.getPrincipalUser().getId());
		Page<Major> majorsPage = majorsService.findAllForStudent(student, pageIndex, pageSize);

		PaginationUtility pager = new PaginationUtility(majorsPage.getTotalPages(), majorsPage.getNumber());

		ModelAndView modelAndView = new ModelAndView("student/majors");
		modelAndView.addObject("majorsPage", majorsPage)
					.addObject("selectedPageSize", pageSize)
					.addObject("pageSizes", PaginationUtility.PAGE_SIZES)
					.addObject("pager", pager)
					.addObject("selected", selected);

		System.out.println("\n\n>>>> SelectedMajor id=" + selected + "\n");

		return modelAndView;
	}

	@PostMapping(value = {"/majors/apply"})
	public ModelAndView applyForMajor(@RequestParam Integer majorId,
								   @RequestParam(value = "pageSize", required = false) String pageSizeStr,
								   @RequestParam(value = "page", required = false) String pageNumStr) {

		System.out.println("\n\n==== Major: " + majorId);
		System.out.println("==== pageSize=" + pageSizeStr + " | pageNum=" + pageNumStr);

		User student = userService.findById(authFacade.getPrincipalUser().getId());
		Major selectedMajor = majorsService.applyForMajor(majorId, student);

		System.out.println("\n\n===> " + selectedMajor + "\n\n");

		ModelAndView modelAndView = new ModelAndView("redirect:/student/majors");
		modelAndView.addObject("pageSize", pageSizeStr)
				.addObject("page", pageNumStr)
				.addObject("selected", majorId);

		return modelAndView;
	}

	@GetMapping(value = {"/majors/{majorId}/applicants"})
	public String showMajorApplicantsRanking(@PathVariable Integer majorId, Model model) {
		Major major = majorsService.findByIdWithUserRanking(majorId);
		System.out.println("\n\n[][]Major-applicants[] " + major + "\n\n");
		model.addAttribute(major);
		return "student/major-applicants";
	}

	@PostMapping(value = {"/majors/exam-register"})
	public ModelAndView registerForExamInMajors(@RequestParam Integer examId,
											   @RequestParam Integer majorId,
											   @RequestParam(value = "pageSize", required = false) String pageSizeStr,
											   @RequestParam(value = "page", required = false) String pageNumStr) {

		System.out.println("\n\n==exam-reg== majorId=" + majorId + "| examId=" + examId);
		System.out.println("==exam-reg== pageSize=" + pageSizeStr + " | pageNum=" + pageNumStr);

		User student = userService.findById(authFacade.getPrincipalUser().getId());
		Exam exam = examService.registerStudent(examId, student);

		System.out.println("\n\nexam=" + exam + "\n\n");

		ModelAndView modelAndView = new ModelAndView("redirect:/student/majors");
		modelAndView.addObject("pageSize", pageSizeStr)
				.addObject("page", pageNumStr)
				.addObject("selected", majorId);

		return modelAndView;
	}

	@GetMapping(value = {"/exams"})
	public String listExams(Model model) {
		User student = userService.findById(authFacade.getPrincipalUser().getId());
		List<Exam> examList = examService.findAllForStudent(student);
		model.addAttribute(examList);
		return "student/exams";
	}

	@PostMapping(value = {"/exams/register"})
	public String registerForExam(@RequestParam Integer examId) {

		System.out.println("\n\nEXAMS||examId=" + examId);

		User student = userService.findById(authFacade.getPrincipalUser().getId());
		Exam exam = examService.registerStudent(examId, student);

		System.out.println("\n\nEXAMS||exam=" + exam + "\n\n");

		return "redirect:/student/exams";
	}

}
