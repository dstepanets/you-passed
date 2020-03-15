package com.youpassed.controller;

import com.youpassed.domain.PagerModel;
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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

	private final UserService userService;

	@GetMapping(value = {"/users"})
	public ModelAndView listUsers(HttpServletRequest req,
								  @RequestParam(value = "pageSize", required = false) String pageSizeStr,
								  @RequestParam(value = "page", required = false) String pageNumStr) {


		final int pageSize = PagerModel.parsePageSize(pageSizeStr);
		final int pageIndex = PagerModel.parsePageNumber(pageNumStr) - 1;

		Page<User> usersPage = userService.findAll(pageIndex, pageSize);

		System.out.println("\n||| totalPages=" + usersPage.getTotalPages() + "; pageNumStr=" + pageNumStr +
				"; pageIndex=" + pageIndex + "; usersPage.number=" + usersPage.getNumber());

		PagerModel pager = new PagerModel(usersPage.getTotalPages(), usersPage.getNumber());

		ModelAndView modelAndView = new ModelAndView("admin/users");

// add usersPage
		modelAndView.addObject("usersPage", usersPage);
// evaluate page size
		modelAndView.addObject("selectedPageSize", pageSize);
// add page sizes
		modelAndView.addObject("pageSizes", PagerModel.PAGE_SIZES);
// add pager
		modelAndView.addObject("pager", pager);

//		modelAndView.addObject("pageSize", pageSize).addObject("page", pageIndex);

		return modelAndView;
	}


}
