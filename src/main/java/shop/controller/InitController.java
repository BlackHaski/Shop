package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by blackhaski on 21.06.17.
 */
@Controller
public class InitController {

    @GetMapping("/")
    public String start(Model model) {
        return "main";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "contacts";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
}
