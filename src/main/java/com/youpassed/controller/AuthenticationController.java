package com.youpassed.controller;

import com.youpassed.domain.User;
import com.youpassed.exception.ValidationException;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

	private final UserService userService;

	@GetMapping({"/", ""})
	public String index(){
		return "index";
	}

	@GetMapping(value = {"/login"})
	public String logIn() {
		return "login";
	}

	@GetMapping(value = {"/register"})
	public String register(Model model) {
		model.addAttribute("user", User.builder().build());
		return "register";
	}

	@PostMapping(value = {"/register"})
	public String register(@ModelAttribute @Valid User user) throws ValidationException {
		userService.register(user);
		return "redirect:/login";
	}

}