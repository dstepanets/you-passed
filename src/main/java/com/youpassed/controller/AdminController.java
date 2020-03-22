package com.youpassed.controller;

import com.youpassed.domain.Exam;
import com.youpassed.domain.Major;
import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.Role;
import com.youpassed.domain.User;
import com.youpassed.exception.ValidationException;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"user", "exam"})
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AdminController {
	private UserService userService;
	private MajorsService majorsService;
	private ExamService examService;
	private AuthenticationFacade authFacade;

	@GetMapping(value = {"/users"})
	public ModelAndView listUsers(@RequestParam(value = "pageSize", required = false) String pageSizeStr,
								  @RequestParam(value = "page", required = false) String pageNumStr) {

		final int pageSize = PaginationUtility.parsePageSize(pageSizeStr);
		final int pageIndex = PaginationUtility.parsePageNumber(pageNumStr) - 1;
		Page<User> usersPage = userService.findAll(pageIndex, pageSize);
		PaginationUtility pager = new PaginationUtility(usersPage.getTotalPages(), usersPage.getNumber());

		ModelAndView modelAndView = new ModelAndView("admin/users");
		modelAndView.addObject("usersPage", usersPage)
				.addObject("selectedPageSize", pageSize)
				.addObject("pageSizes", PaginationUtility.PAGE_SIZES)
				.addObject("pager", pager);

		return modelAndView;
	}

	@GetMapping(value = {"/users/update/{userId}"})
	public ModelAndView showUpdateUserForm(@PathVariable Integer userId) {

		System.out.println("\n\n/////userId=" + userId + "\n\n");
		User user = userService.findById(userId);

		ModelAndView modelAndView = new ModelAndView("admin/user-update");
		modelAndView.addObject(user);
//		modelAndView.getModel().put("userUpd", new User());
		return modelAndView;
	}

	@PostMapping(value = {"/users/update/submit"})
	public ModelAndView submitUserUpdate(@ModelAttribute User user,
										 SessionStatus sessionStatus) {

		System.out.println("\n\n///submit//user=" + user + "\n\n");
		userService.saveStudentWithLists(user);
//		user = userService.saveStudentWithLists(user);

		sessionStatus.setComplete();
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/users/update/" + user.getId());
//		modelAndView.addObject(user);
		return modelAndView;
	}

	@GetMapping(value = {"/majors"})
	public ModelAndView listMajors(@RequestParam(value = "pageSize", required = false) String pageSizeStr,
								   @RequestParam(value = "page", required = false) String pageNumStr,
								   @RequestParam(required = false) Integer selected) {

		final int pageSize = PaginationUtility.parsePageSize(pageSizeStr);
		final int pageIndex = PaginationUtility.parsePageNumber(pageNumStr) - 1;

		Page<Major> majorsPage = majorsService.findAll(pageIndex, pageSize);

		PaginationUtility pager = new PaginationUtility(majorsPage.getTotalPages(), majorsPage.getNumber());

		ModelAndView modelAndView = new ModelAndView("admin/majors");
		modelAndView.addObject("majorsPage", majorsPage)
				.addObject("selectedPageSize", pageSize)
				.addObject("pageSizes", PaginationUtility.PAGE_SIZES)
				.addObject("pager", pager)
				.addObject("selected", selected);

		System.out.println("\n\n>>>> SelectedMajor id=" + selected + "\n");

		return modelAndView;
	}

	@GetMapping(value = {"/exams"})
	public String listExams(Model model) {
		List<Exam> examList = examService.findAll();
		model.addAttribute(examList);
		return "admin/exams";
	}

	@GetMapping(value = {"/exams/new"})
	public String showNewExamForm(Model model) {
		model.addAttribute("exam", new Exam());
		return "admin/exam-edit";
	}

	@GetMapping(value = {"/exams/edit/{examId}"})
	public String showEditExamForm(@PathVariable Integer examId, Model model) {
		Exam exam = examService.findById(examId);
		model.addAttribute(exam);
		return "admin/exam-edit";
	}

	@PostMapping(value = {"/exams/save"})
	public String saveExam(@ModelAttribute @Valid Exam exam,
								SessionStatus sessionStatus) throws ValidationException {
		examService.save(exam);
		sessionStatus.setComplete();
		return "redirect:/admin/exams";
	}

	@PostMapping(value = {"/exams/remove"})
	public String removeExam(@ModelAttribute @Valid Exam exam,
							 SessionStatus sessionStatus) throws ValidationException {
		examService.delete(exam);
		sessionStatus.setComplete();
		return "redirect:/admin/exams";
	}


}
