package sunrise.pokedex.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserManagementController {

    @RequestMapping(value = "/usermanagement", method = RequestMethod.GET)
    public String getUserManagementPage() {
        return "userManagement";
    }
}
