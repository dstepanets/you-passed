package com.youpassed.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

	@GetMapping(value = {"/majors"})
	public String listMajors(Model model) {
		return "student/majors";
	}

}
