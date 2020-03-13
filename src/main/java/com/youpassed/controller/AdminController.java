package com.youpassed.controller;

import com.youpassed.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

	@GetMapping(value = {"/admin/users"})
	public String listUsers(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute(user);
		return "admin/users";
	}

}
