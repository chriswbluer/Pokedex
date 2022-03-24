package sunrise.pokedex.springmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sunrise.pokedex.springmvc.model.Login;

import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

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

	// This needs to be properly implemented with security measures.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> submitLogin(@Valid @RequestBody Login user) {
		if (user.getUserName().equals("chris")) {
			return new ResponseEntity<>(user.getUserName(), HttpStatus.OK);
		} else {
			System.out.println("Error - username: " + user.getUserName());
			return new ResponseEntity<>(user.getUserName(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
