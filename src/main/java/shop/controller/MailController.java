package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.service.MailService;

import java.security.Principal;

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("message") String message,
                           Principal principal,
                           Model model) {
        boolean answer = false;
        if (username.length() > 0 && email.length() > 0 && password.length() > 0 && message.length() > 0) {
            if (principal != null) {
                mailService.send(username, email, password, message, principal.getName());
            } else {
                mailService.send(username, email, password, message, "");
            }
        } else {
            return "contact";
        }
        return "main";
    }
}
