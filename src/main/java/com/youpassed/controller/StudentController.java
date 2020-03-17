package com.youpassed.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

	@GetMapping(value = {"/student/majors"})
	public String listMajors(Model model) {
		return "student/majors";
	}

}
