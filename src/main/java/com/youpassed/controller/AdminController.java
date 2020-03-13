package com.youpassed.controller;

import com.youpassed.domain.PagerModel;
import com.youpassed.domain.User;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

	private final UserService userService;

	@GetMapping(value = {"/admin/users"})
	public ModelAndView listUsers(@RequestParam(value = "pageSize", required = false) String pageSize,
								  @RequestParam(value = "pageNum", required = false) String pageNum) {

		ModelAndView modelAndView = new ModelAndView("admin/users-demo");

		Page<User> usersList = userService.findAll(pageNum, pageSize);

		System.out.println("total pages: " + usersList.getTotalPages() + "; page number: " + usersList.getNumber());

		PagerModel pager = new PagerModel(usersList.getTotalPages(), usersList.getNumber(), PagerModel.BUTTONS_TO_SHOW);


// add usersList
		modelAndView.addObject("usersList", usersList);
// evaluate page size
		modelAndView.addObject("selectedPageSize", usersList.getNumberOfElements());
// add page sizes
		modelAndView.addObject("pageSizes", PagerModel.PAGE_SIZES);
// add pager
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}


}
