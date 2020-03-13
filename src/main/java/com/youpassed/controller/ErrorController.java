package com.youpassed.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
/*

@ControllerAdvice
@Slf4j
public class ErrorController {

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model) {
		log.error("Exception during execution of SpringSecurity application", throwable);
		String errorMessage;
		if (throwable != null) {
			errorMessage = throwable.getMessage();
			String stackTrace = Arrays.toString(throwable.getStackTrace());
			model.addAttribute("stackTrace", stackTrace);
		} else {
			errorMessage = "Unknown error";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
}*/
