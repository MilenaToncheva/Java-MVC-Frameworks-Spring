package softuni.carshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users") //global request for all the methods in the controller
public class UserController {
    @GetMapping("/login")  //the actual route is /users/login
    public ModelAndView login() {
        return new ModelAndView("login");
    }

}
