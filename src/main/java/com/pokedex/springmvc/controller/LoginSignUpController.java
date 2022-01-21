package com.pokedex.springmvc.controller;

import com.pokedex.springmvc.model.Login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class LoginSignUpController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "LoginPage";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpPage() {
		return "SignUpPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(Model model, @ModelAttribute("login") Login login) {
		if (login != null && login.getUserName() != null & login.getPassword() != null) {
			model.addAttribute("user", login.getUserName());
			return "LandingPage";
		} else {
			model.addAttribute("error", "Try again.");
			return "LoginPage";
		}
	}

}
