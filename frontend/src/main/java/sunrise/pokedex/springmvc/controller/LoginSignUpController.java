package sunrise.pokedex.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sunrise.pokedex.springmvc.model.Login;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class LoginSignUpController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage() {
		return "loginPage";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpPage() {
		return "signUpPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(Model model, @ModelAttribute("login") Login login) {
		if (login != null && login.getUserName() != null & login.getPassword() != null) {
			model.addAttribute("user", login.getUserName());
			return "landingPage";
		} else {
			model.addAttribute("error", "Try again.");
			return "loginPage";
		}
	}

}
