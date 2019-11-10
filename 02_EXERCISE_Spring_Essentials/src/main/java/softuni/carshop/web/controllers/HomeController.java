package softuni.carshop.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = {"/home","/","/index"}, method = RequestMethod.GET)
    public ModelAndView home() {
       // ModelAndView modelAndView = new ModelAndView();
       // modelAndView.addObject("msg", "Beta version - only for test purposes");
       // modelAndView.setViewName("home");
        // modelAndView.setViewName("redirect:/home");

        return new ModelAndView("home");
    }


}
