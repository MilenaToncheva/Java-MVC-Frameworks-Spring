package softuni.heroes.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.heroes.data.models.User;
import softuni.heroes.services.models.auth.UserLoginServiceModel;
import softuni.heroes.services.models.auth.UserRegisterServiceModel;
import softuni.heroes.services.services.AuthService;
import softuni.heroes.web.models.UserLoginModel;
import softuni.heroes.web.models.UserRegisterModel;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Autowired
    public AuthController(ModelMapper modelMapper, AuthService authService) {
        this.modelMapper = modelMapper;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "auth/login.html";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserLoginModel userLoginModel,HttpSession session) {


        try {
            UserLoginServiceModel userLoginServiceModel=  this.authService.login(this.modelMapper.map(userLoginModel, UserLoginServiceModel.class));

                   session.setAttribute("user",userLoginServiceModel);
            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/users/login";
        }
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegisterModel userRegisterModel) {
        this.authService.register(this.modelMapper.map(userRegisterModel, UserRegisterServiceModel.class));
        return "redirect:/users/login";
    }
}

