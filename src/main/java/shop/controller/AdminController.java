package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by blackhaski on 29.06.17.
 */
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String initAdmin() {
        return "admin";
    }
}
