package com.youpassed.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

	@GetMapping(value = {"/admin/users"})
	public String logIn() {
		return "/admin/users";
	}

}
