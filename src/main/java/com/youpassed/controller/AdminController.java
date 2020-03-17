package com.youpassed.controller;

import com.youpassed.domain.PaginationUtility;
import com.youpassed.domain.User;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {
	private  UserService userService;

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
}
