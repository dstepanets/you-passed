package com.youpassed.controller;

import com.youpassed.domain.User;
import com.youpassed.exception.ValidationException;
import com.youpassed.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

	private AuthenticationFacade authFacade;
	private UserService userService;

	@GetMapping({"", "/"})
	public String index(){
		Authentication authentication = authFacade.getAuthentication();
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if (!authentication.isAuthenticated() || roles.contains("ROLE_ANONYMOUS")) {
			return "index";
		}
		return authFacade.getPrincipalUser().getRole().equals(User.Role.STUDENT) ?
				"student/student-home" : "admin/admin-home";
	}

	@GetMapping(value = {"/login"})
	public String logIn() {
		return "login";
	}

	@PostMapping("/login-success")
	public String logInSuccess() {
/*		for (int i = 0; i < 100 ; i++) {
			User user = User.builder()
					.email(i + "@email.com")
					.password("passw@RD")
					.password2("passw@RD")
					.firstName(i + " Name")
					.lastName(i + " lastName")
					.role(User.Role.STUDENT)
					.build();
			try {
				userService.register(user);
			} catch (ValidationException e) {
				e.printStackTrace();
			}
		}*/
		return "redirect:";
	}

	@GetMapping(value = {"/register"})
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping(value = {"/register"})
	public String submitRegisterForm(@ModelAttribute @Valid User user) throws ValidationException {
		user.setRole(User.Role.STUDENT);
		userService.register(user);
		return "redirect:/login";
	}

	@GetMapping(value = {"/profile"})
	public String showProfileForm(Model model) {
		User currentUser = userService.findById(authFacade.getPrincipalUser().getId());
//		session.setAttribute("userUpdate", currentUser);
//		User currentUser = authFacade.getPrincipalUser();
		model.addAttribute("userUpdate", currentUser);

		System.out.println("\n\n------------------\n" + currentUser.getExams());
		System.out.println(currentUser.getMajors() + "\n\n========================");

		return "profile";
	}

	@PostMapping(value = {"/profile"})
	public String submitProfileForm(@ModelAttribute @Valid User userUpdate,
									HttpServletRequest request,
									HttpServletResponse response) throws ValidationException {
		User currentUser = authFacade.getPrincipalUser();
		userService.update(currentUser, userUpdate);
		new SecurityContextLogoutHandler().logout(request, response, authFacade.getAuthentication());
		return "redirect:/login";
	}

}