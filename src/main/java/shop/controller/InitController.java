package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import shop.entity.User;
import shop.service.UserService;

/**
 * Created by blackhaski on 21.06.17.
 */
@Controller
@SessionAttributes(names = "user")
public class InitController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String start() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contacts() {
        return "contact";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("isLogin", false);
        return "registration/registration";
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("isLogin", true);
        return "registration/registration";
    }

    @GetMapping("/product")
    public String product() {
        return "product";
    }

    @GetMapping("/shopcart")
    public String shopcart() {
        return "shopcart";
    }

    @GetMapping("/cabinet-{username}")
    public String cabinet(@PathVariable String username, Model model) {
        User principal = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        System.out.println(principal);
        if (principal != null && principal.getUsername().equals(username))
            model.addAttribute("canEdit", true);
        else {
            model.addAttribute("canEdit", false);
        }
        model.addAttribute("user", userService.findByUsernameWithDetails(username));
        return "cabinet/cabinet";
    }

}
